package com.kontagent;


class MatrixRPNCalculator {
	public static void main(String[] args) throws InvalidNodeException {
		
		
		
		String inputString = InputReader.getInput(args[0]);
		
		SpreadSheet spreadSheet = new SpreadSheet(inputString);
		
		spreadSheet.processSpreadsheet();
		
		InputReader.sendOutput(spreadSheet.getAsCSV());
	}
}