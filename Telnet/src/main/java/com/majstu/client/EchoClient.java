package com.majstu.client;

import java.io.*;
import java.net.*;
 
public class EchoClient {
    public static void main(String[] args) throws IOException {

        String hostName = InetAddress.getLocalHost().getHostName();
        int portNumber = 27;
 
        try {
            //Connecting to server
            Socket socket = new Socket(hostName, portNumber);

            //Declaring streams
            PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
            String userInput = "";

            String inputFromServ="";
          while((userInput != "quit")) {

              while (!inputFromServ.endsWith(".")) {
                  inputFromServ+=Character.toString((char)in.read());
//                  System.out.print(Character.toChars(in.read()));
              }
              System.out.println(inputFromServ);


              if ((userInput = stdIn.readLine()) != null) {
                  out.println(userInput);
                  // System.out.println("echo: " + in.readLine());
              }
          }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}