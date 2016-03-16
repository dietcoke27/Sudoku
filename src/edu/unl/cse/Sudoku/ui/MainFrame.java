package edu.unl.cse.Sudoku.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.unl.cse.Sudoku.model.Change;

public class MainFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private SudokuPanel sudokuPanel;
	private JButton newGameButton;
	private JButton undoButton;
	private JButton checkValid;
	
	/**
	 * Constructor for the MainFrame class.
	 */
	public MainFrame() {
		//create and set a new layout for the content panel of the main window
		SpringLayout layout = new SpringLayout();
		this.getContentPane().setLayout(layout);
		//create and add the game frame to the main window
		SudokuPanel frame = new SudokuPanel();
		this.sudokuPanel = frame;
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
		panel.setLayout(new GridLayout(3,1));
		//add a new label element
		this.newGameButton = new JButton("New Game");
		this.newGameButton.addActionListener(this);
		JPanel buttonBlock = new JPanel();
		buttonBlock.add(this.newGameButton);
		panel.add(buttonBlock);
		this.newGameButton.setFocusable(false);
		
		this.undoButton = new JButton("Undo");
		this.undoButton.addActionListener(this);
		buttonBlock = new JPanel();
		buttonBlock.add(this.undoButton);
		panel.add(buttonBlock);
		this.undoButton.setFocusable(false);
		
		this.checkValid = new JButton("Check");
		this.checkValid.addActionListener(this);
		buttonBlock = new JPanel();
		buttonBlock.add(this.checkValid);
		panel.add(buttonBlock);
		this.checkValid.setFocusable(false);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if (this.newGameButton == e.getSource()) {
			System.out.println("New Game");
		} else if (this.undoButton == e.getSource()) {
			System.out.println("Undo");
			Change c = SudokuPanel.moves.pop();
			if (c != null) {
				BlockPanel p = this.sudokuPanel.gamePanels[c.getRow()][c.getCol()];
				p.block.setValue(c.getPrevVal());
				p.checkLabel();
			}
		} else if (this.checkValid == e.getSource()) {
			if (SudokuPanel.checkGameValid()) {
				System.out.println("Valid");
			}
			System.out.println("Check");
		}
		
	}
}
