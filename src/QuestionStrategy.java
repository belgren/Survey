import java.util.ArrayList;
import java.util.HashMap;

/**
 * The interface for Question objects.  Implements strategy pattern
 *
 */
public interface QuestionStrategy {

	public ArrayList<String> getOptions();

	public String displayQuestion();

	public String addAnswer(Answer answer);

	public ArrayList<Answer> getAnswers();
	
	public String toString();

	public void setQuestionNumber(int questionNumber);
	
	public void setOptions(ArrayList<String> options);
	
	public int getQuestionNumber();
	
	public boolean isValidAnswer(String trialAnswer);

}