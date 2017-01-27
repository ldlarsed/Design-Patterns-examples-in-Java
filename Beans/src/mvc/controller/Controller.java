package mvc.controller;

import mvc.model.Model;
import mvc.view.LoginFormEvent;
import mvc.view.LoginListener;
import mvc.view.View;

/**
 * Listener class that listens to fired events.
 *
 */
public class Controller implements LoginListener{
	
	private View view;
	private Model model;
	
	public Controller(Model model, View view) {
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void loginPerformed(LoginFormEvent event) {
		System.out.println("Login event received: " + event.getName() + " " + event.getPassword());
		
	}
	
	
}
