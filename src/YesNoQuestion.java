/**
 * Concrete subclass of question defining the yes/no question type.
 * Contains a process answers method, and an ArrayList of answer objects
 * that will be filled in when the survey is conducted.
 */
import java.util.ArrayList;
public class YesNoQuestion implements Question{
	public ArrayList<String> validAnswers;
	public YesNoQuestion() {
		ArrayList<Answer> answers;
		validAnswers = new ArrayList<String>(); //("T","F","True","False");
		
	}
	
	/**
	 * This method will tally the answers for a specific question. This process will 
	 * be unique to the type of question.
	 */
	public void tallyAnswers() {
		 
	}
}
