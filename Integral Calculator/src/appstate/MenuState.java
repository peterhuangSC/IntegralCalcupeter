package appstate;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/** 
 * This class is the main menu of the game. It sets the correct size and also the various buttons on the 
 * main menu.
 * @author Peter Huang
 * @version 1.0 December 30 2014
 */
public class MenuState extends AppState implements ActionListener
{
	/** 
	 * This integer initiates the myState variable to 0. Used to access other states.
	 */
	int myState = 0;
	
	/** 
	 * This integer initiates the menu value to 0.
	 */
	int menu = 0;
		
	/** 
	 * studyFrame - JFrame reference variable to the study frame with the frame name.
	 */
	JFrame studyFrame = new JFrame("White Blood Cells 101");
	
	/**
	 * This integer contains the starting distance from the left of the panel for the dx sign.
	 */
	private int dxDist = 833;
	
	/**
	 * This integer contains the starting distance from the left of the panel for the f(x) sign.
	 */
	private int fxDist = 683;
	
	/**
	 * This integer contains the starting distance from the left of the panel for the integral sign.
	 */
	private int integralDist = 600;
	
	/** 
	 * This integer contains the starting distance from the left of the panel for the integral house picture.
	 */
	private int intHouseDist = 420;
	
	/** 
	 * This integer contains the starting distance from the left of the panel for the Callisto picture.
	 */
	private int callistoDist = 200;
		
	/** 
	 * This integer contains the starting distance from the left of the panel for the stewart textbook picture.
	 */
	private int textbookDist = 72;
	
	/** 
	 * This integer contains the starting distance from the left of the panel for the violin picture.
	 */
	private int violinDist = -160;
	
	/**
	 * This integer contains the starting distance from the left of the panel for the volume of revolution picture.
	 */
	private int volRevDist = -360;
	
	/**
	 * This integer is the set back distance for the moving image icons.
	 */
	private int resetDistance = -310;

	/** 
	 * This button allows the user to go back to the main menu.
	 */
	public JButton back = new JButton("Back");
	
	/** 
	 * This label holds the picture of the title.
	 */
	private JLabel title = new JLabel();
	
	/** 
	 * This label holds the picture of Callisto.
	 */
	private JLabel callisto = new JLabel();
	
	/** 
	 * This label holds the picture of the Integral House.
	 */
	private JLabel intHouse = new JLabel();  
	
	/** 
	 * This label holds the picture of the James Stewart Calculus Textbook.
	 */
	private JLabel textbook = new JLabel();
	
	/**
	 * This label holds the picture of the integral symbol
	 */
	private JLabel integralSign = new JLabel();
	
	/**
	 * This label holds the picture of the f(x) symbol
	 */
	private JLabel fxSign = new JLabel();
	
	/**
	 * This label holds the picture of the dx symbol
	 */
	private JLabel dxSign = new JLabel();
	
	/** 
	 * This label holds the picture of the violin.
	 */
	private JLabel violin = new JLabel();
	
	/**
	 * This label holds the picture of the volume of revolution.
	 */
	private JLabel volRev = new JLabel();

	/** 
	 * This button is the play button, which leads to the choose-difficulty menu.
	 */
	private JButton calculate = new JButton();
	
	/** 
	 * This button is the Coming Soon! button.
	 */
	private JButton comingSoon = new JButton(); 

	/** 
	 * This button is the study button, which displays study material.
	 */
	private JButton integral101 = new JButton();
	
	/** 
	 * This button is the quit button, which exits the game.
	 */
	private JButton quit = new JButton();

	/** 
	 * This holds the background for the jpanel.
	 */
	BufferedImage image;
	
	/** 
	 * This holds the slide panel for the instructions.
	 */
	int slide = 0;
	
	/** 
	 * This holds the slide panel for the study screen.
	 */
	int slideStudy = 0;

	/**
	 * This constructor sets the game state manager in the stage one state. Labels are also initiated and static constraints are set.
	 * @param asm GameStateManager reference variable.
	 */
	public MenuState(AppStateManager asm) {
		this.asm = asm;
		initLabels();
		setStaticConstraints();
	}

	/** 
	 * This method is overriden from game state, there is no implementation.
	 */
	public void paintComponent(Graphics g) {}

	/** 
	 * This method initiates the game screen and frame per second. Removes things in other screens are replaces it with only the content 
	 * of the menu.
	 */
	public void init() {    
		asm.pane.setSize(960, 640);
		SwingUtilities.getWindowAncestor(asm.pane).pack();
		SwingUtilities.getWindowAncestor(asm.pane).setLocationRelativeTo(null);
		asm.pane.FPS = 20;
		asm.pane.removeAll();
		addMenu();
	}

