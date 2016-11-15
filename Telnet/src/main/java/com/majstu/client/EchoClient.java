package com.majstu.client;

import com.majstu.TelnetCommunicator;

import java.io.*;
import java.net.*;
 
public class EchoClient extends TelnetCommunicator {
    public static void main(String[] args) throws IOException {

        String hostName = InetAddress.getLocalHost().getHostName();
        int portNumber = 27;
 
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

            String inputFromServ="";
          while((userInput != "quit")) {

              inputFromServ = waitForInput(inputFromServ, in);
              System.out.println(inputFromServ);


              if ((userInput = stdIn.readLine()) != null) {
                  if (userInput.equals("quit")) {
                      break;
                  }
                  sendCharByChar(userInput, out);
              }

              String inputFromServ2 = "";
              inputFromServ2 = waitForInput(inputFromServ2, in);

              System.out.println(inputFromServ2);

              if ((userInput = stdIn.readLine()) != null) {
                  if (userInput.equals("quit")) {
                      break;
                  }
                  sendCharByChar(userInput, out);
              }

              String inputFromServ3 = "";
              inputFromServ3 = waitForInput(inputFromServ3, in);

              System.out.println(inputFromServ3);
          }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}