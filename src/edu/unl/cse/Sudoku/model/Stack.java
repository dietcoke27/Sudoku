package edu.unl.cse.Sudoku.model;

public class Stack {

	private Node top;

	/**
	 * Constructor for a Stack. Initializes the top of the stack to null.
	 */
	public Stack() {
		this.top = null;
	}

	/**
	 * Push the parameter node to the top of the stack.
	 * 
	 * @param newTop
	 */
	public void push(Node newTop) {
		newTop.setNodeBelow(top);
		top = newTop;
	}

	/**
	 * Remove the last change from the stack and return its information.
	 * 
	 * @return the last change record returns null if stack is empty.
	 */
	public Change pop() {
		if (top == null) {
			return null;
		} else {
			Node retNode = top;
			top = top.getNodeBelow();
			return retNode.getChange();
		}
	}
}
