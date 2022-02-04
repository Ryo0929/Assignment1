# Assignment One

### Team Member

WeiTung Liao, Bofan Zheng

### Description

+ ### System Desgin

  The architecture diagram below shows major components in our system

  ![](https://github.com/Ryo0929/Assignment1/raw/client/System_Architecture.png)

  + **UI Input** (Not Implemented): The user can use graph interface to input all request in shopping cart, buyer & seller interface
  + **Request Sender** (worked): The sender can direct all request to given server socket
  + **Response Handler**(worked): The handler can receive and process server's response, depend on the type of response, the handler will write response message or the items (for the list / display request ) to output console.
  + **Text Output** (worked): The processed response will be output to the program console, we may change it to graph interface in the future
  + **Request Handler** (worked): The handler mainly function as a router here, receives socket input, and then redirect request to corresponding data api

+ ### RTT Test ###

  The Test result for client and server process depolyed in local machine, database deployed in google cloud platform

  + **addItem request** 294ms
  + **Change sale price** 290ms
  + **Remove Item** 311ms
  + **Display item by seller request** 304ms
  + **Search Item** 272ms
  + **ADD Item** 262ms
  + **Remove Item from shopping cart** 281ms
  + **Clear shopping cart** 305ms
  + **Display shopping cart** 310ms

  





