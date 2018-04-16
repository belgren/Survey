import java.util.ArrayList;
import java.util.HashMap;

/**
 * Concrete subclass of question defining the yes/no question type.
 * Contains an ArrayList of answer objects that will be filled in when the survey is conducted
 * and a method to display the question
 */
public class YesNoQuestion implements QuestionStrategy {

	public ArrayList<String> validAnswers;
	private String questionText;
	private ArrayList<Answer> answers;
	private HashMap<String, Integer> tally;
	public int questionNumber;
	private int questionType;

	/**
	 * Constructor instantiates fields and sets up valid answers for yes/no questions.  
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
	
	/**
	 * Displays the yes/no question
	 */
	public String displayQuestion() {
		return this.toString();
	}
	
	/**
	 * Formatting method to standardize all forms of yes/no to be 'Yes' or 'No'
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
	
	/**
	 * Checks if an answer to a yes/no question is valid
	 * @param trialAnswer
	 */
	public boolean isValidAnswer(String trialAnswer) {
		for (String ans : validAnswers) {
			if (ans.equalsIgnoreCase(trialAnswer)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds answer object to this questions list of answers
	 * Converts the string to standard answer format
	 * and returns the string
	 * @param answer
	 * @return answerText
	 */
	public String addAnswer(Answer answer) {
		answers.add(answer);
		String answerText = answer.toString();
		answerText = formatAnswer(answerText);
		return answerText;
	}
	
	public void setQuestionNumber(int questionNumber) {  
		this.questionNumber = questionNumber;
	}
	
	public int getQuestionType() {
		return questionType;
	}
	
	public void setOptions(ArrayList<String> options) {}

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