package mvc.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class. 
 * Allows only one connection to the database at time.
 *
 */
public class Database {
	
	private static Database instance = new Database();
	
	private Connection con;

	private Database() {
		readDbCredentials();
	}
	
	/**
	 * Standard way of providing the singleton object.
	 * @return
	 */
	public static Database getInstance() {
		return instance;
	}
	
/*	
	private static Database instanceClassic;
	
	*//**
	 * Classic way of providing singleton with lazy instantation. 
	 * The instatiation won't happend until the last possible moment
	 * until the getInstanceClassic() is called.
	 * 
	 * This method is not thread safe!
	 * Multiple threads can call this method multiple times.
	 * @return
	 * @throws Exception 
	 *//*
	public static Database getInstanceClassic(){
		if(instanceClassic == null)
			instanceClassic = new Database();
		
		return instanceClassic;
	}
	*/
	
	public Connection getConnection() {
		return con;
	}
	
	public void connect() throws Exception{
		if(con != null)
			return;
		try {
			//http://stackoverflow.com/questions/17484764/java-lang-classnotfoundexception-com-mysql-jdbc-driver-in-eclipse
		
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			Class.forName("com.mysql.jdbc.Driver");
		
		}catch(ClassNotFoundException e){
			throw new Exception("Driver not found");
		}
		
		String[] cred = readDbCredentials();
		
		String url = String.format(cred[0], cred[1]);
		
		con = DriverManager.getConnection(url, cred[2], cred[3]);
		
		System.out.println("\nSuccesfully connected to database.");
	}
	
	public void disconnect() {
		if(con != null){
			try {
				con.close();
				
			} catch(SQLException e){
				System.out.println("\nCannot close the database connection.");
			}
		}
		
		con = null;
	}
	
	/**
	 * Read credentials and settings to MySql database 
	 * @return
	 */
	private String[] readDbCredentials(){
		String[] dbCredentials = new String[4];
		try {
			for (int i = 0; i < dbCredentials.length; i++) {
				dbCredentials[i] = Files.readAllLines(Paths.get("src/mvc/model/credentials.txt")).get(i);
				System.out.print(dbCredentials[i].toString() + " ");
			}
		} catch (IOException e) {
			System.out.println("Colud not read the credentials file.");
			e.printStackTrace();
		}
		
		return dbCredentials;
	}
	
}
