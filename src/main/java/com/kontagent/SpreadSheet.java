package com.kontagent;

import java.util.HashMap;

public class SpreadSheet extends HashMap<String, Node>{

	private static final long serialVersionUID = 1L;
	
	public int numberOfRows;
	public int numberOfColumns;
	
	public SpreadSheet(String inputCSV){
		//Split the input on the CRLF character
		String[] rows = inputCSV.split("\\r?\\n");
		
		try{
			int rowNumber=0;
			int columnNumber=0;
			//Iterate over all rows in the table
			for (rowNumber=1; rowNumber <= rows.length; ++rowNumber){
				
				String currentRow = rows[rowNumber - 1];
				//Split the current row into columns on the comma character
				String[] columns = currentRow.split(",\\s*");
				
				//Iterate over all columns in the row
				for(columnNumber=1; columnNumber<=columns.length; ++columnNumber){
					//Create a new "node" and add it to our map
					String nodeContent = columns[columnNumber - 1];
					Node node = new Node(nodeContent);
					this.put(getNodeKey(columnNumber, rowNumber), node);
				}
			}
			//Keep track of the number of rows and columns in this spreadsheet
			this.numberOfRows=rowNumber-1;
			this.numberOfColumns=columnNumber-1;
			
		}catch(InvalidNodeException e){
			System.out.println("Failed to parse node at: " + e.nodeKey);
		}
	}

	//This method iterates over the rows and columns of this spreadsheet
	//It processes the nodes one by one, following references if it finds them
	public void processSpreadsheet() {
		for (int rowNumber = 1; rowNumber <= this.numberOfRows; rowNumber++) {
			for (int columnNumber = 1; columnNumber <= this.numberOfColumns; columnNumber++) {
				String currentKey = getNodeKey(columnNumber, rowNumber);
				Node currentNode = this.get(currentKey);
				try {
					process(currentKey, currentNode);
				} catch (InvalidNodeException e) {
					System.out.println("Failed to process node at" + currentKey);
					System.exit(0);
				}
			}
		}
	}
	
	//Formats this spreadsheet as a CSV and returns it as a string
	public String getAsCsv() {
		StringBuilder csvString = new StringBuilder();
		for (int rowNumber = 1; rowNumber <= this.numberOfRows; rowNumber++) {
			//For all but the first row, we need to prepend with CRLF
			if(rowNumber!=1){csvString.append("\n");}
			for (int columnNumber = 1; columnNumber <= this.numberOfColumns; columnNumber++) {
				//For all but the first column, we need to prepend with ", "
				if(columnNumber!=1){csvString.append(", ");}
				String currentKey = getNodeKey(columnNumber, rowNumber);
				Node currentNode = this.get(currentKey);
				csvString.append(currentNode.getContents());
			}
		}
		
		return csvString.toString();
	}
	
	//Recursive: If a node is a "CELL_REFERENCE", the reference will be processed first
	private void process(String currentKey, Node currentNode) throws InvalidNodeException {
		switch (currentNode.getType()){
			case SIMPLE_VALUE:
				//If it's a SIMPLE_VALUE, there is no extra processing
				this.put(currentKey, currentNode);
				break;
			case CELL_REFERENCE:
				//If it's a CELL_REFERENCE, we should go ahead and process the referenced cell now
				String referencedKey = currentNode.getReferencedKey();
				Node referencedNode = this.get(referencedKey);
				if(referencedNode == null){
					throw new InvalidNodeException("The referenced node was not found in the matrix", referencedKey);
				}
				process(referencedKey, referencedNode);
				Node updatedReferencedNode = this.get(referencedKey);
				this.put(currentKey, updatedReferencedNode);
				break;
			case OPERATIONWITHCELLREF:
				String withoutEqual = currentNode.getContents().substring(1);
				String keyToLookup = withoutEqual.split(" ")[0];
				Node ref = this.get(keyToLookup);
				process(keyToLookup, ref);
				Node updatedRef = this.get(keyToLookup);
				//Good god this needs to be fixed ASAP.
				//TODO: Fix this nasty kludge
				Node newNode = new Node("=" + updatedRef.getContents() + " " + withoutEqual.split(" ")[1] + " " + withoutEqual.split(" ")[2]);
				process(currentKey, newNode);
				break;
			case OPERATION:
				//If it's an operation, go ahead and perform the calculation
				this.put(currentKey, currentNode.performCalculation());
				break;
		}
	}
	
	//Convenience method to turn (3, 2) into C2
	private static String getNodeKey(int columnNumber, int rowNumber) {
		return (char)(columnNumber + 64) + String.valueOf(rowNumber);
	}
}