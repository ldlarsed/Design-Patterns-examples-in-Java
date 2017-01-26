package mvc.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import mvc.model.Model;

public class View extends JFrame{

	private static final long serialVersionUID = 1L;
	private Model model;

	public View(Model model) throws HeadlessException {
		super("MVC Demo");
		
		this.model = model;
				
		//JFrame specific
		this.setSize(new Dimension(800, 600));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	

}
