import java.io.*;
import java.net.*;

public class CPEntryClient1 {
    public static void main(String[] args) throws IOException {

        // Set up the socket, in and out variables
        Socket CPClientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int CPSocketNumber = 4444;
        String CPServerName = "localhost";
        String CPClientID = "CPEntryClient1";

        try {
        	CPClientSocket = new Socket(CPServerName, CPSocketNumber);
            out = new PrintWriter(CPClientSocket.getOutputStream(), true);
            //send out information to the server
            in = new BufferedReader(new InputStreamReader(CPClientSocket.getInputStream()));
			//read what the server has sent
        } 
        catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost ");
            System.exit(1);
        } 
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+ CPSocketNumber);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        //read the user key input
        String fromServer;
        String fromUser;

        System.out.println(" ");
        System.out.println("Initialised " + CPClientID + " client and IO connections");
        System.out.println("For a car to enter the car park, please type 'enter car park'");

        while (true) {
            
            fromUser = stdIn.readLine();
            if (fromUser != null) {
            	//check if the user has typed anything
                System.out.println(" ");
                System.out.println(CPClientID + " sending " + fromUser + " to CPServer");
                out.println(fromUser);
            }
            fromServer = in.readLine();
            System.out.println(CPClientID + " acknowledged " + fromServer + " from CPServer.");
           
        }
            
    }
}
