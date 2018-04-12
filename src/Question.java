import java.util.ArrayList;
import java.util.HashMap;

/**
 * The interface for Question objects.  This
 * @author belgren
 *
 */
public interface Question {

	public HashMap<String, Integer> tallyAnswers();

	public String toString();
	
	public void addAnswer(Answer answer);
	
	public ArrayList<Answer> getAnswers();
	
	public void setQuestionNumber(int questionNumber);
}
