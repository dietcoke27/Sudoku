package edu.unl.cse.Sudoku.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import edu.unl.cse.Sudoku.model.*;

public class BlockPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	Block block;
	JPanel displayNumberBlock;
	JLabel label;
	int row;
	int column;
	
	/**
	 * Constructor for the BlockPanel class.
	 * BlockPanel is a JPanel that contains another JPanel, as well as a text field.
	 * BlockPanel also has a Block that is associated with it.
	 * @param row
	 * @param column
	 */
	public BlockPanel(int row, int column) {
		this.row = row;
		this.column = column;
		this.setBackground(Color.black);
		
		//create a panel inside the BlockPanel to display the number
		this.displayNumberBlock  = new JPanel();
		this.add(this.displayNumberBlock);
		
		//create and set a new layout for the BlockPanel
		SpringLayout l = new SpringLayout();
		this.setLayout(l);
		//set constraints for the BlockPanel
		l.putConstraint(SpringLayout.NORTH, this.displayNumberBlock,  3, SpringLayout.NORTH,  this);						// 3 px between the top    of the number box and the top    of the BlockPanel
		l.putConstraint(SpringLayout.EAST,  this.displayNumberBlock, -3, SpringLayout.EAST,   this);						// 3 px between the right  of the number box and the right  of the BlockPanel 
		l.putConstraint(SpringLayout.SOUTH, this.displayNumberBlock, -3, SpringLayout.SOUTH,  this);						// 3 px between the bottom of the number box and the bottom of the BlockPanel
		l.putConstraint(SpringLayout.WEST,  this.displayNumberBlock,  4, SpringLayout.WEST,   this);						// 3 px between the left   of the number box and the left   of the BlockPanel
		l.putConstraint(SpringLayout.WIDTH, this.displayNumberBlock,  0, SpringLayout.HEIGHT, this.displayNumberBlock);		// 0 px between the height of the number box and the width  of the number box
		//set a layout for the interior number panel
		this.displayNumberBlock.setLayout(new BorderLayout());
		//create a new label for the numbers, set the font of the label, add the label to the number box
		this.label = new JLabel("", SwingConstants.CENTER);
		this.label.setFont(new Font("Serif", Font.PLAIN, 50));
		this.displayNumberBlock.add(this.label, BorderLayout.CENTER);
	}
	
	public void updateEditable() {
		if (!this.block.isEditable()) {
			this.displayNumberBlock.setBackground(Color.lightGray);
		}
	}
	
	/**
	 * Method is called when the panel is first selected.
	 * Changes the background color of the panel to blue
	 */
	public void panelWasClicked() {
		if(this.block.isEditable()){
			this.displayNumberBlock.setBackground(Color.cyan);
		}else{
			this.displayNumberBlock.setBackground(Color.gray);
		}
	}
	
	/**
	 * Method is called when another panel is selected.
	 * Changes the background color of the panel to black
	 */
	public void deselectPanel() {
		this.displayNumberBlock.setBackground(Color.white);
		this.updateEditable();
	}
	
	public void checkLabel(){
		if(this.block != null){
			if(this.block.getValue() != 0){
				this.label.setText( "" + this.block.getValue());
			}else{
				this.label.setText("");
			}
		}
	}
}
