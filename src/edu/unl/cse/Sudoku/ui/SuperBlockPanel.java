package edu.unl.cse.Sudoku.ui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class SuperBlockPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JPanel displayPanel;

	public SuperBlockPanel() {
		this.setBackground(Color.BLACK);
		this.displayPanel = new JPanel(new GridLayout(3, 3));
		this.add(this.displayPanel);

		// create and set a new layout for the BlockPanel
		SpringLayout l = new SpringLayout();
		this.setLayout(l);
		// set constraints for the BlockPanel
		l.putConstraint(SpringLayout.NORTH, this.displayPanel, 3,
				SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.EAST, this.displayPanel, -3,
				SpringLayout.EAST, this);
		l.putConstraint(SpringLayout.SOUTH, this.displayPanel, -3,
				SpringLayout.SOUTH, this);
		l.putConstraint(SpringLayout.WEST, this.displayPanel, 3,
				SpringLayout.WEST, this);
		l.putConstraint(SpringLayout.WIDTH, this.displayPanel, 0,
				SpringLayout.HEIGHT, this.displayPanel);
	}

	public void addBlockPanel(BlockPanel bp) {
		this.displayPanel.add(bp);
	}
}
