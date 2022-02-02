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

    @GetMapping(path="/list_item_by_seller_id/{seller_id}")
    public List<Items> getAllItem(@PathVariable Integer seller_id){
        List<Items> res=itemService.findBySellerId(seller_id);
        printItems(res);
        return itemService.findBySellerId(seller_id);
    }

    @GetMapping(path="/list_item_by_keyword_and_category")
    public List<Items> getSearchItemByCategoryAndKeyword(@RequestBody Items item){
        List<Items>res=itemService.searchItems(item);
        printItems(res);
        return res;
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
    public void update(@PathVariable Integer id,@PathVariable Integer remove_quantity) {
        Items item = new Items();
        item.setItem_id(id);
        itemService.removeItem(item,remove_quantity);
    }
    private void printItems(List<Items> items){
        for(Items item:items){
            System.out.println("id: "+item.getItem_id());
            System.out.println("name: "+item.getItem_name());
            System.out.println("cate: "+item.getItem_category());
            System.out.println("price: "+item.getSale_price());
            System.out.println("quantity: "+item.getQuantity());
            System.out.println("k1: "+item.getKeyword1());
            System.out.println("k2: "+item.getKeyword2());
            System.out.println("k3: "+item.getKeyword3());
            System.out.println("---------------");
        }
    }
}
