import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class SurveyDisplay {

	private JFrame frame;
	private Survey survey;

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
		frame.setBounds(100, 100, 500, 300 + (survey.getQuestionList().size() * 90));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 500, 678);
		frame.getContentPane().add(scrollPane);
		
		JLabel surveyTitle = new JLabel(survey.getSurveyName());
		surveyTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		surveyTitle.setHorizontalAlignment(SwingConstants.CENTER);
		surveyTitle.setBounds(24, 18, 416, 90);
		frame.getContentPane().add(surveyTitle);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(429, 6, 15, 266);
		frame.getContentPane().add(scrollBar);
		
		int counter = 0;
		for(QuestionStrategy q : survey.getQuestionList()) {
			JTextPane textPane = new JTextPane();
			textPane.setBounds(6, 18 + (counter * 90), 414, 90);
			frame.getContentPane().add(textPane);
		}
	}
}
