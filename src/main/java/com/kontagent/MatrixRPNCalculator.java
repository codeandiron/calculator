package com.kontagent;


class MatrixRPNCalculator {
	public static void main(String[] args) throws InvalidNodeException {
		
		String inputString = InputReader.getInput("../../cellref.csv");
		
		SpreadSheet spreadSheet = new SpreadSheet(inputString);
		
		InputReader.sendOutput(spreadSheet.getAsCSV());
		
		spreadSheet.processSpreadsheet();
		
		InputReader.sendOutput(spreadSheet.getAsCSV());
	}
}