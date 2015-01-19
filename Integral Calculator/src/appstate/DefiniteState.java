package appstate;

/** 
 * This class is the definite integration screen of the app.
 * @author Peter Huang
 * @version 1.0.2 January 18 2014
 */

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class DefiniteState extends AppState implements ActionListener {

	/** 
	 * background - JLabel reference variable
	 */
	JLabel background = new JLabel();

	/**
	 *  JButton reference for the integration sign.
	 */
	JButton integrationSign = new JButton();

	/**
	 * JButton reference for the dx sign.
	 */
	JButton dxSign = new JButton();

	/**
	 * JButton reference for the back button.
	 */
	JButton backButton = new JButton();

	/**
	 * function - reference for the function input field.
	 */
	JTextField function = new JTextField();

	/**
	 * answer - reference for the answer input field
	 */
	JTextField answer = new JTextField();

	/**
	 * upperLimit - reference for the upper limit input field. (b)
	 */
	JTextField upperLimit = new JTextField();

	/**
	 * lowerLimit - reference for the lower limit input field. (a)
	 */
	JTextField lowerLimit = new JTextField();

	/**
	 * boundFont - font used in upper and lower bounds (textbox)
	 */
	Font boundFont = new Font ("Segoe UI", Font.ITALIC, 32);

	/**
	 * funcFont - font used in the function textbox
	 */
	Font funcFont = new Font ("Segoe UI", Font.PLAIN, 48);

	double x = 0;

	//make a clear button and also make an answer textbox!

	/**
	 * This constructor sets up the app state manager inside the definite integration state.
	 * @param asm AppStateManager reference variable
	 */
	public DefiniteState(AppStateManager asm){
		this.asm = asm;
	}

	public void init(){
		asm.pane.setSize(960, 640);
		SwingUtilities.getWindowAncestor(asm.pane).pack();
		SwingUtilities.getWindowAncestor(asm.pane).setLocationRelativeTo(null);
		initLabels();
		setStaticConstraints();
		setTextBoxStyles();
		addComponents();
	}

	public void initLabels(){
		ImageIcon backgroundImg = new ImageIcon();
		ImageIcon blueIntImg = new ImageIcon();
		ImageIcon fireIntImg = new ImageIcon();
		ImageIcon bluedxImg = new ImageIcon();
		ImageIcon evildxImg = new ImageIcon();
		ImageIcon backImg = new ImageIcon();
		ImageIcon backRollImg = new ImageIcon();  

		try {
			backgroundImg = new ImageIcon("src/res/definite_integration/background.png");
			blueIntImg = new ImageIcon("src/res/definite_integration/blue_integral_symbol.png");
			fireIntImg = new ImageIcon("src/res/definite_integration/fire_integration_symbol.png");
			bluedxImg = new ImageIcon("src/res/definite_integration/blue_dx.png");
			evildxImg = new ImageIcon("src/res/definite_integration/evil_dx.png");
			backImg = new ImageIcon("src/res/menu_options/Back.png");
			backRollImg = new ImageIcon("src/res/menu_options/Back2.png");	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		background = new JLabel(backgroundImg);

		integrationSign = new JButton(blueIntImg);
		integrationSign.setRolloverIcon(fireIntImg);
		integrationSign.setBorderPainted(false);
		integrationSign.setContentAreaFilled(false);
		integrationSign.setFocusPainted(false);
		integrationSign.setMnemonic(KeyEvent.VK_I);

		dxSign = new JButton(bluedxImg);
		dxSign.setRolloverIcon(evildxImg);
		dxSign.setBorderPainted(false);
		dxSign.setContentAreaFilled(false);
		dxSign.setFocusPainted(false);
		dxSign.setMnemonic(KeyEvent.VK_D);

		backButton = new JButton(backImg);
		backButton.setRolloverIcon(backRollImg);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.setMnemonic(KeyEvent.VK_B);

		integrationSign.setActionCommand("Integrate");
		dxSign.setActionCommand("Derive With Respect To");
		backButton.setActionCommand("Back");

		integrationSign.addActionListener(this);
		dxSign.addActionListener(this);
		backButton.addActionListener(this);
	}

	public void setStaticConstraints() {
		asm.layout.putConstraint(SpringLayout.NORTH, background, 0, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, background, 0, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, integrationSign, 180, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, integrationSign, -5, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, dxSign, 250, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, dxSign, 675, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, backButton, 560, SpringLayout.NORTH, background);
		asm.layout.putConstraint(SpringLayout.WEST, backButton, -25, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, upperLimit, 215, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, upperLimit, 295, SpringLayout.WEST, asm.pane);		

		asm.layout.putConstraint(SpringLayout.NORTH, lowerLimit, 500, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, lowerLimit, 245, SpringLayout.WEST, asm.pane);		

		asm.layout.putConstraint(SpringLayout.NORTH, function, 335, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, function, 270, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, answer, 525, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, answer, 485, SpringLayout.WEST, asm.pane);	
	}

	public void setTextBoxStyles() {
		upperLimit.setOpaque(false);
		upperLimit.setPreferredSize(new Dimension(69, 65));
		upperLimit.setFont(boundFont);

		lowerLimit.setOpaque(false);
		lowerLimit.setPreferredSize(new Dimension(69, 65));
		lowerLimit.setFont(boundFont);

		function.setOpaque(false);
		function.setPreferredSize(new Dimension(370, 110));
		function.setFont(funcFont);

		answer.setOpaque(false);
		answer.setPreferredSize(new Dimension(430, 95));
		answer.setFont(funcFont);
	}

	public void addComponents() {
		asm.pane.add(function);
		asm.pane.add(answer);
		asm.pane.add(upperLimit);
		asm.pane.add(lowerLimit);
		asm.pane.add(background);
		asm.pane.add(integrationSign);
		asm.pane.add(dxSign);
		asm.pane.add(backButton);			
	}

	public void update() { }

	public void draw(Graphics2D g) { }

	public void paintComponent(Graphics g) {}

	/**
	 * This function computes the integration with a given function written by the user.
	 * @return Returns the answer
	 */
	private double computeIntegralA(double funcResult) {
		Expression e = new ExpressionBuilder(function.getText())
		.variables("x")
		.build()
		.setVariable("x", x);
		return e.evaluate();
		//double result = e.evaluate();
	}

	private double computeIntegralB (double step) {
		double sum = 0.0;
		double a = 0, b = 0; 
		try { //parse int?
			a = Double.parseDouble(lowerLimit.getText());
			b = Double.parseDouble(upperLimit.getText());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		for (x = a; x < b; x += step) {
			sum += computeIntegralA(x) * step;
		}
		return sum;
	}

	private double RoundTo3Decimals(double val) {
		DecimalFormat df2 = new DecimalFormat("###.###");
		return Double.valueOf(df2.format(val));
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

	public void actionPerformed(ActionEvent ae) {
		double upperNum = 0, lowerNum = 0;
		String textboxMsg = "";
		
		if (ae.getActionCommand().equals("Integrate")) {			
			System.out.println("Integrating!");
			
			try {
				upperNum = Double.parseDouble(upperLimit.getText());
				lowerNum = Double.parseDouble(lowerLimit.getText());
			} catch (Exception e) {
				e.printStackTrace();
				//textboxMsg = "Bound not a number!";
			}			
			
			try {		
				if ((!isNumeric(upperLimit.getText())) || (!isNumeric(lowerLimit.getText()))) {
					textboxMsg = "Bound not numeric!";
				}				
				else if ( upperNum < lowerNum ) {
					textboxMsg = "Bound error!";
				}
				else {
					textboxMsg = RoundTo3Decimals(computeIntegralB(0.0001))+"";
				}
				//answer.setText(result+"");
			}
			catch (Exception e) {
				e.printStackTrace();								
				textboxMsg = "Please enter a proper function!";					
			}
			answer.setText(textboxMsg);
		}		
		else if (ae.getActionCommand().equals("Derive With Respect To")) {
			System.out.println("In the future there will be a dx and dy switcher.");
		}
		else { //back
			asm.pane.stateCheck = true; //iffy?
			asm.pane.state = asm.SELECTSTATE;
		}		
	}	
}
