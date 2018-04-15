import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class SurveyDisplay {

	private JFrame frame;
	private Survey survey;
	private JLabel label;

	public void newSurveyDisplay() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SurveyDisplay window = new SurveyDisplay(survey);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public SurveyDisplay(Survey survey) {
		this.survey = survey;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, (300 + (survey.getQuestionList().size() * 90)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel surveyTitle = new JLabel(survey.getSurveyName());
		surveyTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		surveyTitle.setHorizontalAlignment(SwingConstants.CENTER);
		surveyTitle.setBounds(24, 15, 416, 90);
		frame.getContentPane().add(surveyTitle);
		
		int counter = 0;
		for(QuestionStrategy q : survey.getQuestionList()) {
			label = new JLabel("Question " + q.getQuestionNumber() +" : " + q.toString());
			label.setBackground(Color.blue);
			label.setSize(414, 20);
			label.setLocation(6, 100);
			frame.getContentPane().add(label);
			counter++;
		}
		
		JScrollPane scrollPane = new JScrollPane(label);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 500, 678);
		frame.getContentPane().add(scrollPane);
//		frame.add(scrollPane);
		
		
	}
}
