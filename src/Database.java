
/**
 * This class defines the methods that allow our program to interact with a database.
 * Each time a new database is created using the createDatabase method, the database "StrategyDatabase"
 * is cleared, and then reset with new Answer and Question tables.  These tables are populated 
 * when a user creates questions and when the emails containing answers are processed, and
 * the survey results are tallied using the queries in getAnswers method.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class Database {

	private Statement stmt;
	private Connection conn;
	public static final String PORT_NUMBER = "8889";

	private HashMap<String, Integer> tuple;
	HashMap<Integer, String> questionTableContents;

	/**
	 * Constructor that establishes a new connection and creates a new statement
	 */
	public Database() {
		
		try {
			// Allocate a database "Connection" object
			conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/", "root", "root"); // MySQL
			// Allocate a "Statement" object in the Connection
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Getter method to return the set of questions as question number and text
	 * 
	 * @return
	 * @throws SQLException
	 */
	public HashMap<Integer, String> getQuestions() throws SQLException {
		questionTableContents = new HashMap<Integer, String>();

		String choose_database = "use StrategyDatabase;";
		stmt.execute(choose_database);

		String getQuestionsQuery = "SELECT QID, QuestionText FROM Question;";
		ResultSet report = stmt.executeQuery(getQuestionsQuery);

		while (report.next()) {
			int qid = report.getInt(1);
			String qText = report.getString(2);
			questionTableContents.put(qid, qText);
		}
		return questionTableContents;
	}

	/**
	 * Getter method to return all answers in Answer table as Answer text and number
	 * of occurrences.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Integer> getAnswers() throws SQLException {
		tuple = new HashMap<String, Integer>();

		String choose_database = "use StrategyDatabase;";
		stmt.execute(choose_database);

		String getAnswersQuery = "SELECT AnswerText, count(*) FROM Answer GROUP BY AnswerText;";
		ResultSet report = stmt.executeQuery(getAnswersQuery);

		while (report.next()) {
			tuple.put(report.getString(1), report.getInt(2));
		}
		return tuple;
	}

	/**
	 * Creates a new database called StrategyDatabase and performs the necessary
	 * setup tasks: deletes Question and Answer tables if they exist from a previous
	 * survey, then creates new versions of both. Question table has columns
	 * Question ID and Question Text, while Answer table has columns Answer ID,
	 * Answer Text, and the associated Question ID.
	 * 
	 * @throws SQLException
	 */
	public void createDatabase() throws SQLException {
		// create our database
		String createDB = "create database if not exists StrategyDatabase;";
		stmt.execute(createDB);

		String choose_database = "use StrategyDatabase;";
		stmt.execute(choose_database);

		// drop old question and answer tables from the database
		String dropAnswerTable = "drop table if exists Answer;";
		String dropQuestionTable = "drop table if exists Question;";
		stmt.execute(dropAnswerTable);
		stmt.execute(dropQuestionTable);

		// Add Question and Answer tables
		String createQuestionTable = "create table Question(QID int, QuestionText Varchar(100), Primary Key(QID));";

		String createAnswertable = "CREATE table Answer(AID int AUTO_INCREMENT, AnswerText Varchar(100), QID int, "
				+ "Primary Key(AID), " + "Foreign Key(QID) REFERENCES Question(QID));";

		stmt.execute(createQuestionTable);
		stmt.execute(createAnswertable);

	}

	/**
	 * Adds a new question entry to the Question table in StrategyDatabase The input
	 * question Number is used as the questionID primary key.
	 * 
	 * @param questionNumber
	 * @param questionText
	 * @throws SQLException
	 */
	public void addQuestion(int questionNumber, String questionText) throws SQLException {
		String chooseDatabase = "use StrategyDatabase;"; // select the correct DB
		stmt.execute(chooseDatabase);

		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Question(QID, QuestionText) VALUES (?, ?)");

		pstmt.setInt(1, questionNumber);
		pstmt.setString(2, questionText);
		pstmt.executeUpdate();
	}

	/**
	 * Creates a new answer entry in the Answer table, using input AID, text, and
	 * the ID of the question that is answered by the given answer. This establishes
	 * the foreign key reference to QID.
	 * 
	 * @param AID
	 * @param answerText
	 * @param questionNumber
	 * @throws SQLException
	 */
	public void addAnswer(String answerText, int questionNumber) throws SQLException {
		String chooseDatabase = "use StrategyDatabase;"; // select the correct DB
		stmt.execute(chooseDatabase);

		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Answer(AnswerText, QID) VALUES (?, ?)");

		pstmt.setString(1, answerText);
		pstmt.setInt(2, questionNumber);

		pstmt.executeUpdate();
	}

	/**
	 * Generates a SQL report summarizing the answer data for the question
	 * identefied by the input question ID.
	 * 
	 * @param QID
	 * @throws SQLException
	 */
	public HashMap<String, Integer> printSeparatedReport(int QID) throws SQLException {
		tuple = new HashMap<String, Integer>();

		String chooseDatabase = "use StrategyDatabase;"; // select the correct DB
		stmt.execute(chooseDatabase);

		String qidQuery = "SELECT AnswerText, count(AnswerText) FROM Answer WHERE QID = " + QID
				+ " GROUP BY AnswerText;";
		ResultSet r = stmt.executeQuery(qidQuery);

		while (r.next()) {
			String atxt = r.getString(1);
			int count = r.getInt(2);
			tuple.put(atxt, count);
		}
		r.close();
		return tuple;
	}

}