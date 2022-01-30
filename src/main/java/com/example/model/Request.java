package com.example.model;

/**
 * @author Brayden
 * @create 1/30/22 2:25 AM
 * @Description
 */
public class Request<T>
{
	public Operation operation;
	public T content;

	public Request(Operation operation, T content)
	{
		this.operation = operation;
		this.content = content;
	}
}
