import java.util.ArrayList;
import java.util.HashMap;

public class Survey {
	
	private Question question;
	private ArrayList<Question> questionList = new ArrayList<Question>();
	private static ArrayList<Email> emailList;
	
	public void addYesNoQuestion(String questionText) {
		question = new YesNoQuestion(questionText);
		questionList.add(question);
		emailList = new ArrayList<Email>();
	}
	
	/**
	 * Read through all emails for a given survey and split each line in each email
	 * into individual answers. Create answer objects for each of them, and put all
	 * of the answers into an array list corresponding to one survey taker's set of answers.
	 * Each of these arrays will be added to another array that will hold all responses and
	 * be returned by the method.
	 */
	public void parseEmails() {
		String protocol = "imap";
		String host = "imap.gmail.com";
		String port = "993";
		String userName = "cp274survey@gmail.com";
		String password = "DarrylBenJordan";
		EmailReader reader = new EmailReader();
		emailList = reader.downloadEmails(protocol, host, port, userName, password);
		
		//to be replaced by real email reading.
		Answer answer = new Answer("Yes");
		this.question.addAnswer(answer);
	} 
	
	public ArrayList<ArrayList<Answer>> separateAnswers(ArrayList<Email> emailList){
		ArrayList<ArrayList<Answer>> allAnswers = new ArrayList<ArrayList<Answer>>();
		
		for(Email email: emailList) {
			ArrayList<Answer> answersPerPerson = new ArrayList<Answer>(); 
			String surveyAnswers = email.getMessage();
			String[] answersPerEmail = surveyAnswers.split("\n");
			for(String singleAnswer : answersPerEmail) {
				Answer answer = new Answer(singleAnswer);
				answersPerPerson.add(answer);
			}
			allAnswers.add(answersPerPerson);
		}
		
		return allAnswers;
	}
	
	 
	public static void main(String args[]) {
		Survey survey = new Survey();
		
		survey.addYesNoQuestion("Yes or No?");
		survey.parseEmails();
		Question question = survey.questionList.get(0);
		
	} 
}