	/** 
	 * Sets the constraints of components that don't move on the JPanel, and adds components in.
	 */
	public void setStaticConstraints() {
		asm.layout.putConstraint(SpringLayout.NORTH, title, 0, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, calculate, 330, SpringLayout.NORTH, title);
		asm.layout.putConstraint(SpringLayout.WEST, calculate, 95, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, comingSoon, -10, SpringLayout.SOUTH, calculate);
		asm.layout.putConstraint(SpringLayout.WEST, comingSoon, 105, SpringLayout.WEST, asm.pane); 

		asm.layout.putConstraint(SpringLayout.NORTH, integral101, -20, SpringLayout.SOUTH, comingSoon);
		asm.layout.putConstraint(SpringLayout.WEST, integral101, 105, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, quit, -20, SpringLayout.SOUTH, integral101);
		asm.layout.putConstraint(SpringLayout.WEST, quit, 105, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, back, 535, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, back, 100, SpringLayout.WEST, asm.pane);
	}

	/** 
	 * This method adds the various buttons in the main menu.
	 */
	public void addMenu() {
		asm.pane.add(title);
		asm.pane.add(calculate);
		asm.pane.add(comingSoon);
		asm.pane.add(integral101);
		asm.pane.add(quit);
	}

	/** 
	 * Initializes all the labels and buttons with their pictures. Also activates the labels and buttons.
	 * <p><b>Variable Dictionary: Reference, Type, Purpose</b> requires updating!
	 * <ul>
	 * <li>titleImg, ImageIcon, holds the title image
	 * <li>integralImg, ImageIcon, holds the integral sign image
	 * <li>neutImg, ImageIcon, holds the neutrophil image
	 * <li>eosImg, ImageIcon, holds the eosinophil image
	 * <li>basoImg, ImageIcon, holds the basophil image
	 * <li>violinImg, ImageIcon, holds the violin image
	 * <li>playImg, ImageIcon, holds the play button image
	 * <li>playRollImg, ImageIcon, holds the play button rollover image
	 * <li>instImg, ImageIcon, holds the comingSoon button image
	 * <li>instRollImg, ImageIcon, holds the comingSoon button rollover image
	 * <li>integral101Img, ImageIcon, holds the integral101 button image
	 * <li>integral101RollImg, ImageIcon, holds the integral101 button rollover image
	 * <li>quitImg, ImageIcon, holds the quit button image
	 * <li>quitRollImg, ImageIcon, holds the quit button rollover image
	 * <li>backImg, ImageIcon, holds the back button image
	 * <li>backRollImg, ImageIcon, holds the back button rollover image
	 * </ul>
	 */
	public void initLabels() {
		ImageIcon titleImg = new ImageIcon();
		
		ImageIcon integralImg = new ImageIcon();
		ImageIcon fxImg = new ImageIcon();
		ImageIcon dxImg = new ImageIcon();
		
		ImageIcon intHouseImg = new ImageIcon();		
		ImageIcon callistoImg = new ImageIcon();	
		ImageIcon textbookImg = new ImageIcon();
		ImageIcon violinImg = new ImageIcon();
		ImageIcon volRevImg = new ImageIcon();

		ImageIcon calculateImg = new ImageIcon();
		ImageIcon calculateRollImg = new ImageIcon();

		ImageIcon comingSoonImg = new ImageIcon();
		ImageIcon comingSoonRollImg = new ImageIcon();

		ImageIcon integral101Img = new ImageIcon();
		ImageIcon integral101RollImg = new ImageIcon();

		ImageIcon quitImg = new ImageIcon();
		ImageIcon quitRollImg = new ImageIcon();

		ImageIcon backImg = new ImageIcon();
		ImageIcon backRollImg = new ImageIcon();

		try {
			titleImg = new ImageIcon("src/res/title1.png");
			integralImg = new ImageIcon("src/res/menu_ss/integral_sign.png");
			fxImg = new ImageIcon("src/res/menu_ss/fx_sign.png");
			dxImg = new ImageIcon("src/res/menu_ss/dx_sign.png");
			callistoImg = new ImageIcon("src/res/menu_ss/Callisto.png");
			intHouseImg = new ImageIcon("src/res/menu_ss/integral_house.png");
			textbookImg = new ImageIcon("src/res/menu_ss/calculus_textbook.png");
			violinImg = new ImageIcon("src/res/menu_ss/violin.png");
			volRevImg = new ImageIcon("src/res/menu_ss/volume_rev.png");
			
			calculateImg = new ImageIcon("src/res/menu_options/Calculate.png");
			calculateRollImg = new ImageIcon("src/res/menu_options/Calculate2.png");
			comingSoonImg = new ImageIcon("src/res/menu_options/Coming_Soon.png");
			comingSoonRollImg = new ImageIcon("src/res/menu_options/Coming_Soon2.png");
			integral101Img = new ImageIcon("src/res/menu_options/Integrals101.png");
			integral101RollImg = new ImageIcon("src/res/menu_options/Integrals1012.png");
			quitImg = new ImageIcon("src/res/menu_options/Exit.png");
			quitRollImg = new ImageIcon("src/res/menu_options/Exit2.png");
			backImg = new ImageIcon("src/res/menu_options/Back.png");
			backRollImg = new ImageIcon("src/res/menu_options/Back2.png");
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}

		title = new JLabel(titleImg);
		integralSign = new JLabel(integralImg);
		fxSign = new JLabel(fxImg);
		dxSign = new JLabel(dxImg);
		
		callisto = new JLabel(callistoImg);
		intHouse = new JLabel(intHouseImg);
		textbook = new JLabel(textbookImg);
		violin = new JLabel(violinImg);
		volRev = new JLabel(volRevImg);

		calculate = new JButton(calculateImg);
		calculate.setRolloverIcon(calculateRollImg);
		calculate.setBorderPainted(false);
		calculate.setContentAreaFilled(false);
		calculate.setFocusPainted(false);
		calculate.setMnemonic(KeyEvent.VK_A);

		comingSoon = new JButton(comingSoonImg);
		comingSoon.setRolloverIcon(comingSoonRollImg);
		comingSoon.setBorderPainted(false);
		comingSoon.setContentAreaFilled(false);
		comingSoon.setFocusPainted(false);
		comingSoon.setMnemonic(KeyEvent.VK_S);

		integral101 = new JButton(integral101Img);
		integral101.setRolloverIcon(integral101RollImg);
		integral101.setBorderPainted(false);
		integral101.setContentAreaFilled(false);
		integral101.setFocusPainted(false);
		integral101.setMnemonic(KeyEvent.VK_W);

		quit = new JButton(quitImg);
		quit.setRolloverIcon(quitRollImg);
		quit.setBorderPainted(false);
		quit.setContentAreaFilled(false);
		quit.setFocusPainted(false);
		quit.setMnemonic(KeyEvent.VK_X);

		back = new JButton(backImg);
		back.setFocusPainted(false);
		back.setRolloverIcon(backRollImg);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setMnemonic(KeyEvent.VK_B);

		calculate.setActionCommand("Calculate"); //changed setActionCommand from "Play"
		comingSoon.setActionCommand("Coming Soon"); //changed setActionCommand from "Instructions"
		quit.setActionCommand("Quit");
		back.setActionCommand("Back");
		integral101.setActionCommand("Integration 101");

		calculate.addActionListener(this);
		comingSoon.addActionListener(this);
		integral101.addActionListener(this);
		quit.addActionListener(this);
		back.addActionListener(this);
	}

