package com.kontagent;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SpreadSheetTest {
	
	private static final String basic = "1, 2, 3\n"+
										"4, 5, 6\n"+
										"7, 8, 9";
	
	private static final String basicResult = 	"1, 2, 3\n"+
												"4, 5, 6\n"+
												"7, 8, 9";
	
	private static final String operation = "1, 2, 3, 4\n" +
											"=5 1 +, =6 2 +, =7 3 -, =8 2 -\n" +
											"=9 3.5 *, =10 5 *, =11 2 /, =12 4 /";
	
	private static final String operationResult = 	"1, 2, 3, 4\n" +
													"6.0, 8.0, 4.0, 6.0\n" +
													"31.5, 50.0, 5.5, 3.0";

	private static final String cellRef = 	"1, 2, 3\n" +
											"=A1, =B1, 6\n" +
											"=B3, 8, =C2";
	
	private static final String cellRefResult = "1, 2, 3\n" +
												"1, 2, 6\n" +
												"8, 8, 6";
			
	private static final String complex = 	"1, =B2, 3, =C3\n" +
											"=5 1 +, =6 2 +, =7 3 -, =8 2 -\n" +
											"=9 3.5 *, =10 5 *, =11 2 /, =12 4 /";
	
	private static final String complexResult = "1, 8.0, 3, 5.5\n" +
												"6.0, 8.0, 4.0, 6.0\n" +
												"31.5, 50.0, 5.5, 3.0";
	
	private static final String multiIndirection = 	"=C2, 1, 1, 1\n" +
												 	"1, 1, =D3, 1\n" +
												 	"1, 1, 1, 2";

	private static final String multiIndirectionResult = 	"2, 1, 1, 1\n" +
												 			"1, 1, 2, 1\n" +
												 			"1, 1, 1, 2";
	
	private String inputCSV;
	private String outputCSV;
	
    public SpreadSheetTest(String inputCSV, String outputCSV) {
    	this.inputCSV = inputCSV;
    	this.outputCSV = outputCSV;
	}
	
    @Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][] {
                 {basic, basicResult},
                 {operation, operationResult},
                 {cellRef, cellRefResult},
                 {complex, complexResult},
                 {multiIndirection, multiIndirectionResult}
                 });
    }
    
	@Test
	public void testSpreadsheets() throws InvalidNodeException{
		SpreadSheet ss = new SpreadSheet(inputCSV);
		ss.processSpreadsheet();
		Assert.assertEquals(outputCSV, ss.getAsCSV());
	}
}
