package mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.xml.transform.Source;

import mvc.model.Model;

/**
 * Subject class that raises the event.
 *
 */
public class View extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Model model;
	private JPanel topPanel, mainPanel, inputsPanel, buttonsPanel;
	private JLabel nameLabel, passwordLabel;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JButton confirmButton, closeButton;

	private LoginListener loginListener;

	public View(Model model) throws HeadlessException {
		super("MVC Demo");

		this.model = model;

		// Layout
		mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel);

		// Instantiate components
		createComponents();

		// JFrame specific
		this.setSize(new Dimension(600, 400));
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLookAndFeel();
	}

	private void createComponents() {
		inputsPanel = new JPanel(new GridLayout(2, 2));
		nameLabel = new JLabel("Name: ");
		nameField = new JTextField(10);
		passwordLabel = new JLabel("Password: ");
		passwordField = new JPasswordField(10);

		inputsPanel.add(nameLabel);
		inputsPanel.add(nameField);
		inputsPanel.add(passwordLabel);
		inputsPanel.add(passwordField);
		inputsPanel.setBorder(new TitledBorder("Credentials"));

		mainPanel.add(inputsPanel, BorderLayout.CENTER);

		buttonsPanel = new JPanel(new GridLayout(1, 2));
		confirmButton = new JButton("Log In");
		closeButton = new JButton("Close");

		buttonsPanel.add(confirmButton);
		buttonsPanel.add(closeButton);

		mainPanel.add(buttonsPanel, BorderLayout.AFTER_LAST_LINE);

		// Connect buttons to listener
		confirmButton.addActionListener(this);
		closeButton.addActionListener(this);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String password = new String(passwordField.getPassword());
		String name = nameField.getText();
		
		JButton source = (JButton) e.getSource();
		
		if(source == confirmButton)
		fireLoginEvent(new LoginFormEvent(name, password));
		else
			this.dispose(); //close the application
	}

	private void fireLoginEvent(LoginFormEvent event){
		if (loginListener != null)
			loginListener.loginPerformed(event);
	}
	
	/**
	 * This one is called from the main method
	 * @param loginListener
	 */
	public void setLoginListener(LoginListener loginListener) {
		this.loginListener = loginListener;
	}
	
}
