package com.kontagent;

import java.util.HashMap;

class MatrixRPNCalculator {
	public static void main(String[] args) {
		
		
		//Input of two file names
		
		String inputString = InputReader.getInput("../../basic.csv");
		
		HashMap<String,Node> input = InputReader.convertCSVStringToHashMap(inputString);
	
		
		//Start with A1? pass into something that decides what it is?
		//3 types are simple, cell referencing, operating
			//if it's a cell reference, value.process, recursive?, key.process, return
			//if it's an operation, key.process, return
			//if it's a simple number, return
		//Once you have the result set, build up the output and dump it.
	}
	
	private static HashMap<String,Node> processMatrix(HashMap<String, Node> inputMatrix){
			
		
		return inputMatrix;
	}
	
	//Potential for recursion if node contains reference to another node.
	private Node processNode(Node currentNode){
		switch (currentNode.getType()){
			case SIMPLE_VALUE:
				return currentNode;
		}
		return null;
	}

}