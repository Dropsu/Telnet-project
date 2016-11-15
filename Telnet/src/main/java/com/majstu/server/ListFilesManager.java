package com.majstu.server;

import java.io.*;

public class ListFilesManager {
 
 
    public static String list() throws IOException {
 
        String dirPathname = ".\\Telnet\\src\\main\\resources";
       
        File directory = new File(dirPathname);

 
        if(!directory.isDirectory()){
 
            System.out.println(dirPathname + " is not directory");
 
        }
 

        String result = "";

        File[] files = directory.listFiles();
 
        for (File file : files) {
 
            if(file.isFile()){
 
                result+=file.getName()+"\n";
            }
 
        }
        return result;
    }
}