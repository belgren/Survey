import java.util.ArrayList;
import java.util.HashMap;

/**
 * Concrete subclass of question defining the multiple choice question type.
 * Contains a tally answers method, and an ArrayList of answer objects
 * that will be filled in when the survey is conducted.
 */
public class MultipleChoiceQuestion implements QuestionStrategy {

	public ArrayList<String> validAnswers;
	private String questionText;
	private ArrayList<Answer> answers;
	private HashMap<String, Integer> tally;
	public int questionNumber;
	private int questionType;
	private ArrayList<String> options;
	

	/**
	 * Sets up valid answers for multiple choice questions
	 * @param questionText
	 */
	public MultipleChoiceQuestion(String questionText) {

		this.questionText = questionText;
		questionType = 2;
		options = new ArrayList<String>();
		answers = new ArrayList<Answer>();
		tally = new HashMap<String, Integer>();
		validAnswers = new ArrayList<String>();
		validAnswers.add("A");
		validAnswers.add("B");
		validAnswers.add("C");
		validAnswers.add("D");
	} 

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
	
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	public int getQuestionType() {
		return questionType;
	}
	
	public String displayQuestion() {
		String mcText = questionText + "\n\tA: " + options.get(0) + "\n\tB: " + options.get(1) + "\n\tC: " + options.get(2) + "\n\tD: " + options.get(3);
		return mcText;
	}
	
	public boolean isValidAnswer(String trialAnswer) {
		for (String ans : validAnswers) {
			if (ans.equalsIgnoreCase(trialAnswer)) {
				return true;
			}
		}
		return false;
	}

	public String addAnswer(Answer answer) {
		answers.add(answer);
		return answer.toString();
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
