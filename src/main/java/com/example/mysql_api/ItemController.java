package com.example.mysql_api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path="/items")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addItem(@RequestParam String name,@RequestParam int category,@RequestParam String condition,@RequestParam double price,@RequestParam int quantity){
        Item n = new Item();
        n.setName(name);
        n.setCategory(category);
        n.setCondition(condition);
        n.setPrice(price);
        n.setQuantity(quantity);
        itemRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Item> getAllItem(){
        return itemRepository.findAll();
    }
}
