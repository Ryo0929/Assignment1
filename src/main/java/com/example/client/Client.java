package com.example.client; /**
 * @author Brayden
 * @create 1/29/22 1:03 AM
 * @Description
 * @Source com.example.client and server socket connection part
 */

/***************************************************************************************
 * 	  The socket connection part of com.example.client and server implementation are referenced and edited from resource below
 *    Title: <Socket Programming in Java/source code>
 *    Author: <Souradeep Barua>
 *    Date: <08 Nov, 2021>
 *    Availability: <https://www.geeksforgeeks.org/socket-programming-in-java/>
 *
 ***************************************************************************************/

// A Java program for a Client

import com.example.model.Food;
import com.example.model.Operation;
import com.example.model.Request;
import com.example.model.Response;
import com.example.mysql_api.Items;

import java.io.*;
import java.net.Socket;

public class Client
{
	// initialize socket and input output streams
	private Socket socket            = null;
	private Socket socket2           = null;
//	private DataInputStream  input   = null;
	private ObjectInputStream input = null;
	private ObjectOutputStream  out  = null;
//	private DataOutputStream out     = null;

	// constructor to put ip address and port
	public Client(String address, int port)
	{
		// establish a connection
		try
		{
			socket = new Socket(address, port);
			System.out.println("Connected");

			input  = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

			// sends output to the socket
			out = new ObjectOutputStream(socket.getOutputStream());
		}
		catch(IOException u)
		{
			System.out.println(u);
		}

		String line = "";
		Response res = new Response("");

		// keep reading until "Over" is input
		while (!line.equals("Over"))
		{
			try
			{
				// Encapsulate request content
				Items apple = new Items();
				apple.setItem_id(453);
				apple.setItem_name("big apple");

				// Request sending
//				Request<Items> addItemRequest = new Request<>(Operation.PUT_SALE, apple);
//				out.writeObject(addItemRequest);
				Request<Integer> listItemBySellerRequest = new Request<>(Operation.LIST_ITEM, 1);
				out.writeObject(listItemBySellerRequest);
				out.flush();

				// Extract response
				res = (Response) input.readObject();
				line = res.message;
				System.out.println(line);
			}
			catch(IOException | ClassNotFoundException i)
			{
				System.out.println(i);
			}
		}

		// close the connection
		try
		{
			input.close();
			out.close();
			socket.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Client client = new Client("127.0.0.1", 5000);
	}
}