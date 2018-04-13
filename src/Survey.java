import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;

public class Survey {
	
	private QuestionStrategy question;
	private ArrayList<QuestionStrategy> questionList = new ArrayList<QuestionStrategy>();
	private ArrayList<Email> emailList;
	private int counter;
	private ArrayList<HashMap<String, Integer>> allAnswerTallys;
	private String surveyName;
	
	public Survey(String name) {
		surveyName = name;
		counter = 0;
		allAnswerTallys = new ArrayList<HashMap<String, Integer>>();
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
		System.out.println("Please enter \"y\" or \"n\" and separate question answers by line\n");
		
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
	 * takes in email list and separates into 2D array will each column corresponding
	 * to one survey taker, and each row being answers to one question.
	 * 
	 * @param emailList
	 * @return 2D array of Answer objects
	 */
	public void separateAnswers(ArrayList<Email> emailList, ArrayList<QuestionStrategy> questionList){
		//ArrayList<ArrayList<Answer>> allAnswers = new ArrayList<ArrayList<Answer>>();
		int i = 0;
		for (QuestionStrategy question : questionList) {
			//System.out.println("Question !!!!!" + question);
			for(Email email: emailList) {
				
				//ArrayList<Answer> answersPerPerson = new ArrayList<Answer>(); 
				String surveyAnswers = email.getMessage();
				String[] answersPerEmail = surveyAnswers.split("\n");
				if (answersPerEmail.length > i) {
					Answer answer = new Answer(answersPerEmail[i]);
					question.addAnswer(answer);
					//System.out.println("Answer !!!!!" + answer);

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
		
		survey.separateAnswers(survey.emailList, survey.questionList);
		survey.tallySurvey();			
			
	} 
}
