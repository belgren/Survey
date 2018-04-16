import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;
import java.lang.NumberFormatException;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Survey class, which creates the survey. Also has a main the does UI/O.
 * 
 * @author Jordan
 *
 */
public class Survey {

	private QuestionStrategy question;
	private ArrayList<QuestionStrategy> questionList;
	private static ArrayList<Email> emailList;
	private int counter;
	private ArrayList<HashMap<String, Integer>> allAnswerTallys;
	private String surveyName;
	private HashMap<Integer, QuestionStrategy> questionNumberMap;
	private static String option1 = "";
	private static String option2 = "";
	private static String option3 = "";
	private static String option4 = "";
	private HashMap<String, Integer> answerData;


	private Database database;
	private ResultSet report;
	HashMap<Integer, ResultSet> rsMap;


	private static Survey surveyInstance;
	

	//Get the only object available
	public static Survey getInstance(String name){
		if(surveyInstance == null) {
			surveyInstance = new Survey(name);
		}
		
		return surveyInstance;
	}
	
	/**
	 * Constructor for survey. Sets up survey name, initializes question counter to
	 * 0, initializes allAnswerTallys
	 * 
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
	 * creates a question object with the given question text. Adds the question to
	 * the survey's attribute, questionList Assigns question number based on
	 * incrementing counter. Adds the new question to the Question table in
	 * SurveyDatabase.
	 * 
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
	 * creates a multiple choice question object with the given question text.
	 * Adds the question to the survey's attribute, questionList
	 * @param questionText
	 */
	public void addMultChoiceQuestion(String questionText) {
		question = new MultipleChoiceQuestion(questionText);
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
	 * creates a quantity question object with the given question text.
	 * Adds the question to the survey's attribute, questionList
	 * @param questionText
	 */
	public void addQuantityQuestion(String questionText) {
		question = new QuantityQuestion(questionText);
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
	 * Prints survey instructions followed by survey questions
	 */
	public void showSurvey() {
		int i = 1;
		System.out.println("\nWelcome to " + surveyName + "!");
		System.out.println("\nInstructions:");
		System.out.println("Please email your survey answers to cp274survey@gmail.com");
		System.out.println("Email subject must be \"" + surveyName + "\"");
		System.out.println("Separate question answers by line\n");
  
		for (QuestionStrategy question : questionList) {
			if(question.getQuestionType() == 2) {
				System.out.println("Question " + i + ": " + question);
				System.out.println("            A: " + option1);
				System.out.println("            B: " + option2);
				System.out.println("            C: " + option3);
				System.out.println("            D: " + option4);
			}
			else {
				System.out.println("Question " + i + ": " + question);
			}
			i++;
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
	 * 
	 * @param emailList
	 * @return 2D array of Answer objects
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
						answerText = answerText.trim();		//removing white space from answer text
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
				} catch (NumberFormatException e) {
				} // this catches line spaces in emails
			}
		}
	}

	/**
	 * Calls tallyAnswers for the appropriate question, adds the resulting HashMap
	 * to an ArrayList of HashMaps Prints the results of each HashMap
	 * 
	 * @return
	 
	public ArrayList<HashMap<String, Integer>> tallySurvey() {
		for (QuestionStrategy question : questionList) {
			if (question.getAnswers().isEmpty()) {
				System.out.println("\nNo answers provided for question: " + question);
			} else {
				System.out.println("\nTally for question: " + question);
			}
			// hashmap of answers and their tally for all answers for a given question
			HashMap<String, Integer> answerTallyHashMap = question.tallyAnswers();
			// Adding one question's tally map to a list of all maps for all question in a
			// survey
			allAnswerTallys.add(answerTallyHashMap);
			// print one
			for (String key : answerTallyHashMap.keySet()) {
				int occurances = answerTallyHashMap.get(key);
				System.out.println("Response: '" + key + "' -- Count: " + occurances);
			}
		}
		return allAnswerTallys;
	}
   */

	/**
	 * Loops through the question numbers in the hashmap that keeps track of them,
	 * calling the printSeparatedReport method on each qid. This method generates a
	 * new report that displays each answer and the number of occurrences, for the
	 * Inputed question.
	 * 
	 * @param questions
	 */
	public void printReport() {
		for (int qid : this.questionNumberMap.keySet()) {
			System.out.print("\n\nQuestion " + qid + ":  ");
			System.out.println(questionNumberMap.get(qid).toString());
			try {
				answerData = database.printSeparatedReport(qid);
			} catch (SQLException e) { 
				e.printStackTrace();
				System.out.println("Error in printReport");
			}
			for (String answer : answerData.keySet()) {
				//string answer = answer text
				int count = answerData.get(answer);
				System.out.print("\nAnswer: " + answer + " ===== Tally: " + answerData.get(answer));
			}
		}
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
	
	/**
	 * Runs the main survey maker UI/O
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		System.out.println("-----------------------------------------------------------------");
		System.out.println("                          Survey Maker                           ");
		System.out.println("-----------------------------------------------------------------");

		String surveyName;
		do {
			System.out.println("Please enter survey name:");
			Scanner nameScanner = new Scanner(System.in);
			surveyName = nameScanner.nextLine();
			if (surveyName.equals("")) {
				System.out.println("No name entered, try again");
			}

		} while (surveyName.equals(""));

		Survey survey = new Survey(surveyName);

		Scanner sc = new Scanner(System.in);
		boolean done = false;


		while (!done) {
			System.out.println("What would you like to do?");
			System.out.println("1: Add a yes/no question");
			System.out.println("2: Add a multiple choice question");
			System.out.println("3: Add a quantity question");
			System.out.println("4: Finish and create survey");

			try {
				int questionType = sc.nextInt();

				switch (questionType) { 
				case 1:
					System.out.println("Please enter the yes/no question you would like to add.");
					Scanner ynScanner = new Scanner(System.in);
					String ynQuestion = ynScanner.nextLine();

					survey.addYesNoQuestion(ynQuestion);

					break;

				case 2:

					System.out.println("Please enter the multiple choice question you would like to add.");
					Scanner mcScanner1 = new Scanner(System.in);
					String mcQuestion = mcScanner1.nextLine();

					System.out.println("Please enter 4 multiple choice answer options");
					Scanner mcScanner2 = new Scanner(System.in);
					option1 = mcScanner2.nextLine();
					option2 = mcScanner2.nextLine();
					option3 = mcScanner2.nextLine();
					option4 = mcScanner2.nextLine();

					survey.addMultChoiceQuestion(mcQuestion);

					break;

				case 3:
					System.out.println("Please enter the quantity question you would like to add.");
					Scanner qScanner = new Scanner(System.in);
					String qQuestion = qScanner.nextLine();

					survey.addQuantityQuestion(qQuestion);

					break;

				case 4:

					if (survey.questionList.isEmpty()) {
						System.out.println("Survey has no questions. Please add a question.\n");
						break;
					} else {
						survey.showSurvey();
						done = true;
						break;
					}
				default:
					System.out.println("Please enter a valid option between 1 and 2\n");
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Please enter a valid option between 1 and 4\n");
			}
		}

		System.out.print("\nPress enter to tally results");
		Scanner in = new Scanner(System.in);
		in.nextLine();
		System.out.println("\nFetching Email data. . . .");
		survey.getSurveyEmailData();

		System.out.println("\nThere were " + emailList.size() + " responses to the survey!");

		survey.separateAnswers(survey.emailList, survey.questionList);
		survey.printReport();

	} 
	
}