import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class DisplayResults {

	private JFrame frame;
	private Survey currentSurvey;
	private JTextArea label;
	private JLabel Background;

	/**
	 * Launch the application.
	 */
	public void newDisplayResultsBuilder() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayResults window = new DisplayResults(currentSurvey);
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
	public DisplayResults(Survey survey) {
		currentSurvey = null;
		currentSurvey = survey;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		label = new JTextArea();
		for(QuestionStrategy q : currentSurvey.getQuestionList()) {
			label.append("");
		}
		label.setEditable(false);


		JScrollPane scrollPane = new JScrollPane(label);
		scrollPane.setBounds(23, 83, 448, 369);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(scrollPane);
		frame.setResizable(false);

		JLabel surveyTitle = new JLabel(currentSurvey.getSurveyName() + " Results");
		surveyTitle.setBounds(23, 6, 429, 48);
		frame.getContentPane().add(surveyTitle);
		surveyTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		surveyTitle.setHorizontalAlignment(SwingConstants.CENTER);

		Background = new JLabel("");
		Background.setIcon(new ImageIcon(SurveyDisplay.class.getResource("/resources/arcticsea.jpg")));
		Background.setBounds(0, 0, 500, 478);
		frame.getContentPane().add(Background);
	}

}
