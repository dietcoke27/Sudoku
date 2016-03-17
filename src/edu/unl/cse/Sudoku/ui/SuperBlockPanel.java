package edu.unl.cse.Sudoku.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class SuperBlockPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public JPanel displayPanel;
	public static SuperBlockPanel[][] superPanels = new SuperBlockPanel[3][3];
	
	public SuperBlockPanel() {
		this.setBackground(Color.BLACK);
		this.displayPanel = new JPanel(new GridLayout(3, 3));
		this.add(this.displayPanel);
		
		//create and set a new layout for the BlockPanel
		SpringLayout l = new SpringLayout();
		this.setLayout(l);
		//set constraints for the BlockPanel
		l.putConstraint(SpringLayout.NORTH, this.displayPanel,  3, SpringLayout.NORTH,  this);						// 3 px between the top    of the number box and the top    of the BlockPanel
		l.putConstraint(SpringLayout.EAST,  this.displayPanel, -3, SpringLayout.EAST,   this);						// 3 px between the right  of the number box and the right  of the BlockPanel 
		l.putConstraint(SpringLayout.SOUTH, this.displayPanel, -3, SpringLayout.SOUTH,  this);						// 3 px between the bottom of the number box and the bottom of the BlockPanel
		l.putConstraint(SpringLayout.WEST,  this.displayPanel,  3, SpringLayout.WEST,   this);						// 4 px between the left   of the number box and the left   of the BlockPanel
		l.putConstraint(SpringLayout.WIDTH, this.displayPanel,  0, SpringLayout.HEIGHT, this.displayPanel);		// 0 px between the height of the number box and the width  of the number box
		
	}
	
	public static void addBlockPanel(BlockPanel bp, int row, int column) {
		SuperBlockPanel sp = SuperBlockPanel.superPanels[row / 3][column / 3];
		
		if (sp == null) {
			sp = new SuperBlockPanel();
			SuperBlockPanel.superPanels[row / 3][column / 3] = sp;
		}
		
		sp.displayPanel.add(bp);
		
	}
	
	public static List<SuperBlockPanel> flattenPanelList() {
		ArrayList<SuperBlockPanel> list = new ArrayList<SuperBlockPanel>();
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				SuperBlockPanel p = SuperBlockPanel.superPanels[i][j];
				System.out.println(p);
				list.add(p);
			}
		}
		
		return list;
	}
}
