package com.example.atomic;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

//@Configuration
@Service
@Scope("singleton")
public class UdpUnicastServer implements Runnable {
    /**
     * The port where the client is listening.
     */
//    private int clientPort;

    public UdpUnicastServer() {
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
                    SentPacket packet = new SentPacket(2, "Node Ready", SentPacket.getCurLocalSeq(), -1, null, -1);
                    SentPacket.incLocalSeq();

                    final ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
                    final ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(packet);
                    final byte[] message = baos.toByteArray();

                    DatagramPacket datagramPacket = new DatagramPacket(
                            message,
                            message.length,
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
    public void broadcast(SentPacket packet) {
        /**
         * Create a new server socket and bind it to a free port. I have chosen
         * one in the 49152 - 65535 range, which are allocated for internal applications
         */
        for (String address : AddressConfig.ADDRESS_POOL) {
            try (DatagramSocket serverSocket = new DatagramSocket(50000)) {
                // The server will generate 3 messages and send them to the client

                    // encoding send packet
                    final ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
                    final ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(packet);
                    final byte[] message = baos.toByteArray();


                    // udp packet
                    DatagramPacket datagramPacket = new DatagramPacket(
                            message,
                            message.length,
                            InetAddress.getByName(address),
                            AddressConfig.CLIENT_LISTEN_PORT
                    );
                    serverSocket.send(datagramPacket);

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
