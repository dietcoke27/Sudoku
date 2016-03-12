package edu.unl.cse.Sudoku.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class SudokuFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel rootPanel;
	
	public SudokuFrame() {
		this.setupUI();
	}
	
	public void setupUI() {
		this.setBackground(Color.gray);
		
		this.rootPanel = new JPanel();
		this.rootPanel.setLayout(new GridLayout(3, 1));
		JPanel panel = new JPanel();
		panel.setBackground(Color.red);
		this.rootPanel.add(panel);
		panel = new JPanel();
		panel.setBackground(Color.blue);
		this.rootPanel.add(panel);
		panel = new JPanel();
		panel.setBackground(Color.green);
		this.rootPanel.add(panel);
		
		this.getContentPane().add(this.rootPanel);
		this.setSize(1000, 400);
	}
	
	public static void main(String args[]) {
		SudokuFrame frame = new SudokuFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
