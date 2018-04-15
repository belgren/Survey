import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ArrayList;

public class Database {

	private Statement stmt;
	private Connection conn;
	public static final String PORT_NUMBER = "8889";
	
	private HashMap<String, Integer> answersTally;
	private ArrayList<Integer> questionNumbers;
	private HashMap<Integer, ResultSet> separatedReports;
	private ResultSet currentReport;
	
	/**
	 * Constructor that establishes a new connection and creates a new statement
	 */
	public Database() {
		separatedReports = new HashMap<Integer, ResultSet>();
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
		String createAnswertable = "CREATE table Answer(AID int, AnswerText Varchar(100), QID int, "
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
	public void addAnswer(int AID, String answerText, int questionNumber) throws SQLException {
		String chooseDatabase = "use StrategyDatabase;"; // select the correct DB
		stmt.execute(chooseDatabase);

		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Answer(AID, AnswerText, QID) VALUES (?, ?, ?)");

		pstmt.setInt(1, AID);
		pstmt.setString(2, answerText);
		pstmt.setInt(3, questionNumber);

		pstmt.executeUpdate();
	}


	/** 
	 * Generates a SQL report summarizing the answer data for the question identefied 
	 * by the input question ID.
	 * @param QID
	 * @throws SQLException
	 */
	public void printSeparatedReport(int QID) throws SQLException {
		String chooseDatabase = "use StrategyDatabase;"; // select the correct DB
		stmt.execute(chooseDatabase);
		
		String qidQuery = "SELECT AnswerText, count(AnswerText) FROM Answer WHERE QID = " + QID + " GROUP BY AnswerText;";
		ResultSet r = stmt.executeQuery(qidQuery);
		
		while (r.next()) {
			System.out.print("\nAnswer: " + r.getString(1) + " ======= count : " + r.getInt(2));
		}
		r.close();
	}
	
	public static void main(String args[]) {
		Database db = new Database();
		try {
			db.createDatabase();
			db.addQuestion(1, "Q1");
			db.addAnswer(1, "ONE", 1);
		}
		catch(SQLException e) { 
			e.printStackTrace();
		}
		
	}
}