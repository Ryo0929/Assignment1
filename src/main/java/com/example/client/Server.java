package com.example.client; /**
 * @author Brayden
 * @create 1/29/22 1:03 AM
 * @Description
 */

/***************************************************************************************
 * 	  The socket connection part of com.example.client and server implementation are referenced and edited from resource below
 *    Title: <Socket Programming in Java/source code>
 *    Author: <Souradeep Barua>
 *    Date: <08 Nov, 2021>
 *    Availability: <https://www.geeksforgeeks.org/socket-programming-in-java/>
 *
 ***************************************************************************************/

// A Java program for a Server

import com.example.model.Operation;
import com.example.model.Request;
import com.example.model.Response;
import com.example.util.SpringUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	//initialize socket and input stream
	private Socket          socket   = null;
	private ServerSocket    server   = null;
//	private DataInputStream in       =  null;
	private ObjectInputStream in     =  null;
	private ObjectOutputStream out   =  null;
	private RequestHandler rc;

	// constructor with port
	public Server(int port)
	{
		rc = (RequestHandler) SpringUtil.getBean(RequestHandler.class);
		// starts server and waits for a connection
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a com.example.client ...");

			socket = server.accept();
			System.out.println("Client accepted");

			// takes input from the com.example.client socket
//			in = new DataInputStream(
//					new BufferedInputStream(socket.getInputStream()));
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(
					new BufferedInputStream(socket.getInputStream()));

			String line = "";

			// reads message from com.example.client until "Over" is sent
			while (!line.equals("Over"))
			{
				try
				{
//					Food food = (Food) in.readObject();
//					line = in.readUTF();
					Request request = (Request) in.readObject();
//					line = food.name;
					Operation operation = request.operation;
					System.out.println(operation);
					Response res = rc.redirect(request);
					out.writeObject(res);
				}
				catch(IOException | ClassNotFoundException i)
				{
					System.out.println(i);
				}
			}
			System.out.println("Closing connection");

			// close connection
			socket.close();
			in.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Server server = new Server(5000);
	}
}
