package com.example.config;

import com.example.mysql_api.item.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Brayden
 * @create 1/30/22 4:18 PM
 * @Description
 */
@Configuration
public class BeanConfig
{

	@Bean(name = {"itemServiceBean"})
	@Scope(value = "prototype")
	public ItemService getItemServcie() {
		return new ItemService();
	}
}