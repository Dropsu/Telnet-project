package com.majstu.server;

import java.net.*;
import java.io.*;
 
public class EchoServer {
    public static void main(String[] args) throws IOException {

        int portNumber = 27;
        boolean justInitiated = true;
         
        try {
            ServerSocket serverSocket =
                new ServerSocket(27);
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true); 
            out.println("Telnet Connection Established\n"
            		+ "/help - for help\n"
            		+ "/quit - for quit");
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

            
            
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {	
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}