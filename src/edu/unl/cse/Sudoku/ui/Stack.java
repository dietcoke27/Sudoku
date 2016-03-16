package edu.unl.cse.Sudoku.ui;

public class Stack {
	
	private Node top;
	
	public Stack(){
		this.top = null;
	}
	
	/**
	 * Push the parameter node to the top of the stack
	 * @param n
	 */
	void push(Node n){
		n.setNodeBelow(top);
		top = n;
	}
	
	/**
	 * Remove the last change from the stack and return its information.
	 * @return the last change record returns null if stack is null
	 */
	public Change pop(){
		if( top == null)
		{
			return null;
		}
		else{
		Node retNode = top;
		top = top.getNodeBelow();
		return retNode.getChange();
		}
	}
	
}
