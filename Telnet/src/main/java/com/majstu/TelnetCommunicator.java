package com.majstu;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Created by Damian on 15.11.2016.
 */
public class TelnetCommunicator {

    protected static String endChar;

    protected static PrintWriter out;

    protected static BufferedReader in;

    private static String cutCross(String str) {
        endChar=Character.toString ((char) 255);
        if (str != null && str.length() > 0 && str.charAt(str.length()-1)==endChar.toCharArray()[0]) {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

     protected static void sendCharByChar (String message,PrintWriter out ) {
         endChar=Character.toString ((char) 255);
    
         for(char a: message.toCharArray()){   
            out.print(a);
            out.flush();
        }
        out.print(endChar);
        out.flush();
    }

     
     protected static void sendCharByChar (String filename, String message, PrintWriter out ) {
         endChar=Character.toString ((char) 255);
    
         for(char a: message.toCharArray()){   
            out.print(a);
            out.flush();
        }
        out.print(endChar);
        out.flush();
    }
     
    static protected String waitForInput (String input, BufferedReader in ) throws Exception {
        endChar=Character.toString ((char) 255);
        while (!input.endsWith(endChar)) {
            input += Character.toString((char) in.read());
        }

        return cutCross(input);
    }

}