package com.majstu;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Created by Damian on 15.11.2016.
 */
public class TelnetCommunicator {

    protected static PrintWriter out;

    protected static BufferedReader in;

    private static String cutCross(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length()-1)=='#') {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

     protected static void sendCharByChar (String message,PrintWriter out ) {
        for(char a: message.toCharArray()){
            out.print(a);
            out.flush();
        }
        out.print("#");
        out.flush();
    }

    static protected String waitForInput (String input, BufferedReader in ) throws Exception {
        while (!input.endsWith("#")) {
            input += Character.toString((char) in.read());
        }

        return cutCross(input);
    }

}
