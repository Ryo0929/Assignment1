package com.example.mysql_api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Items> getAllItem(){
        return itemService.listAllItem();
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
