package edu.unl.cse.Sudoku.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import edu.unl.cse.Sudoku.model.*;

public class SudokuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static int difficulty;
	public BlockPanel[][] gamePanels = new BlockPanel[9][9];
	private BlockPanel selected = null;
	public static Stack moves = new Stack();;
	public boolean editingNotes;
	public JButton newGameButton;
	public JButton undoButton;
	public JButton checkButton;

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

		this.checkButton = new JButton("Check");
		this.checkButton.setFocusable(false);
		this.checkButton.addActionListener(this);
		buttonBlock = Utilities.createPanelWithComponentCentered(
				this.checkButton, 5, 1);
		panel.add(buttonBlock);
		return panel;
	}

	@Override
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
		} else if (this.checkButton == e.getSource()) {
			if (PuzzleGenerator.checkComplete()) {
				JOptionPane.showMessageDialog(this, "You won!", "Check Game",
						JOptionPane.PLAIN_MESSAGE, null);
			} else {
				JOptionPane.showMessageDialog(this, "Game is not valid.",
						"Check Game", JOptionPane.PLAIN_MESSAGE, null);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// parse which key was pressed
		int val = -1;
		switch (e.getKeyChar()) {
		case '1':
			val = 1;
			break;
		case '2':
			val = 2;
			break;
		case '3':
			val = 3;
			break;
		case '4':
			val = 4;
			break;
		case '5':
			val = 5;
			break;
		case '6':
			val = 6;
			break;
		case '7':
			val = 7;
			break;
		case '8':
			val = 8;
			break;
		case '9':
			val = 9;
			break;
		case '\u0008':
			val = 0;
			break;
		default:
			return;
		}
		// if the block is non null
		if (selected != null) {
			if (selected.getBlock().isEditable()) {
				// if we are editing notes, then add the key pressed as a
				// note to the selected Block
				if (editingNotes) {
					selected.getBlock().addNote(val);
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
		PuzzleGenerator.checkComplete();
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
