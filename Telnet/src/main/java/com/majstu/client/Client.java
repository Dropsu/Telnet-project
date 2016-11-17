package com.majstu.client;

import com.majstu.TelnetCommunicator;


import java.io.*;
import java.net.*;
public class Client extends TelnetCommunicator {








    public static void main(String[] args) throws IOException {

        String hostName = InetAddress.getLocalHost().getHostName();
        int portNumber = 23;

        try {

            System.out.println("To quit Client type: 'quit.'\n");
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
            inputFromServ = waitForInput(inputFromServ, in);
            System.out.println(inputFromServ);


            while((userInput != "quit.")) {

                // accessible methods: /help. /quit. /list. /open.

                if ((userInput = stdIn.readLine()) != null) {
                    if (userInput.equals("quit.")) {
                        break;
                    }
                    sendCharByChar(userInput, out);
                }

                String inputFromServ2 = "";
                inputFromServ2 = waitForInput(inputFromServ2, in);

                System.out.println(inputFromServ2);
            }
        }

        catch (UnknownHostException e) {
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