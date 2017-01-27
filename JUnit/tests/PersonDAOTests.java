import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import mvc.model.DAOFactory;
import mvc.model.Database;
import mvc.model.Person;
import mvc.model.PersonDAO;

public class PersonDAOTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Database.getInstance().connect();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Database.getInstance().disconnect();
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("set up");
		
		PersonDAO dao = DAOFactory.getPersonDAO();
		
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tear down");
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		int value = 7;
		value += 2;
		
		assertEquals("Check if it works", 9, value);
	}
	
	@Test
	public void testCreate() throws SQLException {
		
		PersonDAO dao = DAOFactory.getPersonDAO();
		
		Person person1 = new Person("Bob", "letmein");
		Person person2 = new Person("Sue", "qwerty");
		
		dao.addPerson(person1);
		dao.addPerson(person2);
		
		List<Person> people = dao.getPeople();
		
		assertEquals("Should be two peolple", 2, people.size());
		assertEquals("These two people should be the same", person1, people.get(0));
		
	}

}
