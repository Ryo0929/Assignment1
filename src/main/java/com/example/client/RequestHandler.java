package com.example.client;

import com.example.model.Request;
import com.example.model.Response;
import com.example.mysql_api.Items;
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

	public RequestHandler()
	{
	}

	public Response redirect(Request request) {
		switch (request.operation) {
			case PUT_SALE:
				return seller.addItemToSale((Items) request.content);
			case LIST_ITEM:
				return seller.listItemBySeller((Integer) request.content);
//			case CHANGE_PRICE:
//				REMOVE_ITEM, DISPLAY_ITEM
		}
		return new Response("Invalid Input");
	}
}
