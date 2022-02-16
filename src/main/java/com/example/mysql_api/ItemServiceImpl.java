package com.example.mysql_api;

import com.example.mysql_api.mysql_api.DbService;
import com.example.mysql_api.mysql_api.Empty;
import com.example.mysql_api.mysql_api.db_servicesGrpc;
import com.example.mysql_api.mysql_api.item;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class ItemServiceImpl extends db_servicesGrpc.db_servicesImplBase {
    @Autowired
    ItemService itemService;

    @Override
    public void saveItem(item input_item, StreamObserver<Empty> empty){
        ItemService itemService = new ItemService();
        Items items = new Items();
        items.setItem_name(input_item.getItemName());
        items.setQuantity(input_item.getQuantity());
        itemService.saveItem(items);
    }
}
