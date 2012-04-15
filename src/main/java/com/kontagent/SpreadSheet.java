package com.kontagent;

import java.util.HashMap;

public class SpreadSheet extends HashMap<String, Node>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int numberOfRows;
	public int numberOfColumns;
	
	public SpreadSheet(String inputCSV){
		String[] rows = inputCSV.split("\\r?\\n");
		
		try{
			int rowNumber=0;
			int columnNumber=0;
			for (rowNumber=1; rowNumber <= rows.length; ++rowNumber){
				String currentRow = rows[rowNumber - 1];
				String[] columns = currentRow.split(",\\s*");
				
				for(columnNumber=1; columnNumber<=columns.length; ++columnNumber){
					String nodeContent = columns[columnNumber - 1];
					Node node = new Node(nodeContent);
					this.put(getNodeKey(columnNumber, rowNumber), node);
				}
			}
			this.numberOfRows=rowNumber-1;
			this.numberOfColumns=columnNumber-1;
			
		}catch(InvalidNodeException e){
			System.out.println("Failed to parse all of the nodes into one of the three types");
		}
	}

	public void processSpreadsheet() throws InvalidNodeException {
		for (int rowNumber = 1; rowNumber <= this.numberOfRows; rowNumber++) {
			for (int columnNumber = 1; columnNumber <= this.numberOfColumns; columnNumber++) {
				//Get and process the current node in the spreadsheet
				String currentKey = getNodeKey(columnNumber, rowNumber);
				Node currentNode = this.get(currentKey);
				process(currentKey, currentNode);
			}
		}
	}
	
	public String getAsCSV() {
		StringBuilder csvString = new StringBuilder();
		for (int rowNumber = 1; rowNumber <= this.numberOfRows; rowNumber++) {
			if(rowNumber!=1){csvString.append("\n");}
			for (int columnNumber = 1; columnNumber <= this.numberOfColumns; columnNumber++) {
				//Get the requested node for output
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
					throw new InvalidNodeException("The referenced node was not found in the matrix");
				}
				process(referencedKey, referencedNode);
				Node updatedReferencedNode = this.get(referencedKey);
				this.put(currentKey, updatedReferencedNode);
				break;
			case OPERATION:
				//If it's an operation, go ahead and perform the calculation
				this.put(currentKey, currentNode.performCalculation());
				break;
		}
	}
	
	private static String getNodeKey(int columnNumber, int rowNumber) {
		return (char)(columnNumber + 64) + String.valueOf(rowNumber);
	}
}
