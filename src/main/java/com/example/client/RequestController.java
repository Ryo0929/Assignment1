package com.example.client;

import com.example.model.Request;
import com.example.mysql_api.Item;
import com.example.service.Seller;

/**
 * @author Brayden
 * @create 1/30/22 2:23 AM
 * @Description function as router to redirect request
 */
public class RequestController
{
	public void redirect(Request request) {
		switch (request.operation) {
			case PUT_SALE:
				Seller.addItemToSale((Item) request.content);
//			case CHANGE_PRICE:
//				REMOVE_ITEM, DISPLAY_ITEM
		}
	}
}
