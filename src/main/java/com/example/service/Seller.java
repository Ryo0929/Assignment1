package com.example.service;

import com.example.model.Response;
import com.example.mysql_api.ItemService;
import com.example.mysql_api.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Brayden
 * @create 1/30/22 2:57 AM
 * @Description
 */
@Component
public class Seller
{
	@Autowired
	ItemService itemService;

	public Response addItemToSale(Items item)
	{
		itemService.saveItem(item);
		return new Response();
	}

	public Response<List<Items>> listItemBySeller(Integer id)
	{
		List<Items> itemList = itemService.findBySellerId(id);
		return new Response<List<Items>>(itemList);
	}

	public Response changeSalePrice(Items content)
	{
		itemService.changeSalePrice(content);
		return new Response();
	}

	public Response removeItem(Items content)
	{
		itemService.removeItem(content, content.getQuantity());
		return new Response();
	}
}
