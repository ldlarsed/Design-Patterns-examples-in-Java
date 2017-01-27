package mvc.controller;

import java.sql.SQLException;

import mvc.model.DAOFactory;
import mvc.model.Model;
import mvc.model.Person;
import mvc.model.PersonDAO;
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
	
	//Using the DAOFactory class
	private PersonDAO personDAO = DAOFactory.getPersonDAO();
	
	public Controller(Model model, View view) {
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void userCreated(LoginFormEvent event) {
		System.out.println("\nLogin event received: " + event.getName() + " " + event.getPassword());
		
		try {
			personDAO.addPerson(new Person(event.getName(), event.getPassword()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
