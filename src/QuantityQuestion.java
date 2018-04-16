import java.util.ArrayList;
import java.util.HashMap;

/**
 * Concrete subclass of question defining the quantity question type.
 * Contains a tally answers method, and an ArrayList of answer objects
 * that will be filled in when the survey is conducted.
 */
public class QuantityQuestion implements QuestionStrategy {

	//public ArrayList<String> validAnswers;
	private String questionText;
	private ArrayList<Answer> answers;
	private HashMap<String, Integer> tally;
	public int questionNumber;
	private int questionType;

	/**
	 * Sets up valid answers for multiple choice questions
	 * @param questionText
	 */
	public QuantityQuestion(String questionText) {

		this.questionText = questionText;
		questionType = 3;
		answers = new ArrayList<Answer>();
		tally = new HashMap<String, Integer>();
		//validAnswers = new ArrayList<String>();
	} 

	public String displayQuestion() {
		return this.toString();
	}
	
	public void setOptions(ArrayList<String> options) {}
	
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	public int getQuestionType() {
		return questionType;
	}


	public String addAnswer(Answer answer) {
		answers.add(answer);
		String answerText = answer.toString().toLowerCase();
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
