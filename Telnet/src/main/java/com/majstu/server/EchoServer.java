package com.majstu.server;

import com.majstu.TelnetCommunicator;

import java.net.*;
import java.io.*;
 
public class EchoServer extends TelnetCommunicator {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static String clientInput;

    private static void waitForConnection () throws IOException {
        serverSocket =
                new ServerSocket(27);
        clientSocket = serverSocket.accept();
        out =
                new PrintWriter(clientSocket.getOutputStream(), true);

        in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
    }


    public static void main(String[] args) throws Exception {

        try {
            waitForConnection();

            sendCharByChar("Welcome to our Telnet Bro\nCommands:\n/help - list commands\n/quit - exit\n/list - list files\n/open - open file",out);


            while (clientSocket.isConnected()) {
                clientInput = "";
                clientInput = waitForInput(clientInput, in);
                if (clientInput.equals("/help#")){
                    sendCharByChar("Commands:\n/help - list commands\n/quit - exit\n/list - list files\n/open - open file",out);
                }
                if(clientInput.equals("/list#")) {
                    sendCharByChar(ListFilesManager.list(),out);
                }
                if(clientInput.equals("/open#")) {
                    sendCharByChar(TelnetFileReader.read("tekst.txt"),out);
                }
                sendCharByChar(clientInput, out);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}