import java.net.*;
import java.io.*;

public class CPServer {
  public static void main(String[] args) throws IOException {

	ServerSocket CPServerSocket = null;
    boolean listening = true;
    String CPServerName = "CPServer";
    int CPServerNumber = 4444;
    
    double carParkCapacity = 0;//set default 0 as the number of cars in the car park

    //Create the shared car park capacity object in the global scope...
    CarParkState sharedCPCapacity = new CarParkState(carParkCapacity);
        
    try {
    	//create server socket
    	CPServerSocket = new ServerSocket(CPServerNumber);
    } 
    catch (IOException e) {
    	System.err.println("Could not start " + CPServerName + " specified port.");
    	System.exit(-1);
    }
    System.out.println(CPServerName + " started");

    //Ensure the following is executed in this order!
    while (listening){
      new CPServerThread(CPServerSocket.accept(), "CPServerThread1", sharedCPCapacity).start();
      new CPServerThread(CPServerSocket.accept(), "CPServerThread2", sharedCPCapacity).start();
      new CPServerThread(CPServerSocket.accept(), "CPServerThread3", sharedCPCapacity).start();
      new CPServerThread(CPServerSocket.accept(), "CPServerThread4", sharedCPCapacity).start();
      System.out.println("New " + CPServerName + " thread started.");
    }
    CPServerSocket.close();
  }
}