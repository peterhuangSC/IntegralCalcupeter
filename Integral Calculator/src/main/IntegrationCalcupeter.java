package main;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.AppScreen;

/**
 * Main driver class of Integration Calcupeter
 * @author Peter Huang
 * @version 1.0 December 27 2014
 */
public class IntegrationCalcupeter extends JFrame implements ActionListener {

	/** This object is the pane in which Leukocytic Defense takes place.*/
	AppScreen app;

	/** This object is used to open up the .chm file.*/
	Desktop desk = Desktop.getDesktop();

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
		setLocationRelativeTo(null);
		setFocusable(true);
		requestFocus();
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Help")) {
			try {
				desk.open(new File("User Manual.chm"));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/** This method is the main method of the class, and creates a LeukocyticDefense object.
	 * @param args Takes in the command-line arguments. */
	public static void main(String[] args) {
		new IntegrationCalcupeter();
	}

}
