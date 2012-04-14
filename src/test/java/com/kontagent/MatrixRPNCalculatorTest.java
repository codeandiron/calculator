package com.kontagent;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;


public class MatrixRPNCalculatorTest {
	
	@Test
	public void testBasic(){
		String csvString = InputReader.getInput("../../basic.csv");
		Assert.assertNotNull(csvString);
		
		HashMap<String,Node> result = InputReader.convertCSVStringToHashMap(csvString);
		Assert.assertNotNull(result);
	}
}
