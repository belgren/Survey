import java.util.ArrayList;
import java.util.HashMap;

/**
 * Concrete subclass of question defining the yes/no question type.
 * Contains a tally answers method, and an ArrayList of answer objects
 * that will be filled in when the survey is conducted.
 */
public class YesNoQuestion implements QuestionStrategy {

	public ArrayList<String> validAnswers;
	private String questionText;
	private ArrayList<Answer> answers;
	private HashMap<String, Integer> tally;
	public int questionNumber;
	private int questionType;

	/**
	 * Sets up valid answers for yes/no questions
	 * @param questionText
	 */
	public YesNoQuestion(String questionText) {
		
		this.questionText = questionText;
		questionType = 1;
		validAnswers = new ArrayList<String>();
		validAnswers.add("y");
		validAnswers.add("n");
		validAnswers.add("yes");  
		validAnswers.add("no");
		answers = new ArrayList<Answer>();
 
	}   
	
	public String displayQuestion() {
		return this.toString();
	}
	
	/**
	 * formatting method to standardize all forms of yes/no to be 'Yes' or 'No'
	 * @param questionText
	 * @return
	 */
	public String formatAnswer(String answerText) {
		if (answerText.equalsIgnoreCase("y") | answerText.equalsIgnoreCase("yes")) {
			return "Yes";    
		}
		else if (answerText.equalsIgnoreCase("n") | answerText.equalsIgnoreCase("no") ) {
			return "No";
		}
		return answerText;
	}

	public void setQuestionNumber(int questionNumber) {  
		this.questionNumber = questionNumber;
	}
	
	public int getQuestionType() {
		return questionType;
	}

	public boolean isValidAnswer(String trialAnswer) {
		for (String ans : validAnswers) {
			if (ans.equalsIgnoreCase(trialAnswer)) {
				return true;
			}
		}
		return false;
	}
	
	public void setOptions(ArrayList<String> options) {}


	/**
	 * adds answer object to this questions list of answers
	 * converts the string to standardize answer format.
	 * returns new string
	 */
	public String addAnswer(Answer answer) {
		answers.add(answer);
		String answerText = answer.toString();
		answerText = formatAnswer(answerText);
		return answerText;
	}

	public ArrayList<Answer> getAnswers() {
		return this.answers;
	}

	public String toString() {
		return this.questionText;
	}
	
	public int getQuestionNumber() {
		return questionNumber;
	}
}