package edu.unl.cse.Sudoku.model;

public class Change {

	private int prevVal;
	private int row;
	private int col;

	/**
	 * Constructor for a Change element
	 * 
	 * @param prevVal
	 *            : the previous value of the element.
	 * @param r
	 *            : the row of the element that changed.
	 * @param c
	 *            : the col of the element that changed.
	 */
	public Change(int prevVal, int r, int c) {
		this.prevVal = prevVal;
		this.row = r;
		this.col = c;
	}

	/**
	 * Setter for the previous value variable of the Block change
	 * 
	 * @param prev
	 */
	public void setPrevVal(int prev) {
		this.prevVal = prev;
	}

	/**
	 * Setter for the row in which the Changed Block lives
	 * 
	 * @param r
	 */
	public void setRow(int r) {
		this.row = r;
	}

	/**
	 * Setter for the column in which the changed Block lives
	 * 
	 * @param c
	 */
	public void setCol(int c) {
		this.col = c;
	}

	/**
	 * Getter for the previous value of the changed Block
	 * 
	 * @return
	 */
	public int getPrevVal() {
		return this.prevVal;
	}

	/**
	 * Getter for the row of the changed Block
	 * 
	 * @return
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * Getter for the column of the changed Block
	 * 
	 * @return
	 */
	public int getCol() {
		return this.col;
	}
}
