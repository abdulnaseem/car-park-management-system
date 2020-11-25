
import java.net.*;
import java.io.*;


public class CPServerThread extends Thread {
	
  private Socket CPSocket = null;
  private CarParkState sharedCPCapacity;
  private String myCPServerThreadName;
   
  //Setup the thread
  public CPServerThread(Socket CPSocket, String CPServerThreadName, CarParkState SharedObject) {
	
	  this.CPSocket = CPSocket;
	  sharedCPCapacity = SharedObject;
	  myCPServerThreadName = CPServerThreadName;
  }

  public void run() {
    try {
      System.out.println(myCPServerThreadName + "initialising.");
      PrintWriter out = new PrintWriter(CPSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(CPSocket.getInputStream()));
      String inputLine, outputLine;

      while ((inputLine = in.readLine()) != null) {
    	  // Get a lock first
    	  try { 
    		  sharedCPCapacity.acquireLock();  
    		  outputLine = sharedCPCapacity.processInput(myCPServerThreadName, inputLine);
    		  out.println(outputLine);
    		  sharedCPCapacity.releaseLock();  
    	  } 
    	  catch(InterruptedException e) {
    		  System.err.println("Failed to get lock when reading:"+ e);
    	  }
      }

      out.close();
      in.close();
      CPSocket.close();

    } 
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}