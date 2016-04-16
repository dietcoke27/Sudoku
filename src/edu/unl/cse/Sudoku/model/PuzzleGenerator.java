package edu.unl.cse.Sudoku.model;

import java.util.ArrayList;
import java.util.Random;

public class PuzzleGenerator {

	private static Block[][] base;
	public static Block[][] currentGame;
	private static int dif;
	private static int blockSize = 3;
	private static int gameSize = blockSize * blockSize;

	/**
	 * Method to generate a valid puzzle based on difficulty.
	 * 
	 * @param difficulty
	 *            : should be in the range of [1-3]
	 */
	public static void generateGame(int difficulty) {
		dif = difficulty;
		// // manually create a correct base puzzle that is correct and always
		// the
		// // same
		// Block[] r0 = { new Block(1, true), new Block(2, true),
		// new Block(3, true), new Block(4, true), new Block(5, true),
		// new Block(6, true), new Block(7, true), new Block(8, true),
		// new Block(9, true) };
		// Block[] r1 = { new Block(4, true), new Block(5, true),
		// new Block(6, true), new Block(7, true), new Block(8, true),
		// new Block(9, true), new Block(1, true), new Block(2, true),
		// new Block(3, true) };
		// Block[] r2 = { new Block(7, true), new Block(8, true),
		// new Block(9, true), new Block(1, true), new Block(2, true),
		// new Block(3, true), new Block(4, true), new Block(5, true),
		// new Block(6, true) };
		//
		// Block[] r3 = { new Block(2, true), new Block(3, true),
		// new Block(1, true), new Block(5, true), new Block(6, true),
		// new Block(4, true), new Block(8, true), new Block(9, true),
		// new Block(7, true) };
		// Block[] r4 = { new Block(5, true), new Block(6, true),
		// new Block(4, true), new Block(8, true), new Block(9, true),
		// new Block(7, true), new Block(2, true), new Block(3, true),
		// new Block(1, true) };
		// Block[] r5 = { new Block(8, true), new Block(9, true),
		// new Block(7, true), new Block(2, true), new Block(3, true),
		// new Block(1, true), new Block(5, true), new Block(6, true),
		// new Block(4, true) };
		//
		// Block[] r6 = { new Block(3, true), new Block(1, true),
		// new Block(2, true), new Block(6, true), new Block(4, true),
		// new Block(5, true), new Block(9, true), new Block(7, true),
		// new Block(8, true) };
		// Block[] r7 = { new Block(6, true), new Block(4, true),
		// new Block(5, true), new Block(9, true), new Block(7, true),
		// new Block(8, true), new Block(3, true), new Block(1, true),
		// new Block(2, true) };
		// Block[] r8 = { new Block(9, true), new Block(7, true),
		// new Block(8, true), new Block(3, true), new Block(1, true),
		// new Block(2, true), new Block(6, true), new Block(4, true),
		// new Block(5, true) };
		//
		// // Block[][] basePuzzle = { r0, r1, r2, r3, r4, r5, r6, r7, r8 };
		genBasePuzzle();
		do {
			// try to make a new puzzle based on making swaps to rows and
			// columns
			currentGame = base;
			makeSwaps();
			// make sure the game is still valid before proceeding
		} while (!checkGameValid());
		createEmptyBlocksForPuzzle();
	}

	/**
	 * This method is called to generate the base puzzle to use for the given
	 * block and puzzle size.
	 */
	private static void genBasePuzzle() {
		base = new Block[gameSize][gameSize];
		int[][][][] temp = new int[blockSize][blockSize][blockSize][blockSize];
		int[][] first = new int[blockSize][blockSize];
		for (int i = 0; i < gameSize; i++) {
			first[i / blockSize][i % blockSize] = i + 1;
		}
		for (int gameRow = 0; gameRow < blockSize; gameRow++) {
			for (int gameCol = 0; gameCol < blockSize; gameCol++) {
				// for every blockSize x blockSize group in the game.

				// if this is the first row
				if (gameRow == 0) {
					if (gameCol == 0) {
						// if this is the first block, it is already defined
						temp[gameRow][gameCol] = first;
					} else {
						// otherwise use the previous block to generate the new
						// one
						temp[gameRow][gameCol] = rotateRowUp(temp[gameRow][gameCol - 1]);
					}
				} else {
					// it this is not the first row, use the previous row to
					// generate this one
					temp[gameRow][gameCol] = rotateColLeft(temp[gameRow - 1][gameCol]);
				}
			}
		}
		for (int gameRow = 0; gameRow < blockSize; gameRow++) {
			for (int gameCol = 0; gameCol < blockSize; gameCol++) {
				for (int blockRow = 0; blockRow < blockSize; blockRow++) {
					for (int blockCol = 0; blockCol < blockSize; blockCol++) {
						base[gameRow * blockSize + blockRow][gameCol
								* blockSize + blockCol] = new Block(
								temp[gameRow][gameCol][blockRow][blockCol],
								true);
					}
				}
			}
		}
	}

