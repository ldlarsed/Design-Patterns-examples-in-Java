package mvc.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mvc.model.Model;

public class View extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Model model;
	private JButton button, closeButton;

	public View(Model model) throws HeadlessException {
		super("MVC Demo");
		
		this.model = model;
		
		//Layout
		this.setLayout(new GridBagLayout());
		
		
		//Instantiate components
		createButton();
		createCloseButton();
		
		//JFrame specific
		this.setSize(new Dimension(600, 400));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLookAndFeel();
	}

	private void createButton() {
		button = new JButton("Click Me!");
		button.addActionListener(this);
		button.setName("Thank's for clicking");
		
		//Set another listener as example
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Secondary listener fired: Main button clicked!");
			}
		});
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		this.add(button, gc);
	}
	
	private void createCloseButton(){
		closeButton = new JButton("Close");
		closeButton.addActionListener(this);
		closeButton.setName("Close");
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		this.add(closeButton, gc);
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
		
		JButton source = (JButton) e.getSource();
		
		if(source == button)
			JOptionPane.showMessageDialog(this, source.getName());
		else{
			JOptionPane.showMessageDialog(this, "Some other button");
			this.dispose();
		}
	}
	
	
}
