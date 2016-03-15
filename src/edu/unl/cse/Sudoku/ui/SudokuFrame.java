package edu.unl.cse.Sudoku.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class SudokuFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private boolean titleScreenShown;
	private boolean gameScreenShown;
	private boolean winScreenShown;
	private int difficulty;
	private long gameTimer;
	private int selectedRow;
	private int selectedCol;
	private Block [] [] currentGame;
	private boolean gameWon;
	private Stack moves;
	private boolean editingNotes;
//	private JPanel rootPanel;
	
	/**
	 * Main execution body
	 * @param args
	 */
	public static void main(String args[]) {
		//create the window for the game
		SudokuFrame frame = new SudokuFrame();
		
	
	}
	
	/**
	 * SudokuFrame constructor
	 */
	public SudokuFrame() {
		this.setupUI();
		this.setVisible(true);
		this.setFocusable(true);
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Method to set up the visual arrangement of the screen
	 */
	public void setupUI() {
		this.setBackground(Color.gray);
		Color[] colors = {Color.red, Color.blue, Color.green};
		this.getContentPane().setLayout(new GridLayout(9, 9));
//		for (Color c: colors) {
//			JPanel panel = new JPanel();
//			panel.setBackground(c);
//			this.getContentPane().add(panel);
//		}
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				JPanel panel = new JPanel();
				panel.setBackground(colors[(i+j)%3]);
				this.getContentPane().add(panel);
			}
		}
	}
	
	/**
	 * Method to generate a valid puzzle based on difficulty
	 * @param difficulty
	 * @return validIncompletePuzzle
	 */
	public Block[][] generateGame(int difficulty){
		//TODO:
		return null;
	}
	/**
	 * Method to check whether the current arrangement is valid or not.
	 * @return true if there are no errors
	 */
	boolean checkGameValid(){
		return true;
	}
	
	
}