	/** 
	 * Removes cell JLabels, updates their constraints, and adds them back in.
	 */
	public void setConstraints() {
		asm.pane.remove(integralSign);
		asm.layout.putConstraint(SpringLayout.NORTH, integralSign, 200, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, integralSign, integralDist, SpringLayout.WEST, asm.pane);
		asm.pane.add(integralSign);
		
		asm.pane.remove(fxSign);
		asm.layout.putConstraint(SpringLayout.NORTH, fxSign, 200, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, fxSign, fxDist, SpringLayout.WEST, asm.pane);
		asm.pane.add(fxSign);
		
		asm.pane.remove(dxSign);
		asm.layout.putConstraint(SpringLayout.NORTH, dxSign, 200, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, dxSign, dxDist, SpringLayout.WEST, asm.pane);
		asm.pane.add(dxSign);		
		
		asm.pane.remove(callisto);
		asm.layout.putConstraint(SpringLayout.NORTH, callisto, 200, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, callisto, callistoDist, SpringLayout.WEST, asm.pane);
		asm.pane.add(callisto);

		asm.pane.remove(intHouse);
		asm.layout.putConstraint(SpringLayout.NORTH, intHouse, 200, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, intHouse, intHouseDist, SpringLayout.WEST, asm.pane);
		asm.pane.add(intHouse);

		asm.pane.remove(textbook);
		asm.layout.putConstraint(SpringLayout.NORTH, textbook, 200, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, textbook, textbookDist, SpringLayout.WEST, asm.pane);
		asm.pane.add(textbook);

		asm.pane.remove(violin);
		asm.layout.putConstraint(SpringLayout.NORTH, violin, 200, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, violin, violinDist, SpringLayout.WEST, asm.pane);
		asm.pane.add(violin);
		
		asm.pane.remove(volRev);
		asm.layout.putConstraint(SpringLayout.NORTH, volRev, 200, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, volRev, volRevDist, SpringLayout.WEST, asm.pane);
		asm.pane.add(volRev);			
		
		setStaticConstraints();
	}