	/**
	 * Takes in a matrix of integers, and moves all columns to the left.
	 * 
	 * @param b
	 * @return
	 */
	private static int[][] rotateColLeft(int[][] b) {
		int[][] temp = new int[b.length][b.length];
		for (int r = 0; r < b.length; r++) {
			for (int c = 0; c < b.length; c++) {
				temp[r][c] = b[r][(c + 1) % b.length];
			}
		}
		return temp;
	}

	/**
	 * Takes in a matrix of integers and moves all rows up one.
	 * 
	 * @param b
	 * @return
	 */
	private static int[][] rotateRowUp(int[][] b) {
		int[][] temp = new int[b.length][b.length];
		for (int r = 0; r < b.length; r++) {
			temp[r] = b[(r + 1) % b.length];
		}
		return temp;
	}

	/**
	 * Method to check whether the current arrangement is valid or not.
	 * 
	 * @return Whether or not there are any errors in the puzzle.
	 */
	static boolean checkGameValid() {
		// check if the row and column are valid
		if (checkRowsValid() == false || checkColsValid() == false
				|| checkBoxes() == false) {
			return false;
		}
		return true;
	}

	/**
	 * Prints out the value of all of the elements in the matrix. Does not
	 * account for null elements. Size of game must be larger than s x s.
	 * 
	 * @param game
	 * @param s
	 */
	public static void printPuzzle(Block[][] game, int size) {
		for (int i = 0; i < size + Math.sqrt(size); i++) {
			System.out.print("--");
		}
		System.out.println();

		// for every row
		for (int r = 0; r < size; r++) {
			// for every column
			System.out.print("| ");
			for (int c = 0; c < size; c++) {
				// print the value of the element with a space after it
				if (game[r][c] != null) {
					System.out.print(game[r][c].getValue() + " ");
				} else {
					System.out.print("  ");
				}
				if (c % Math.sqrt(size) == Math.sqrt(size) - 1) {
					System.out.print("| ");
				}
			}
			// after each row move to the next line
			System.out.println();
			if (r % Math.sqrt(size) == Math.sqrt(size) - 1) {
				for (int i = 0; i < size + Math.sqrt(size); i++) {
					System.out.print("--");
				}
				System.out.println();
			}
		}
	}

