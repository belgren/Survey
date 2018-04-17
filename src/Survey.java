import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Survey class, which creates the survey
 */
public class Survey {

	private QuestionStrategy question;
	private ArrayList<QuestionStrategy> questionList;
	private static ArrayList<Email> emailList;
	private int counter;
	private ArrayList<HashMap<String, Integer>> allAnswerTallys;
	private String surveyName;
	private HashMap<Integer, QuestionStrategy> questionNumberMap;
	private HashMap<String, Integer> answerData;

	private Database database;
	private ResultSet report;
	HashMap<Integer, ResultSet> rsMap;
	private String returnValue;
	private String surveySummary;

	private static Survey surveyInstance;

	//Get the only object available
	public static Survey getInstance(String name){
		if(surveyInstance == null) {
			surveyInstance = new Survey(name);
		}
		
		return surveyInstance;
	}
	
	/**
	 * Constructor for survey. Sets up survey name, initializes question counter to 0,
	 * instantiates various fields and creates the database
	 * @param name
	 */
	private Survey(String name) {
		surveyName = name;
		counter = 0;
		allAnswerTallys = new ArrayList<HashMap<String, Integer>>();
		questionNumberMap = new HashMap<Integer, QuestionStrategy>();
		emailList = new ArrayList<Email>();
		questionList = new ArrayList<QuestionStrategy>();
	
		database = new Database();
		try {  
			database.createDatabase();
		} catch (SQLException e) {
			System.out.println("Error creating Database");
			e.printStackTrace();
		}
	}

	/**
	 * Creates a yes/no question object with the given question text. Adds the question to
	 * the survey's attribute, questionList. Assigns question number based on
	 * incrementing counter. Adds the new question to the Question table in
	 * SurveyDatabase.
	 * @param questionText
	 */
	public void addYesNoQuestion(String questionText) {
		question = new YesNoQuestion(questionText);
		counter++;
		question.setQuestionNumber(counter);
		questionList.add(question);
		questionNumberMap.put(counter, question);
		try {
			database.addQuestion(counter, questionText);
		} catch (SQLException e) {
			System.out.println("Error adding question " + counter + " to the database.");
			e.printStackTrace();
		}
	}

	/**
	 * Creates a multiple choice question object with the given question text.
	 * Adds the question to the survey's attribute, questionList. Assigns question number based on
	 * incrementing counter. Adds the new question to the Question table in
	 * SurveyDatabase.
	 * @param questionText
	 */
	public void addMultChoiceQuestion(String questionText, ArrayList<String> options) {
		question = new MultipleChoiceQuestion(questionText);
		counter++;
		question.setQuestionNumber(counter);
		questionList.add(question);
		questionNumberMap.put(counter, question);
		question.setOptions(options);
		try {
			database.addQuestion(counter, questionText);
			//add valid answers to db, so options are always reported
			database.addAnswer("A", counter);
			database.addAnswer("B", counter);
			database.addAnswer("C", counter);
			database.addAnswer("D", counter);
			
		} catch (SQLException e) {
			System.out.println("Error adding question " + counter + " to the database.");
			e.printStackTrace();
		}
	}

	/**
	 * Creates a short answer question object with the given question text.
	 * Adds the question to the survey's attribute, questionList. Assigns question number based on
	 * incrementing counter. Adds the new question to the Question table in
	 * SurveyDatabase.
	 * @param questionText
	 */
	public void addShortAnswerQuestion(String questionText) {
		question = new ShortAnswerQuestion(questionText);
		counter++;
		question.setQuestionNumber(counter);
		questionList.add(question);
		questionNumberMap.put(counter, question);
		try {
			database.addQuestion(counter, questionText);
		} catch (SQLException e) {
			System.out.println("Error adding question " + counter + " to the database.");
			e.printStackTrace();
		}
	}

	/**
	 * Gets emails and creates list of email objects.
	 */
	public void getSurveyEmailData() {
		String protocol = "imap";
		String host = "imap.gmail.com";
		String port = "993";
		String userName = "cp274survey@gmail.com";
		String password = "DarrylBenJordan";
		EmailReader reader = new EmailReader(surveyName);
		emailList = reader.downloadEmails(protocol, host, port, userName, password);
	} 

