/**
 * Concrete subclass of question defining the yes/no question type.
 * Contains a process answers method, and an ArrayList of answer objects
 * that will be filled in when the survey is conducted.
 */
import java.util.ArrayList;
public class YesNoQuestion implements Question{
	
	
	public ArrayList<String> validAnswers;
	private String questionText;
	private ArrayList<Answer> answers;

	
	public YesNoQuestion(String questionText) {
		this.questionText = questionText;
		validAnswers = new ArrayList<String>(); //("T","F","True","False");
		answers = new ArrayList<Answer>();
		
	}
	
	/**
	 * This method will tally the answers for a specific question. This process will 
	 * be unique to the type of question.
	 */
	public void tallyAnswers() {
		 
	}
	
	public String getQuestionText() {
		return this.questionText;
	}
	
	public void addAnswer(Answer answer) {
		answers.add(answer);
	} 
	
	public ArrayList<Answer> getAnswers(){
		return this.answers;
	}
	 
}
