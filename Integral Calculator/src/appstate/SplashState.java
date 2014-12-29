package appstate;

import javax.swing.*;
import main.AppScreen;

/**
 * Splash Screen Class of Integration Calcupeter
 * @author Peter Huang
 * @version 1.0 December 28 2014
 */

public class SplashState extends AppState {

	//can access the final width and height variables from AppScreen
	AppScreen app1;	

	/** This is the constructor which sets up the gsm variables.
	 * The variables is set to the passed in value of gsm.
	 * @param gsm GameStateManager reference variable */
	public SplashState(AppStateManager asm){
		this.asm = asm;
	}

	/** This method initializes the animation. The try catch structure is used to prevent any errors when opening the
	 * image and animated image files to load onto the screen.
	 * @param splashScreen - Reference variable for JLabel, stores the picture.
	 * @param gif - ImageIcon variable that stores the animated image.
	 * @param e - reference variable for the Exception thrown.
	 */
	public void init() {
		JLabel splashScreen = new JLabel();
		ImageIcon gif = new ImageIcon();
		try {
			gif = new ImageIcon(getClass().getResource("/source/resources/Splash Screen.gif"));
			splashScreen = new JLabel(gif);
			splashScreen.setBounds(1, 1, app1.MAIN_WIDTH, app1.MAIN_HEIGHT);
			asm.pane.add(splashScreen);
			asm.layout.putConstraint(SpringLayout.WEST, splashScreen, 1, SpringLayout.WEST, asm.pane);
		}
		catch (Exception e) {
		}
	}

	/** This method creates the delay to allow the animations in the splash screen.
	 * @param e - reference variable for the Exception thrown.
	 */
	public void update() {
		try {
			Thread.sleep(9500);
		}
		catch (Exception e) {
		}
		asm.setState(asm.MENUSTATE);
	}

	/** This method is used for drawing 2D graphics. It is inherited from the GameState abstract class.
	 * @param g java.awt.Graphics2D reference variable. */
	public void draw(java.awt.Graphics2D g){}

	/** This method is used to tell when a specific key is pressed. It is inherited from the GameState abstract class.
	 * @param k int reference variable of the specific key. */
	public void keyPressed(int k){}

	/** This method is used to tell when a specific key is released. It is inherited from the GameState abstract class.
	 * @param k int reference variable of the specific key. */
	public void keyReleased(int k){}

	/** This method is responsible for the paint component. It is inherited from the GameState abstract class.
	 * @param g java.awt.Graphics reference variable. */
	public void paintComponent(java.awt.Graphics g) {}
}
