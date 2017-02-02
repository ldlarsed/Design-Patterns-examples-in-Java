package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * One DAO class per table or view. 
 * CRUD functionality must be implemented.
 *
 */
public class MySQLPersonDAO implements PersonDAO {

	/* (non-Javadoc)
	 * @see mvc.model.PersonDAO#addPerson(mvc.model.Person)
	 */
	@Override
	public int addPerson(Person person) throws SQLException{
		Connection conn = Database.getInstance().getConnection(); //usnig singleton
		
		PreparedStatement p = conn.prepareStatement("insert into people (name, password) values (?,?)");
	
		p.setString(1, person.getName());
		p.setString(2, person.getPassword());
		
		System.out.println("\nExecuting database updadte " + person.getName() + " " + person.getPassword());
		
		int updated = p.executeUpdate();
		p.close();
		
		return updated;
	}
	
	/* (non-Javadoc)
	 * @see mvc.model.PersonDAO#getPerson(int)
	 */
	@Override
	public Person getPerson(int id){
		return null;
	}
	
	/* (non-Javadoc)
	 * @see mvc.model.PersonDAO#getPeople()
	 */
	@Override
	public List<Person> getPeople() throws SQLException{
		
		List<Person> people = new ArrayList<Person>();
		
		Connection conn = Database.getInstance().getConnection();
		
		String sql = "select id, name, password from people order by id";
		
		java.sql.Statement selectStatement = conn.createStatement();
		
		java.sql.ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next()){
			//int id = results.getInt("id");
			String name = results.getString("name");
			String password = results.getString("password");
			
			Person person = new Person(name, password);
			people.add(person);
		}
		
		results.close();
		selectStatement.close();
		
		return people;
		
	}
	
	/* (non-Javadoc)
	 * @see mvc.model.PersonDAO#updatePerson(mvc.model.Person)
	 */
	@Override
	public void updatePerson(Person person){
		
	}
	
	/* (non-Javadoc)
	 * @see mvc.model.PersonDAO#deletePerson(int)
	 */
	@Override
	public void deletePerson(int id){
		
	}
}
