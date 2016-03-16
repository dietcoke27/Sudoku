package edu.unl.cse.Sudoku.model;

public class Node {

	private Node nodeBelow;
	private Change change;
	
	public Node(Node below, Change lastChange){
		this.nodeBelow = below;
		this.change = lastChange;
	}

	/**
	 * Getter for the next node in the stack
	 * @return
	 */
	public Node getNodeBelow() {
		return nodeBelow;
	}

	/**
	 * Setter for the next node variable of this node
	 * @param nodeBelow
	 */
	public void setNodeBelow(Node nodeBelow) {
		this.nodeBelow = nodeBelow;
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
