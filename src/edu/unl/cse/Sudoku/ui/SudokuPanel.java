package edu.unl.cse.Sudoku.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.*;

import edu.unl.cse.Sudoku.model.Block;
import edu.unl.cse.Sudoku.model.Stack;

public class SudokuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static boolean titleScreenShown = true;
	private static boolean gameScreenShown = false;
	private static boolean winScreenShown = false;
	private static int difficulty;
	private long gameTimer;
	private boolean gameWon;
	BlockPanel[][] gamePanels = new BlockPanel[9][9];
//	private JPanel rootPanel;

	private int selectedRow = 0;
	private int selectedCol = 0;
	private static Block[][] currentGame;
	static Stack moves;
	boolean editingNotes;

	// private JPanel rootPanel;

	//PHASE ONE REQUREMENTS
	//TODO: GAME STARTS WITH PLAYER SELECTING THEIR DIFFICULTY
	//TODO: CLICK ON A TILE TO SELECT THAT TILE
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
	 * Main execution body
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		// create the window for the game
		SudokuPanel frame = new SudokuPanel();
		//create loop for game play
		while (true) {
			// display start screen
			while (titleScreenShown) {
				frame.showTitleScreen();
//				System.out.println("title Screen");
//				for(int i =0; i< delay; i++);//add delay for printing
			}
			// select difficulty
			difficulty = 3;

			// generate game based on difficulty
			generateGame(difficulty);
			moves = new Stack();
			// display the game screen
			while (gameScreenShown) {
				frame.showGameScreen();
//				System.out.println("game Screen");
//				for(int i =0; i< delay; i++);//add delay for printing
			}
			while (winScreenShown){
				frame.showWinScreen();
//				System.out.println("win Screen");
//				for(int i =0; i< delay; i++);//add delay for printing
			}
		}
	}

	/**
	 * SudokuPanel constructor Used to define properties of the game window
	 */
	public SudokuPanel() {
		this.setVisible(true);
		this.setFocusable(true);
		this.setSize(400, 400);
		this.addKeyListener(new KL());
		this.addMouseListener(new ML());
		this.setupUI();
	}

	/**
	 * This method is intended to display all relevant pieces of the title screen
	 */
	public void showTitleScreen(){
//		this.setBackground(Color.red);
	}
	
	/**
	 * This method is intended to display all relevant pieces of the game screen
	 */
	public void showGameScreen(){
		this.setBackground(Color.blue);
	}
	
	/**
	 * This method is intended to display all relevant pieces of the win screen
	 */
	public void showWinScreen(){
		this.setBackground(Color.green);
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
	 * 
	 * @param difficulty
	 * @return validIncompletePuzzle
	 */
	public static void generateGame(int difficulty) {
		// manually create a correct base puzzle that is correct and always the
		// same
		Block[] r0 = { new Block(1, true), new Block(2, true),
				new Block(3, true), new Block(4, true), new Block(5, true),
				new Block(6, true), new Block(7, true), new Block(8, true),
				new Block(9, true) };
		Block[] r1 = { new Block(4, true), new Block(5, true),
				new Block(6, true), new Block(7, true), new Block(8, true),
				new Block(9, true), new Block(1, true), new Block(2, true),
				new Block(3, true) };
		Block[] r2 = { new Block(7, true), new Block(8, true),
				new Block(9, true), new Block(1, true), new Block(2, true),
				new Block(3, true), new Block(4, true), new Block(5, true),
				new Block(6, true) };
		Block[] r3 = { new Block(2, true), new Block(3, true),
				new Block(4, true), new Block(5, true), new Block(6, true),
				new Block(7, true), new Block(8, true), new Block(9, true),
				new Block(1, true) };
		Block[] r4 = { new Block(5, true), new Block(6, true),
				new Block(7, true), new Block(8, true), new Block(9, true),
				new Block(1, true), new Block(2, true), new Block(3, true),
				new Block(4, true) };
		Block[] r5 = { new Block(8, true), new Block(9, true),
				new Block(1, true), new Block(2, true), new Block(3, true),
				new Block(4, true), new Block(5, true), new Block(6, true),
				new Block(7, true) };
		Block[] r6 = { new Block(3, true), new Block(4, true),
				new Block(5, true), new Block(6, true), new Block(7, true),
				new Block(8, true), new Block(9, true), new Block(1, true),
				new Block(2, true) };
		Block[] r7 = { new Block(6, true), new Block(7, true),
				new Block(8, true), new Block(9, true), new Block(1, true),
				new Block(2, true), new Block(3, true), new Block(4, true),
				new Block(5, true) };
		Block[] r8 = { new Block(9, true), new Block(1, true),
				new Block(2, true), new Block(3, true), new Block(4, true),
				new Block(5, true), new Block(6, true), new Block(7, true),
				new Block(8, true) };

		Block[][] basePuzzle = { r0, r1, r2, r3, r4, r5, r6, r7, r8 };
		do{
			//try to make a new puzzle based on making swaps to rows and columns
			currentGame = basePuzzle;
			makeSwaps();
			//make sure the game is still valid before proceeding
		}while(!checkGameValid());
		printBlockMatrix(currentGame, 9);
		createEmptyBlocksForPuzzle(difficulty);
		changeEditabilityOfGame();
		printBlockMatrix(currentGame, 9);
	}

	/**
	 * Method to check whether the current arrangement is valid or not.
	 * 
	 * @return true if there are no errors
	 */
	static boolean checkGameValid() {
		// check if the row and column are valid
		//TODO: OPTIMIZE CHECK GAME TO BE FASTER
		//TODO: IMPLEMENT A SINGLE METHOD TO CHECK A SINGLE ROW AND COLUMN FASTER
		
		if (checkRowsValid() == false ||checkColsValid() == false ||checkBoxes() == false) {
			return false;
		}
		System.out.println("valid");
		return true;
	}
	
	/**
	 * Prints out the value of all of the elements in the matrix.
	 * Does not account for null elements.
	 * Size of game must be larger than s x s.
	 * @param game
	 * @param s
	 */
	public static void printBlockMatrix(Block[][] game, int s) {
		System.out.println("---------------------------------------------");
		//for every row
		for (int r = 0; r < s; r++) {
			//for every column
			for (int c = 0; c < s; c++) {
				//print the value of the element with a space after it
				if(game[r][c] != null){
					System.out.print(game[r][c].getValue() + "  ");
				}else{
					System.out.print("   ");
				}
			}
			//after each row move to the next line
			System.out.println();
		}
	}

	/**
	 * Checks the if all of the rows are valid.
	 * 
	 * @return if every row in the game is valid
	 */
	public static boolean checkRowsValid() {
		// for every row
		for (int r = 0; r < 9; r++) {
			if(!checkRowValid(r)){
				return false;
			}
		}
		// no invalid rows in the game
		return true;
	}

	/**
	 * Check if a specific row is valid.
	 * 
	 * @param r
	 * @return if the row is valid
	 */
	public static boolean checkRowValid(int r){
		// check every element vs every other element in the row
		for (int c = 0; c < 9; c++) {
			for (int c2 = c + 1; c2 < 9; c2++) {
				//check if the two elements are not null
				if (currentGame[r][c] != null && currentGame[r][c2] != null) {
					//make sure they are also non zero because zero is considered "empty"
					if (currentGame[r][c].getValue() != 0 && currentGame[r][c2].getValue() != 0) {
						// if two elements in a row are equal, return false
						if (currentGame[r][c].getValue() == currentGame[r][c2]
								.getValue()) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks the if all of the columns are valid. Does NOT account for null
	 * boxes
	 * 
	 * @return if every column in the game is valid
	 */
	public static boolean checkColsValid() {
		// for every column
		for (int c = 0; c < 9; c++) {
			if(!checkColValid(c)){
				return false;
			}
		}
		// no invalid columns in the game
		return true;
	}

	/**
	 * Checks if a specific column is valid
	 * @param c
	 * @return
	 */
	public static boolean checkColValid(int c) {
		// check every element vs every other element in the column
		for (int r = 0; r < 9; r++) {
			for (int r2 = r + 1; r2 < 9; r2++) {
				// if two elements in a column are equal, return false
				if (currentGame[r][c].getValue() != 0 && currentGame[r2][c].getValue() != 0) {
					if (currentGame[r][c].getValue() == currentGame[r2][c]
							.getValue()) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks an all of the 3x3 boxes that must contain the numbers 1-9 each only once
	 * @return true if all boxes are valid
	 */
	public static boolean checkBoxes() {
		//for each of the 3 rows of 3x3 blocks
		for (int r = 0; r < 3; r++) {
			//for each of the 3 columns of 3xx3 blocks
			for (int c = 0; c < 3; c++) {
				//create a temp block that contains only the elements in the 3x3
				Block[][] temp = {
						{ currentGame[3 * r + 0][3 * c + 0],
						  currentGame[3 * r + 0][3 * c + 1],
						  currentGame[3 * r + 0][3 * c + 2] },
						{ currentGame[3 * r + 1][3 * c + 0],
						  currentGame[3 * r + 1][3 * c + 1],
					   	  currentGame[3 * r + 1][3 * c + 2] },
						{ currentGame[3 * r + 2][3 * c + 0],
						  currentGame[3 * r + 2][3 * c + 1],
						  currentGame[3 * r + 2][3 * c + 2] } };
				//if that box is not valid return false
				if (!checkBox(temp)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks an individual 3x3 box to see if it contains the numbers 1-9 each only once.
	 * Parameter box must be 3x3 or larger.
	 * @param box
	 * @return if the passed matrix is valid
	 */
	public static boolean checkBox(Block[][] box) {
		//for every element in the matrix
		for (int i = 0; i < 9; i++) {
			//if the element we are checking is zero, ignore it
			if(box[i / 3][i % 3].getValue() == 0){
				continue;
			}
			
			//check against every other element in the matrix
			for (int j = i + 1; j < 9; j++) {
				//if the values of the two elements are the same, return false.
				if (box[i / 3][i % 3].getValue() == box[j / 3][j % 3]
						.getValue()) {
					return false;
				}
			}
		}
		//every element in the box only appears once.
		return true;
	}
	
	/**
	 * Method takes the base puzzle and randomly decides how many times to swap rows and columns.
	 * Directly modifies the currentGame matrix
	 */
	public static void makeSwaps(){
		Random r = new Random();
		int rand1, rand2, swaps = r.nextInt(2);
		// determine how many times to swap columns
		for (int i = 0; i < swaps; i++) {
			// randomly choose 2 columns to swap, where the cols are not the same
			rand1 = r.nextInt(8);
			do {
				rand2 = r.nextInt(8);
			} while (rand1 == rand2);
			swapColumns(rand1, rand2);
			// randomly choose 2 rows to swap, where the rows are not the same
			rand1 = r.nextInt(8);
			do {
				rand2 = r.nextInt(8);
			} while (rand1 == rand2);
			swapRows(rand1, rand2);
		}
	}
	
	/**
	 * Swaps two columns in the currentGame matrix.
	 * a and b must be 0-8
	 * @param a
	 * @param b
	 */
	public static void swapColumns(int a, int b){
		Block temp;
		//for every row
		for(int r=0; r<9; r++){
			temp= currentGame[r][a];						//save element from col a
			currentGame[r][a]= currentGame[r][b];			//place element from b into a
			currentGame[r][b] = temp;						//place element from a into b
		}
	}
	
	/**
	 * Swaps two rows in the currentGame matrix
	 * a and b must be 0-8
	 * @param a
	 * @param b
	 */
	public static void swapRows(int a, int b){
		Block[] temp = currentGame[a];						//save row a
		currentGame[a] = currentGame[b];					//replace row a with row b
		currentGame[b] = temp;								//replace row b with row a
	}
	
	/**
	 * Takes a difficulty and deletes a number of spaces in the game based on the difficulty.
	 * Difficulty is expected to be 1-3
	 * @param difficulty
	 */
	private static void createEmptyBlocksForPuzzle(int dif) {
		int numToRemovePerRow;
		Random rand = new Random();
		switch(dif){
		case 1:
			numToRemovePerRow = 5;
			break;
		case 2:
			numToRemovePerRow = 6;
			break;
		case 3:
			numToRemovePerRow = 7;
			break;
		default:
			System.out.println("invalid difficulty selected");
			return;
		}
		//for every row
		for(int r=0; r<9;r++){
			for(int removalNum = 0; removalNum<numToRemovePerRow ; removalNum++){
				int col;
				do{
					col = rand.nextInt(9);
					
				}while(currentGame[r][col].getValue() == 0);
				currentGame[r][col].setValue(0);
			}
			
		}
	}
	
	/**
	 * turns the editable variable of all non null elements in current game to false
	 */
	public static void changeEditabilityOfGame(){
		for(int r = 0; r<9; r++){
			for(int c = 0; c<9; c++){
				if(currentGame[r][c].getValue() != 0){
					currentGame[r][c].setEditable(false);
				}
			}
		}
		
	}
	
	public static void checkComplete(){
		for(int i=0;i<9; i++)
		{
			for(int j = 0;j<9;j++)
			{
				if(currentGame[i][j].getValue() ==0)
				{
					return;
				}
			}
		}
		if(checkGameValid()== true)
		{
		gameScreenShown = false;
		winScreenShown = true;
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
			int val =0;
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
			case '':
				//TODO: HANDLE THE BACKSPACE KEY BEING PRESSED
				break;
			default:
				return;
			}			
			//if the block is non null but is editable, proceed
			if (currentGame[selectedRow][selectedCol] != null && (currentGame[selectedRow][selectedCol].isEditable())){
				//if we are editing notes, then add the key pressed as a note to the selected Block
				if(editingNotes){
					currentGame[selectedRow][selectedCol].addNote(val);
				//otherwise change the value of the block
				}else{
					currentGame[selectedRow][selectedCol].setValue(val);
				}
			}
			printBlockMatrix(currentGame, 9);
			checkComplete();
//			System.out.println("KeyReleased: "+e);
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
		public void mouseClicked(MouseEvent e){
			//called when one of the mouse buttons is clicked
		}
		
		public void mouseEntered(MouseEvent e){
			//when the mouse is moved from another window to this one
		}
		
		public void mouseExited(MouseEvent e){
			//when the mouse if moved from this window to another
		}
		
		public void mousePressed(MouseEvent e){
			//triggered when one of the mouse buttons is pressed down
		}
		
		public void mouseReleased(MouseEvent e){
			//TODO: IMPLEMENT MORE DETAILED MOUSE RELEASED METHOD, OR IMPLEMENT MOUSEPRESSED OR MOUSE CLICKED
			int x = e.getX();
			int y = e.getY();
			//is called when one of the mouse buttons is released
			if(titleScreenShown){
				titleScreenShown = false;
				gameScreenShown = true;
			}else if(gameScreenShown){
				gameScreenShown = false;
				winScreenShown = true;
			}else if(winScreenShown){
				winScreenShown = false;
				titleScreenShown = true;
			}
		}
	}
	//-------------------END MOUSE LISTENER CLASS---------------------
}
