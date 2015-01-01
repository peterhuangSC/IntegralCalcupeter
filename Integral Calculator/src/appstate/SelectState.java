package appstate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

/** 
 * Description: This class controls which type of calculation the user wishes to choose.
 * @author Peter Huang
 * @version 1.0 December 31 2014 
 */

public class SelectState extends AppState implements ActionListener {

	/** 
	 * titleDiff - JLabel reference variable
	 */
	JLabel titleDiff = new JLabel();
	
	/** 
	 * statement - JLabel reference variable
	 */
	JLabel statement = new JLabel();
		
	/**
	 * defIntegral - JButton reference variable to select definite integral calculations.
	 */
	JButton defIntegral = new JButton();
	
	/**
	 * indIntegral - JButton reference variable to select indefinite integral calculations.
	 */
	JButton indIntegral = new JButton();	
	
	/** 
	 * backDiff - JButton reference variable to go back.
	 */
	JButton backDiff = new JButton();

	/** 
	 * This constructor sets the app state manager in the selection state.
	 * @param asm AppStateManager reference variable.
	 */
	public SelectState(AppStateManager asm) {
		this.asm = asm;
	}

	/** 
	 * This is the init method that initializes the constraints and the pane's size. It also adds the components.
	 */
	public void init() {
		asm.pane.setSize(960, 640);
		SwingUtilities.getWindowAncestor(asm.pane).pack();
		SwingUtilities.getWindowAncestor(asm.pane).setLocationRelativeTo(null);
		initLabels();
		setStaticConstraints();
		addComponents();
	}

	/** 
	 * This method initializes the all of the labels used in to select the difficulty. It loads all the image icons used
	 * for the buttons. It also adds the key mnemonics for each button and also activates each button.
	 * @param titleImg ImageIcon reference variable.
	 * @param selectImg ImageIcon reference variable.
	 * @param splash ImageIcon reference variable, activates when cursor is hovering over its corresponding button.
	 * @param backDiffImg ImageIcon reference variable.
	 * @param backDiffRollImg ImageIcon reference variable, activates when cursor is hovering over its corresponding button.
	 */
	public void initLabels() {
		ImageIcon titleImg = new ImageIcon();    
		
		ImageIcon defIntImg = new ImageIcon();
		ImageIcon indIntImg = new ImageIcon();
		
		ImageIcon defIntRollImg = new ImageIcon();
		ImageIcon indIntRollImg = new ImageIcon();

		ImageIcon backDiffImg = new ImageIcon();
		ImageIcon backDiffRollImg = new ImageIcon();    
		try {
			titleImg = new ImageIcon("src/res/title1.png");
			backDiffImg = new ImageIcon("src/res/menu_options/Back.png");
			backDiffRollImg = new ImageIcon("src/res/menu_options/Back2.png");			
			defIntImg = new ImageIcon("src/res/calculate_options/closed_definite.png");
			defIntRollImg = new ImageIcon("src/res/calculate_options/unlocked100.png");
			indIntImg = new ImageIcon("src/res/calculate_options/closed_indefinite.png");
			indIntRollImg = new ImageIcon("src/res/calculate_options/locked105.png");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		titleDiff = new JLabel(titleImg);
		
		defIntegral = new JButton(defIntImg);
		defIntegral.setRolloverIcon(defIntRollImg);
		defIntegral.setBorderPainted(false);
		defIntegral.setContentAreaFilled(false);
		defIntegral.setFocusPainted(false);
		defIntegral.setMnemonic(KeyEvent.VK_D);
		
		indIntegral = new JButton(indIntImg);
		indIntegral.setRolloverIcon(indIntRollImg);
		indIntegral.setBorderPainted(false);
		indIntegral.setContentAreaFilled(false);
		indIntegral.setFocusPainted(false);
		indIntegral.setMnemonic(KeyEvent.VK_I);

		backDiff = new JButton(backDiffImg);
		backDiff.setRolloverIcon(backDiffRollImg);
		backDiff.setBorderPainted(false);
		backDiff.setContentAreaFilled(false);
		backDiff.setFocusPainted(false);
		backDiff.setMnemonic(KeyEvent.VK_B);

		defIntegral.setActionCommand("Definite Integral");
		indIntegral.setActionCommand("Indefinite Integral");
		backDiff.setActionCommand("Back");

		defIntegral.addActionListener(this);
		indIntegral.addActionListener(this);
		backDiff.addActionListener(this);
	}

	/** 
	 * This method sets the static constraints for each button and label. A spring layout is used.
	 */
	public void setStaticConstraints() {
		asm.layout.putConstraint(SpringLayout.NORTH, titleDiff, 0, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, titleDiff, 0, SpringLayout.WEST, asm.pane);
		
		asm.layout.putConstraint(SpringLayout.NORTH, defIntegral, 300, SpringLayout.NORTH, titleDiff);
		asm.layout.putConstraint(SpringLayout.WEST, defIntegral, 125, SpringLayout.WEST, asm.pane);
		
		asm.layout.putConstraint(SpringLayout.NORTH, indIntegral, 300, SpringLayout.NORTH, titleDiff);
		asm.layout.putConstraint(SpringLayout.WEST, indIntegral, 400, SpringLayout.WEST, defIntegral);

		asm.layout.putConstraint(SpringLayout.NORTH, backDiff, 530, SpringLayout.NORTH, titleDiff);
		asm.layout.putConstraint(SpringLayout.WEST, backDiff, 390, SpringLayout.WEST, asm.pane);
	}

	/**
	 * This method adds all the JLabel and JButton components onto the screen.
	 */
	public void addComponents() {
		asm.pane.add(titleDiff);
		asm.pane.add(defIntegral);
		asm.pane.add(indIntegral);
		asm.pane.add(backDiff);
	}

	/**
	 * This is an overriden method that has no implementation.
	 */
	public void update() {}

	/**
	 * This is an overriden method that has no implementation.
	 * @param g Graphics2D reference variable
	 */
	public void draw(Graphics2D g) {}

	/**
	 * This is an overriden method that has no implementation.
	 * @param g Graphics reference variable
	 */
	public void paintComponent(Graphics g) {}

	/**
	 * This the action performed method that defines the action of each button. Easy, medium, hard, and back are the 
	 * choices.
	 * @param ae ActionEvent reference variable
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Definite Integral")) {
			asm.pane.stateCheck = true;
			asm.pane.state = asm.DEFINITE_INTEGRAL_STATE;
			System.out.println("Definite Integration Selected");
		}
		else if (ae.getActionCommand().equals("Indefinite Integral")){
			asm.pane.stateCheck = true;
			System.out.println("Indefinite Integration Selected");
		}
		else
		{
			asm.pane.stateCheck = true;
			asm.pane.state = asm.MENUSTATE;
		}
	}
}