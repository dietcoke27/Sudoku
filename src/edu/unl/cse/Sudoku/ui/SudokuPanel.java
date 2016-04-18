package edu.unl.cse.Sudoku.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import edu.unl.cse.Sudoku.model.*;

public class SudokuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static int difficulty;
	public static int blockSize = 3;
	public static int gameSize;
	public BlockPanel[][] gamePanels;
	private BlockPanel selected;
	public static Stack moves = new Stack();
	public boolean editingNotes = false;
	public JButton newGameButton;
	public JButton undoButton;
	public JRadioButton notesButton;

	// PHASE TWO REQUIREMENTS
	// TODO: CHECK IF EACH TILE IS VALID AS IT IS PLACED
	// TODO: ADD FEATURE TO ALLOW NOTES

	// FUTURE PHASE REQUIREMENTS
	// TODO: ADD ABILITY TO SAVE THE GAME
	// TODO: ADD ABILITY TO LOAD A SAVED GAME
	// TODO: ADD A TIMER TO SEE HOW LONG EACH PUZZLE TAKES

	/**
	 * SudokuPanel constructor used to define properties of the game window
	 * Generates a valid game, and displays it on the screen
	 */
	public SudokuPanel() {
		this.selected = null;
		this.gameSize = blockSize*blockSize;
		gamePanels = new BlockPanel[gameSize][gameSize];
		PuzzleGenerator.generateGame(difficulty, blockSize);
		this.setLayout(new GridLayout(blockSize, blockSize));
		this.requestFocus();
		for (int superRow = 0; superRow < blockSize; superRow++) {
			for (int superCol = 0; superCol < blockSize; superCol++) {
				SuperBlockPanel temp = new SuperBlockPanel();
				for (int blockRow = 0; blockRow < blockSize; blockRow++) {
					for (int blockCol = 0; blockCol < blockSize; blockCol++) {
						BlockPanel panel = new BlockPanel(blockSize * superRow
								+ blockRow, blockSize * superCol + blockCol);
						panel.setBlock(PuzzleGenerator.currentGame[blockSize * superRow
								+ blockRow][blockSize * superCol + blockCol]);

						gamePanels[blockSize * superRow + blockRow][blockSize * superCol
								+ blockCol] = panel;
						panel.update();
						panel.setColors();
						panel.addMouseListener(new ML());
						temp.addBlockPanel(panel);
					}
				}
				this.add(temp);
			}
		}
	}

	/**
	 * Method that creates and returns a JPanel to house buttons
	 */
	public JPanel createButtonPanel() {
		// make a new panel to house elements
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		// add a new label element
		this.newGameButton = new JButton("New Game");
		this.newGameButton.setFocusable(false);
		this.newGameButton.addActionListener(this);
		JPanel buttonBlock = Utilities.createPanelWithComponentCentered(
				this.newGameButton, 5, 1);
		panel.add(buttonBlock);

		this.undoButton = new JButton("Undo");
		this.undoButton.setFocusable(false);
		this.undoButton.addActionListener(this);
		buttonBlock = Utilities.createPanelWithComponentCentered(
				this.undoButton, 5, 1);
		panel.add(buttonBlock);

		this.notesButton = new JRadioButton("Edit Notes");
		
		this.notesButton.setFocusable(false);
		this.notesButton.addActionListener(this);
		buttonBlock = Utilities.createPanelWithComponentCentered(
				this.notesButton, 5, 1);
		panel.add(buttonBlock);
		return panel;
	}

	/**
	 * Method to handle buttons being pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		if (this.newGameButton == e.getSource()) {
			MainFrame.isTitleScreen = true;
		} else if (this.undoButton == e.getSource()) {
			Change c = SudokuPanel.moves.pop();
			if (c != null) {
				BlockPanel p = this.gamePanels[c.getRow()][c.getCol()];
				p.getBlock().setValue(c.getPrevVal());
				p.update();
			}
			isCorrect();
		} else if (this.notesButton == e.getSource()) {
			editingNotes = !editingNotes;
		}
	}

	
	/**
	 * Called to test if every tile in the puzzle is filled.
	 * @return
	 */
	public boolean isComplete(){
		for(int r =0; r< gameSize;r++){
			for(int c = 0; c<gameSize; c++){
				if(gamePanels[r][c].getBlock().getValue() == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Called to test if the current arrangement of the puzzle is correct. Errors are highlighted with a red background.
	 * @return
	 */
	public boolean isCorrect(){
		boolean ret = true;
		ArrayList<BlockPanel> errors = new ArrayList<BlockPanel>();
		for(int con = 0; con < gameSize; con++){
			for(int v1 = 0; v1 < gameSize; v1++){
				for(int v2 = v1+1; v2 < gameSize; v2++){
					BlockPanel a, b;
					// duplicate in row
					a = gamePanels[con][v1];
					b = gamePanels[con][v2];
					if(a.getBlock().getValue() != 0 && b.getBlock().getValue() != 0 ){
						if(a.getBlock().getValue() == b.getBlock().getValue() ){
							errors.add(a);
							errors.add(b);
							ret = false;
						}
					}
					//duplicate in column
					a = gamePanels[v1][con];
					b = gamePanels[v2][con];
					if(a.getBlock().getValue() != 0 && b.getBlock().getValue() != 0 ){
						if(a.getBlock().getValue() == b.getBlock().getValue() ){
							errors.add(a);
							errors.add(b);
							ret = false;
						}
					}
					//duplicate in block
					a = gamePanels[blockSize*(con/blockSize) + v1/blockSize][blockSize*(con%blockSize) + v1%blockSize];
					b = gamePanels[blockSize*(con/blockSize) + v2/blockSize][blockSize*(con%blockSize) + v2%blockSize];
					
						
					if(a.getBlock().getValue() != 0 && b.getBlock().getValue() != 0 ){
						if(a.getBlock().getValue() == b.getBlock().getValue() ){
							System.out.println("con:"+con + " v1:"+v1 + " v2:"+v2 + 
									" a:"+ (con/blockSize + v1/blockSize)+","+ (con%blockSize + v1%blockSize)+ 
									" b:"+ (con/blockSize + v2/blockSize)+","+ (con%blockSize + v2%blockSize));
							errors.add(a);
							errors.add(b);
							ret = false;
						}
					}
					gamePanels[con][v1].update();
					gamePanels[v1][con].update();
					gamePanels[con][v1].setBackground(Color.black);
					gamePanels[v1][con].setBackground(Color.black);
				}
			}
		}
		for(BlockPanel e : errors){
			e.setBackground(Color.red);
		}
		return ret;
	}
	
	
	
	
	
	/**
	 * Method to handle a key being pressed. Called from the launcher when a key is pressed.
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		// parse which key was pressed		
		int val = -1;
		if(gameSize >=4){
			val = keyReleased4(e);
		}
		if(gameSize >= 9 && val == -1){
			val = keyReleased9(e);
		}
		if(gameSize >= 16 && val == -1){
			val = keyReleased16(e);
		}
		switch (e.getKeyCode()) {
		case 37: //left arrow
			if(selected != null){
				if(selected.getCol() > 0){
					selected.deselectPanel();
					selected = gamePanels[selected.getRow()][selected.getCol()-1];
					selected.selectPanel();
				}
			}
			return;
		case 38: //up arrow
			if(selected != null){
				if(selected.getRow() > 0){
					selected.deselectPanel();
					selected = gamePanels[selected.getRow()-1][selected.getCol()];
					selected.selectPanel();
				}
			}
			return;
		case 39: //right arrow
			if(selected != null){
				if(selected.getCol() < gameSize-1){
					selected.deselectPanel();
					selected = gamePanels[selected.getRow()][selected.getCol()+1];
					selected.selectPanel();
				}
			}
			return;
		case 40: //down arrow
			if(selected != null){
				if(selected.getRow() < gameSize-1){
					selected.deselectPanel();
					selected = gamePanels[selected.getRow()+1][selected.getCol()];
					selected.selectPanel();
				}
			}
			return;
		}
		// if the block is non null
		if (selected != null && val != -1) {
			if (selected.getBlock().isEditable()) {
				// if we are editing notes, then add the key pressed as a
				// note to the selected Block
				if (editingNotes) {
					selected.getBlock().toggleNote(val);
					// otherwise change the value of the block
				} else {
					Node temp = new Node(null, new Change(selected.getBlock()
							.getValue(), selected.getRow(), selected.getCol()));
					SudokuPanel.moves.push(temp);
					selected.getBlock().setValue(val);
					selected.update();
					
					
					//automatically check if the puzzle is complete and correct after every button is pressed.
					if(isCorrect() && isComplete()){
						JOptionPane.showMessageDialog(this, "You won!", "Congratulations!",
								JOptionPane.PLAIN_MESSAGE, null);
					}
				}
			}
		}
	}

	private static int keyReleased4(KeyEvent e){
		int val = -1;
		switch (e.getKeyCode()) {
		case 49: //#1
		case 97:
			val = 1;
			break;
		case 50: //#2
		case 98:
			val = 2;
			break;
		case 51: //#3
		case 99:
			val = 3;
			break;
		case 52: //#4
		case 100:
			val = 4;
			break;
		case 8: //#backspace and delete
		case 127:
			val = 0;
			break;
		}
		return val;
	}
	
	private static int keyReleased9(KeyEvent e){
		int val = -1;
		switch (e.getKeyCode()) {
		case 53: //#5
		case 101:
			val = 5;
			break;
		case 54: //#6
		case 102:
			val = 6;
			break;
		case 55: //#7
		case 103:
			val = 7;
			break;
		case 56: //#8
		case 104:
			val = 8;
			break;
		case 57: //#9
		case 105:
			val = 9;
			break;
		}
		return val;
	}
	
	private static int keyReleased16(KeyEvent e){
		int val =-1;
		switch(e.getKeyCode()){
		case 65:
			val = 10;
			break;
		case 66:
			val = 11;
			break;
		case 67:
			val = 12;
			break;
		case 68:
			val = 13;
			break;
		case 69:
			val = 14;
			break;
		case 70:
			val = 15;
			break;
		}
		return val;
	}
	
	// ------------------------MOUSE LISTENER CLASS---------------------
	/**
	 * This class handles mouse button events. left click, right click, mouse
	 * wheel click and entering or exiting the frame.
	 *
	 */
	private class ML extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			Component c = e.getComponent();
			if (c instanceof BlockPanel) {
				if (selected != null) {
					selected.deselectPanel();
				}
				BlockPanel panel = (BlockPanel) c;
				selected = panel;
				panel.selectPanel();
			}
			// is called when one of the mouse buttons is released
		}
	}// -------------------END MOUSE LISTENER CLASS---------------------
}