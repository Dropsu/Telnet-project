package com.majstu.server;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;


public class FileService {

    private static String dirPathname = ".\\Telnet\\src\\main\\resources";
    private static ArrayList<String> subDirs = new ArrayList<String>();

    public FileService () {
        subDirs.add(0,"");
    }
 
	
 
    public static String list() throws IOException {
    
    	String result = "";     
        File directory = new File(dirPathname+subDirsToString());

	        if(!directory.isDirectory()){
	            System.out.println(dirPathname+subDirsToString() + " is not directory");
	        }
	 
 
	       File[] directories = new File(dirPathname+subDirsToString()).listFiles();
	       for (File file : directories)
	    	   result += file.getName()+"\n";
      
	  return result;
    }

    
    public static String read(String filename) {

        BufferedReader br = null;

        String result = "";

        try {

            String sCurrentLine;
            File directory = new File(dirPathname+subDirsToString()+"\\"+filename);
            if (directory.isFile()){
	            br = new BufferedReader(new java.io.FileReader(dirPathname+subDirsToString()+"\\"+filename));

	            while ((sCurrentLine = br.readLine()) != null) {
	                result+=sCurrentLine+"\n";}
	            }
            else result = filename +" is not a file";

            if (result.equals("")){
                result = "<file is empty>";
            };



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
   	 File directory = new File(dirPathname+subDirsToString()+"\\"+filename);
   	 if(directory.isFile()||directory.isDirectory()){
   	 directory.delete();
   	 result = filename+" deleted.";
   	 }
   	 else result = "There is no file: "+filename;

   	return result;
   }

   public static String changeDir (String subdir) {
       File directory = new File(dirPathname+subDirsToString()+"\\"+subdir);
       if(!directory.isDirectory()){
           return "No such directory";
       }
       if(subdir.equals("..")){
           subDirs.remove(subDirs.size()-1);
       } else {
           subDirs.add(subdir);
       }
       if (subDirsToString().trim().length() == 0){
           return "You are into main directory";
       }
       return "You are into "+subDirsToString();
   }

   private static String subDirsToString() {
       String result = "";
       for (String a:subDirs) {
           if (a=="")
               continue;
           result+="\\"+a;
       }
       return result;
   }
    
    public static String add(String filename) throws IOException {
    	
    	
    	
	    	String result = "";

			try{ 
					File directory = new File(dirPathname+subDirsToString()+"\\"+filename);
					if(!directory.isFile()){
						directory.createNewFile();
						FileWriter fw = new FileWriter(directory.getAbsoluteFile());
			            BufferedWriter bw = new BufferedWriter(fw);
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

		public static String createDir (String dirName) {
            File file = new File(dirPathname+subDirsToString()+"\\"+dirName);
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            return "<Directory "+dirName+" Created>";
        }
    
    
    public static String edit(String filename, String message) throws IOException {
    	
    	
    String result="";
    try{
		File directory = new File(dirPathname+subDirsToString()+"\\"+filename);
		if(directory.isFile()){
				
			FileWriter fw = new FileWriter(directory.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            bw.write(message);
            out.close();
            bw.close();	
            result = "File: " +filename+" edited.";}
		else{
			result = "File "+filename+" doesn't exist!";
		}
} catch(IOException e)
		{
		result = "Error! File cannot be edited!";
	    System.out.println(e);
}
    
    	return result;
    }
    
    
}