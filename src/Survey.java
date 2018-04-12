
public class Survey {
	
	private Question question;
	
	
	public void addYesNoQuestion(String questionText) {
		question = new YesNoQuestion();
	}
	
	/**
	 * Read through all emails for a given survey and split each line in each email
	 * into individual answers. Create answer objects for each of them, and put all
	 * of the answers into an array list corresponding to one survey taker's set of answers.
	 * Each of these arrays will be added to another array that will hold all responses and
	 * be returned by the method.
	 */
	public void parseEmails() {
		
	}
	
	public void runSurvey() {
		Survey survey = new Survey();
		survey.addYesNoQuestion("Yes or No?");
	}
	
	public static void main(String args[]) {
		
	}
}
