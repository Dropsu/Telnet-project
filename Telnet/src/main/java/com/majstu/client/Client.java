package com.majstu.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {

	 private BufferedReader in;
	 private PrintWriter out;
		
	 // todo:	graphical user interface
	
	 String message;
	 String addressIP;
	 
	 
	public Client(){
	
		gotMessage();
	
		
	}
	
	
	
	
	
	
	public void gotMessage() {
	
		try {
            message = in.readLine();
            if (message == null || message.equals("")) {
                  System.exit(0);
              }
        } catch (IOException ex) {
               message = "Error: " + ex;
        }
		
	}






	public void connectToServer() throws IOException {
		
		System.out.println("Podaj adres IP serwera");
		addressIP = in.readLine();
        Socket socket = new Socket(addressIP, 992);

        
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

		
		
		
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		Client client = new Client();
		
		 client.connectToServer();
	}
	
}
