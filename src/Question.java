import java.util.ArrayList;
import java.util.HashMap;

/**
 * The interface for Question objects.  This
 * @author belgren
 *
 */
public interface Question {
	
	
	public HashMap<Answer, Integer> tallyAnswers();

	public String getQuestionText();
	
	public void addAnswer(Answer answer);
	
	public ArrayList<Answer> getAnswers();
	
}
