package mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.xml.transform.Source;

import mvc.model.Database;
import mvc.model.Model;

/**
 * Subject class that raises the event.
 *
 */
public class View extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Model model;
	private JPanel topPanel, mainPanel, inputsPanel, textAreaPanel, buttonsPanel;
	private JLabel nameLabel, passwordLabel;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JButton confirmButton, closeButton;
	private JTextArea textArea;

	private LoginListener loginListener;

	public View(Model model) throws HeadlessException {
		super("AppDAOClasses");

		this.model = model;

		// Layout
		mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel);

		// Instantiate components
		createComponents();

		// JFrame specific
		this.setSize(new Dimension(600, 800));
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLookAndFeel();

		// Using singleton class
		//Database db = Database.getInstance();

		// Connect and disconnect from the database with singleton when windows
		// opens and closes
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// Use method chaining with the singleton
				Database.getInstance().disconnect();
				super.windowClosing(e);
			}

			@Override
			public void windowOpened(WindowEvent e) {
				try {
					Database.getInstance().connect();
					super.windowOpened(e);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(View.this, "Unable to connect to the database.", "Error",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
	}
	
SLUTTET HER
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
		
		//textAreaPanel = new JPanel(new BorderLayout());
		textArea = new JTextArea(10, 10);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textAreaPanel = new JPanel(new BorderLayout());
		textAreaPanel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		mainPanel.add(inputsPanel, BorderLayout.NORTH);
		mainPanel.add(textAreaPanel, BorderLayout.CENTER);

		buttonsPanel = new JPanel(new GridLayout(1, 2));
		confirmButton = new JButton("Create user");
		closeButton = new JButton("Close");

		buttonsPanel.add(confirmButton);
		buttonsPanel.add(closeButton);

		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

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

		if (source == confirmButton)
			fireLoginEvent(new LoginFormEvent(name, password));
		else
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); // close
																					// the
																					// application
	}

	private void fireLoginEvent(LoginFormEvent event) {
		if (loginListener != null)
			loginListener.userCreated(event);
	}

	/**
	 * This one is called from the main method
	 * 
	 * @param loginListener
	 */
	public void setLoginListener(LoginListener loginListener) {
		this.loginListener = loginListener;
	}

}
