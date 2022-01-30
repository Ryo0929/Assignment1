package com.example.client;

import com.example.model.Request;
import com.example.mysql_api.Items;
import com.example.service.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author Brayden
 * @create 1/30/22 2:23 AM
 * @Description function as router to redirect request
 */
@Service
public class RequestController
{
	Seller seller;

	public RequestController()
	{
		seller = new Seller();
	}

	public void redirect(Request request) {
		switch (request.operation) {
			case PUT_SALE:
				seller.addItemToSale((Items) request.content);
//			case CHANGE_PRICE:
//				REMOVE_ITEM, DISPLAY_ITEM
		}
	}
}
