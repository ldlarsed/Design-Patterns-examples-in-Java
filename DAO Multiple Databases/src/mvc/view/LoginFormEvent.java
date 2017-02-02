package mvc.view;

/**
 * Bean class used to transmit data between events
 *
 */
public class LoginFormEvent {
	private String name, password;

	public LoginFormEvent(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
