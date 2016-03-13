package edu.unl.cse.Sudoku.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class SudokuFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
//	private JPanel rootPanel;
	
	public SudokuFrame() {
		this.setupUI();
	}
	
	public void setupUI() {
		this.setBackground(Color.gray);
		
		Color[] colors = {Color.red, Color.blue, Color.green};
		this.getContentPane().setLayout(new GridLayout(3, 1));
		
		for (Color c: colors) {
			
			JPanel panel = new JPanel();
			panel.setBackground(c);
			this.getContentPane().add(panel);
		}
		this.setSize(400, 400);
	}
	
	public static void main(String args[]) {
		SudokuFrame frame = new SudokuFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
