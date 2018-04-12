/**
 * Concrete subclass of question defining the yes/no question type.
 * Contains a process answers method, and an ArrayList of answer objects
 * that will be filled in when the survey is conducted.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class YesNoQuestion implements Question{
	
	
	public ArrayList<String> validAnswers;
	private String questionText;
	private ArrayList<Answer> answers;
	private HashMap<Answer, Integer> tally;

	
	public YesNoQuestion(String questionText) {
		this.questionText = questionText;
		validAnswers = new ArrayList<String>(Arrays.asList("Y","N","Yes","No")); 
		answers = new ArrayList<Answer>();
		tally = new HashMap<Answer, Integer>();
		
	}
	
	/**
	 * This method will tally the answers for a specific question. This process will 
	 * be unique to the type of question.
	 */
	public HashMap<Answer, Integer> tallyAnswers() {
		 for (Answer answer : answers) {
			 String answerText = answer.getAnswerText();
			 
			 if (validAnswers.contains(answerText)) {
				 //Insert a new answer with one count into tally map if legal answer has not yet 
				 //been counted. If it exists, then increment the value;
				 tally.merge(answer, 1, Integer::sum);
			 }
			 //unnecessary, just here for testing
			 else {
				 System.out.print(answerText + " is not a valid answer");
			 }
		 }
		 return tally;
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
