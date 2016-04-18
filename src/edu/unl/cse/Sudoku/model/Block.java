package edu.unl.cse.Sudoku.model;

import edu.unl.cse.Sudoku.ui.SudokuPanel;

public class Block {

	private int value;
	private boolean editable;
	private boolean[] notes;

	/**
	 * Constructor for a Block. Assigns the values passed, and creates an array of notes that is initially false.
	 * @param value
	 * @param editable
	 */
	public Block(int value, boolean editable) {
		this.value = value;
		this.editable = editable;
		this.notes = new boolean[SudokuPanel.gameSize];
		for(int i = 0; i < notes.length; i++){
			notes[i] = false;
		}
	}

	/**
	 * Getter for the value of the Block
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Setter for the value of the Block. Value is only changed if the block is
	 * editable.
	 */
	public void setValue(int newVal) {
		if (this.isEditable()) {
			this.value = newVal;
		}
	}

	/**
	 * Returns if the current block is editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Getter for the notes of this block
	 * 
	 * @return notes
	 */
	public boolean[] getNotes() {
		return this.notes;
	}

	/**
	 * Method to add an integer to the list of notes for this Block
	 * 
	 * @param note
	 *            : should be in the range of [1--9]
	 */
	public void toggleNote(int note) {
		if(note < 1 || note > notes.length){
			return;
		}
		this.notes[note - 1] = !this.notes[note - 1];
	}

	/**
	 * Method to remove an integer from the list of notes for this Block
	 * 
	 * @param note
	 *            : should be in the range of [1-9]
	 */
	void removeNote(int note) {
		if(note < 1 || note > notes.length){
			return;
		}
		this.notes[note - 1] = false;
	}

	/**
	 * Method to remove ALL notes from a Block
	 */
	void clearNotes() {
		for (int i = 0; i < notes.length; i++) {
			this.notes[i] = false;
		}
	}

	/**
	 * Method to set the editable variable of the block.
	 */
	public void setEditable(boolean b) {
		this.editable = b;
	}
}
