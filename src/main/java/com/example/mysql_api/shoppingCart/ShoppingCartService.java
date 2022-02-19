package com.example.mysql_api.shoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> listAllItem() {
        return shoppingCartRepository.findAll();
    }

    //Add item to the shopping cart: provide item id and quantity
    public void saveItem(ShoppingCart shoppingCart) {
        List<ShoppingCart> carts=shoppingCartRepository.getByBuyerIdAndItemId(shoppingCart.getItem_id(),shoppingCart.getBuyer_id());
        int quantity=shoppingCart.getQuantity();
        if (carts.isEmpty()){
            shoppingCartRepository.save(shoppingCart);
        }else{
            ShoppingCart existShoppingCart=carts.get(0);
            existShoppingCart.setQuantity(existShoppingCart.getQuantity()+quantity);
            shoppingCartRepository.save(existShoppingCart);
        }

    }
    //Remove item from the shopping cart: provide item id and quantity
    public void removeItemFromShoppingCart(int item_id,int quantity,int buyer_id){
        List<ShoppingCart> cartItems=shoppingCartRepository.getByBuyerIdAndItemId(item_id,buyer_id);
        for(ShoppingCart cartItem:cartItems){
            if (cartItem.getQuantity()>quantity){
                cartItem.setQuantity(cartItem.getQuantity()-quantity);
                shoppingCartRepository.save(cartItem);
            }else{
                shoppingCartRepository.deleteById(cartItem.getId());
            }
        }
    }
    //Clear the shopping cart
    public void clearShoppingCart(int buyerId){
        shoppingCartRepository.clearByBuyerId(buyerId);
    }
    public List<ShoppingCart> displayShoppingCart(int buyerId){
        return shoppingCartRepository.getByBuyerId(buyerId);
    }

    public ShoppingCart getShoppingCart(Integer id) {
        return shoppingCartRepository.findById(id).get();
    }

    public void deleteItem(Integer id) {
        shoppingCartRepository.deleteById(id);
    }
}
