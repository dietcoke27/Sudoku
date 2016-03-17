package edu.unl.cse.Sudoku.model;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Utilities {
	static public JPanel createPanelWithComponentCentered(Component c, int vertPartitions, int horiPartitions) {
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
