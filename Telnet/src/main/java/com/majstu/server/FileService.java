package com.majstu.server;

import java.io.*;

public class FileService {
 
 
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

    public static String read(String filename) {

        BufferedReader br = null;

        String result = "";

        try {

            String sCurrentLine;

            br = new BufferedReader(new java.io.FileReader(".\\Telnet\\src\\main\\resources\\"+filename));

            while ((sCurrentLine = br.readLine()) != null) {
                result+=sCurrentLine+"\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }
}