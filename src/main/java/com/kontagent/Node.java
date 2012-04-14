package com.kontagent;

public class Node {

	private String contents;
	private Type type;
	//Just a 
	private static String FLOAT_OR_INT = "(([0-9]+)|([0-9]+)\\.([0-9]+))";
	
	@SuppressWarnings("unused")
	private Node(){
		//Disable direct instantiation without contents
	}
	
	public Node (String contents) throws InvalidNodeException{
		this.contents = contents;

		//Each node of the matrix should conform to one of the 3 types in the challenge document
		for(Type type : Type.values()){
			if(doesNodeMatchType(type)){
				this.type = type;
				break;
			}
		}
		//Confirm that the node has been assigned a type
		if(this.type == null){
			throw new InvalidNodeException("This node does not match a valid format");
		}
	}
	
	public enum Type {
		//Each type of matrix node has a regular expression that defines it
		//SIMPLE_VALUE i.e. 6, 1.2, or 0.53
		//CELL_REFERENCE i.e. A21, Z28, F2203, NOT F02
		//OPERATION i.e. =0.40 1.58 +, =4 2 -
		SIMPLE_VALUE(FLOAT_OR_INT),
		CELL_REFERENCE("=[A-Z]([1-9]|[1-9][0-9]+)"),
		OPERATION("(=)" + FLOAT_OR_INT + "[( )]" + FLOAT_OR_INT + "[( )]" + "([\\+|\\-|\\/|\\*])");
		
		Type(String regex){
			this.regex = regex;
		}
		
		private String regex;
	}
	
	private boolean doesNodeMatchType(Type type){
		return this.contents.matches(type.regex);
	}
	
	public Type getType(){
		return this.type;
	}
}
