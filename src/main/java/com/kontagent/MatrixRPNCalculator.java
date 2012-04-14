package com.kontagent;

import java.util.HashMap;

class MatrixRPNCalculator {
	public static void main(String[] args) {
		
		
		//Input of two file names
		
		String inputString = InputReader.getInput(args[0]);
		
		HashMap<String,Node> input = InputReader.convertCSVStringToHashMap(inputString);
		
		System.out.println(input.get("A1").getType());

		//Start with A1? pass into something that decides what it is?
		//3 types are simple, cell referencing, operating
			//if it's a cell reference, value.process, recursive?, key.process, return
			//if it's an operation, key.process, return
			//if it's a simple number, return
		//Once you have the result set, build up the output and dump it.
	}

}