	/**
	 * Loops through the list of emails submitted and for a survey, splits each
	 * email up by line and loops through the lines. Checks if the line has a line
	 * number in the first place and if so, finds the corresponding question number
	 * in the hashmap of questions. Then, creates an answer object containing the
	 * test of the rest of the line and adds that answer to the corresponding
	 * question's list of answers.
	 * @param emailList
	 * @param questionList
	 */
	public void separateAnswers(ArrayList<Email> emailList, ArrayList<QuestionStrategy> questionList) {
		for (Email email : emailList) {
			String surveyAnswers = email.getMessage();
			String[] answersPerEmail = surveyAnswers.split("\n"); 
			  
			for (String line : answersPerEmail) {
				try {
					String questionNumberAsString = line.substring(0, 1);
					int questionNumber = Integer.parseInt(questionNumberAsString);
					if (questionNumberMap.keySet().contains(questionNumber)) {
  
						// old way without db
						QuestionStrategy currentQuestion = questionNumberMap.get(questionNumber);
						String answerText = line.substring(2);
						answerText = answerText.trim();
						if (currentQuestion.isValidAnswer(answerText)) {
							Answer answer = new Answer(answerText);
							answer.setQuestionNumber(questionNumber);
							answerText = currentQuestion.addAnswer(answer);
	
							// using db 
							try {
								database.addAnswer(answerText, questionNumber);
							} catch (SQLException e) {
								System.out.println("Error adding answer: " + answerText);
								e.printStackTrace();
							}
						}
						
					}
				} catch (NumberFormatException e) {
				} // this catches line spaces in emails
			}
		}
	}

	/**
	 * Loops through the question numbers in the hashmap that keeps track of them,
	 * calling the printSeparatedReport method on each qid. This method generates a
	 * new report that displays each answer and the number of occurrences, for the
	 * inputed question.
	 */
	public String printReport() {
		returnValue = "";
		for (int qid : this.questionNumberMap.keySet()) {

			if (qid>1) {
				returnValue += "\n\n";
			}
			returnValue += "Question " + qid + ":  ";

			returnValue += questionNumberMap.get(qid).toString();
			
			QuestionStrategy currentQuestion = questionNumberMap.get(qid);

			ArrayList<String> options = currentQuestion.getOptions();


			try {
				answerData = database.printSeparatedReport(qid);
			} catch (SQLException e) { 
				e.printStackTrace();
				System.out.println("Error in printReport");
			}
			
			if (options != null) { //question is of type multiple choice
				int i = 0;
				for (String answer : answerData.keySet()) {
					int count = answerData.get(answer)-1;		
					returnValue += "\nAnswer: " + options.get(i) + "  ➪  Tally: " + count;
					i++;
				}
			}
			else {
				for (String answer : answerData.keySet()) {
					int count = answerData.get(answer);		
					returnValue += "\nAnswer: " + answer + "  ➪  Tally: " + count;
				}
			}
			
		}
		//System.out.println(returnValue);
		return returnValue;
	}
	
	public String makeReportFile(){
		surveySummary = this.printReport();
		File file = new File("SurveyReport.txt");
	    FileWriter writer = null;
	    try {
	        writer = new FileWriter(file);
	        writer.write("Test" + surveySummary);
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    } finally {
	        if (writer != null) try { writer.close(); } catch (IOException ignore) {}
	    }
	    String message = "Survey results file is located at: \n" + file.getAbsolutePath();
	    return message;
	}
	
	public String getSurveyName(){
		return surveyName;
	}
	
	public ArrayList<QuestionStrategy> getQuestionList(){
		return questionList;
	}

	public void setQuestionList(ArrayList<QuestionStrategy> questionList) {
		this.questionList = questionList;
	}

	public int getQuestionNumber() {
		return questionList.size();
	}
	
	public ArrayList<Email> getEmailList() {
		return emailList;
	}
}