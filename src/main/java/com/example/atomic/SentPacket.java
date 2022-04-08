package com.example.atomic;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Brayden
 * @create 4/8/22 12:04 PM
 * @Description
 */

@Data
public class SentPacket implements Serializable {
    private String message;
    private String ip;
    private int nodeNum;

    public SentPacket(String message, String ip, int num) {
        this.message = message;
        this.ip = ip;
        this.nodeNum = num;
    }
}
