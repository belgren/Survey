import java.util.ArrayList;
import java.util.HashMap;

/**
 * Concrete subclass of question defining the multiple choice question type.
 * Contains a an ArrayList of answer objects that will be filled in when the survey is conducted
 * and a method to display the question
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
	 * Constructor instantiates fields and sets up valid answers for multiple choice questions.  
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
	
	/**
	 * Checks if an answer to a question is valid
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
	 * Displays multiple choice question with all of its answer options 
	 */
	public String displayQuestion() {
		String mcText = questionText + "\n          "
				+ "A: " + options.get(0) + "\n          "
				+ "B: " + options.get(1) + "\n          "
				+ "C: " + options.get(2) + "\n          "
				+ "D: " + options.get(3);
		return mcText;
	}
	
	/**
	 * Adds answer to answers array and 
	 * converts the string to standard answer format
	 * @param answer
	 * @return answerText string of answer in upper case
	 */
	public String addAnswer(Answer answer) {
		answers.add(answer);
		String answerText = answer.toString().toUpperCase();
		return answerText;
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
