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
	

	/**
	 * Sets up valid answers for multiple choice questions
	 * @param questionText
	 */
	public MultipleChoiceQuestion(String questionText) {

		this.questionText = questionText;
		questionType = 2;
		answers = new ArrayList<Answer>();
		tally = new HashMap<String, Integer>();
		validAnswers = new ArrayList<String>();
		validAnswers.add("A");
		validAnswers.add("B");
		validAnswers.add("C");
		validAnswers.add("D");
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
	
	/**
	 * This method will tally the amount of each answer to a certain question
	 */
	public HashMap<String, Integer> tallyAnswers() {
		
		tally.put("A", 0);
		tally.put("B", 0);
		tally.put("C", 0);
		tally.put("D", 0);
		
		for (Answer answer : answers) {
			String answerText = answer.toString().toUpperCase();
			if (isValidAnswer(answerText)) {
				if (tally.keySet().contains(answerText)) {
					tally.put(answerText, tally.get(answerText) + 1);
				} else {
					tally.put(answerText, 1);
				}
			}
		}
		return tally;
	}

	public String addAnswer(Answer answer) {
		answers.add(answer);
		
	}

	public ArrayList<Answer> getAnswers() {
		return this.answers;
	}


	public String toString() {
		return this.questionText;
	}
}
