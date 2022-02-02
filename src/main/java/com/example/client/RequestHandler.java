package com.example.client;

import com.example.model.Request;
import com.example.model.Response;
import com.example.mysql_api.Items;
import com.example.mysql_api.ShoppingCart;
import com.example.service.Buyer;
import com.example.service.Seller;
import com.example.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author Brayden
 * @create 1/30/22 2:23 AM
 * @Description function as router to redirect request
 */
@Service
public class RequestHandler
{
	@Autowired
	Seller seller;

	@Autowired
	Buyer buyer;

	public RequestHandler()
	{
	}

	public Response redirect(Request request) {
		switch (request.operation) {
			case PUT_SALE:
				return seller.addItemToSale((Items) request.content);
			case CHANGE_PRICE:
				return seller.changeSalePrice((Items) request.content);
			case REMOVE_ITEM:
				return seller.removeItem((Items) request.content);
			case LIST_ITEM:
				return seller.listItemBySeller((Integer) request.content);
			case SEARCH_ITEM:
				return buyer.searchItem((Items) request.content);
			case CART_ADD_ITEM:
				return buyer.cartAddItem((ShoppingCart) request.content);
			case CART_REMOVE_ITEM:
				return buyer.cartRemoveItem((ShoppingCart) request.content);
			case CART_CLEAR:
				return buyer.cartClear((Integer) request.content);
			case CART_DISPLAY:
				return buyer.cartDisplay((Integer) request.content);
		}
		return new Response("Invalid Input");
	}
}
