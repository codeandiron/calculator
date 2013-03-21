package com.calculator;

public class Node {

	private String contents;
	private Type type;
	// Just a
	private static String FLOAT_OR_INT = "(([0-9]+)|([0-9]+)\\.([0-9]+))";

	@SuppressWarnings("unused")
	private Node() {
		// Disable direct instantiation without contents
	}

	public Node(String contents) throws InvalidNodeException {
		this.contents = contents;

		// Each node of the matrix should conform to one of the 3 types in the
		// challenge document
		for (Type type : Type.values()) {
			if (doesNodeMatchType(type)) {
				this.type = type;
				break;
			}
		}
		// Confirm that the node has been assigned a type
		if (this.type == null) {
			throw new InvalidNodeException("This node does not match a valid format");
		}
	}

	public enum Type {
		// Each type of matrix node has a regular expression that defines it
		// SIMPLE_VALUE i.e. 6, 1.2, or 0.53
		// CELL_REFERENCE i.e. A21, Z28, F2203, NOT F02
		// OPERATION i.e. =0.40 1.58 +, =4 2 -
		// OPERATION_WITH_CELL_REF i.e. =A124 4.34 *
		SIMPLE_VALUE(FLOAT_OR_INT), 
		CELL_REFERENCE("=[A-Z]([1-9]|[1-9][0-9]+)"), 
		OPERATION("(=)" + FLOAT_OR_INT + "[( )]" + FLOAT_OR_INT + "[( )]" + "([\\+|\\-|\\/|\\*])"),
		OPERATION_WITH_CELL_REF("(=)(" + FLOAT_OR_INT + "|" + "[A-Z]([1-9]|[1-9][0-9]+)" + ")[( )]" + FLOAT_OR_INT + "[( )]" + "([\\+|\\-|\\/|\\*])");

		Type(String regex) {
			this.regex = regex;
		}

		private String regex;
	}

	private boolean doesNodeMatchType(Type type) {
		return this.contents.matches(type.regex);
	}

	public Type getType() {
		return this.type;
	}
	
	public String getContents() {
		return this.contents;
	}

	public String getReferencedKey() throws InvalidNodeException {
		if(!(this.type == Type.CELL_REFERENCE)){
			throw new InvalidNodeException("Attempt to get referenced cell failed");
		}
		return this.contents.substring(1);
	}

	public Node performCalculation() throws InvalidNodeException {
		if(!(this.type == Type.OPERATION)){
			throw new InvalidNodeException("Attempt to perform calculation failed");
		}
		
		//Chop off the equals sign, and split into the operands and operator
		String[] operation = this.contents.substring(1).split(" ");
		Float operand1 = Float.parseFloat(operation[0]);
		Float operand2 = Float.parseFloat(operation[1]);
		String operator = operation[2];

		Float result = null;
		if (operator.equals("+")) {
			result = operand1 + operand2;
		} else if (operator.equals("-")) {
			result = operand1 - operand2;
		} else if (operator.equals("*")) {
			result = operand1 * operand2;
		} else if (operator.equals("/")) {
			if(operand2.equals(0)){
				throw new IllegalArgumentException("Cannot divide by zero");
			}
			result = operand1 / operand2;
		}

		return new Node(result.toString());
	}
}
