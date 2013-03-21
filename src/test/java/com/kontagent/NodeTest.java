package com.calculator;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class NodeTest {
	
    private String inputContents;
    private String expectedType;
 
    public NodeTest(String inputContents, String expectedType){
		this.inputContents = inputContents;
		this.expectedType = expectedType;
    }   
	
    @Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][] {
                 {"9", "SIMPLE_VALUE"},
                 {"4098302093", "SIMPLE_VALUE"},
                 {"03827", "SIMPLE_VALUE"},
                 {"0.232", "SIMPLE_VALUE"},
                 {"0.0232", "SIMPLE_VALUE"},
                 {"2.323", "SIMPLE_VALUE"},
                 {"524.0", "SIMPLE_VALUE"},
                 {"02304.238", "SIMPLE_VALUE"},
                 {"=A1", "CELL_REFERENCE"},
                 {"=Z32", "CELL_REFERENCE"},
                 {"=Z1042", "CELL_REFERENCE"},
                 {"=F23098", "CELL_REFERENCE"},
                 {"=7 9 +", "OPERATION"},
                 {"=8 4 /", "OPERATION"},
                 {"=2 2 *", "OPERATION"},
                 {"=4 2 -", "OPERATION"},
                 {"=7 9 +", "OPERATION"},
                 {"=1.2 2 +", "OPERATION"},
                 {"=2 2.3 +", "OPERATION"},
                 {"=0.4 2 +", "OPERATION"},
                 {"=0.40 2 +", "OPERATION"},
                 {"=0.40 1.58 +", "OPERATION"},
                 });
    }
	
	@Test
	public void testNode() throws Exception {
		Node node = new Node(inputContents);
		Assert.assertEquals(Node.Type.valueOf(expectedType), node.getType());
	}
}
