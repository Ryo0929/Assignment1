package com.example.model;

import java.io.Serializable;

/**
 * @author Brayden
 * @create 1/30/22 2:25 AM
 * @Description
 */
public class Request<T> implements Serializable
{
	public Operation operation;
	public T content;

	public Request(Operation operation, T content)
	{
		this.operation = operation;
		this.content = content;
	}
}
