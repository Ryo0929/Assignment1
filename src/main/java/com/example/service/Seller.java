package com.example.service;

import com.example.config.BeanConfig;
import com.example.mysql_api.ItemService;
import com.example.mysql_api.Items;
import com.example.mysql_api.MysqlApiApplication;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
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

	public void addItemToSale(Items item)
	{
		List<Items> itemList = itemService.listAllItem();
		for (Items i : itemList) {
			System.out.println(i.getItem_id());
			System.out.println(i.getItem_name());
		}
	}
}
