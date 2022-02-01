package com.example.mysql_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/shopping_cart")
public class shoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @PostMapping("/add")
    public void add(@RequestBody ShoppingCart shoppingCart){shoppingCartService.saveItem(shoppingCart);}
    @PutMapping("/remove_shopping_cart/{item_id}/{buyer_id}/{quantity}")
    public void removeShoppingCart(@PathVariable int item_id,@PathVariable int buyer_id, @PathVariable int quantity){
        Items item =new Items();
        item.setItem_id(item_id);
        shoppingCartService.removeItemFromShoppingCart(item_id,buyer_id,quantity);
    }
    @PutMapping("/display/{buyer_id}")
    public void displayShoppingcart(@PathVariable int buyer_id){
        List<ShoppingCart> res=shoppingCartService.displayShoppingCart(buyer_id);
        printItems(res);
    }
    @PutMapping("/clear/{buyer_id}")
    public void clearShoppingcart(@PathVariable int buyer_id){
        shoppingCartService.clearShoppingCart(buyer_id);
    }
    private void printItems(List<ShoppingCart> cartitems){
        for(ShoppingCart item:cartitems){
            System.out.println("id: "+item.getId());
            System.out.println("item_id: "+item.getItem_id());
            System.out.println("buyer_id: "+item.getBuyer_id());
            System.out.println("quantity: "+item.getQuantity());
            System.out.println("---------------");
        }
    }
}
