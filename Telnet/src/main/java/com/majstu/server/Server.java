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
                    sendCharByChar("Commands:\n/help - list commands\n/list - list files\n/open <filename> - open file\n/quit - exit",out);
                }
                if(clientInput.equals("/list")) {
                    sendCharByChar(FileService.list(),out);
                }
                if(clientInput.substring(0,5).equals("/open")) {
                    sendCharByChar(FileService.read(clientInput.substring(6)),out);
                }
                if(clientInput.equals("/quit")) {
                    sendCharByChar("Logged out",out);
                    clientSocket.close();
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}