package com.example.atomic;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class UdpUnicastClient implements Runnable {
    private final int port;
    private InetAddress address;
    private int nodeNum;
    private OutputPacket<String> outputPacket;
    private UdpUnicastServer sendServer;

    // current MAX global sequence number received, initialized as -1;
    private int currentGlobalSeqReceived = -1;

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

                if (tag != 2) {
                    System.out.println("<sid, seq#>:    <"
                            + receivedPacket.getSentNodeNum()
                            + ", " + SentPacket.getLocalSeq() + ">" +
                            ", ");
                    messageBuffer.add(receivedPacket);
                }

                if (receivedPacket.getGlobalSeqNum() != -1) {
                    System.out.print("Global Sequence Number: " + receivedPacket.getGlobalSeqNum() + ", ");
                }

                System.out.println("Message: " + outputPacket.content + ", ");

                // For received request message, judge whether we need to send a sequence message
                if (tag == 1) {
                    requestMessageReceived();
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

    // Judge whether to send a sequence message
    private void requestMessageReceived() {
        // 1. Whether current node num satisfy k mod n
        if ((this.currentGlobalSeqReceived + 1)
                % AddressConfig.TOTAL_NODE_NUM != AddressConfig.CURRENT_NODE_NUM) {
            return;
        }

        // 2.

        //
    }
}
