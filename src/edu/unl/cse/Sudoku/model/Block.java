package edu.unl.cse.Sudoku.model;

public class Block {

	private int value;
	private boolean editable;
	private int[] notes= {};
	
	public Block(int value, boolean editable){
		
		this.value = value;
		this.editable = editable;
	}
	
	/**
	 * Getter for the value of the Block
	 * @return value
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * Setter for the value of the Block
	 * @param newVal
	 */
	public void setValue(int newVal){
		this.value = newVal;
	}
	
	/**
	 * Returns if the current block is editable
	 * @return
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Setter for the edit-ability of the block
	 * @param editable
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	/**
	 * Getter for the notes of this block
	 * @return notes
	 */
	int[] getNotes(){
		return this.notes;
	}
	
	/**
	 * Method to add an integer to the list of notes
	 * for this Block
	 * @param note
	 */
	public void addNote(int note){
		//TODO:
		
	}
	
	/**
	 * Method to remove an integer from the list of notes
	 * for this Block
	 * @param note
	 */
	void removeNote(int note){
		//TODO:
		
	}
	
	/**
	 * Method to remove ALL notes from a Block
	 */
	void clearNotes(){
		//TODO:
	}
}
