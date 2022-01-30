package com.example.service;

import com.example.mysql_api.ItemService;
import com.example.mysql_api.Items;
import com.example.mysql_api.MysqlApiApplication;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author Brayden
 * @create 1/30/22 2:57 AM
 * @Description
 */
public class Seller
{
	ItemService itemService = new ItemService();

	public void addItemToSale(Items item)
	{
		itemService.listAllItem();
	}
}
