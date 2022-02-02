package com.example.model;

import java.io.Serializable;

/**
 * @author Brayden
 * @create 2/1/22 8:04 PM
 * @Description
 */
public class Response<T> implements Serializable
{
	public String message = "OK";
	public T content;

	public Response(String message, T content)
	{
		this.message = message;
		this.content = content;
	}

	public Response(T content)
	{
		this.content = content;
	}

	public Response(String message)
	{
		this.message = message;
	}

	public Response()
	{
	}
}
