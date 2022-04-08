package com.example.atomic;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class UdpUnicastClient implements Runnable {
    private final int port;
    private InetAddress address;
    private int nodeNum;
    private OutputPacket<String> outputPacket;
    private UdpUnicastServer sendServer;

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
                SentPacket received = (SentPacket) o;
                is.close();


                outputPacket.setContent(received.getMessage());

                System.out.print("Node[" + outputPacket.getNodeNum());
                System.out.print("] IP: " + outputPacket.ip + ", ");
                System.out.print("Port: " + outputPacket.port + ", ");
                System.out.print("Original Node[" + received.getNodeNum());
                System.out.print("] IP: " + received.getIp() + ", ");

                System.out.println("Content: " + outputPacket.content + ".");
                sendServer.broadcast();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Timeout. Client is closing.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
