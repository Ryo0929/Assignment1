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
        // Greeting
        for (String address : AddressConfig.ADDRESS_POOL) {
            try (DatagramSocket serverSocket = new DatagramSocket(50000)) {

                    // encoding send packet
                    SentPacket packet = new SentPacket(2, "Node Ready", SentPacket.getCurLocalSeq(), -1, null, -1, -1);

                    final ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
                    final ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(packet);
                    final byte[] message = baos.toByteArray();

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

    @SneakyThrows
    public void broadcast(SentPacket packet) {
        // increment local sequence number
        if (packet.getTag() == 0) {
            SentPacket.incCurLocalSeq();
        }

        for (String address : AddressConfig.ADDRESS_POOL) {
            try (DatagramSocket serverSocket = new DatagramSocket(50000)) {

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
//            Thread.sleep(5000);
        }

    }
}
