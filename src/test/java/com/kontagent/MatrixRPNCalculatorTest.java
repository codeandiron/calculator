package com.kontagent;

import junit.framework.Assert;

import org.junit.Test;


public class MatrixRPNCalculatorTest {
	
	@Test
	public void testBasicFile(){
		String csvString = InputReader.getInput("../../basic.csv");
		Assert.assertNotNull(csvString);
		
		SpreadSheet spreadSheet = new SpreadSheet(csvString);
		Assert.assertNotNull(spreadSheet);
	}
	
	@Test
	public void testCellRefsFile() throws InvalidNodeException{
		String csvString = InputReader.getInput("../../cellref.csv");
		Assert.assertNotNull(csvString);
		
		SpreadSheet spreadSheet = new SpreadSheet(csvString);
		spreadSheet.processSpreadsheet();
		
		Assert.assertNotNull(spreadSheet);
	}
	
	@Test
	public void testOperationsFile(){
		String csvString = InputReader.getInput("../../operations.csv");
		Assert.assertNotNull(csvString);
		
		SpreadSheet spreadSheet = new SpreadSheet(csvString);
		Assert.assertNotNull(spreadSheet);
	}
}
