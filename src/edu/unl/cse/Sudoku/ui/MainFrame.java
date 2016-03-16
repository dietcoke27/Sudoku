package edu.unl.cse.Sudoku.ui;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public MainFrame() {
		this.setResizable(false);
		SpringLayout layout = new SpringLayout();
		this.getContentPane().setLayout(layout);
		SudokuPanel frame = new SudokuPanel();
		
		this.getContentPane().add(frame);
		SpringLayout.Constraints consts = layout.getConstraints(frame);
		layout.putConstraint(SpringLayout.NORTH, frame, 0, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, frame, 0, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, frame, 0, SpringLayout.SOUTH, this.getContentPane());
		JPanel buttonPanel = this.createButtonPanel();
		this.getContentPane().add(buttonPanel);
		layout.putConstraint(SpringLayout.EAST, buttonPanel, 0, SpringLayout.EAST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, buttonPanel, 0, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.EAST, frame, 0, SpringLayout.WEST, buttonPanel);
		layout.putConstraint(SpringLayout.SOUTH, buttonPanel, 0, SpringLayout.SOUTH, this.getContentPane());
		SpringLayout.Constraints buttonConstraints = layout.getConstraints(buttonPanel);
		buttonConstraints.setWidth(Spring.constant(120));//(Spring.scale(Spring.width(frame), 0.2f));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
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
		main.setSize(1200, 1000);
	}
}
