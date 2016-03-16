package edu.unl.cse.Sudoku.ui;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the MainFrame class.
	 */
	public MainFrame() {
		//create and set a new layout for the content panel of the main window
		SpringLayout layout = new SpringLayout();
		this.getContentPane().setLayout(layout);
		//create and add the game frame to the main window
		SudokuPanel frame = new SudokuPanel();
		this.getContentPane().add(frame);
		//set requirements for the new layout.
		layout.putConstraint(SpringLayout.NORTH, frame, 0, SpringLayout.NORTH, this.getContentPane());			// 0 px between top of frame and content pane
		layout.putConstraint(SpringLayout.WEST , frame, 0, SpringLayout.WEST,  this.getContentPane());			// 0 px between left of frame and context pane
		layout.putConstraint(SpringLayout.SOUTH, frame, 0, SpringLayout.SOUTH, this.getContentPane());			// 0 px between bottom of frame and context pane
		//create and add a panel to contain extra buttons
		JPanel buttonPanel = this.createButtonPanel();
		this.getContentPane().add(buttonPanel);
		//set requirements for the new layout
		layout.putConstraint(SpringLayout.EAST,  buttonPanel, 0, SpringLayout.EAST,  this.getContentPane());	// 0 px between right  of buttonPanel and right  of content pane
		layout.putConstraint(SpringLayout.NORTH, buttonPanel, 0, SpringLayout.NORTH, this.getContentPane());	// 0 px between top    of buttonPanel and top    of content pane
		layout.putConstraint(SpringLayout.EAST,  frame,       0, SpringLayout.WEST,  buttonPanel);				// 0 px between left   of frame       and right  of buttonPanel
		layout.putConstraint(SpringLayout.SOUTH, buttonPanel, 0, SpringLayout.SOUTH, this.getContentPane());	// 0 px between bottom of buttonPanel and bottom of content pane
		//get the constraints on the buttonPanel and set the width to be a constant size
		SpringLayout.Constraints buttonConstraints = layout.getConstraints(buttonPanel);
		buttonConstraints.setWidth(Spring.constant(120));
	}
	
	/**
	 * Method that creates and returns a JPanel to house buttons
	 */
	public JPanel createButtonPanel() {
		//make a new panel to house elements
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//add a new label element
		JLabel label = new JLabel();
		label.setText("Buttons");
		panel.add(label);
		
		return panel;
	}
	
	/**
	 * Main execution body
	 * @param args
	 */
	public static void main(String args[]) {
		//create the window for the game
		MainFrame main = new MainFrame();
		main.setVisible(true);
		main.setSize(900, 780);
		main.setResizable(false);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
