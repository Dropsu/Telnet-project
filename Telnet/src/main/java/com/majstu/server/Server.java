package com.majstu.server;

import com.majstu.TelnetCommunicator;

import java.net.*;
import java.io.*;
 
public class Server extends TelnetCommunicator {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static String clientInput;
    private static void waitForConnection () throws IOException {
        serverSocket =
                new ServerSocket(23);
        clientSocket = serverSocket.accept();
        out =
                new PrintWriter(clientSocket.getOutputStream(), true);

        in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
    }


    public static void main(String[] args) throws Exception {

        try {

            waitForConnection();
            
            
            sendCharByChar("Welcome to YourTextFiles Telnet\nCommands:\n/help - list commands\n/list - list files\n/open <filename> - open file\n/quit - exit",out);
           

            while (clientSocket.isConnected()) {
                clientInput = "";
                clientInput = waitForInput(clientInput, in);
              
                if (clientInput.equals("/help")){
                    sendCharByChar("Commands:\n/help - list commands\n/list - list files\n/open <filename> - open file\n/quit - exit\n/add <filename> - create file\n/delete <filename> - delete file\n",out);
                }
                
                else if(clientInput.equals("/list")) {
                    sendCharByChar(FileService.list(),out);
                }
               
               
                else if( clientInput.length() >= 7 && clientInput.substring(0,7).equals("/delete")) {
            	  
            	  sendCharByChar(FileService.delete(clientInput.substring(8)),out);
            	 
       	   
               			} 
                
                
                else if(clientInput.length() >= 5 && clientInput.substring(0,5).equals("/open")) {
                    sendCharByChar(FileService.read(clientInput.substring(6)),out);
                }
                
                
                else if(clientInput.length() >= 4 && clientInput.substring(0,4).equals("/add")){
                
                	sendCharByChar(FileService.add(clientInput.substring(5)), out);
                
                }
                
                
                else if(clientInput.equals("/quit")) {
                    sendCharByChar("Logged out",out);
                    clientSocket.close();
                }
                else {
                	sendCharByChar("Invalid command",out);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}