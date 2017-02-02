package mvc.controller;

import java.sql.SQLException;

import mvc.model.MySQLDAOFactory;
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
	
	
	
	public Controller(Model model, View view) {
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void userCreated(LoginFormEvent event) {
	
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		
		//Using the DAOFactory class
		PersonDAO personDAO = factory.getPersonDAO();
		
		System.out.println("\nLogin event received: " + event.getName() + " " + event.getPassword());
		
		try {
			personDAO.addPerson(new Person(event.getName(), event.getPassword()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
