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
	 *            : Must be in the range of [0-9]
	 */
	public void setPrevVal(int prev) {
		if (prev < 0 || prev > 9) {
			return;
		}
		this.prevVal = prev;
	}

	/**
	 * Setter for the row in which the Changed Block lives
	 * 
	 * @param r
	 *            : Must be in the range of [0-8]
	 */
	public void setRow(int r) {
		if (r < 0 || r > 8) {
			return;
		}
		this.row = r;
	}

	/**
	 * Setter for the column in which the changed Block lives
	 * 
	 * @param c
	 *            : Must be in the range of [0-8]
	 */
	public void setCol(int c) {
		if (c < 0 || c > 8) {
			return;
		}
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