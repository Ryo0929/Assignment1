package com.example.mysql_api;

import com.example.mysql_api.mysql_api.db_servicesGrpc;
import com.example.mysql_api.mysql_api.item;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class grpcClient {
    public static void main(String[] args){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8050).usePlaintext().build();
        db_servicesGrpc.db_servicesBlockingStub stub=db_servicesGrpc.newBlockingStub(channel);
        item i = item.newBuilder().setItemName("grpcTest").setQuantity(3).build();
        stub.saveItem(i);

        channel.shutdown();
    }
}
