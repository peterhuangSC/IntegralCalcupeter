package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import appstate.AppStateManager;

/**
 * The screen class of Integration Calcupeter
 * @author Peter Huang
 * @version 1.0 December 27 2014
 */

public class AppScreen extends JPanel implements Runnable{

	SpringLayout layout = new SpringLayout(); //This layout is the layout manager for the JPanel.	
	Graphics2D g; //This points to the graphics object of the background, allowing for direct manipulation of its contents.

	public boolean running; //This boolean holds the value of whether or not the game thread is running.

	public int state = 0; //value of the state it is changed into, initialized as 0 to be SplashState
	public boolean check = false; //used for pausing purposes
	public boolean stateCheck = false; //whether a signal has been sent to change state

	public AppStateManager asm;

	public static final int MAIN_WIDTH = 960;
	public static final int MAIN_HEIGHT = 640;

	/** This integer holds the value of the framerate of the game.
	 */
	public static int FPS = 20;

	/** This constructor sets running to true, sets the properties of the JPanel and starts the game thread.
	 */
	public AppScreen() {	
		init();
		setLayout(layout);
		setPreferredSize(new Dimension(MAIN_WIDTH, MAIN_HEIGHT));
		setFocusable(true);
		setBackground(Color.WHITE);
		new Thread(this).start();
	}

	/** Overrides the paintComponent class in JPanel. It calls the superclass' paintComponent and calls the gsm's paintComponent right after.
	 * @param g Takes in the graphics object of the JPanel.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		asm.paintComponent(g);
	}

	/**
	 * This method instantiates the boolean and AppStateManager
	 */
	public void init() {
		running = true;
		asm = new AppStateManager(this, layout);
	}

	/**
	 * This method updates the game with the processing.
	 */
	public void update() {
		asm.update();
	}

	/**
	 * This method updates the pane with the game's graphics and components
	 */
	public void draw() {
		asm.draw(g);
	}

	/** 
	 * Is the method containing the game loop and changes the values of the constraints.
	 * <p><b>Variable Dictionary: Reference, Type, Purpose</b>
	 * <ul>
	 * <li>start, long, holds the time at the start of the loop
	 * <li>elapsed, long, holds the time passed after setting constraints
	 * <li>delay, long, holds the millisecond value of how long the thread should sleep for.
	 * <li>wait, int, holds the current frame count
	 * <li>e, InterruptedException, is the exception for if the thread is interrupted
	 * </ul>
	 * <p><b>Loops: Condition, Purpose</b>
	 * <ul>
	 * <li> while(running), continously updates the screen while the game is running
	 * </ul>
	 */
	public void run()
	{
		long start;
		long elapsed;
		int wait = 0;
		long delay = 1;
		while(true) {
			if (running) {
				if (wait >= FPS) {
					draw();
					update();
					validate();
					repaint();
					wait = 0;
				}
				else
					wait++;
			}
			//checkRun();
			checkChange();

			start = System.nanoTime();
			elapsed = System.nanoTime() - start;

			if (1-elapsed/1000000 < 0)
				delay = 1;
			else
				delay = 1-elapsed/1000000;
			try {
				Thread.sleep(delay);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/** 
	 * This method checks if the loop needs to be paused/unpause, and then pauses/unpauses it.
	 * This method isn't required at the moment.
	 */
//	 public void checkRun() {
//		if (check && asm.currentState == asm.STAGEONESTATE)
//			running = !running;
//		check = false;
//	 }

	 /** 
	  * This method checks if the game state needs to be changed, and then changes it.
	  */
	 public void checkChange() {
		 if (stateCheck)
			 asm.setState(state);
		 stateCheck = false;
	 }

}
