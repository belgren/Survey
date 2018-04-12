import java.util.ArrayList;

/**
 * The interface for Question objects.  This
 * @author belgren
 *
 */
public interface Question {
	
	
	public void tallyAnswers();

	public String getQuestionText();
	
	public void addAnswer(Answer answer);
	
	public ArrayList<Answer> getAnswers();
	
}
