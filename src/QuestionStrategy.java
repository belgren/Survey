import java.util.ArrayList;
import java.util.HashMap;

/**
 * The interface for Question objects.  Implements strategy pattern
 * @author belgren
 *
 */
public interface QuestionStrategy {

	public String displayQuestion();

	public String toString();

	public String addAnswer(Answer answer);

	public ArrayList<Answer> getAnswers();

	public void setQuestionNumber(int questionNumber);

	public int getQuestionType();
	
	public void setOptions(ArrayList<String> options);
	
	public int getQuestionNumber();
	
	public boolean isValidAnswer(String trialAnswer);

}