import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
	public static final String PORT_NUMBER = "8889";

	private Statement stmt;
	
	//Constructor that creates a new Survey database if it does not exist already.
	public CreateDatabase() {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/", "root", "root"); // MySQL
				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();) {
				this.stmt = stmt;
			// Step 3 - create our database
			String createDB = "create database if not exists StrategyDatabase;";
			String choose_database = "use StrategyDatabase;";
			stmt.execute(createDB);
			stmt.execute(choose_database);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void initializeTables(Statement stmt) {
		String createTable = "create table if not exists";////finish this statement 
	}
}
