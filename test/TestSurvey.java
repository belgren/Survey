import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestSurvey {
	
	Survey testSurvey;
	Email email1;
	Email email2;
	ArrayList<Email> emailList; 
	ArrayList<QuestionStrategy> questionList;
	QuestionStrategy question1;
	QuestionStrategy question2;
	Answer answer1;
	Answer answer2;
	Answer answer3;
	Answer answer4;
	Answer answer5;
	
	
	
	@Before
	public void setUp() throws Exception {
		testSurvey = Survey.getInstance("tester");
		email1 = new Email( "subject1","fromAddress1", "sentDate1", "messagecontent1");
		email2 = new Email( "subject2","fromAddress2", "sentDate2", "messagecontent2");
		emailList = new ArrayList<Email>();
		emailList.add(email1);
		emailList.add(email2);
		questionList = new ArrayList<QuestionStrategy>();
		question1 = new YesNoQuestion("Are you happy?");
		question2 = new YesNoQuestion("Are you sad?");
		questionList.add(question1);
		questionList.add(question2);
		answer1 = new Answer("yes");
		answer2 = new Answer("yes");
		answer3 = new Answer("no");
		answer4 = new Answer("yes");
		answer5 = new Answer("no");
	}
	
	//Test separate answers method
	@Test
	public void testSeparateAnswers() {
		testSurvey.separateAnswers(emailList, questionList);
		assertEquals("messagecontent1", question1.getAnswers().get(0).toString());
	}
	
	//test tallySurvey method
	@Test
	public void testTallySurvey() {
		question1.addAnswer(answer1);
		question1.addAnswer(answer2);
		question1.addAnswer(answer3);
		question1.addAnswer(answer4);
		question1.addAnswer(answer5);
		testSurvey.setQuestionList(questionList);
		ArrayList<HashMap<String, Integer>> finalTally = new ArrayList<HashMap<String, Integer>>();
		finalTally = testSurvey.tallySurvey();
		HashMap<String, Integer> tallyAnswers = new HashMap<String, Integer>();
		tallyAnswers.put("Yes", 3);
		tallyAnswers.put("No", 2);
		assertEquals(tallyAnswers.get("yes"), finalTally.get(1).get("yes"));
		assertEquals(tallyAnswers.get("no"), finalTally.get(1).get("no"));
		
	}
}