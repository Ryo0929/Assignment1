package com.example.mysql_api.purchasedItem;

import com.example.mysql_api.item.ItemService;
import com.example.mysql_api.item.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/purchasedItem")
public class purchasedItemController {
    @Autowired
    private purchasedItemService purchasedItemService;
    @Autowired
    private ItemService itemService;

    @PostMapping("/purchase")
    public ResponseEntity purchase(@RequestBody purchasedItem transaction){
        purchasedItemService.add(transaction);
        itemService.removeItem(transaction.getItem_id(),1);//can only buy one item each time
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
