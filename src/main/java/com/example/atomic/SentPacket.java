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

    // 0 : request Message
    // 1 : sequence Message
    // 2 : greeting Message
    int tag;

    private String message;

    // address of sent node
    private String sentIp = AddressConfig.CURRENT_NODE_ADDRESS;

    // node num of sent node
    // also for sender ID
    private int sentNodeNum = AddressConfig.CURRENT_NODE_NUM;

    // local sequence num, initialized
    private static int localSeq = 0;

    // default -1 for not set
    private int globalSeqNum = -1;

    // rest Header(content) for processing request
    private Object restContent;

    // rest tag to determine which request it stands for
    // -1 : not applicable
    // 1 : get Seller rating
    private int restTag;

    public SentPacket(int tag, String message, int globalSeqNum, Object restContent, int restTag) {
        this.tag = tag;
        this.message = message;
        this.globalSeqNum = globalSeqNum;
        this.restContent = restContent;
        this.restTag = restTag;
    }

    public static int getLocalSeq() {
        return localSeq;
    }

    public void incLocalSeq() {
        localSeq++;
    }
}
