package mvc.model;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {

	int addPerson(Person person) throws SQLException;

	Person getPerson(int id);

	List<Person> getPeople() throws SQLException;

	void updatePerson(Person person);

	void deletePerson(int id);

}