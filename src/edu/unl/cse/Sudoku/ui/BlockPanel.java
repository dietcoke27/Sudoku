package edu.unl.cse.Sudoku.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import edu.unl.cse.Sudoku.model.*;

public class BlockPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	Block block;
	JPanel displayNumberBlock = new JPanel();
	JLabel label = new JLabel("", SwingConstants.CENTER);
	int row;
	int column;
	
	public BlockPanel(int row, int column) {
		this.row = row;
		this.column = column;
		this.add(this.displayNumberBlock);
		this.setBackground(Color.black);
		SpringLayout l = new SpringLayout();
		this.setLayout(l);
		l.putConstraint(SpringLayout.NORTH, this.displayNumberBlock, 3, SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.EAST, this.displayNumberBlock, -3, SpringLayout.EAST, this);
		l.putConstraint(SpringLayout.SOUTH, this.displayNumberBlock, -3, SpringLayout.SOUTH, this);
		l.putConstraint(SpringLayout.WEST, this.displayNumberBlock, 3, SpringLayout.WEST, this);
		l.putConstraint(SpringLayout.WIDTH, this.displayNumberBlock, 0, SpringLayout.HEIGHT, this.displayNumberBlock);
		this.displayNumberBlock.setLayout(new BorderLayout());
		this.displayNumberBlock.add(this.label, BorderLayout.CENTER);
		this.label.setFont(new Font("Serif", Font.PLAIN, 50));
	}
	
	public void panelWasClicked() {
		this.setBackground(Color.blue);
	}
	
	public void deselectPanel() {
		this.setBackground(Color.black);
	}
}
