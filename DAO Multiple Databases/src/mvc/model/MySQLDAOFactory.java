package mvc.model;

public class MySQLDAOFactory extends DAOFactory {

	/* (non-Javadoc)
	 * @see mvc.model.DAOFactory#getPersonDAO()
	 */
	@Override
	public PersonDAO getPersonDAO(){
		return new MySQLPersonDAO();
	}

	/* (non-Javadoc)
	 * @see mvc.model.DAOFactory#getLogDAO()
	 */
	@Override
	public LogDAO getLogDAO(){
		return new MySQLLogDAO();
	}
}
