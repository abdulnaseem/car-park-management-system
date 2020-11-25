Car Park Management System

Scenario and Instructions for Completing the Task

The primary goal of this assignment is to develop a demonstrator of a multi-threading client-server system that 
shows how a car park management system can keep track of the number of available spaces as cars leave and enter a car park.

The Car Park

The car park has two entrances and two exits.  A car will attempt to enter the car park and will be allowed to enter if there is space.  
If not it will queue at the entrance until there is space.  A car leaving the car park will reduce the number of cars in the car park by one – 
a waiting car should then be able to enter the car park. For purposes of this assignment you may assume that the car park has five spaces (to make testing easier!)

You are required to create a threaded client-server system that uses locking and has:

•	Two clients that represent each of the entrances 
•	Two clients that represent each of the exits
•	One server to hold and manage the data for the car park.

Functionality

Entrance Client. 
An Entrance client must be able to represent the arrival of cars to the car park and to communicate with the server that a car
wishes to enter the car park.  The client will then wait for permission from the server to allow a car to enter.  
If the server says that there are no spaces then the Entrance client must keep the car waiting (and queue the others) until there is space.  
The car arrival event can be represented by a key press.  The client MUST be able to show cars arriving, entering and/or waiting at the client (e.g. not at the server only!)  
Text messages on the screen are fine.

Exit Client.  
The exit client presents a notification that a car has left the car park. Again, this can be represented by a key press.  Again the client MUST 
be able to show cars leaving. Text messages on the screen are fine.

Car Park Server.  
The Car Park Server will communicate with the clients to ensure the correct number of cars/spaces are in the car park.  
The server will manage the overall number of spaces.  The server coordinates with all the entrances and exits to ensure the correct spaces.   
