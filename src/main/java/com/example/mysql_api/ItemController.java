package com.example.mysql_api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path="/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public void add(@RequestBody Items item) {
        itemService.saveItem(item);
    }

    //Todo:list items by "seller"
    @GetMapping(path="/list_item_by_seller_id/{seller_id}")
    public List<Items> getAllItem(@PathVariable Integer seller_id){
        List<Items> res=itemService.findBySellerId(seller_id);
        return itemService.findBySellerId(seller_id);
    }


    @PutMapping("/update_price/{id}")
    public ResponseEntity<?> update(@RequestBody Items item, @PathVariable Integer id) {
        try {
                Items existItem = itemService.getItem(id);
                existItem.setSale_price(item.getSale_price());
                itemService.saveItem(existItem);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/remove_item/{id}/{remove_quantity}")
    public ResponseEntity<?> update(@PathVariable Integer id,@PathVariable Integer remove_quantity) {
        try {
            Items existItem = itemService.getItem(id);
            existItem.setQuantity(existItem.getQuantity()-remove_quantity);
            itemService.saveItem(existItem);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
