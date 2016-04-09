package edu.unl.cse.Sudoku.model;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Utilities {

	/**
	 * Creates a new panel that centers the element c in the number of specified
	 * vertical and horizontal partitions.
	 * 
	 * @param c
	 * @param vertPartitions
	 * @param horiPartitions
	 * @return : A JPanel containing the centered element.
	 */
	static public JPanel createPanelWithComponentCentered(Component c,
			int vertPartitions, int horiPartitions) {
		JPanel vertPanel = new JPanel(new GridLayout(vertPartitions, 1));
		JPanel horiPanel = new JPanel(new GridLayout(1, horiPartitions));
		JPanel garbagePanel;
		int halfVert = (int) Math.floor(vertPartitions / 2);
		int halfHori = (int) Math.floor(horiPartitions / 2);

		for (int i = 0; i < halfVert; i++) {
			garbagePanel = new JPanel();
			vertPanel.add(garbagePanel);
		}

		for (int i = 0; i < halfHori; i++) {
			garbagePanel = new JPanel();
			horiPanel.add(garbagePanel);
		}

		horiPanel.add(c);

		for (int i = 0; i < halfHori; i++) {
			garbagePanel = new JPanel();
			horiPanel.add(garbagePanel);
		}

		vertPanel.add(horiPanel);

		for (int i = 0; i < halfVert; i++) {
			garbagePanel = new JPanel();
			vertPanel.add(garbagePanel);
		}

		return vertPanel;
	}
}
