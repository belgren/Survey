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
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
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
	
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	
	public String getOptionA() {
		return optionA;
	}
	
	public String getOptionB() {
		return optionB;
	}
	
	public String getOptionC() {
		return optionC;
	}
	
	public String getOptionD() {
		return optionD;
	}
	
	public String displayQuestion() {
		String mcText = questionText + "\nA: " + options.get(0) + "\nB: " + options.get(1) + "\nC: " + options.get(2) + "\nD: " + options.get(3);
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
	
	/**
	 * This method will tally the amount of each answer to a certain question
	 
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
	}*/

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
