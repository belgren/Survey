import java.util.ArrayList;

/**
 * Concrete subclass of question defining the short answer question type.
 * Contains an ArrayList of answer objects that will be filled in when the survey is conducted
 * and a method to display the question
 */
public class ShortAnswerQuestion implements QuestionStrategy {

	private String questionText;
	private ArrayList<Answer> answers;
	private int questionNumber;

	/**
	 * Constructor instantiates fields for short answer questions.  
	 * @param questionText
	 */
	public ShortAnswerQuestion(String questionText) {

		this.questionText = questionText;
		answers = new ArrayList<Answer>();
	} 
	
	public ArrayList<String> getOptions(){
		return null;
	}


	/**
	 * Displays short answer question
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

	public ArrayList<Answer> getAnswers() {
		return this.answers;
	}
	
	public String toString() {
		return questionText;
	}
	
	public int getQuestionNumber() {
		return questionNumber;
	}
}
