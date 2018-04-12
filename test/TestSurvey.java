import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
		System.out.println(questionList.size());
		testSurvey.separateAnswers(emailList, questionList);
		assertEquals("messageContent1", question1.getAnswers().get(0).toString());
	}

}
