package com.example.mysql_api;

import com.example.mysql_api.item.Items;
import com.example.mysqljdbc.db_servicesGrpc;
import com.example.mysqljdbc.item;
import com.example.mysqljdbc.itemList;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GrpcService {
    //ManagedChannel channel = ManagedChannelBuilder.forAddress("34.106.36.207",8050).usePlaintext().build();
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8050).usePlaintext().build();
    db_servicesGrpc.db_servicesBlockingStub stub=db_servicesGrpc.newBlockingStub(channel);

    public void saveItem(Items inputItem){
        item i = convert2grpcItem(inputItem);
        stub.saveItem(i);
        //channel.shutdown();
    }
    public itemList getItem(Items inputItem){
        item i = convert2grpcItem(inputItem);
        itemList response=stub.getItem(i);
        return response;
    }
    public void delItem(Items inputItem){
        item i = convert2grpcItem(inputItem);
        stub.delItem(i);
    }
    private item convert2grpcItem(Items inputItem){
        item.Builder itemBuilder = item.newBuilder();
        if (inputItem.getItem_id() != null){
            itemBuilder.setItemId(inputItem.getItem_id());
        }
        if (inputItem.getItem_name() != null){
            itemBuilder.setItemName(inputItem.getItem_name());
        }
        if (inputItem.getItem_category() != null){
            itemBuilder.setItemCategory(inputItem.getItem_category());
        }
        if (inputItem.getItem_condition() !=null){
            itemBuilder.setItemCondition(inputItem.getItem_condition());
        }
        if (inputItem.getKeyword1() !=null){
            itemBuilder.setKeyword1(inputItem.getKeyword1());
        }if (inputItem.getKeyword2() !=null){
            itemBuilder.setKeyword2(inputItem.getKeyword2());
        }if (inputItem.getKeyword3() !=null){
            itemBuilder.setKeyword3(inputItem.getKeyword3());
        }if (inputItem.getKeyword4() !=null){
            itemBuilder.setKeyword4(inputItem.getKeyword4());
        }if (inputItem.getKeyword5() !=null){
            itemBuilder.setKeyword5(inputItem.getKeyword5());
        }if (inputItem.getQuantity() !=null){
            itemBuilder.setQuantity(inputItem.getQuantity());
        } if (inputItem.getSeller_id() !=null){
            itemBuilder.setSellerId(inputItem.getSeller_id());
        } if (inputItem.getSale_price() !=null){
            itemBuilder.setSalePrice(inputItem.getSale_price());
        }
        item response = itemBuilder.build();
        return response;
    }
}
