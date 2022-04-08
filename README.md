# Assignment One

### Team Member

WeiTung Liao, Bofan Zheng

### Description

+ ### System Design

  The architecture diagram below shows major components in our system

  ![](https://github.com/Ryo0929/Assignment1/raw/client/System_Architecture.png)

  + **UI Input** (Not Implemented): The user can use graph interface to input all request in shopping cart, buyer & seller interface
  + **Request Sender** (worked): The sender can direct all request to given server socket
  + **Response Handler**(worked): The handler can receive and process server's response, depend on the type of response, the handler will write response message or the items (for the list / display request ) to output console.
  + **Text Output** (worked): The processed response will be output to the program console, we may change it to graph interface in the future
  + **Request Handler** (worked): The handler mainly function as a router here, receives socket input, and then redirect request to corresponding data api
  
+ ### Data API (works)
  + **Put an item for sale**: provide all item characteristics and quantity 
  + **Change the sale price of an item**: provide item id and new sale price 
  + **Remove an item from sale**: provide item id and quantity 
  + **Display items currently on sale put up by this seller**: provide seller id
  + **Search items for sale**: provide an item category and up to five keywords 
  + **Add item to the shopping cart**: provide item id and quantity 
  + **Remove item from the shopping cart**: provide item id and quantity 
  + **Clear the shopping cart**: provide buyer id
  + **Display shopping cart**: provide buyer id


  Assignment 2
  + **Create an account:(sellers)**: provide seller_name, seller_feedback, number_of_items_sold, password
  + **Get seller rating:**: provide seller_id
  + **Create an account:(buyers)**: provide buyer_name, number_of_item_purchased, password
  + **Get buyer history**: provide buyer_id

  

+ ### Database tables
current tables

![](https://github.com/Ryo0929/Assignment1/blob/release/assignment1/tables.png)

items

![](https://github.com/Ryo0929/Assignment1/blob/release/assignment1/table_items.png)

sellers

![](https://github.com/Ryo0929/Assignment1/blob/release/assignment1/table_sellers.png)

shopping cart

![](https://github.com/Ryo0929/Assignment1/blob/release/assignment1/tables_shoppingcart.png)

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
  
    

### System Design Part II

![](https://raw.githubusercontent.com/Ryo0929/Assignment1/release/assignment2/System_Architecture2.png)

+ ### SOAP client ### 

  + Perform RPC call to SOAP server 

+ ### SOAP Server ###

  + Reply xml response to indicate transaction status
  
    

### System Design Part III 
![](https://raw.githubusercontent.com/Ryo0929/Assignment1/release/assignment4/System_Architecture3.png)
