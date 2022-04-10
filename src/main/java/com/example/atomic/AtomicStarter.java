package com.example.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicStarter {

    public AtomicStarter() {
        int port = 50001;
        int port2 = 50002;
        UdpUnicastServer server = new UdpUnicastServer();
        UdpUnicastClient client = new UdpUnicastClient(AddressConfig.CURRENT_NODE_ADDRESS, AddressConfig.CURRENT_NODE_NUM, AddressConfig.CLIENT_LISTEN_PORT, server);
//        UdpUnicastClient client2 = new UdpUnicastClient(AddressConfig.NODE0_ADDRESS, AddressConfig.CLIENT_LISTEN_PORT2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(client);
//        executorService.submit(client2);
        executorService.submit(server);
    }
}
