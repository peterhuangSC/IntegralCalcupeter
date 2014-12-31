package main;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Main driver class of Integration Calcupeter
 * @author Peter Huang
 * @version 1.0 December 27 2014
 */
public class IntegrationCalcupeter extends JFrame implements ActionListener, KeyListener {

	/** This object is the pane in which Leukocytic Defense takes place.*/
	AppScreen app;

	/** This object is used to open up the .chm file.*/
	Desktop desk = Desktop.getDesktop();

	/** This constructor creates the JFrame, with the game title, sets window properties 
	 * and adds a GameScreen object to the frame. 
	 * <p><b>Variable Dictionary: Reference, Type, Purpose</b>
	 * <ul>
	 * <li>bar, JMenuBar, is the bar that holds the file and help menus
	 * <li>helpMe, JMenu, is the menu that holds the option to open up the .chm file
	 * <li>file, JMenu, is the menu that holds the option to open up the main menu
	 * <li>helpItem, JMenuItem, opens up the .chm file
	 * <li>fileItem, JMenuItem, opens up the main menu
	 * </ul>
	 */
	public IntegrationCalcupeter(){
		super ("The Integration Calcupeter 1.0");
		JMenuBar bar = new JMenuBar();
		JMenu helpMe = new JMenu("Help");
		JMenu file = new JMenu("File");
		JMenuItem helpItem = new JMenuItem("Help");
		JMenuItem fileItem = new JMenuItem("Main Menu");
		helpMe.add(helpItem);
		file.add(fileItem);
		setJMenuBar(bar);
		bar.add(file);
		bar.add(helpMe);
		helpItem.addActionListener(this);
		fileItem.addActionListener(this);
		add(app = new AppScreen());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		pack();
		addKeyListener(this);
		setLocationRelativeTo(null);
		setFocusable(true);
		requestFocus();
	}

	/** This method overrides the abstract method actionPerformed and executes instructions according to what menu item is pressed.
	 * @param ae ActionEvent takes in actionevents from jmenubar
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Help")) {
			try {
				desk.open(new File("User Manual.chm"));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			app.asm.setState(app.asm.MENUSTATE);
	}

	/** 
	 * This method overrides the abstract method keyPressed and checks if the user pressed P.
	 *  @param e KeyEvent takes in a key event
	 * @param k int gets the key code of the key event
	 */
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_P)
			app.check = true;
	}

	/** 
	 * This method overrides the abstract method keyReleased.
	 * @param e KeyEvent takes in a key event
	 */
	public void keyReleased(KeyEvent e) {}

	/** 
	 * This method overrides the abstract method keyTyped.
	 * @param e KeyEvent takes in a key event
	 */
	public void keyTyped(KeyEvent e) {}

	/** 
	 * This method is the main method of the class, and creates a LeukocyticDefense object.
	 * @param args Takes in the command-line arguments. */
	public static void main(String[] args) {
		new IntegrationCalcupeter();
	}

}
