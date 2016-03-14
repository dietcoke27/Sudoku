package edu.unl.cse.Sudoku.ui;

public class Node {

	private Node nextNode;
	private Change change;
	
	public Node(Node next, Change lastChange){
		this.nextNode = next;
		this.change = lastChange;
	}

	/**
	 * Getter for the next node in the stack
	 * @return
	 */
	public Node getNextNode() {
		return nextNode;
	}

	/**
	 * Setter for the next node variable of this node
	 * @param nextNode
	 */
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	/**
	 * Getter for the change variable of this node
	 * @return
	 */
	public Change getChange() {
		return change;
	}

	/**
	 * Setter for the change variable of this node
	 * @param change
	 */
	public void setChange(Change change) {
		this.change = change;
	}
	
}
