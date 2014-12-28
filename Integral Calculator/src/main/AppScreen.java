package main;

/**
 * The screen class of Integration Calcupeter
 * @author Peter Huang
 * @version 1.0 December 27 2014
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class AppScreen extends JPanel{

	/** This layout is the layout manager for the JPanel.
	 */
	SpringLayout layout = new SpringLayout();

	/** This boolean holds the value of whether or not the game thread is running.
	 */
	public boolean running;

	/** This points to the graphics object of the background, allowing for direct manipulation of its contents.
	 */
	Graphics2D g;

	/** This constructor sets running to true, sets the properties of the JPanel and starts the game thread.
	 */
	public AppScreen() {	
		setLayout(layout);
		setPreferredSize(new Dimension(600, 600));
		setFocusable(true);
		setBackground(Color.WHITE);
	}

	/** Overrides the paintComponent class in JPanel. It calls the superclass' paintComponent and calls the gsm's paintComponent right after.
	 * @param g Takes in the graphics object of the JPanel.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
