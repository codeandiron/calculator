package com.kontagent;


class MatrixRPNCalculator {
	public static void main(String[] args) throws InvalidNodeException {
		
		
		//Input of two file names
		
		String inputString = InputReader.getInput("../../operations.csv");
		
		SpreadSheet spreadSheet = new SpreadSheet(inputString);
		
		spreadSheet.processSpreadsheet();
		
	}
}