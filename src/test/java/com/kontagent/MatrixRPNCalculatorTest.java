package com.kontagent;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;


public class MatrixRPNCalculatorTest {
	
	@Test
	public void testBasicFile(){
		String csvString = InputReader.getInput("../../basic.csv");
		Assert.assertNotNull(csvString);
		
		HashMap<String,Node> result = InputReader.convertCSVStringToHashMap(csvString);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testCellRefsFile(){
		String csvString = InputReader.getInput("../../cellref.csv");
		Assert.assertNotNull(csvString);
		
		HashMap<String,Node> result = InputReader.convertCSVStringToHashMap(csvString);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testOperationsFile(){
		String csvString = InputReader.getInput("../../operations.csv");
		Assert.assertNotNull(csvString);
		
		HashMap<String,Node> result = InputReader.convertCSVStringToHashMap(csvString);
		Assert.assertNotNull(result);
	}
}
