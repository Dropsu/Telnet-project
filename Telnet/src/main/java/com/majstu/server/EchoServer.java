package com.majstu.server;

import com.majstu.TelnetCommunicator;

import java.net.*;
import java.io.*;
 
public class EchoServer extends TelnetCommunicator {


    public static void main(String[] args) throws IOException {

        int portNumber = 27;

        try {
            ServerSocket serverSocket =
                new ServerSocket(27);
            Socket clientSocket = serverSocket.accept();
             out =
                new PrintWriter(clientSocket.getOutputStream(), true);

            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            sendCharByChar("Welcome to our Telnet Bro .", out);

            String clientInput = "";

            clientInput = waitForInput(clientInput, in);

            sendCharByChar(clientInput, out);


        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}