	/** 
	 * This method changes the values of the constraints for the main menu animation.
	 */
	public void update() {
		if (myState == menu) {
			if (integralDist > 960)
				integralDist = resetDistance;
			else if (fxDist > 960)
				fxDist = resetDistance;
			else if (dxDist > 960)
				dxDist = resetDistance;
			else if (callistoDist > 960)
				callistoDist = resetDistance;
			else if (intHouseDist > 960)
				intHouseDist = resetDistance;
			else if (textbookDist > 960)
				textbookDist = resetDistance;
			else if (violinDist > 960)
				violinDist = resetDistance;
			else {
				if(volRevDist > 960)
					volRevDist = resetDistance;
			}
			integralDist+=2;
			fxDist+=2;
			dxDist+=2;
			callistoDist+=2;
			intHouseDist+=2;
			textbookDist+=2;
			violinDist+=2;
			volRevDist+=2;
			setConstraints();
		}
	}

	/** 
	 * This method removes the menu buttons, such as going into highscores when the screen changes.
	 */
	public void removeMenu() {
		asm.pane.remove(calculate);
		asm.pane.remove(comingSoon);
		asm.pane.remove(integral101);
		asm.pane.remove(quit);
	}

	/** 
	 * This method removes the game animations.
	 */
	public void removeAnimation() {
		asm.pane.remove(integralSign);
		asm.pane.remove(fxSign);
		asm.pane.remove(dxSign);
		asm.pane.remove(violin);
		asm.pane.remove(textbook);
		asm.pane.remove(intHouse);
		asm.pane.remove(callisto);
		asm.pane.remove(volRev);
	}

	/** 
	 * This method adds back the "back" button.
	 */
	public void addBack() {
		asm.pane.add(back);
	}

	/** 
	 * This method removes the "back" button.
	 */
	public void removeBack() {
		asm.pane.remove(back);
	}

	/**
	 * This method controls the hover over screen for coming soon.
	 */
	public void comingSoonHover () {
		System.out.println("A hover over board on the right will be placed in the future. Likely won't be here as it is a hover over.");
	}

