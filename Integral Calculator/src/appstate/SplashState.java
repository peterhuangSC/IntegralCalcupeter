package appstate;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

/**
 * Splash Screen Class of Integration Calcupeter
 * @author Peter Huang
 * @version 1.0 December 28 2014
 */

public class SplashState extends AppState {

	/** 
     * This is the constructor which sets up the gsm variables.
	 * The variables is set to the passed in value of gsm.
	 * @param gsm GameStateManager reference variable 
	 * */
	public SplashState(AppStateManager asm){
		this.asm = asm;
	}

	/** 
	 * This method initializes the animation. The try catch structure is used to prevent any errors when opening the
	 * image and animated image files to load onto the screen.
	 * @param splashScreen - Reference variable for JLabel, stores the picture.
	 * @param gif - ImageIcon variable that stores the animated image.
	 * @param e - reference variable for the Exception thrown.
	 */
	public void init() {
		JLabel splashScreen = new JLabel();
		ImageIcon gif;
		try {
			//gif = new ImageIcon(getClass().getResource("D:/Integral Calculator WS/Integral Calculator/src/res/Splash_Screen.gif"));
			gif = new ImageIcon("src/res/Splash_Screen.gif");
			splashScreen = new JLabel(gif);
			splashScreen.setBounds(1, 1, 960, 640);
			asm.pane.add(splashScreen);
			asm.layout.putConstraint(SpringLayout.WEST, splashScreen, 1, SpringLayout.WEST, asm.pane);
		}
		catch (Exception e) {
			System.out.println("Splash Screen Initialization Error!");
		}
		music();
	}

	/** This method creates the delay to allow the animations in the splash screen.
	 * @param e - reference variable for the Exception thrown.
	 */
	public void update() {
		try {
			Thread.sleep(5500);
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
	
	public void music() {
//		//URL url = SoundTest.class.getResource("back.wav");
//		URL introMusic = SplashState.class.getResource("src/res/splash music");
//		//InputStream introMusic = new FileInputStream("src/res/splash music");
//		AudioClip clip = Applet.newAudioClip(introMusic);
//		//AudioClip clip2 = Applet.newAudioClip(url);
//		clip.play();
	}
}
