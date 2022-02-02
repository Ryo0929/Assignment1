package com.example.client;

import com.example.model.Request;
import com.example.model.Response;
import com.example.mysql_api.Items;
import com.example.mysql_api.ShoppingCart;

import java.util.List;

/**
 * @author Brayden
 * @create 2/1/22 11:19 PM
 * @Description
 */
public class ResponseHandler
{
	public ResponseHandler()
	{
	}
	public static void display(Request request, Response response)
	{
		String message;
		switch (request.operation)
		{
			case PUT_SALE:
			case CART_CLEAR:
			case CART_REMOVE_ITEM:
			case CART_ADD_ITEM:
			case CHANGE_PRICE:
			case REMOVE_ITEM:
				message = response.message;
				System.out.println(message);
				break;

			case LIST_ITEM:
				List<Items> itemList = (List<Items>) response.content;
				for (Items i : itemList) {
					System.out.print("id " + i.getItem_id());
					System.out.print(" name " + i.getItem_name());
					System.out.print(" price " + i.getSale_price());
					System.out.println("\n");
				}
				break;
			case SEARCH_ITEM:
				List<Items> searchList = (List<Items>) response.content;
				for (Items i : searchList) {
					System.out.print("id " + i.getItem_id());
					System.out.print(" name " + i.getItem_name());
					System.out.print(" price " + i.getSale_price());
					System.out.println("\n");
				}
				message = response.message;
				System.out.println(message);
				break;
			case CART_DISPLAY:
				List<ShoppingCart> cart = (List<ShoppingCart>) response.content;
				for (ShoppingCart i : cart) {
					System.out.print("id " + i.getItem_id());
					System.out.print(" quantity " + i.getQuantity());
					System.out.print(" buyer_id " + i.getBuyer_id());
					System.out.println("\n");
				}
				message = response.message;
				System.out.println(message);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + request.operation);
		}
	}
}
