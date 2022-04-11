package com.example.service;

import com.example.atomic.SentPacket;
import com.example.mysql_api.buyer.BuyerService;
import com.example.mysql_api.buyer.Buyers;
import com.example.mysql_api.seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Brayden
 * @create 4/07/22 6:48 PM
 * @Description
 */
@Service
public class MessageRequestHandler {
    @Autowired
    SellerService sellerService;

    @Autowired
    BuyerService buyerService;

    @Autowired
    private com.example.mysql_api.purchasedItem.purchasedItemService purchasedItemService;


    public MessageRequestHandler() {
    }

    public void redirect(SentPacket packet) {
        switch (packet.getRestTag()) {
            case 1:
                sellerService.getSellerRatingById((int) packet.getRestContent());
                break;
            case 2:
                buyerService.saveBuyer((Buyers) packet.getRestContent());
                break;
            case 3:
                purchasedItemService.listByBuyerId((int) packet.getRestContent());
                break;
            default:
                break;
        }
    }
}
