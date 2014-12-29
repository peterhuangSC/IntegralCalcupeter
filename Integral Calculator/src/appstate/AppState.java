package appstate;

/** This class accounts for the animations at the start of the program.
 * @author Peter Huang
 * @version 1.0 Dec 28, 2014  
 * The AppState interface is used for the different types of "App States" across this game such as the splash screen, menu screens, input screens
 * and program screens
 */
public abstract class AppState {
 /**
  * gsm - Reference variable for GameStateManager.*/
 public AppStateManager asm;  
 /**
  * Abstract method, used for initialization in other classes that implements this abstract class. */
 public abstract void init();
 /**
  * Abstract method, used for initialization in other classes that implements this abstract class. */
 public abstract void update();
 /**
  * Abstract method, used for initialization in other classes that implements this abstract class. 
  * @param g java.awt.Graphics2D reference variable. */
 public abstract void draw(java.awt.Graphics2D g);
 /**
  * Abstract method, used for initialization in other classes that implements this abstract class.
  * @param g java.awt.Graphics reference variable. */
 public abstract void paintComponent(java.awt.Graphics g);
}
