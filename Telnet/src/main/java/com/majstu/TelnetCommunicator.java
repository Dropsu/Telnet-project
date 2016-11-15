package com.majstu;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Created by Damian on 15.11.2016.
 */
public class TelnetCommunicator {

    protected static PrintWriter out;

    protected static BufferedReader in;

     protected static void sendCharByChar (String message,PrintWriter out ) {
        for(char a: message.toCharArray()){
            out.print(a);
            out.flush();
        }
    }

    static protected String waitForInput (String input, BufferedReader in ) throws Exception {
        while (!input.endsWith(".")) {
            input += Character.toString((char) in.read());
        }
        return input;
    }



}
