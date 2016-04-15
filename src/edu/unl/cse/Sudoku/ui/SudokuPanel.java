package edu.unl.cse.Sudoku.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import edu.unl.cse.Sudoku.model.*;

public class SudokuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static int difficulty;
	public BlockPanel[][] gamePanels = new BlockPanel[9][9];
	private BlockPanel selected = null;
	public static Stack moves = new Stack();;
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
		PuzzleGenerator.generateGame(difficulty);
		this.setLayout(new GridLayout(3, 3));
		this.requestFocus();
		for (int superRow = 0; superRow < 3; superRow++) {
			for (int superCol = 0; superCol < 3; superCol++) {
				SuperBlockPanel temp = new SuperBlockPanel();
				for (int blockRow = 0; blockRow < 3; blockRow++) {
					for (int blockCol = 0; blockCol < 3; blockCol++) {
						BlockPanel panel = new BlockPanel(3 * superRow
								+ blockRow, 3 * superCol + blockCol);
						panel.setBlock(PuzzleGenerator.currentGame[3 * superRow
								+ blockRow][3 * superCol + blockCol]);

						gamePanels[3 * superRow + blockRow][3 * superCol
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
		
//		this.notesButton = new JButton("Edit Notes");
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
	 * Method to handle a key being pressed. Called from the launcher when a key is pressed.
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		// parse which key was pressed		
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
		case 8: //#backspace and delete
		case 127:
			val = 0;
			break;
		case 37: //left arrow
			if(selected != null){
				if(selected.getCol() > 0){
					selected.deselectPanel();
					selected = gamePanels[selected.getRow()][selected.getCol()-1];
					selected.selectPanel();
				}
			}
			break;
		case 38: //up arrow
			if(selected != null){
				if(selected.getRow() > 0){
					selected.deselectPanel();
					selected = gamePanels[selected.getRow()-1][selected.getCol()];
					selected.selectPanel();
				}
			}
			break;
		case 39: //right arrow
			if(selected != null){
				if(selected.getCol() < 8){
					selected.deselectPanel();
					selected = gamePanels[selected.getRow()][selected.getCol()+1];
					selected.selectPanel();
				}
			}
			break;
		case 40: //down arrow
			if(selected != null){
				if(selected.getRow() < 8){
					selected.deselectPanel();
					selected = gamePanels[selected.getRow()+1][selected.getCol()];
					selected.selectPanel();
				}
			}
			break;
		
		default:
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
				}
			}
		}
		if(isCorrect() && isComplete()){
			JOptionPane.showMessageDialog(this, "You won!", "Check Game",
					JOptionPane.PLAIN_MESSAGE, null);
		}
		
	}

	public boolean isComplete(){
		for(int r =0; r< 9;r++){
			for(int c = 0; c<9; c++){
				if(gamePanels[r][c].getBlock().getValue() == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isCorrect(){
		boolean ret = true;
		ArrayList<BlockPanel> errors = new ArrayList<BlockPanel>();
		for(int con = 0; con < 9; con++){
			for(int v1 = 0; v1 < 9; v1++){
				for(int v2 = v1+1; v2 < 9; v2++){
					// duplicate in row
					if(gamePanels[con][v1].getBlock().getValue() != 0 && gamePanels[con][v2].getBlock().getValue() != 0 ){
						if(gamePanels[con][v1].getBlock().getValue() == gamePanels[con][v2].getBlock().getValue() ){
							errors.add(gamePanels[con][v1]);
							errors.add(gamePanels[con][v2]);
							ret = false;
						}
					}
					//duplicate in column
					if(gamePanels[v1][con].getBlock().getValue() != 0 && gamePanels[v2][con].getBlock().getValue() != 0 ){
						if(gamePanels[v1][con].getBlock().getValue() == gamePanels[v2][con].getBlock().getValue() ){
							errors.add(gamePanels[v1][con]);
							errors.add(gamePanels[v2][con]);
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
