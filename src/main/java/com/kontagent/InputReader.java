package com.kontagent;

import java.io.InputStream;
import java.util.Scanner;

public final class InputReader {
	
	public static String getInput(String fileName){		
		InputStream fileInput = InputReader.class.getResourceAsStream(fileName);
		return new Scanner(fileInput).useDelimiter("\\A").next();
	}
	
	public static void sendOutput(String csvString){
		System.out.println(csvString);
	}
}
