/**
 * Class to define answer objects. Answers can be created with a string containing
 * the text of the answer.  Class contains getters and setters.
 * @author belgren
 *
 */
public class Answer {

	private String answerText;
	private int questionNumber;
	
	public Answer(String answerText) {
		this.answerText = answerText.replaceAll("\\r|\\n", "").toLowerCase();
	}
	
	public void setQuestionNumber(int number) {
		this.questionNumber = number;
	}
	
	public String toString() {
		return this.answerText;
	}
	
	
}
