package com.example.atomic;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

public class UdpUnicastServer implements Runnable {
    /**
     * The port where the client is listening.
     */
    private final int clientPort;

    public UdpUnicastServer(int clientPort) {
        this.clientPort = clientPort;
    }

    @SneakyThrows
    @Override
    public void run() {
        /**
         * Create a new server socket and bind it to a free port. I have chosen
         * one in the 49152 - 65535 range, which are allocated for internal applications
         */
//        for (String address : AddressConfig.ADDRESS_POOL) {
//            if (port == 50001) {
//                continue;
//            }
            try (DatagramSocket serverSocket = new DatagramSocket(50000)) {
                // The server will generate 3 messages and send them to the client
                for (int i = 0; i < 3; i++) {
//                    String message = "Message number " + i;

                    // encoding send packet
                    SentPacket packet = new SentPacket("Message Number " + i, AddressConfig.CURRENT_NODE_ADDRESS, AddressConfig.CURRENT_NODE_NUM);
                    final ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
                    final ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(packet);
                    final byte[] message = baos.toByteArray();

//                    final DatagramPacket packet = new DatagramPacket(data, data.length);
// Send the packet

                    DatagramPacket datagramPacket = new DatagramPacket(
                            message,
                            message.length,
//                            InetAddress.getLocalHost(),
                            InetAddress.getByName(AddressConfig.CURRENT_NODE_ADDRESS),
                            AddressConfig.CLIENT_LISTEN_PORT
                    );
                    serverSocket.send(datagramPacket);
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(5000);
//        }
    }

    @SneakyThrows
    public void broadcast() {
        /**
         * Create a new server socket and bind it to a free port. I have chosen
         * one in the 49152 - 65535 range, which are allocated for internal applications
         */
        for (String address : AddressConfig.ADDRESS_POOL) {
//            if (port == 50001) {
//                continue;
//            }
            try (DatagramSocket serverSocket = new DatagramSocket(50000)) {
                // The server will generate 3 messages and send them to the client
                for (int i = 0; i < 3; i++) {
//                    String message = "Message number " + i;

                    // encoding send packet
                    SentPacket packet = new SentPacket("Message Number " + i, AddressConfig.CURRENT_NODE_ADDRESS, AddressConfig.CURRENT_NODE_NUM);
                    final ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
                    final ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(packet);
                    final byte[] message = baos.toByteArray();

//                    final DatagramPacket packet = new DatagramPacket(data, data.length);
// Send the packet

                    DatagramPacket datagramPacket = new DatagramPacket(
                            message,
                            message.length,
//                            InetAddress.getLocalHost(),
                            InetAddress.getByName(address),
                            AddressConfig.CLIENT_LISTEN_PORT
                    );
                    serverSocket.send(datagramPacket);
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(5000);
        }

    }
}
