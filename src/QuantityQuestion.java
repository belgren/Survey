import java.util.ArrayList;
import java.util.HashMap;

/**
 * Concrete subclass of question defining the quantity question type.
 * Contains an ArrayList of answer objects that will be filled in when the survey is conducted
 * and a method to display the question
 */
public class QuantityQuestion implements QuestionStrategy {

	//public ArrayList<String> validAnswers;
	private String questionText;
	private ArrayList<Answer> answers;
	private HashMap<String, Integer> tally;
	public int questionNumber;
	private int questionType;

	/**
	 * Constructor instantiates fields for quantity questions.  
	 * @param questionText
	 */
	public QuantityQuestion(String questionText) {

		this.questionText = questionText;
		questionType = 3;
		answers = new ArrayList<Answer>();
		tally = new HashMap<String, Integer>();
		//validAnswers = new ArrayList<String>();
	} 

	/**
	 * Displays quantity question
	 */
	public String displayQuestion() {
		return this.toString();
	}
	
	/**
	 * Adds answer object to this questions list of answers
	 * Converts the string to standard answer format
	 * and returns the string
	 * @param answer
	 * @return capitalText
	 */
	public String addAnswer(Answer answer) {
		answers.add(answer);
		String answerText = answer.toString().toLowerCase();
		String capitalText = answerText.substring(0, 1).toUpperCase() + answerText.substring(1);
		return capitalText;
	}
	
	public boolean isValidAnswer(String trialAnswer) {
		return true;
	}
	
	public void setOptions(ArrayList<String> options) {}
	
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	public int getQuestionType() {
		return questionType;
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
