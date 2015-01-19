package appstate;

/** 
 * This class is the consumer surplus screen of the app.
 * @author Peter Huang
 * @version 1.0.1 January 9 2014
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

public class ConsumerSurplusState extends AppState implements ActionListener {

	/** 
	 * background - JLabel reference variable
	 */
	JLabel background = new JLabel();

	/**
	 *  JButton reference for the integration sign.
	 */
	JButton submit = new JButton();

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
	 * upperLimit - reference for the commodities sold input field. (b)
	 */
	JTextField comSold = new JTextField();

	/**
	 * lowerLimit - reference for the commodity price input field. (a)
	 */
	JTextField comPrice= new JTextField();

	/**
	 * boundFont - textbox italic font
	 */
	Font boundFont = new Font ("Segoe UI", Font.ITALIC, 25);

	/**
	 * funcFont - textbox plain font
	 */
	Font funcFont = new Font ("Segoe UI", Font.PLAIN, 25);
	
	/**
	 * correctInput - determines if the commodities sold price is a correct input
	 */
	boolean correctInput = false;

	double x = 0;

	//make a clear button and also make an answer textbox!

	/**
	 * This constructor sets up the app state manager inside the definite integration state.
	 * @param asm AppStateManager reference variable
	 */
	public ConsumerSurplusState(AppStateManager asm){
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
		ImageIcon submitImg = new ImageIcon();
		ImageIcon submitHoverImg = new ImageIcon();
		ImageIcon backImg = new ImageIcon();
		ImageIcon backRollImg = new ImageIcon();  

		try {
			backgroundImg = new ImageIcon("src/res/definite_integration/consumer_bg.png");
			submitImg = new ImageIcon("src/res/definite_integration/submit_normal.png");
			submitHoverImg = new ImageIcon("src/res/definite_integration/submit_hover.png");
			backImg = new ImageIcon("src/res/menu_options/Back.png");
			backRollImg = new ImageIcon("src/res/menu_options/Back2.png");	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		background = new JLabel(backgroundImg);

		submit = new JButton(submitImg);
		submit.setRolloverIcon(submitHoverImg);
		submit.setBorderPainted(false);
		submit.setContentAreaFilled(false);
		submit.setFocusPainted(false);
		submit.setMnemonic(KeyEvent.VK_S);

		backButton = new JButton(backImg);
		backButton.setRolloverIcon(backRollImg);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.setMnemonic(KeyEvent.VK_B);

		submit.setActionCommand("Calculate Consumer Surplus");
		backButton.setActionCommand("Back");

		submit.addActionListener(this);
		backButton.addActionListener(this);
	}

	public void setStaticConstraints() {
		asm.layout.putConstraint(SpringLayout.NORTH, background, 0, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, background, 0, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, submit, 420, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, submit, 645, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, backButton, 565, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, backButton, 20, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, comSold, 265, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, comSold, 665, SpringLayout.WEST, asm.pane);		

		asm.layout.putConstraint(SpringLayout.NORTH, comPrice, 345, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, comPrice, 665, SpringLayout.WEST, asm.pane);		

		asm.layout.putConstraint(SpringLayout.NORTH, function, 185, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, function, 665, SpringLayout.WEST, asm.pane);

		asm.layout.putConstraint(SpringLayout.NORTH, answer, 530, SpringLayout.NORTH, asm.pane);
		asm.layout.putConstraint(SpringLayout.WEST, answer, 380, SpringLayout.WEST, asm.pane);	
	}

	public void setTextBoxStyles() {
		comSold.setOpaque(false);
		comSold.setPreferredSize(new Dimension(260, 45));
		comSold.setFont(boundFont);

		comPrice.setOpaque(false);
		comPrice.setPreferredSize(new Dimension(260, 45));
		comPrice.setFont(boundFont);
		comPrice.setEditable(false);

		function.setOpaque(false);
		function.setPreferredSize(new Dimension(260, 45));
		function.setFont(funcFont);

		answer.setOpaque(false);
		answer.setPreferredSize(new Dimension(530, 80));
		answer.setFont(funcFont);
		answer.setEditable(false);
	}

	public void addComponents() {
		asm.pane.add(function);
		asm.pane.add(answer);
		asm.pane.add(comSold);
		asm.pane.add(comPrice);
		asm.pane.add(submit);
		asm.pane.add(backButton);
		asm.pane.add(background);					
	}

	public void update() { }

	public void draw(Graphics2D g) { }

	public void paintComponent(Graphics g) {}

	/**
	 * This function computes the integration with a given function written by the user.
	 * @return Returns the answer
	 */
	private double computeIntegralA() {
		Expression e = new ExpressionBuilder(function.getText())
		.variables("x")
		.build()
		.setVariable("x", x);
		return e.evaluate();
		//double result = e.evaluate();
	}
	
	private double commoditySoldParse(){
		double a = 0;
		try {
			a = Double.parseDouble(comSold.getText());
			correctInput = true;
		}
		catch (Exception e){
			e.printStackTrace();
			correctInput = false;
			answer.setText("Please enter a proper number for commodities sold!");
			a = Double.NaN;
		}
		return a;
	}

	private double computeIntegralB (double step) {
		double sum = 0;
		double sold1 = 0;
		double adjustedPrice = 0;
				
		sold1 = commoditySoldParse(); //checks to make sure correct input is activated!
		System.out.println(sold1+"");
		
		System.out.println(computeIntegralA());
		if (correctInput){
			for (x = 0; x < sold1; x += step) {
				sum += (computeIntegralA())* step;
			}
			
			adjustedPrice = RoundTo2Decimals(computeIntegralA());
			
			for (int y = 0; y < sold1; y+=1){
				//sum -= computeIntegralA()*1;
				sum -= adjustedPrice;
			}
		    			
			System.out.println("The value of x:" + x);
			System.out.println("The value of price:" + computeIntegralA() + "" + adjustedPrice);
			comPrice.setText("$"+adjustedPrice);
			return sum;
		}
		return Double.NaN;
	}	

	private double RoundTo2Decimals(double val) {
		DecimalFormat df2 = new DecimalFormat("###.##");
		return Double.valueOf(df2.format(val));
	}
	
	private void clearFields(){
		comSold.setText("");
		comPrice.setText("");
		function.setText("");
		answer.setText("");
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Calculate Consumer Surplus")) {			
			//answer.setText("Loading...");
			System.out.println("Calculating!");
			try {
				answer.setText("$"+(RoundTo2Decimals(computeIntegralB(0.0001))));
				//answer.setText(result+"");
			}
			catch (Exception e) {
				e.printStackTrace();
				answer.setText("Please enter a proper function!");				
			}
		}
		else { //back
			asm.pane.stateCheck = true; //iffy?
			clearFields();
			asm.pane.state = asm.MENUSTATE;
		}
	}	
}



