package com.majstu.server;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;


public class FileService {
 
	
 
    public static String list() throws IOException {
    
    	String result = "";     
        String dirPathname = ".\\Telnet\\src\\main\\resources";      
        File directory = new File(dirPathname);

	        if(!directory.isDirectory()){
	            System.out.println(dirPathname + " is not directory");
	        }
	 
 
	       File[] directories = new File(dirPathname).listFiles();
	       for (File file : directories)
	    	   result += file.getName()+"\n";
      
	  return result;
    }

    
    public static String read(String filename) {

        BufferedReader br = null;

        String result = "";

        try {

            String sCurrentLine;
            File directory = new File(".\\Telnet\\src\\main\\resources\\"+filename+".txt");
            if (directory.isFile()){
	            br = new BufferedReader(new java.io.FileReader(".\\src\\main\\resources\\"+filename+".txt"));
	           
	            while ((sCurrentLine = br.readLine()) != null) {
	                result+=sCurrentLine+"\n";}
	            }
            else result = filename +" is not a file";
            
            

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
	
    public static String delete(String filename) throws IOException {
    String result;
   	 String dirPathname = ".\\Telnet\\src\\main\\resources\\"+filename+".txt";
   	 File directory = new File(dirPathname);
   	 if(directory.isFile()){
   	 directory.delete();
   	 result = "File "+filename+" deleted.";
   	 }
   	 else result = "There is no file: "+filename;
   	 
   	return result;
   }
    
    public static String add(String filename) throws IOException {
	    	String result = "";
			 String dirPathname = ".\\Telnet\\src\\main\\resources\\"+filename+".txt";
			
			try{ 
					File directory = new File(dirPathname);
					if(!directory.isFile()){
						directory.createNewFile();
						FileWriter fw = new FileWriter(directory.getAbsoluteFile());
			            BufferedWriter bw = new BufferedWriter(fw);
			            bw.write("@File genereted by Telnet@");       
			            bw.close();	
			            result = "File: " +filename+" properly created ";}
					else{
						result = "File "+filename+" already exists!";
					}
			} catch(IOException e)
					{
					result = "Error! File cannot be created!";
				    System.out.println(e);
			}
	           		
	        return result;    
		}
    
}