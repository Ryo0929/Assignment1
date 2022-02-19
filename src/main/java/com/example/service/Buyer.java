package com.example.service;

import com.example.model.Response;
import com.example.mysql_api.item.ItemService;
import com.example.mysql_api.item.Items;
import com.example.mysql_api.shoppingCart.ShoppingCart;
import com.example.mysql_api.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Brayden
 * @create 1/30/22 2:57 AM
 * @Description
 */
@Component
public class Buyer
{
	@Autowired
	ItemService itemService;

	@Autowired
	ShoppingCartService shoppingCartService;

	public Response<List<Items>> searchItem(Items item)
	{
		List<Items> itemList = itemService.searchItems(item);
		return new Response<List<Items>>(itemList);
	}

	public Response cartAddItem(ShoppingCart content)
	{
		shoppingCartService.saveItem(content);
		return new Response();
	}

	public Response cartRemoveItem(ShoppingCart content)
	{
		shoppingCartService.removeItemFromShoppingCart(content.getItem_id(), content.getQuantity(),content.getBuyer_id());
		return new Response();
	}

	public Response cartClear(Integer content)
	{
		shoppingCartService.clearShoppingCart(content);
		return new Response();
	}

	public Response<List<ShoppingCart>> cartDisplay(Integer content)
	{
		List<ShoppingCart> cartList = shoppingCartService.displayShoppingCart(content);
		return new Response<List<ShoppingCart>>(cartList);
	}
}
