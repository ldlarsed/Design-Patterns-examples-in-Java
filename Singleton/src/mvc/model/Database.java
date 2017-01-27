package mvc.model;

/**
 * Singleton class. 
 * Allows only one connection to the database at time.
 *
 */
public class Database {
	
	private static Database instance = new Database();

	private Database() {
		
	}
	
	/**
	 * Standard way of providing the singleton object.
	 * @return
	 */
	public static Database getInstance() {
		return instance;
	}
	
	
	private static Database instanceClassic;
	
	/**
	 * Classic way of providing singleton with lazy instantation. 
	 * The instatiation won't happend until the last possible moment
	 * until the getInstanceClassic() is called.
	 * 
	 * This method is not thread safe!
	 * Multiple threads can call this method multiple times.
	 * @return
	 */
	public static Database getInstanceClassic(){
		if(instanceClassic == null)
			instanceClassic = new Database();
		
		return instanceClassic;
	}
	
	public void connect(){
		System.out.println("Connected to the database.");
	}
	
	public void disconnect() {
		System.out.println("Disconnected from the database.");
	}
	
}
