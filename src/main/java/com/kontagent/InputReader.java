package com.kontagent;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class InputReader {
	
	public static String getInput(String fileName){		
		InputStream fileInput = InputReader.class.getResourceAsStream(fileName);
		return new Scanner(fileInput).useDelimiter("\\A").next();
	}
	
	public static HashMap<String, Node> convertCSVStringToHashMap (String inputString){
		HashMap<String, Node> resultMap = new HashMap<String, Node>();
		
		//Split off CRLF into lines
		String[] rows = inputString.split("\\r?\\n");
		
		try{
			for (int rowNumber=1; rowNumber <= rows.length; rowNumber++){
				String currentRow = rows[rowNumber - 1];
				
				String[] columns = currentRow.split(",\\s*");
				
				
				for(int columnNumber=1; columnNumber<=columns.length; columnNumber++){

					String nodeContent = columns[columnNumber - 1];
					
					Node node = new Node(nodeContent);
					
					resultMap.put(getNodeKey(columnNumber, rowNumber), node);
				}
			}
		}catch(InvalidNodeException e){
			System.out.println("Failed to parse all of the nodes into one of the three types");
		}
		
		return resultMap;
	}

	private static String getNodeKey(int columnNumber, int rowNumber) {
		
		return (char)(columnNumber + 64) + String.valueOf(rowNumber);
	}
}
