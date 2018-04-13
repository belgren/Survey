
/**
 * Concrete subclass of question defining the yes/no question type.
 * Contains a process answers method, and an ArrayList of answer objects
 * that will be filled in when the survey is conducted.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class YesNoQuestion implements Question {

	public ArrayList<String> validAnswers;
	private String questionText;
	private ArrayList<Answer> answers;
	private HashMap<String, Integer> tally;
	public int questionNumber;

	public YesNoQuestion(String questionText) {

		this.questionText = questionText;
		validAnswers = new ArrayList<String>();
		validAnswers.add("y");
		validAnswers.add("n");
		answers = new ArrayList<Answer>();
		tally = new HashMap<String, Integer>();

	} 

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	public boolean isValidAnswer(String trialAnswer) {
		for (String ans : validAnswers) {
			if (ans.equalsIgnoreCase(trialAnswer)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method will tally the answers for a specific question. This process will
	 * be unique to the type of question.
	 */
	public HashMap<String, Integer> tallyAnswers() {
		for (Answer answer : answers) {
			String answerText = answer.toString();
			if (isValidAnswer(answerText)) {

				if (!tally.keySet().contains(answerText)) {
					tally.put(answerText, 1);
				} else {
					tally.put(answerText, tally.get(answerText) + 1);
				}
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
