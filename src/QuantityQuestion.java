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

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	public int getQuestionType() {
		return questionType;
	}
	
	/*
	public boolean isValidAnswer(String trialAnswer) {
		for (String ans : validAnswers) {
			if (ans.equalsIgnoreCase(trialAnswer)) {
				return true;
			}
		}
		return false;
	}*/

	/**
	 * This method will tally the amount of each answer to a certain question
	 */
	public HashMap<String, Integer> tallyAnswers() {

		for (Answer answer : answers) {
			String answerText = answer.toString().toLowerCase();
			String capitalText = answerText.substring(0, 1).toUpperCase() + answerText.substring(1);
			if (tally.keySet().contains(capitalText)) {
				tally.put(capitalText, tally.get(capitalText) + 1);
			} else {
				tally.put(capitalText, 1);
			}
		}
		return tally;
	}

	public void addAnswer(Answer answer) {
		answers.add(answer);
	}

	public ArrayList<Answer> getAnswers() {
		return this.answers;
	}


	public String toString() {
		return this.questionText;
	}
}
