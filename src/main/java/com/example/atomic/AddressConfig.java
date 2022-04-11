package com.example.atomic;

/**
 * @author Brayden
 * @create 4/6/22 11:53 AM
 * @Description
 */
public class AddressConfig {

    // Port 50000 used as server (work) port
    // Port 50001 used as server send destination & client (receive) port

    public static String[] ADDRESS_POOL = {"10.0.0.178", "10.0.0.39"};
    public static int[] PORT_POOL = {50001, 50002};

    // Current node configuration, differ in each server
    public static int CURRENT_NODE_NUM = 0;
    public static String CURRENT_NODE_ADDRESS = "10.0.0.178";
    public static int CLIENT_LISTEN_PORT = 50001;
    public static int TOTAL_NODE_NUM = 2;

}
