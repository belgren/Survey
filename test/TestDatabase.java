import static org.junit.Assert.*;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.Before;
import java.util.HashMap;
public class TestDatabase {
	Database db;
	
	@Before
	public void setUp() {
		db  = new Database();
	}

	
	/**
	 * Tests the database constructor which should clear the contents of the db.
	 * @throws SQLException
	 */
	@Test
	public void testCreateDatabase() throws SQLException{
		db.createDatabase();
		HashMap<Integer, String> questionTableContents = db.getQuestions();
		assert(questionTableContents.size() == 0);
	}

	@Test
	public void testAddQuestion() throws SQLException {
		
	}
}