	/** 
	 * This method loads the study pane. Mnemonics are also set for the buttons. A window listener is also added here.
	 * @param pane JPanel reference variable
	 * @param slide1 JButton reference variable for study page 1.
	 * @param slide2 JButton reference variable for study page 2.
	 * @param slide3 JButton reference variable for study page 3.
	 * @param slide4 JButton reference variable for study page 4.
	 * @param slide5 JButton reference variable for study page 5.
	 * @param slideClose JButton reference variable for back to main menu button.
	 * @param toolbar JToolBar reference variable for where the toolbar is placed.
	 * @param s SpringLayout reference variable.
	 * @param studyLabels Arraylist of JLabels of study labels.
	 * @param studyImages ArrayList of JLabels of study images.
	 * @param e Exception reference variable, prints the stack trace in case of error.
	 */
	public void loadStudy() {
		JPanel pane = new JPanel();

		JButton slide1 = new JButton("Study 1");
		JButton slide2 = new JButton("Study 2");
		JButton slide3 = new JButton("Study 3");
		JButton slide4 = new JButton("Study 4");
		JButton slide5 = new JButton("Study 5");

		JButton slideClose = new JButton("Back to Main Menu");

		JToolBar toolbar = new JToolBar(null, JToolBar.HORIZONTAL);

		toolbar.add(slide1);
		toolbar.add(new JSeparator(SwingConstants.VERTICAL));
		toolbar.add(slide2);
		toolbar.add(new JSeparator(SwingConstants.VERTICAL));
		toolbar.add(slide3);
		toolbar.add(new JSeparator(SwingConstants.VERTICAL));
		toolbar.add(slide4);
		toolbar.add(new JSeparator(SwingConstants.VERTICAL));
		toolbar.add(slide5);
		toolbar.add(new JSeparator(SwingConstants.VERTICAL));
		toolbar.add(slideClose);

		studyFrame.add(toolbar, BorderLayout.SOUTH);
		toolbar.setFloatable(false);
		toolbar.setRollover(true);

		slide1.addActionListener(this);
		slide1.setMnemonic(KeyEvent.VK_1);
		slide2.addActionListener(this);
		slide2.setMnemonic(KeyEvent.VK_2);
		slide3.addActionListener(this);
		slide3.setMnemonic(KeyEvent.VK_3);
		slide4.addActionListener(this);
		slide4.setMnemonic(KeyEvent.VK_4);
		slide5.addActionListener(this);
		slide5.setMnemonic(KeyEvent.VK_5);
		slideClose.addActionListener(this);
		slideClose.setMnemonic(KeyEvent.VK_B);

		SpringLayout s = new SpringLayout();
		ArrayList<JLabel> studyLabels = new ArrayList<JLabel>();
		ArrayList<ImageIcon> studyImages = new ArrayList<ImageIcon>();
		try {
			for (int x = 0; x < 5; x++)
				studyImages.add(new ImageIcon("src/res/Study Images/Page " + (x+1) + ".png"));
			for (ImageIcon i : studyImages)
			{
				studyLabels.add(new JLabel(i));
			}

			pane.setLayout(s);
			pane.add(studyLabels.get(slideStudy));
			s.putConstraint(SpringLayout.NORTH, studyLabels.get(slideStudy), 0, SpringLayout.NORTH, pane);
			s.putConstraint(SpringLayout.WEST, studyLabels.get(slideStudy), 0, SpringLayout.WEST, pane);
			studyFrame.add(pane);
			studyFrame.setVisible(true);
			studyFrame.setSize (510, 575);
			studyFrame.setLocationRelativeTo(null);

			studyFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					SwingUtilities.getWindowAncestor(asm.pane).setVisible(true);
					removeAnimation();
					addAnimation();
					studyFrame.setVisible(false);
					myState = 0;
				}
			});

			SwingUtilities.getWindowAncestor(asm.pane).setVisible(false);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	 * This method adds the animation of the main menu moving cells.
	 */
	public void addAnimation() {
		asm.pane.add(integralSign);
		asm.pane.add(fxSign);
		asm.pane.add(dxSign);
		asm.pane.add(callisto);
		asm.pane.add(intHouse);
		asm.pane.add(textbook);
		asm.pane.add(violin);
		asm.pane.add(volRev);
	}
	
	/** 
	 * This method is overriden. No implementation is used in this method.
	 * @param g Graphics2D reference variable.
	 */
	public void draw(Graphics2D g) {}
	
	/** 
	 * This method is overriden. No implementation is used in this method.
	 * @param k keyPressed int variable.
	 */
	
	public void keyPressed(int k) {}
	
	/** 
	 * This method is overriden. No implementation is used in this method.
	 * @param k keyReleased int variable.
	 */
	public void keyReleased(int k) {}

	/** 
	 * This is the action performed method that sets the functions for each button when clicked. They include the main menu
	 * buttons, study buttons, instruction buttons, stage selectors, and allows printing when the print variable is clicked.
	 * @param ae ActionEvent refernece variable
	 * @param type String variable that is substituted for ae reference variable.
	 * @param e Exception reference variable.
	 */
	public void actionPerformed(ActionEvent ae) {
		String type = ae.getActionCommand();

		if (type.equals("Quit"))
			System.exit(0);
		else if (type.equals("Calculate")) {
			asm.pane.stateCheck = true;
			asm.pane.state = asm.SELECTSTATE;
		}
		else if (type.equals("Coming Soon")) {
			comingSoonHover();
		}
		else if (type.equals("Integration 101")) {
			loadStudy();
		}
		else if (type.equals("Back")) {
			removeAnimation();
			removeBack();
			addMenu();
			addAnimation();
			myState = 0;
		}
		else if (type.equals("Study 1")) {
			slideStudy = 0;
			loadStudy();
			System.out.println("hi");
		}
		else if (type.equals("Study 2")) {
			slideStudy = 1;
			loadStudy();
		}
		else if (type.equals("Study 3")) {
			slideStudy = 2;
			loadStudy();
		}
		else if (type.equals("Study 4")) {
			slideStudy = 3;
			loadStudy();
		}
		else if (type.equals("Study 5")) {
			slideStudy = 4;
			loadStudy();
		}    
		else {
			SwingUtilities.getWindowAncestor(asm.pane).setVisible(true);
			studyFrame.setVisible(false);
			myState = 0;
		}
	}
}