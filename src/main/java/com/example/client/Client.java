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
import com.example.mysql_api.ShoppingCart;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
	// initialize socket and input output streams
	private Socket socket            = null;
	private Socket socket2           = null;
	private ObjectInputStream input = null;
	private ObjectOutputStream  out  = null;
	private Scanner scanner;
	private ResponseHandler handler = null;

	// constructor to put ip address and port
	public Client(String address, int port)
	{
//		handler = new ResponseHandler();
		// establish a connection
		try
		{
			socket = new Socket(address, port);
			scanner = new Scanner(System.in);
			System.out.println("Connected");

			input  = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

			// sends output to the socket
			out = new ObjectOutputStream(socket.getOutputStream());
		}
		catch(IOException u)
		{
			System.out.println(u);
		}

		int line = 0;
		Response res = new Response("");

		// keep reading until "Over" is input
		while (line != -1)
		{
			try
			{
				line = scanner.nextInt();
				String message;

				// Encapsulate request content
				Items apple = new Items();
				apple.setItem_id(3);
				apple.setSale_price(10);
				apple.setQuantity(2);
				apple.setItem_category(2);
				apple.setKeyword2("b");
				apple.setKeyword3("c");
				apple.setItem_name("test2");

				ShoppingCart cartItem = new ShoppingCart();
				cartItem.setItem_id(5);
				cartItem.setQuantity(1);

				Request request = new Request<>(Operation.LIST_ITEM, 1);

				long startTime = System.currentTimeMillis();
				switch (line) {
					case 1 :
						// 1. addItem request
						request = new Request<>(Operation.PUT_SALE, apple);
						break;
					case 2 :
						// 2. Change sale price
						request = new Request<>(Operation.CHANGE_PRICE, apple);
						break;
					case 3 :
						// 3. Remove Item
						request = new Request<>(Operation.REMOVE_ITEM, apple);
						break;
					case 4 :
						// 4. Display item by seller request
						request = new Request<>(Operation.LIST_ITEM, 1);
						break;
					case 5 :
						// 5. Search Item
						request = new Request<>(Operation.SEARCH_ITEM, apple);
						break;
					case 6 :
						// 6. ADD Item
						request =new Request<>(Operation.CART_ADD_ITEM, cartItem);
						break;
					case 7 :
						// 7. Remove Item from shopping cart
						request = new Request<>(Operation.CART_REMOVE_ITEM, cartItem);
						break;
					case 8 :
						// 8. Clear shopping cart
						request = new Request<>(Operation.CART_CLEAR, 4);
						break;
					case 9 :
						// 9. Display shopping cart
						request = new Request<>(Operation.CART_DISPLAY, 2);
						break;
				}

				// Request sending
				// 1. addItem request
//				Request<Items> request = new Request<>(Operation.PUT_SALE, apple);
				// 2. Change sale price
//				Request<Items> request = new Request<>(Operation.CHANGE_PRICE, apple);
				// 3. Remove Item
//				Request<Items> request = new Request<>(Operation.REMOVE_ITEM, apple);
				// 4. Display item by seller request
//				Request<Integer> request = new Request<>(Operation.LIST_ITEM, 1);
				// 5. Search Item
//				Request<Items> request = new Request<>(Operation.SEARCH_ITEM, apple);
				// 6. ADD Item
//				Request<ShoppingCart> request = new Request<>(Operation.CART_ADD_ITEM, cartItem);
				// 7. Remove Item from shopping cart
//				Request<ShoppingCart> request = new Request<>(Operation.CART_REMOVE_ITEM, cartItem);
				// 8. Clear shopping cart
//				Request<Integer> request = new Request<>(Operation.CART_CLEAR, 4);
				// 9. Display shopping cart
//				Request<Integer> request = new Request<>(Operation.CART_DISPLAY, 2);
				out.writeObject(request);
				out.flush();

				// Extract response
				res = (Response) input.readObject();
				ResponseHandler.display(request, res);
				long endTime = System.currentTimeMillis();
				System.out.println("RTT: "+ (endTime - startTime) + "ms");
				message = res.message;
				System.out.println(message);
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