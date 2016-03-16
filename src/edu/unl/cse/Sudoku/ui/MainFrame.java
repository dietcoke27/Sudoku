package edu.unl.cse.Sudoku.ui;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public MainFrame() {
		this.getContentPane().setLayout(new BorderLayout());
		SudokuPanel frame = new SudokuPanel();
		
		this.add(frame, BorderLayout.CENTER);
		JPanel buttonPanel = this.createButtonPanel();
		this.getContentPane().add(buttonPanel, BorderLayout.EAST);
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
		main.setSize(600, 400);
	}
}
