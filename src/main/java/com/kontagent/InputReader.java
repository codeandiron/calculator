package com.kontagent;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class InputReader {
	
	public static String getInput(String fileName){		
		InputStream fileInput = InputReader.class.getResourceAsStream(fileName);
		return new Scanner(fileInput).useDelimiter("\\A").next();
	}
	
	public static HashMap<String, String> convertCSVStringToHashMap (String inputString){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		//Split off CRLF into lines
		String[] lines = inputString.split("\\r?\\n");
		
		//B3
		//B is the column number
		//3 is the row number
	
		for (int i=0; i<lines.length; i++){
			//split lines into operands
			lines[i].split(",\\s*");
		}
		
		return resultMap;
	}
}
