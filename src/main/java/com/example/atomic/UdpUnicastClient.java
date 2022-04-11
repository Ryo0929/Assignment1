package com.example.atomic;

import com.example.service.MessageRequestHandler;
import com.example.util.SpringUtil;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UdpUnicastClient implements Runnable {
    private final int port;
    private InetAddress address;
    private int nodeNum;
    private OutputPacket<String> outputPacket;
    private UdpUnicastServer sendServer;
    private MessageRequestHandler handler;

    private int[] groupGlobalSeqReceived = new int[AddressConfig.TOTAL_NODE_NUM];
    // Current MAX global sequence number received, initialized as -1;
    private int currentGlobalSeqReceived = -1;

    // Current MAX global sequence number delivered, initialized as -1;
    private int currentGlobalSeqDelivered = -1;

    private List<SentPacket> messageBuffer = new ArrayList<>();

    public UdpUnicastClient(String add, int num, int port, UdpUnicastServer sendServer) {
        this.port = port;
        {
            try {
                this.address = InetAddress.getByName(add);
                this.outputPacket = new OutputPacket(address.toString(), port);
                this.sendServer = sendServer;
                this.nodeNum = num;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        // Initialize global received array as -1
        Arrays.fill(groupGlobalSeqReceived, -1);

        handler = (MessageRequestHandler) SpringUtil.getBean(MessageRequestHandler.class);
        /**
         * Bind the client socket to the port on which you expect to
         * read incoming messages
         */
        try (DatagramSocket clientSocket = new DatagramSocket(port, address)) {
            /**
             * Create a byte array buffer to store incoming data. If the message length
             * exceeds the length of your buffer, then the message will be truncated. To avoid this,
             * you can simply instantiate the buffer with the maximum UDP packet size, which
             * is 65506
             */

            byte[] buffer = new byte[65507];

            while (true) {

                DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);

                /**
                 * The receive method will wait for 3000 ms for data.
                 * After that, the client will throw a timeout exception.
                 */
                clientSocket.receive(datagramPacket);
                byte[] datas = datagramPacket.getData();

                int byteCount = datagramPacket.getLength();
                ByteArrayInputStream byteStream = new
                        ByteArrayInputStream(buffer);
                ObjectInputStream is = new
                        ObjectInputStream(new BufferedInputStream(byteStream));
                Object o = is.readObject();
                SentPacket receivedPacket = (SentPacket) o;
                is.close();

                // use tag to judge what type of message (request / sequence / greeting)
                int tag = receivedPacket.getTag();

                outputPacket.setContent(receivedPacket.getMessage());

                // print essential details
                System.out.print("Node[" + outputPacket.getNodeNum());
                System.out.print("] IP: " + outputPacket.ip + ", ");
                System.out.print("Port: " + outputPacket.port + ", ");
                System.out.print("Original Node[" + receivedPacket.getSentNodeNum());
                System.out.print("] IP: " + receivedPacket.getSentIp() + ", ");

                if (tag == 0 || tag == 1) {
                    System.out.println("<sid, seq#>:    <"
                            + receivedPacket.getSentNodeNum()
                            + ", " + receivedPacket.getLocalSeq() + ">" +
                            ", ");
                    // add all messages to buffer as long as it is not a greeting message
                    messageBuffer.add(receivedPacket);
                }

                if (receivedPacket.getGlobalSeqNum() != -1) {
                    System.out.print("Global Sequence Number: " + receivedPacket.getGlobalSeqNum() + ", ");
                }

                System.out.println("Message: " + outputPacket.content + ", ");

                // For received notify seq message
                if (tag == 3) {
                    // update group received table
                    this.groupGlobalSeqReceived[receivedPacket.getSentNodeNum()] = receivedPacket.getGlobalSeqNum();
                }

                // For received request message, judge whether we need to send a sequence message
                if (tag == 0) {
                    requestMessageReceived(receivedPacket.getSentNodeNum(),
                            receivedPacket.getLocalSeq(), receivedPacket);
                }

                // For received sequence message
                if (tag == 1) {
                    // update corresponding request message Global sequence in buffer
                    updateGlobalSequence(receivedPacket);
                    // Notify other nodes for already received message
                    notifyReceived(this.currentGlobalSeqReceived);
                    deliveryProcess(receivedPacket);
                }

//                sendServer.broadcast();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Timeout. Client is closing.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void notifyReceived(int currentGlobalSeqReceived) {
        SentPacket packet = new SentPacket(3, "Notify Received Global Seq" + currentGlobalSeqReceived, -1, currentGlobalSeqReceived, null, -1);
        sendServer.broadcast(packet);
    }

    private void deliveryProcess(SentPacket receivedPacket) {

        // 1. Only deliver request message with seq s if already received seq number is less than s
        if (receivedPacket.getGlobalSeqNum() != this.currentGlobalSeqReceived) {
            return;
        }
        // TODO check previous message delivery

        // 2. Judge whether majority condition is satisfied
        int satisfyNum = 0;
        for (int i = 0; i < AddressConfig.TOTAL_NODE_NUM; i++) {
            if (groupGlobalSeqReceived[i] >= receivedPacket.getGlobalSeqNum()) {
                satisfyNum++;
            }
        }

        if (satisfyNum > AddressConfig.TOTAL_NODE_NUM / 2) {
            // deliver message
            handler.redirect(receivedPacket);
        }

    }

    private void updateGlobalSequence(SentPacket receivedPacket) {
        for (SentPacket packet : messageBuffer) {
            if (packet.getTag() == 0 && packet.getLocalSeq() == receivedPacket.getLocalSeq()
                    && packet.getSentNodeNum() == receivedPacket.getSentNodeNum()
            ) {
                packet.setGlobalSeqNum(receivedPacket.getGlobalSeqNum());
            }
        }
        // d
        if (receivedPacket.getGlobalSeqNum() == this.currentGlobalSeqReceived + 1) {
            this.currentGlobalSeqReceived++;
        }
    }

    // Judge whether to send a sequence message
    private void requestMessageReceived(int senderSid, int curLocalSeq, SentPacket rec) {
        // 1. Whether current node num satisfy k mod n
        if ((this.currentGlobalSeqReceived + 1)
                % AddressConfig.TOTAL_NODE_NUM != AddressConfig.CURRENT_NODE_NUM) {
            return;
        }

        // 2. Iterate message buffer to check criteria
        for (SentPacket packet : messageBuffer) {
            if (packet.getTag() == 0 && packet.getLocalSeq() < curLocalSeq) {
                if (packet.getGlobalSeqNum() == -1) {
                    return;
                }
            }
        }

        // Send a sequence message if all previous conditions satisfied
        SentPacket seq = new SentPacket(2, null, rec.getLocalSeq(), currentGlobalSeqReceived + 1, rec.getRestContent(), rec.getRestTag());
        sendServer.broadcast(seq);

    }
}
