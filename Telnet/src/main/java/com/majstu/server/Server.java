package com.majstu.server;

import com.majstu.TelnetCommunicator;

import java.net.*;
import java.io.*;
 
public class Server extends TelnetCommunicator {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static String clientInput;
    private static String commands = "Commands:\n/help - list commands\n/list - list files\n" +
            "/open <filename> - open file\n/quit - exit\n/add <filename> - create file\n/overwrite <filename> - overwrite file\n" +
            "/delete <filename|dirName> - delete file or directory\n/createDir <dirName> - creates directory in working directory\n" +
            "/changeDir <dirName> / <..> - jumps into directory <dirName> or <..> goes back one direcory";
    private static void waitForConnection () throws IOException {
        serverSocket =
                new ServerSocket(23);
        clientSocket = serverSocket.accept();
        System.out.println("Client has connected");
        out =
                new PrintWriter(clientSocket.getOutputStream(), true);

        in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
    }


    public static void main(String[] args) throws Exception {

        try {

            waitForConnection();

            FileService fileService = new FileService();


            sendCharByChar("Welcome to YourTextFiles Telnet\n"+commands,out);


            while (clientSocket.isConnected()) {
                clientInput = "";
                clientInput = waitForInput(clientInput, in);

                if (clientInput.equals("/help")){
                    sendCharByChar(commands,out);
                }

                else if(clientInput.equals("/list")) {
                    sendCharByChar(FileService.list(),out);
                }


                else if( clientInput.length() >= 8 && clientInput.substring(0,7).equals("/delete")) {
                    if (clientInput.substring(8).trim().length() == 0){
                        sendCharByChar("<Empty name>",out);
                    } else {
                        sendCharByChar(FileService.delete(clientInput.substring(8)),out);
                        }

                    }

                else if(clientInput.length() >= 11 && clientInput.substring(0,10).equals("/changeDir")){
                    if (clientInput.substring(11).trim().length() == 0) {
                        sendCharByChar("<Empty name>", out);
                    } else {
                        sendCharByChar(FileService.changeDir(clientInput.substring(11)),out);
                    }
                }


                else if(clientInput.length() >= 6 && clientInput.substring(0,5).equals("/open")) {
                    if (clientInput.substring(6).trim().length() == 0){
                        sendCharByChar("<Empty name>",out);
                    } else {
                        sendCharByChar(FileService.read(clientInput.substring(6)), out);
                    }
                }

                else if(clientInput.length() >= 11 && clientInput.substring(0,10).equals("/overwrite")){
                    if (clientInput.substring(11).trim().length() == 0) {
                        sendCharByChar("<Empty name>", out);
                    } else {
                        sendCharByChar("Write your text:" ,out);
                        String fname = clientInput.substring(11);
                        clientInput = "";
                        clientInput = waitForInput(clientInput, in);
                        sendCharByChar(FileService.edit(fname, clientInput), out);
                    }
                } else if (clientInput.length() >= 11 && clientInput.substring(0,10).equals("/createDir")){
                    if (clientInput.substring(11).trim().length() == 0) {
                        sendCharByChar("<Empty name>", out);
                    }else {
                        sendCharByChar(FileService.createDir(clientInput.substring(11)), out);
                    }
                }

                else if(clientInput.length() >= 5 && clientInput.substring(0,4).equals("/add")){
                    if (clientInput.substring(5).trim().length() == 0){
                        sendCharByChar("<Empty name>",out);
                    } else {
                        sendCharByChar(FileService.add(clientInput.substring(5)), out);
                    }

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
            sendCharByChar(e.getMessage()+"\nCRITICAL\nCONNECTION CLOSED",out);
            System.err.println(e.getMessage());
        }
    }
}