package com.example.model;

import java.io.Serializable;

/**
 * @author Brayden
 * @create 1/29/22 4:58 PM
 * @Description
 */
public class Food implements Serializable
{
	public String name;
	public int price;

	public Food(String name, int price)
	{
		this.name = name;
		this.price = price;
	}
}
