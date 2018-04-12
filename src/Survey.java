import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Survey {
	
	private Question question;
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private static ArrayList<Email> emailList;
	private int counter;
	private ArrayList<HashMap<String, Integer>> answerTallys;
	private String surveyName;
	
	public Survey(String name) {
		surveyName = name;
		counter = 0;
		answerTallys = new ArrayList<HashMap<String, Integer>>();
	}
	
	/**
	 * creates a question object with the given question txt.
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
	 * Print all questions in survey;
	 */
	public void showSurvey() {
		int i = 1;
		System.out.println("\nWelcome to " + surveyName + "!");
		System.out.println("\nInstructions:");
		System.out.println("Please email your survey answers to cp274survey@gmail.com");
		System.out.println("Email subject must be \"" + surveyName + "\"");
		System.out.println("Separate question answers by line\n");
		
		for (Question question : questionList) {
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
	 * takes in email list and separates into 2D array will each column corresponding
	 * to one survey taker, and each row being answers to one question.
	 * 
	 * @param emailList
	 * @return 2D array of Answer objects
	 */
	public void separateAnswers(ArrayList<Email> emailList, ArrayList<Question> questionList){
		//ArrayList<ArrayList<Answer>> allAnswers = new ArrayList<ArrayList<Answer>>();
		int i = 0;
		for (Question question : questionList) {
			for(Email email: emailList) {
				//ArrayList<Answer> answersPerPerson = new ArrayList<Answer>(); 
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
	 * for each row in the 2D array it takes each individual answer and assigns it to the question
	 * that it was answering.  Question objects have an attribute answerslist.  
	 * @param allAnswers
	
	public void assignAnswersToQuestion(ArrayList<ArrayList<Answer>> allAnswers) {
		int i = 0;
		for (Question question : this.questionList) { //loop through all questions in this survey
			for (ArrayList<Answer> onePersonsAnswers :allAnswers) {
				Answer answer = onePersonsAnswers.get(i);
				question.addAnswer(answer);
			}
			i++;
		}
	} */
	
	public ArrayList<HashMap<String, Integer>> tallySurvey() {
		for (Question question : this.questionList) {
			if (question.getAnswers().isEmpty()) {
				System.out.println("No answers provided for question: " + question);
			}
			else {
				System.out.println("Tally for question: " + question);
			}
			//hashmap of answers and their tally for all answers for a given question
			HashMap<String, Integer> answerTally = question.tallyAnswers();
			//Adding one question's tally map to a list of all maps for all question in a survey
			answerTallys.add(answerTally); 
			//print one 
			for (String key : answerTally.keySet()) {
				
				int occurances = answerTally.get(key);
		
				System.out.println("Response: '" + key + "' ---  occurrances: " + occurances);
				
			}
		}
		return answerTallys;
	}
	
	public ArrayList<Question> getQuestionList(){
		return questionList;
	}
	 
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
					System.out.println("Please Enter the Yes/No question you would like to add.");
					Scanner s = new Scanner(System.in);
					String ynQuestion = s.nextLine();

					survey.addYesNoQuestion(ynQuestion);

					break;

				case 2:
					//while(questionList.isEmpty()) {
					//	System.out.println("Survey has no questions. Please add a question.");
					//}
					survey.showSurvey();
					done = true;
					break;
				default:
					System.out.println("Please enter a valid option between 1 and 2\n");					
				}
			} catch(InputMismatchException ex){
				sc.nextLine();
				System.out.println("Please enter a valid option between 1 and 2\n");
			}
		}
		 
		System.out.println("\nPress enter to tally results");
		Scanner in = new Scanner(System.in);
		in.nextLine(); 
		
		survey.getSurveyEmailData();
		
		survey.separateAnswers(survey.emailList, survey.questionList);
		survey.tallySurvey();			
			
	} 
}
