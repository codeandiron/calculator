package com.kontagent;

import junit.framework.Assert;

import org.junit.Test;


public class MatrixRPNCalculatorTest {
	
	@Test
	public void testBasic(){
		String csv = InputReader.getInput("../../basic.csv");
		Assert.assertNotNull(csv);
	}
}
