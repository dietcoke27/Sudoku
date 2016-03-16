package edu.unl.cse.Sudoku.ui;

import java.awt.Color;

import javax.swing.*;

import edu.unl.cse.Sudoku.model.*;

public class BlockPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	Block block;
	JPanel displayNumberBlock = new JPanel();
	JLabel label = new JLabel();
	
	public BlockPanel() {
		this.add(this.displayNumberBlock);
		this.setBackground(Color.black);
		SpringLayout l = new SpringLayout();
		this.setLayout(l);
		l.putConstraint(SpringLayout.NORTH, this.displayNumberBlock, 5, SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.EAST, this.displayNumberBlock, 5, SpringLayout.EAST, this);
		l.putConstraint(SpringLayout.SOUTH, this.displayNumberBlock, 5, SpringLayout.SOUTH, this);
		l.putConstraint(SpringLayout.WEST, this.displayNumberBlock, 5, SpringLayout.WEST, this);
		SpringLayout.Constraints parentCons = l.getConstraints(this);
	}
}