	/**
	 * Checks the if all of the rows are valid.
	 * 
	 * @return If every row in the game is valid.
	 */
	public static boolean checkRowsValid() {
		for (int r = 0; r < gameSize; r++) {
			if (!checkRowValid(r)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if a specific row is valid. Neglects null and empty elements in the
	 * row.
	 * 
	 * @param r
	 * @return if the row is valid
	 */
	public static boolean checkRowValid(int r) {
		// check every element vs every other element in the row
		for (int c = 0; c < gameSize; c++) {
			for (int c2 = c + 1; c2 < gameSize; c2++) {
				// make sure they are also non zero because zero is
				// considered "empty"
				if (currentGame[r][c].getValue() != 0
						&& currentGame[r][c2].getValue() != 0) {
					// if two elements in a row are equal, return false
					if (currentGame[r][c].getValue() == currentGame[r][c2]
							.getValue()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Checks the if all of the columns are valid.
	 * 
	 * @return if every column in the game is valid.
	 */
	public static boolean checkColsValid() {
		for (int c = 0; c < gameSize; c++) {
			if (!checkColValid(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if a specific column is valid.
	 * 
	 * @param c
	 * @return
	 */
	public static boolean checkColValid(int c) {
		// check every element vs every other element in the column
		for (int r = 0; r < gameSize; r++) {
			for (int r2 = r + 1; r2 < gameSize; r2++) {
				// if two elements in a column are equal, return false
				if (currentGame[r][c].getValue() != 0
						&& currentGame[r2][c].getValue() != 0) {
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
	 * Checks an all of the blockSize x blockSize boxes that must contain the
	 * numbers [1-gameSize] each only once
	 * 
	 * @return true if all boxes are valid
	 */
	public static boolean checkBoxes() {
		// for each of the blockSize rows of blockSize x blockSize blocks
		for (int gameRow = 0; gameRow < blockSize; gameRow++) {
			// for each of the blockSize columns of blockSize x blockSize blocks
			for (int gameCol = 0; gameCol < blockSize; gameCol++) {
				// create a temp block that contains only the elements in the
				// blockSize x blockSize blocks
				Block[][] temp = new Block[blockSize][blockSize];
				for (int blockRow = 0; blockRow < blockSize; blockRow++) {
					for (int blockCol = 0; blockCol < blockSize; blockCol++) {
						temp[blockRow][blockCol] = currentGame[gameRow
								* blockSize + blockRow][gameCol * blockSize
								+ blockCol];
					}
				}
				// if that box is not valid return false
				if (!checkBox(temp)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks an individual blockSize x blockSize box to see if it contains the
	 * numbers 1-9 each only once. Parameter box must be blockSize x blockSize
	 * or larger.
	 * 
	 * @param box
	 * @return if the passed matrix is valid
	 */
	private static boolean checkBox(Block[][] box) {
		// for every element in the matrix
		for (int i = 0; i < gameSize; i++) {
			// if the element we are checking is zero, ignore it
			if (box[i / blockSize][i % blockSize].getValue() == 0) {
				continue;
			}
			// check against every other element in the matrix
			for (int j = i + 1; j < 9; j++) {
				// if the values of the two elements are the same, return false.
				if (box[i / blockSize][i % blockSize].getValue() == box[j
						/ blockSize][j % blockSize].getValue()) {
					return false;
				}
			}
		}
		// every element in the box only appears once.
		return true;
	}

	/**
	 * Method takes the base puzzle and randomly decides how many times to swap
	 * rows and columns. Directly modifies the currentGame matrix
	 */
	private static void makeSwaps() {
		Random r = new Random();
		int row1, row2, col1, col2, swaps = r.nextInt(blockSize)+blockSize;
		for (int i = 0; i < swaps; i++) {
			// randomly choose a column
			col1 = r.nextInt(gameSize);
			// generate a set of columns that you can swap with col1
			ArrayList<Integer> temp = findSwapableCol(col1);
			col2 = temp.get(r.nextInt(temp.size()));
			swapColumns(col1, col2);

			// randomly choose 2 rows to swap, where the rows are not the same
			row1 = r.nextInt(gameSize);
			// generate a set of rows that you can swap with row1
			temp = findSwapableRow(row1);
			row2 = temp.get(r.nextInt(temp.size()));
			swapRows(row1, row2);
		}
	}

	/**
	 * Generates a list of columns that are swapable with column c
	 * 
	 * @param c
	 */
	private static ArrayList<Integer> findSwapableCol(int c) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int col = 0; col < gameSize; col++) {
			if (c == col) {
				// do nothing for a duplicate
			} else if (c / blockSize == col / blockSize) {
				// if they are in the same block they are swappable
				temp.add(col);
			} else {
				// for every block in the columns
				boolean[] WholeBlockMatches = new boolean[blockSize];
				for (int blockIndex = 0; blockIndex < blockSize; blockIndex++) {
					// for every element in the column in the block
					boolean[] blockElementMatches = new boolean[blockSize];
					for (int cIndex = 0; cIndex < 3; cIndex++) {
						for (int colIndex = 0; colIndex < 3; colIndex++) {
							if (currentGame[blockIndex * blockSize + cIndex][c]
									.getValue() == currentGame[blockIndex
									* blockSize + colIndex][col].getValue()) {
								blockElementMatches[cIndex] = true;
							}
						}
					}
					boolean everyElementMaches = true;
					for (boolean b : blockElementMatches) {
						everyElementMaches = everyElementMaches & b;
					}
					if (everyElementMaches) {
						WholeBlockMatches[blockIndex] = true;
					}
				}
				boolean everyBlockMaches = true;
				for (boolean b : WholeBlockMatches) {
					everyBlockMaches = everyBlockMaches & b;
				}
				if (everyBlockMaches) {
					temp.add(col);
				}

			}
		}
		return temp;
	}

	/**
	 * Generates a list of rows that are swapable with row r
	 * 
	 * @param r
	 */
	private static ArrayList<Integer> findSwapableRow(int r) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int row = 0; row < gameSize; row++) {
			if (r == row) {
				// do nothing for a duplicate
			} else if (r / blockSize == row / blockSize) {
				// if they are in the same block they are swappable
				temp.add(row);
			} else {
				// if for every block, the set of elements in each block matches

				// for every block in the columns
				boolean[] WholeBlockMatches = new boolean[blockSize];
				for (int blockIndex = 0; blockIndex < blockSize; blockIndex++) {
					// for every element in the column in the block
					boolean[] blockElementMatches = new boolean[blockSize];
					for (int rIndex = 0; rIndex < 3; rIndex++) {
						for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
							if (currentGame[r][blockIndex * blockSize + rIndex]
									.getValue() == currentGame[row][blockIndex
									* blockSize + rowIndex].getValue()) {
								blockElementMatches[rIndex] = true;
							}
						}
					}
					boolean everyElementMaches = true;
					for (boolean b : blockElementMatches) {
						everyElementMaches = everyElementMaches & b;
					}
					if (everyElementMaches) {
						WholeBlockMatches[blockIndex] = true;
					}
				}

				boolean everyBlockMaches = true;
				for (boolean b : WholeBlockMatches) {
					everyBlockMaches = everyBlockMaches & b;
				}
				if (everyBlockMaches) {
					temp.add(row);
				}
			}
		}
		return temp;
	}

	/**
	 * Swaps two columns in the currentGame matrix. a and b must be 0-8
	 * 
	 * @param a
	 * @param b
	 */
	private static void swapColumns(int a, int b) {
		Block temp;
		// for every row
		for (int r = 0; r < gameSize; r++) {
			temp = currentGame[r][a]; // save element from col a
			currentGame[r][a] = currentGame[r][b]; // place element from b into
													// a
			currentGame[r][b] = temp; // place element from a into b
		}
	}

	/**
	 * Swaps two rows in the currentGame matrix a and b must be 0-8
	 * 
	 * @param a
	 * @param b
	 */
	private static void swapRows(int a, int b) {
		Block[] temp = currentGame[a]; // save row a
		currentGame[a] = currentGame[b]; // replace row a with row b
		currentGame[b] = temp; // replace row b with row a
	}

	/**
	 * Takes a difficulty and deletes a number of spaces in the game based on
	 * the difficulty. Difficulty is expected to be 1-3
	 * 
	 * @param difficulty
	 */
	private static void createEmptyBlocksForPuzzle() {
		int[] keepers;
		// for every row
		for (int r = 0; r < gameSize; r++) {
			// determine which columns to keep
			keepers = genKeepers();
			// take the elements we decided to keep and make them un-editable
			for (int c : keepers) {
				currentGame[r][c].setEditable(false);
			}
			for (int c = 0; c < gameSize; c++) {
				currentGame[r][c].setValue(0);
			}
		}
	}

	/**
	 * Generates an array of integers based on the number needed. Numbers are
	 * randomly generated, but evenly distributed.
	 * 
	 * @param len
	 * @return
	 */
	private static int[] genKeepers() {
		Random rand = new Random();
		int[] ret;
		switch (dif) {
		case 1:
			ret = new int[4];
			ret[0] = rand.nextInt(blockSize);
			ret[1] = rand.nextInt(blockSize) + blockSize;
			ret[2] = rand.nextInt(blockSize) + 2 * blockSize;
			do {
				ret[3] = rand.nextInt(gameSize);
			} while (ret[3] == ret[0] || ret[3] == ret[1] || ret[3] == ret[2]);
			break;
		case 2:
			ret = new int[3];
			ret[0] = rand.nextInt(blockSize);
			ret[1] = rand.nextInt(blockSize) + blockSize;
			ret[2] = rand.nextInt(blockSize) + 2 * blockSize;
			break;
		case 3:
			ret = new int[2];
			ret[0] = rand.nextInt(gameSize);
			do {
				ret[1] = rand.nextInt(gameSize);
			} while (ret[1] / blockSize == ret[0] / blockSize);
			break;
		default:
			ret = new int[0];
			break;
		}
		return ret;
	}
}