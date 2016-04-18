package edu.unl.cse.Sudoku.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.unl.cse.Sudoku.model.Utilities;

public class TitlePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JButton easyButton;
	private JButton mediumButton;
	private JButton hardButton;

	public TitlePanel() {
		// set the layout to be two sections tall and one section wide
		this.setLayout(new GridLayout(2, 1));

		// the top section is a centered label with the title
		title = new JLabel("Welcome to Sudoku!", SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 50));
		this.add(title);

		// the bottom panel is split up into 3 vertical sections
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

		// for the first section create a panel that contains the easy button
		easyButton = new JButton("Easy");
		easyButton.setFocusable(false);
		easyButton.addActionListener(this);
		JPanel tempPanel = Utilities.createPanelWithComponentCentered(
				easyButton, 7, 3);
		buttonPanel.add(tempPanel);

		// for the second section create a panel that contains the medium button
		mediumButton = new JButton("Medium");
		mediumButton.setFocusable(false);
		mediumButton.addActionListener(this);
		tempPanel = Utilities.createPanelWithComponentCentered(mediumButton, 7,
				3);
		buttonPanel.add(tempPanel);

		// for the last section create a panel that contains the hard button
		hardButton = new JButton("Hard");
		hardButton.setFocusable(false);
		hardButton.addActionListener(this);
		tempPanel = Utilities
				.createPanelWithComponentCentered(hardButton, 7, 3);
		buttonPanel.add(tempPanel);

		// add the sectioned panel to the whole title screen
		this.add(buttonPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.easyButton == e.getSource()) {
			SudokuPanel.difficulty = 1;
		} else if (this.mediumButton == e.getSource()) {
			SudokuPanel.difficulty = 2;
		} else if (this.hardButton == e.getSource()) {
			SudokuPanel.difficulty = 3;
		}
		SudokuPanel.blockSize = 3;
		MainFrame.isTitleScreen = false;
	}
}
