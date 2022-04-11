package com.example.mysql_api.buyer;


import com.example.atomic.AddressConfig;
import com.example.atomic.SentPacket;
import com.example.atomic.UdpUnicastServer;
import com.example.mysql_api.purchasedItem.purchasedItem;
import com.example.mysql_api.purchasedItem.purchasedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/buyers")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @Autowired
    private com.example.mysql_api.purchasedItem.purchasedItemService purchasedItemService;

    @Autowired
    UdpUnicastServer udpUnicastServer;

    @PostMapping("/add")
    public ResponseEntity add(
            @RequestBody Buyers buyers) {
        SentPacket packet = new SentPacket(0, " ", SentPacket.getCurLocalSeq(), -1, buyers, 2, AddressConfig.CURRENT_NODE_NUM);
        udpUnicastServer.broadcast(packet);
//        buyerService.saveBuyer(buyers);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @GetMapping(path="/list_item_by_buyerer_id/{buyer_id}")
    public ResponseEntity getAllPurchasedItem(@PathVariable Integer buyer_id){
        SentPacket packet = new SentPacket(0, " ", SentPacket.getCurLocalSeq(), -1, buyer_id, 2, AddressConfig.CURRENT_NODE_NUM);
        udpUnicastServer.broadcast(packet);
//        List<purchasedItem> res=purchasedItemService.listByBuyerId(buyer_id);
//        return ResponseEntity.ok(res);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
