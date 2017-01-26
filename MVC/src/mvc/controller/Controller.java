package mvc.controller;

import mvc.model.Model;
import mvc.view.View;

public class Controller {
	
	private View view;
	private Model model;
	public Controller(Model model, View view) {
		this.view = view;
		this.model = model;
	}
	
	
}
