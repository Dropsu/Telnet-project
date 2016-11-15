package com.majstu.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TelnetFileReader {

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