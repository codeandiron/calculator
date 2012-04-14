package com.kontagent;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Scanner;

public class InputReader {
	
	public static String getInput(String fileName){
		String resultString = null;
		
		InputStream fileInput = InputReader.class.getResourceAsStream(fileName);
		StringWriter writer = new StringWriter();
		
		return new Scanner(fileInput).useDelimiter("\\A").next();
	}
}
