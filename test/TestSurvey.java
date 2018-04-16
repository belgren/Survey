import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestSurvey {
	
	private Survey testSurvey;
	private Email email1;
	private Email email2;
	private ArrayList<Email> emailList; 
	private ArrayList<QuestionStrategy> questionList;
	private QuestionStrategy question1;
	
	private Answer answer1;

	
	@Before
	public void setUp() throws Exception {
		testSurvey = Survey.getInstance("tester");  
		email1 = new Email( "subject1","fromAddress1", "sentDate1", "1 yes");
		email2 = new Email( "subject2","fromAddress2", "sentDate2", "2 no");
		
		emailList = new ArrayList<Email>();
		emailList.add(email1);
		emailList.add(email2);
		testSurvey.addYesNoQuestion("question 1");
		questionList = testSurvey.getQuestionList();
	}
	
	
	/**
	 * Test separate answers method. This test the functionality of majority of the project.
	 * By calling the separate answers method, the answer object is added to the list of answers
	 * which is an attribute of the question object. This test confirms this function by retreiving 
	 * the answer text from that attribute.
	 */
	@Test
	public void testSeparateAnswers() {
		testSurvey.separateAnswers(this.emailList, this.questionList);
		QuestionStrategy question = this.questionList.get(0);
		Answer answer = question.getAnswers().get(0);
		assertEquals(answer.toString(), "yes");
	}
}