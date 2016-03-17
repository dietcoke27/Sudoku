package edu.unl.cse.Sudoku.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.*;

import edu.unl.cse.Sudoku.model.Block;
import edu.unl.cse.Sudoku.model.Change;
import edu.unl.cse.Sudoku.model.Node;
import edu.unl.cse.Sudoku.model.PuzzleGenerator;
import edu.unl.cse.Sudoku.model.Stack;

public class SudokuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static int difficulty;
	private long gameTimer;
	private boolean gameWon;
	BlockPanel[][] gamePanels = new BlockPanel[9][9];
//	private JPanel rootPanel;

	private int selectedRow = 0;
	private int selectedCol = 0;
	private BlockPanel selected = null;
	static Stack moves = new Stack();;
	boolean editingNotes;
	public static SudokuPanel mostRecentPanel;

	//PHASE ONE REQUREMENTS
	//TODO: GAME STARTS WITH PLAYER SELECTING THEIR DIFFICULTY
	//TODO: ONCE EVERY TILE IS FILLED, CHECK IF THE PUZZLE IS SOLVED
	//TODO: PLAYER CAN CLICK AN UNDO BUTTON TO ROLL BACK THE LAST CHANGE MADE
	//TODO: ADD A NEW GAME BUTTON TO ALLOW PLAYER TO DISCARD THE CURRENT GAME AND GENERATE A NEW ONE
	
	//PHASE TWO REQUIREMENTS
	//TODO: CHECK IF EACH TILE IS VALID AS IT IS PLACED
	//TODO: ADD FEATURE TO ALLOW NOTES
	
	//FUTURE PHASE REQUIREMENTS
	//TODO: ADD ABILITY TO SAVE THE GAME
	//TODO: ADD ABILITY TO LOAD A SAVED GAME
	//TODO: ADD A TIMER TO SEE HOW LONG EACH PUZZLE TAKES

	/**
	 * SudokuPanel constructor used to define properties of the game window
	 * Generates a valid game, and displays it on the screen
	 */
	public SudokuPanel() {
		SudokuPanel.mostRecentPanel = this;
		this.setVisible(true);
		this.setFocusable(true);
		this.setSize(400, 400);
		this.addKeyListener(new KL());
		
		PuzzleGenerator.generateGame(SudokuPanel.difficulty);
		
		this.setBackground(Color.gray);
		this.setLayout(new GridLayout(3,3));
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				BlockPanel panel = new BlockPanel(i, j);
				panel.block = PuzzleGenerator.currentGame[i][j];
				panel.checkLabel();
				this.gamePanels[i][j] = panel;
				SuperBlockPanel.addBlockPanel(panel, i, j);
				panel.addMouseListener(new ML());
				panel.displayNumberBlock.setBackground(Color.white);
				panel.updateEditable();
			}
		}
		
		for (SuperBlockPanel p : SuperBlockPanel.flattenPanelList()) {
			this.add(p);
		}
	}
	
//-------------------------KEY LISTENER CLASS-----------------------
	
	/**
	 * This class takes events from the keyboard to interpret them.
	 * 
	 */
	private class KL extends KeyAdapter{
		public void keyReleased(KeyEvent e){
			//TODO: WRITE KEYRELEASED CODE FOR KL AND FOR SUKOKUFRAME
			//handle out of bounds selected blocks
			if(selectedRow >8 || selectedCol >8 || selectedRow <0 || selectedCol <0){
				return;
			}
			//parse which key was pressed
			int val = -1;
			switch(e.getKeyChar()){
			case '1':
				val=1;
				break;
			case '2':
				val=2;
				break;
			case '3':
				val=3;
				break;
			case '4':
				val=4;
				break;
			case '5':
				val=5;
				break;
			case '6':
				val=6;
				break;
			case '7':
				val=7;
				break;
			case '8':
				val=8;
				break;
			case '9':
				val=9;
				break;
			case '\u0008':
				//TODO: HANDLE THE BACKSPACE KEY BEING PRESSED
				val = 0;
				break;
			default:
				return;
			}			
			
//			if (selected != null) {
//				selected.label.setText(String.format("%c", e.getKeyChar()));
//			}
//			if the block is non null
			if (selected != null){
				if(selected.block.isEditable()){
					//if we are editing notes, then add the key pressed as a note to the selected Block
					if(editingNotes){
						selected.block.addNote(val);
					//otherwise change the value of the block
					}else{
						
						SudokuPanel.moves.push(new Node(null, new Change(selected.block.getValue(), selected.row, selected.column)));
						selected.block.setValue(val);
						selected.checkLabel();
					}
				}
				
				
			}
			PuzzleGenerator.checkComplete();
		}
	}
//------------------------END KEY LISTENER CLASS--------------------
	
//------------------------MOUSE LISTENER CLASS---------------------
	/**
	 * This class handles mouse button events.
	 * left click, right click, mouse wheel click and entering or exiting the frame.
	 *
	 */
	private class ML extends MouseAdapter{
		
		public void mouseReleased(MouseEvent e){
			//TODO: IMPLEMENT MORE DETAILED MOUSE RELEASED METHOD, OR IMPLEMENT MOUSEPRESSED OR MOUSE CLICKED
			
			SudokuPanel.mostRecentPanel.requestFocus();
			Component c = e.getComponent();
			if (c instanceof BlockPanel ) {
				if (selected != null) {
					selected.deselectPanel();
					
				}
				
				BlockPanel panel = (BlockPanel) c;
				selected = panel;
				System.out.println(String.format("%d, %d", selected.row, selected.column));
				panel.panelWasClicked();
			}
			//is called when one of the mouse buttons is released
		}
	}
	//-------------------END MOUSE LISTENER CLASS---------------------
}
