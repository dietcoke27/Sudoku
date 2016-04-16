package edu.unl.cse.Sudoku.model;

import javax.swing.JOptionPane;

import edu.unl.cse.Sudoku.ui.SudokuLauncher;

public class Stack {

	private Node top;
	private boolean full;
	/**
	 * Constructor for a Stack. Initializes the top of the stack to null.
	 */
	public Stack() {
		this.top = null;
		this.full = false;
	}

	/**
	 * Push the parameter node to the top of the stack.
	 * 
	 * @param newTop
	 */
	public void push(Node newTop) {
		if(!full){
			try {
				newTop.setNodeBelow(top);
				top = newTop;
			} catch (OutOfMemoryError e) {
				full = true;
				JOptionPane
						.showMessageDialog(
								SudokuLauncher.game,
								"You have exceeded the maximum number of moves. From here moves will no longer be recorded.",
								"Out of memory warning!",
								JOptionPane.PLAIN_MESSAGE, null);
			}
		}
	}

	/**
	 * Remove the last change from the stack and return its information.
	 * 
	 * @return the last change record returns null if stack is empty.
	 */
	public Change pop() {
		full = false;
		if (top == null) {
			return null;
		} else {
			Node retNode = top;
			top = top.getNodeBelow();
			return retNode.getChange();
		}
	}
}