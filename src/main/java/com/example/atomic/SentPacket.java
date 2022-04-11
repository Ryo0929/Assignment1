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
    // 3 : notify received seq
    // 4 : retransmit Message
    int tag;

    private String message;

    // address of sent node
    private String sentIp = AddressConfig.CURRENT_NODE_ADDRESS;

    // node num of sent node
    // also for sender ID
    private int sentNodeNum = AddressConfig.CURRENT_NODE_NUM;

    // local sequence num, initialized
    private static int curLocalSeq = 0;

    private int localSeq;

    // default -1 for not set
    private int globalSeqNum = -1;

    // rest Header(content) for processing request
    private Object restContent;

    // original Node sent request Message
    private int reqSentNodeNum;

    // rest tag to determine which request it stands for
    // -1 : not applicable
    // 1 : get Seller rating
    // 2 : create account
    // 3 : get Buyer history
    private int restTag;

    private boolean delivered = false;

    public SentPacket(int tag, String message, int lseq, int globalSeqNum, Object restContent, int restTag, int rsn) {
        this.tag = tag;
        this.message = message;
        this.localSeq = lseq;
        this.globalSeqNum = globalSeqNum;
        this.restContent = restContent;
        this.restTag = restTag;
        this.reqSentNodeNum = rsn;
    }

    public static int getCurLocalSeq() {
        return curLocalSeq;
    }

    public static void incCurLocalSeq() {
        curLocalSeq++;
    }
}
