import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class TestSurvey {

	@Test
	public void testSeparateAnswers() {
		Survey testSurvey = new Survey("tester");
		Email email1 = new Email( "subject1","fromAddress1", "sentDate1", "messageContent1");
		Email email2 = new Email( "subject2","fromAddress2", "sentDate2", "messageContent2");
		ArrayList<Email> emailList = new ArrayList<Email>();
		emailList.add(email1);
		emailList.add(email2);
		ArrayList<Question> questionList = new ArrayList<Question>();
		Question question1 = new YesNoQuestion("Are you happy?");
		Question question2 = new YesNoQuestion("Are you sad?");
		questionList.add(question1);
		questionList.add(question2);
		testSurvey.separateAnswers(emailList, questionList);
		assertEquals("messageContent1", question1.getAnswers().get(0).toString());
	}
	
	@Test
	public void tallySurvey() {
		Survey testSurvey = new Survey("tester");
		ArrayList<Question> questionList = new ArrayList<Question>();
		Question question1 = new YesNoQuestion("Are you happy?");
		questionList.add(question1);
		Answer answer1 = new Answer("yes");
		Answer answer2 = new Answer("yes");
		Answer answer3 = new Answer("no");
		Answer answer4 = new Answer("yes");
		Answer answer5 = new Answer("no");
		question1.addAnswer(answer1);
		question1.addAnswer(answer2);
		question1.addAnswer(answer3);
		question1.addAnswer(answer4);
		question1.addAnswer(answer5);
		ArrayList<HashMap<String, Integer>> finalTally = new ArrayList<HashMap<String, Integer>>();
		finalTally = testSurvey.tallySurvey();
		HashMap<String, Integer> tallyAnswers = new HashMap<String, Integer>();
		tallyAnswers.put("yes", 3);
		tallyAnswers.put("no", 2);
		assertEquals(tallyAnswers.get("yes"), finalTally.get(0).get("yes"));
		assertEquals(tallyAnswers.get("no"), finalTally.get(0).get("no"));
		
	}
}
