package edu.unl.cse.Sudoku.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class SudokuLauncher {

	public static TitlePanel title;
	public static SudokuPanel game;

	public static void main(String args[]) {
		// create the main window for the game
		MainFrame main = new MainFrame();
		while (true) {
			showTitle(main);
			while (MainFrame.isTitleScreen) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			showGame(main);
			while (!MainFrame.isTitleScreen) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * Takes the title panel and adds it to the main window while setting layout
	 * constraints
	 * 
	 * @param main
	 */
	public static void showTitle(JFrame main) {
		title = new TitlePanel();
		main.getContentPane().removeAll();
		main.add(title);
		SpringLayout layout = new SpringLayout();
		main.setLayout(layout);
		layout.putConstraint(SpringLayout.NORTH, title, 0, SpringLayout.NORTH,
				main.getContentPane());
		layout.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST,
				main.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, title, 0, SpringLayout.SOUTH,
				main.getContentPane());
		layout.putConstraint(SpringLayout.EAST, title, 0, SpringLayout.EAST,
				main.getContentPane());
		main.revalidate();
	}

	/**
	 * Creates the game panel and side buttons to add into the game and show the
	 * game.
	 */
	public static void showGame(MainFrame main) {
		main.getContentPane().removeAll();
		game = new SudokuPanel();
		main.addKeyListener(new LauncherKL());
		main.add(game);

		JPanel buttonPanel = game.createButtonPanel();
		main.add(buttonPanel);

		SpringLayout layout = new SpringLayout();
		main.setLayout(layout);
		layout.putConstraint(SpringLayout.NORTH, game, 0, SpringLayout.NORTH,
				main.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, game, 0, SpringLayout.SOUTH,
				main.getContentPane());
		layout.putConstraint(SpringLayout.WEST, game, 0, SpringLayout.WEST,
				main.getContentPane());
		layout.putConstraint(SpringLayout.EAST, game, 0, SpringLayout.WEST,
				buttonPanel);

		layout.putConstraint(SpringLayout.NORTH, buttonPanel, 0,
				SpringLayout.NORTH, main.getContentPane());
		layout.putConstraint(SpringLayout.EAST, buttonPanel, 0,
				SpringLayout.EAST, main.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, buttonPanel, 0,
				SpringLayout.SOUTH, main.getContentPane());
		main.revalidate();
	}

	// -------Launcher Key Listener Class
	static class LauncherKL extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			if (game != null) {
				game.keyReleased(e);
			}
		}
	}// -----------End LauncherKL
}
