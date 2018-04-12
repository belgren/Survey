/**
 * Class to define answer objects. Answers can be created with a string containing
 * the text of the answer.  Class contains getters and setters.
 * @author belgren
 *
 */
public class Answer {

	private String answerText;
	
	public Answer(String answerText) {
		this.answerText = answerText;
	}
	
	public String getAnswerText() {
		return answerText;
	}
}
