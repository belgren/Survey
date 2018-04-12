import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Survey {
	
	private Question question;
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private static ArrayList<Email> emailList;
	private int counter;
	private ArrayList<HashMap<String, Integer>> answerTallys;
	
	public Survey() {
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
		for (Question question : questionList) {
			System.out.println(question);
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
		EmailReader reader = new EmailReader();
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
				Answer answer = new Answer(answersPerEmail[i]);
				question.addAnswer(answer);
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
			//hashmap of answers and their tally for all answers for a given question
			HashMap<String, Integer> answerTally = question.tallyAnswers();
			//Adding one question's tally map to a list of all maps for all question in a survey
			answerTallys.add(answerTally); 
			//print one 
			for (Map.Entry<String, Integer> entry  : answerTally.entrySet()) {
				String key = entry.getKey();
				Integer occurances = entry.getValue();
				
				//int occurrances = answerTally.get(answerText);
				System.out.print(occurances + " : " + key);
				//System.out.print(" : " + answerTally.get(answerText));
			}
		}
		return answerTallys;
	}
	
	 
	public static void main(String args[]) {
		Survey survey = new Survey();
		survey.addYesNoQuestion("Yes or no?");
		survey.showSurvey();
		
		survey.getSurveyEmailData();
		
		survey.separateAnswers(survey.emailList, survey.questionList);
		survey.tallySurvey();		
	} 
}
