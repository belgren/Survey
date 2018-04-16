import static org.junit.Assert.*;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.Before;
import java.util.HashMap;
public class TestDatabase {
	private Database db;
	private HashMap<Integer, String> questionTableContents;
	private HashMap<String, Integer> answerTableContents;

	
	@Before
	public void setUp(){
		db  = new Database();
	}

	
	/**
	 * Tests the database constructor which should clear the contents of the db.
	 * @throws SQLException
	 */
	@Test
	public void testCreateDatabase() throws SQLException{
		db.createDatabase();//clear the db
		questionTableContents = db.getQuestions();
		assert(questionTableContents.size() == 0);
	}

	@Test
	public void testAddQuestion() throws SQLException {
		db.createDatabase();//clear the db
		db.addQuestion(10, "question one");
		questionTableContents = db.getQuestions();
		//confirm the adding of one question to the Question table in the database
		assert(questionTableContents.size() == 1);
		Object questionNumber = questionTableContents.keySet().toArray()[0];
		//test the question number
		assertEquals(questionNumber.toString(), "10");
		//test the question text 
		assertEquals(questionTableContents.get(questionNumber), "question one");
	}
	
	@Test
	public void testAddAnswer() throws SQLException {
		db.createDatabase(); //clear the db
		//it is necessary to add a question because the QID in answer is a 
		//foreign key referencing the QID col in Questions.
		db.addQuestion(1, "question one"); 	
		db.addAnswer("answer one", 1);	//'answer one' provided for question number 1
		db.addAnswer("answer one", 1);  //'answer one' provided for question number 1
		answerTableContents = db.getAnswers();
		
		Object firstAnswer = answerTableContents.keySet().toArray()[0];
		System.out.println(firstAnswer);
		assertEquals(firstAnswer.toString(), "answer one");
	}
	
	@Test 
	public void testDBTally() throws SQLException {
		db.createDatabase(); //clear the db
		
		db.addQuestion(1, "question one"); 	
		db.addAnswer("answer one", 1);	//'answer one' provided for question number 1
		db.addAnswer("answer one", 1);  //'answer one' provided for question number 1
		db.addAnswer("answer two", 1);  //'answer one' provided for question number 1

		answerTableContents = db.printSeparatedReport(1);		//get the answer report for question 1
		
		//confirm that the first answer is 'answer one' and it occurs twice
		int occurrances = answerTableContents.get("answer one");
		assertEquals(occurrances, 2);
	}
}


