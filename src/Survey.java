import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;

/**
 * Survey class, which creates the survey.  Also has a main the does UI/O.
 * @author Jordan
 *
 */
public class Survey {
	
	private QuestionStrategy question;
	private ArrayList<QuestionStrategy> questionList = new ArrayList<QuestionStrategy>();
	private static ArrayList<Email> emailList;
	private int counter;
	private ArrayList<HashMap<String, Integer>> allAnswerTallys;
	private String surveyName;
	
	/**
	 * Constructor for survey.  Sets up survey name, initializes question counter to 0, initializes allAnswerTallys
	 * @param name
	 */
	public Survey(String name) {
		surveyName = name;
		counter = 0;
		allAnswerTallys = new ArrayList<HashMap<String, Integer>>();
	}
	
	/**
	 * creates a question object with the given question text.
	 * Adds the question to the survey's attribute, questionList
	 * @param questionText
	 */
	public void addYesNoQuestion(String questionText) {
		question = new YesNoQuestion(questionText);
		counter++;
		question.setQuestionNumber(counter);
		questionList.add(question);
		emailList = new ArrayList<Email>();
		
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
			System.out.println("Question " + i + ": " + question);
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
	 * Takes in email list and question list and assigns each email's answers to the correct question 
	 * @param emailList
	 * @param questionList
	 */
	public void separateAnswers(ArrayList<Email> emailList, ArrayList<QuestionStrategy> questionList){
		int i = 0;
		for (QuestionStrategy question : questionList) {
			for(Email email: emailList) {
				
				String surveyAnswers = email.getMessage();
				String[] answersPerEmail = surveyAnswers.split("\n");
				
				if (answersPerEmail.length > i) {
					Answer answer = new Answer(answersPerEmail[i]);
					question.addAnswer(answer);
				}
			}
			i++;
		} 
	}
	 
	/**
	 * Calls tallyAnswers for the appropriate question, adds the resulting HashMap to an ArrayList of HashMaps
	 * Prints the results of each HashMap
	 * @return
	 */
	public ArrayList<HashMap<String, Integer>> tallySurvey() {
		for (QuestionStrategy question : this.questionList) {
			if (question.getAnswers().isEmpty()) {
				System.out.println("\nNo answers provided for question: " + question);
			}
			else {
				System.out.println("\nTally for question: " + question);
			}
			//hashmap of answers and their tally for all answers for a given question
			HashMap<String, Integer> answerTallyHashMap = question.tallyAnswers();
			//Adding one question's tally map to a list of all maps for all question in a survey
			allAnswerTallys.add(answerTallyHashMap); 
			//print one 
			for (String key : answerTallyHashMap.keySet()) {
				
				int occurances = answerTallyHashMap.get(key);
		
				System.out.println("Response: '" + key + "' -- Count: " + occurances);
				
			}
		}
		return allAnswerTallys;
	}
	
	public ArrayList<QuestionStrategy> getQuestionList(){
		return questionList;
	}
	
	public void setQuestionList(ArrayList<QuestionStrategy> questionList) {
		this.questionList = questionList;
	}
	 
	/**
	 * Runs the main survey maker UI/O
	 * @param args
	 */
	public static void main(String args[]) {
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("                          Survey Maker                           ");
		System.out.println("-----------------------------------------------------------------");

		System.out.println("Please enter survey name:");
		Scanner nameScanner = new Scanner(System.in);
		String surveyName = nameScanner.nextLine();
		Survey survey = new Survey(surveyName);
		
		Scanner sc = new Scanner(System.in);
		boolean done = false;
		
		while(!done) {
			System.out.println("What would you like to do?");
			System.out.println("1: Add a yes/no question");
			System.out.println("2: Finish and create survey");

			try {
				int questionType = sc.nextInt();

				switch(questionType) {
				case 1:
					System.out.println("Please enter the yes/no question you would like to add.");
					Scanner s = new Scanner(System.in);
					String ynQuestion = s.nextLine();

					survey.addYesNoQuestion(ynQuestion);

					break;

				case 2:
					if(survey.questionList.isEmpty()) {
						System.out.println("Survey has no questions. Please add a question.\n");
						break;
					}
					else {
						survey.showSurvey();
						done = true;
						break;
					}
				default:
					System.out.println("Please enter a valid option between 1 and 2\n");					
				}
			} catch(InputMismatchException ex){
				sc.nextLine();
				System.out.println("Please enter a valid option between 1 and 2\n");
			}
		}
		 
		System.out.print("\nPress enter to tally results");
		Scanner in = new Scanner(System.in);
		in.nextLine(); 
		System.out.println("\nFetching Email data. . . .");
		survey.getSurveyEmailData();
		
		System.out.println("\nThere were " + emailList.size() + " responses to the survey!");
		
		survey.separateAnswers(survey.emailList, survey.questionList);
		survey.tallySurvey();			
			
	} 
}
