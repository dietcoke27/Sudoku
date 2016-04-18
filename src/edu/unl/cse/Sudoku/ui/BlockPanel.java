package edu.unl.cse.Sudoku.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import edu.unl.cse.Sudoku.model.*;

//ADD SOME TRIVIAL COMMENT CODE TO PROVE COMMITS WORK

public class BlockPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Block block;
	private JPanel displayNumberBlock;
	private JLabel label;
	private int row;
	private int col;
	private Color dispNormal;
	private Color dispSelected;

	/**
	 * Constructor for the BlockPanel class. BlockPanel is a JPanel that
	 * contains another JPanel, as well as a text field. BlockPanel also has a
	 * Block that is associated with it.
	 * 
	 * @param row
	 * @param column
	 */
	public BlockPanel(int row, int col) {
		this.row = row;
		this.col = col;
		this.setBackground(Color.black);

		// create a panel inside the BlockPanel to display the number
		this.displayNumberBlock = new JPanel();
		this.add(this.displayNumberBlock);

		// create and set a new layout for the BlockPanel
		SpringLayout l = new SpringLayout();
		this.setLayout(l);
		// set constraints for the BlockPanel
		l.putConstraint(SpringLayout.NORTH, this.displayNumberBlock, 3,
				SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.EAST, this.displayNumberBlock, -3,
				SpringLayout.EAST, this);
		l.putConstraint(SpringLayout.SOUTH, this.displayNumberBlock, -3,
				SpringLayout.SOUTH, this);
		l.putConstraint(SpringLayout.WEST, this.displayNumberBlock, 4,
				SpringLayout.WEST, this);
		l.putConstraint(SpringLayout.WIDTH, this.displayNumberBlock, 0,
				SpringLayout.HEIGHT, this.displayNumberBlock);
		// set a layout for the interior number panel
		this.displayNumberBlock.setLayout(new BorderLayout());
	}

	/**
	 * Getter method for the block variable.
	 */
	public Block getBlock() {
		return this.block;
	}

	/**
	 * Setter method for the block variable.
	 * 
	 * @param newBlock
	 */
	public void setBlock(Block newBlock) {
		this.block = newBlock;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            : The row to set.
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col
	 *            : The col to set.
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Updates the label on the block. Updates the background color.
	 */
	public void update() {
		if (this.block.getValue() != 0) {
			this.displayNumberBlock.removeAll();
			this.displayNumberBlock.setLayout(new GridLayout(1,1));
			this.label = new JLabel("" + this.block.getValue(), SwingConstants.CENTER);
			this.label.setFont(new Font("Serif", Font.PLAIN, 25));
			this.displayNumberBlock.add(this.label, BorderLayout.CENTER);
			
			this.displayNumberBlock.revalidate();
		} else {
			this.displayNumberBlock.removeAll();
			this.displayNumberBlock.setLayout(new GridLayout(3,3));
			
			for(int i = 0; i < SudokuPanel.gameSize; i++){
				String temp = "";
				if(this.getBlock().getNotes()[i]){
					temp = "" + (i+1);
				}
				JLabel tempLabel = new JLabel(temp);
				tempLabel.setFont(new Font("Serif", Font.PLAIN, 10));
				this.displayNumberBlock.add(tempLabel);
			}
			this.displayNumberBlock.revalidate();
		}
	}

	/**
	 * Called to automatically set the colors of the block
	 */
	public void setColors() {
		if (!this.block.isEditable()) {
			this.dispNormal = Color.LIGHT_GRAY;
			this.dispSelected = Color.GRAY;
			this.displayNumberBlock.setBackground(dispNormal);
		} else {
			this.dispNormal = Color.white;
			this.dispSelected = Color.cyan;
			this.displayNumberBlock.setBackground(dispNormal);
		}
	}

	/**
	 * Method is called when the panel is first selected. Changes the background
	 * color of the panel to blue
	 */
	public void selectPanel() {
		this.displayNumberBlock.setBackground(dispSelected);
	}

	/**
	 * Method is called when another panel is selected. Changes the background
	 * color of the panel to black
	 */
	public void deselectPanel() {
		this.displayNumberBlock.setBackground(dispNormal);
	}
}