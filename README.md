# Distributed E-Commerce System

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
  
    

### System Design Part III (change in Assignment 4)
![](https://raw.githubusercontent.com/Ryo0929/Assignment1/release/assignment4/System_Architecture3.png)

#### Assumption
+ ### Rotating Sequencer Atomic Broadcast Protocol

  + Atomic controller is responsible for message sending, deliver status judge
  + The rest gateway still received request direct from client, but for the request related to customer database, it will be redirected to Atomic system
  + The atomic sytem begin "message send and deliver" logic after receiving redirected request from gateway, only after current deliver the messgae, the original business logic (eg. get buyer history) will then continue.
  + reference: https://github.com/dangeabunea/RomanianCoderExamples/tree/master/UdpUnicastSimple

+ ### Raft Protocol
  +  We migrate part of current database to RedisRaft cluster. All data relate to product will be store at a strongly-consistent cluster of redis server  using Raft consensus. 
  +  Compare to traditional Redis deployments trade strong consistency guarantees for improved performance and greater availability. RedisRaft was created for those occasions where strong consistency is required.
  +  A RedisRaft cluster will remain available (i.e., able to process reads and writes) as long as the majority of its nodes remains online and able to communicate with each other. If this is not the case, the cluster becomes unavailable.
  +  reference : https://github.com/RedisLabs/redisraft 

#### Current State
+ ### Rotating Sequencer Atomic Broadcast Protocol

  + The original project inherited from assignment 2 and new atomic protocol sytem have been duplicated to 5 servers on the cloud. 
  + The rotating Sequencer Atomic Broadcast Protocol work in production environment.
  + The original api and all existing functions works well.

+ ### Raft Protocol
  + For now, all product related data was moved to redis cluster, all data was stored at different nodes.
  + we deploy redis server for each 5 server, all 5 server have a state of either leader or follwer(1 leader 4 follower)
  + when a leader is down, the new election will happen, all the node will be a "candidate" state untill new leader is elected.

### RTT Test ###

+ **Average response time for each client function when all replicas run normally (no failures).**
  + **Put an item for sale**  173ms 	
  
  + **Change the sale price of an item**  204ms
  
  + **Remove Item**  205ms
  
  + **Display item by seller request**  365ms

  + **Create an account**  3742ms
  
  + **ADD Item to shopping cart**  599ms
  
  + **Remove Item from shopping cart**  405ms

  + **Display shopping cart**  673ms
  
  + **Clear shopping cart**  205ms
  	
  + **Search Item for sale**  754ms
  
  + **Get seller rating**  4529ms

  + **Get buyer history** 5323ms
  
+ **Average response time for each client function when one server-side sellers interface replica and one serverside buyers interface to which some of the clients are connected fail.**
	+ **Put an item for sale**  879ms 	
  
	+ **Change the sale price of an item**  794ms
  
  + **Remove Item**  582ms
  
  + **Display item by seller request**  1037ms

  + **Create an account**  7230ms
  
  + **ADD Item to shopping cart**  573ms
  
  + **Remove Item from shopping cart**  465ms

  + **Display shopping cart**  700ms
  
  + **Clear shopping cart**  402ms
  	
  + **Search Item for sale**  1038ms
  
  + **Get seller rating**  7905ms

  + **Get buyer history**  9124ms
  
+ **Average response time for each client function when one product database replica (not the leader) fails.**
	+ **Put an item for sale**  205ms 	
  
	+ **Change the sale price of an item**  276ms
  
  + **Remove Item**  593ms
  
  + **Display item by seller request**  483ms

  + **Create an account**  3805ms
  
  + **ADD Item to shopping cart**  292ms
  
  + **Remove Item from shopping cart**  106ms

  + **Display shopping cart**  204ms
  
  + **Clear shopping cart**  266ms
  	
  + **Search Item for sale**  296ms
  
  + **Get seller rating**  4699ms

  + **Get buyer history**  8832ms
  
+ **Average response time for each client function when the product database replica acting as leader fails.**
	+ **Put an item for sale**  ms 	
  
	+ **Change the sale price of an item**  ms
  
  + **Remove Item**  145ms
  
  + **Display item by seller request**  296ms

  + **Create an account**  3601ms
  
  + **ADD Item to shopping cart**  306ms
  
  + **Remove Item from shopping cart** 286 ms

  + **Display shopping cart**  178ms
  
  + **Clear shopping cart**  206ms
  	
  + **Search Item for sale**  406ms
  
  + **Get seller rating**  4512ms

  + **Get buyer history**  5402ms
