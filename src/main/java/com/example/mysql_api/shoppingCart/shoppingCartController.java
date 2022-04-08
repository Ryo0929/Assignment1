package com.example.mysql_api.shoppingCart;

import com.example.mysql_api.item.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/shopping_cart")
public class shoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ShoppingCart shoppingCart){
        shoppingCartService.saveItem(shoppingCart);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PutMapping("/remove_shopping_cart/{item_id}/{buyer_id}/{quantity}")
    public ResponseEntity removeShoppingCart(@PathVariable int item_id,@PathVariable int buyer_id, @PathVariable int quantity){
        Items item =new Items();
        item.setItem_id(item_id);
        shoppingCartService.removeItemFromShoppingCart(item_id,buyer_id,quantity);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PutMapping("/display/{buyer_id}")
    public ResponseEntity<List<ShoppingCart>> displayShoppingcart(@PathVariable int buyer_id){
        List<ShoppingCart> res=shoppingCartService.displayShoppingCart(buyer_id);
        printItems(res);
        return ResponseEntity.ok(res);
    }
    @PutMapping("/clear/{buyer_id}")
    public ResponseEntity clearShoppingcart(@PathVariable int buyer_id){
        shoppingCartService.clearShoppingCart(buyer_id);
        return new ResponseEntity<>("success", HttpStatus.OK);
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
