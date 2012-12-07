package lhs.qmaker.account;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;

public class SignInPanel extends JPanel {
	private JTextField emailField;

	/**
	 * Create the panel.
	 */
	public SignInPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JTextField emailText = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, emailText, 10, SpringLayout.WEST, this);
		emailText.setText("email:");
		springLayout.putConstraint(SpringLayout.NORTH, emailText, 10, SpringLayout.NORTH, this);
		add(emailText);
		
		emailField = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, emailText, this.getWidth()/-2 , SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, emailField, this.getWidth()/2, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, emailField, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, emailField, -10, SpringLayout.EAST, this);
		add(emailField);

	}
}
