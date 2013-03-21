package com.calculator;


class MatrixRPNCalculator {
	public static void main(String[] args) throws InvalidNodeException {
		
		if(args.length != 1)
	    {
	        System.out.println("Usage: java MatrixRPNCalculator csvInputFileName");
	        System.exit(0);
	    }
		
		String inputCsv = IOUtils.getInput(args[0]);
		
		//Create a new spreadsheet from input, and validate contents
		SpreadSheet spreadSheet = new SpreadSheet(inputCsv);
		
		//Perform the actual calculations inside the spreadsheet
		spreadSheet.processSpreadsheet();
		
		//Send output of spreadsheet after processing
		IOUtils.sendOutput(spreadSheet.getAsCsv());
	}
}
