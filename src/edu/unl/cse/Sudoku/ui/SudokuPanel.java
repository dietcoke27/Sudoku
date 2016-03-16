package edu.unl.cse.Sudoku.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import edu.unl.cse.Sudoku.model.Block;
import edu.unl.cse.Sudoku.model.Stack;

public class SudokuPanel extends JPanel {
	
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
	private BlockPanel[][] gamePanels = new BlockPanel[9][9];
//	private JPanel rootPanel;
	
	/**
	 * SudokuFrame constructor
	 */
	public SudokuPanel() {
		this.setupUI();
		this.setFocusable(true);
		this.setSize(400, 400);
	}
	
	/**
	 * Method to set up the visual arrangement of the screen
	 */
	public void setupUI() {
		this.setBackground(Color.gray);
		Color[] colors = {Color.red, Color.blue, Color.green};
		this.setLayout(new GridLayout(9, 9));
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				BlockPanel panel = new BlockPanel();
				this.gamePanels[i][j] = panel;
				panel.displayNumberBlock.setBackground(colors[(i+j)%3]);
				this.add(panel);
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
