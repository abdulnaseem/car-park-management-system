import java.net.*;
import java.io.*;

public class CarParkState{
	
	private double carParkCapacity;
	private double carsWaiting = 0;
	private boolean accessing=false; // true a thread has a lock, false otherwise
	private int threadsWaiting=0; // number of waiting writers

	
	CarParkState(double carPark) {
		//set shared car park capacity variable to 0
		carParkCapacity = carPark;
	}
	
	public synchronized void acquireLock() throws InterruptedException{
		//Attempt to acquire a lock
		Thread me = Thread.currentThread(); // get a ref to the current thread
		System.out.println("");
	    System.out.println(me.getName()+" is attempting to acquire a lock!");	
	    ++threadsWaiting;
		
	    while (accessing) {  // while someone else is accessing or threadsWaiting > 0
	    	System.out.println(me.getName()+" waiting to get a lock as someone else is accessing...");
		    //wait for the lock to be released - see releaseLock() below
		    wait();
	    }
		// nobody has got a lock so get one
	    --threadsWaiting;
		accessing = true;
		System.out.println(me.getName()+" got a lock!"); 
	}

	public synchronized void releaseLock() {
		//release the lock when thread is completed and tell everyone
		accessing = false;
		notifyAll();
		Thread me = Thread.currentThread(); // get a ref to the current thread
		System.out.println(me.getName()+" released a lock!");
		
	}
	
	public synchronized String processInput(String myThreadName, String theInput) {
		
		System.out.println(myThreadName + " received "+ theInput);
    	String theOutput = null;
    	// verify what the client said
    	if (theInput.equalsIgnoreCase("Enter car park")||theInput.equalsIgnoreCase("Exit car park")) {
    		// if the client enters correct request, check which client sent the request
    		if (myThreadName.equals("CPServerThread1")) {
    			//only add a car to car park if it is less then 5,	
    			if (carParkCapacity<5) {
    				carParkCapacity = carParkCapacity + 1;
    			}
    			else{
    			//otherwise, make the car wait	
    				carsWaiting = carsWaiting + 1;
    			}
    			//let the client know how many cars are in the car park and waiting
    			System.out.println(myThreadName + " There are " + carParkCapacity + " cars in the car park.");
    			theOutput = "a car has entered. Car park capacity now: " + carParkCapacity + " | Cars Waiting: " + carsWaiting;
    				
    		}
    		else if (myThreadName.equals("CPServerThread2")) {
    				
    			if (carParkCapacity<5) {
    				carParkCapacity = carParkCapacity + 1;
    			}
    			else{
    				carsWaiting = carsWaiting + 1;
    			}
    			System.out.println(myThreadName + " There are " + carParkCapacity + " cars in the car park.");
    			theOutput = "a car has entered. Car park capacity now: " + carParkCapacity + " | Cars Waiting: " + carsWaiting;

    		}
       		else if (myThreadName.equals("CPServerThread3")) {
       			//for the exit clients, minus a car from car park	
       			carParkCapacity = carParkCapacity - 1;
					
				if (carsWaiting>0) {
					//minus a car from waiting and add a car to car park
    				carsWaiting = carsWaiting - 1;
    				carParkCapacity = carParkCapacity + 1;
				}
    			//let the client know how many cars are in the car park and waiting
       			System.out.println(myThreadName + " There are " + carParkCapacity + " cars in the car park.");
    			theOutput = "a car has exited. Car park capacity now: " + carParkCapacity + " | Cars Waiting: " + carsWaiting;

       		}
       		else if (myThreadName.equals("CPServerThread4")) {
    			
       			carParkCapacity = carParkCapacity - 1;
					
				if (carsWaiting>0) {
    				carsWaiting = carsWaiting - 1;
    				carParkCapacity = carParkCapacity + 1;
				}
    			System.out.println(myThreadName + " There are " + carParkCapacity + " cars in the car park.");
    			theOutput = "a car has exited. Car park capacity now: " + carParkCapacity + " | Cars Waiting: " + carsWaiting;

       		}
       		else {
       			System.out.println("DO NOT RECOGNISE THREAD CALL!");
       		}
    		
    	}   		
    	else { 
    		//incorrect request
    		theOutput = myThreadName + " received incorrect request - only understand \"Enter car park or Exit car park\"";
		}
 
     	//Return the output message to the CPServer
    	String outputCap = theOutput.substring(0,1).toUpperCase();
    	String output = outputCap + theOutput.substring(1);
    	System.out.println(output);
    	return theOutput;
    	
    }	
}

