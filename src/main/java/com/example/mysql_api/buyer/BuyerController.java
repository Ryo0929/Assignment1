package com.example.mysql_api.buyer;


import com.example.mysql_api.purchasedItem.purchasedItem;
import com.example.mysql_api.purchasedItem.purchasedItemService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/add")
    public ResponseEntity add(
        @RequestBody Buyers buyers){buyerService.saveBuyer(buyers);
        return ResponseEntity.ok().build();
    }
    @GetMapping(path="/list_item_by_buyerer_id/{buyer_id}")
    public ResponseEntity<List<purchasedItem>> getAllPurchasedItem(@PathVariable Integer buyer_id){
        List<purchasedItem> res=purchasedItemService.listByBuyerId(buyer_id);
        return ResponseEntity.ok(res);
    }
}
