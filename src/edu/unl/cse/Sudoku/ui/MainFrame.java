package edu.unl.cse.Sudoku.ui;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static boolean isTitleScreen = true;

	/**
	 * Constructor for the MainFrame class.
	 */
	public MainFrame() {
		super("Sudoku");
		// create and set a new layout for the content panel of the main window
		this.getContentPane().setBackground(Color.black);
		this.setVisible(true);
		this.setSize(580, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
