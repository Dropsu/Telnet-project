package com.majstu.client;

import com.majstu.TelnetCommunicator;


import java.io.*;
import java.net.*;
public class Client extends TelnetCommunicator {








    public static void main(String[] args) throws IOException {

      String hostName = InetAddress.getLocalHost().getHostName();
    //	String hostName =  "25.62.197.160";
        int portNumber = 23;

        try {

          
            //Connecting to server
            Socket socket = new Socket(hostName, portNumber);

            //Declaring streams
            out =
                    new PrintWriter(socket.getOutputStream(), true);
            in =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn =
                    new BufferedReader(
                            new InputStreamReader(System.in));


            String userInput = "";
            String HandShake= "";
           
            
            HandShake = waitForInput(HandShake, in);
            System.out.println(HandShake);


            while(!socket.isClosed()) {


                if ((userInput = stdIn.readLine()) != null) {
                    
                		if (userInput.equals("/quit")) {
                			socket.close();
                		}
                		
                    sendCharByChar(userInput, out);
                    String inputFromServer = "";
                    inputFromServer = waitForInput(inputFromServer, in);
                    System.out.println(inputFromServer);
                }

                
               
            }
        }

        catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}