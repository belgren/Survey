import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SurveyDisplay {

	private JFrame frame;
	private Survey currentSurvey;
	private JTextArea label;
	private JLabel Background;
	private JButton resultButton;
	private JTextArea instructions;

	public void newSurveyDisplay() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SurveyDisplay window = new SurveyDisplay(currentSurvey);
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
		this.currentSurvey = survey;
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
			label.append("Question " + q.getQuestionNumber() +" : " + q.displayQuestion() + "\n\n");
		}

		instructions = new JTextArea();
		instructions.setText("      Instructions:\n      "
				+ "Please email your survey answers to cp274survey@gmail.com\n      "
				+ "Email subject must be \"" + currentSurvey.getSurveyName() + "\".\n      "
				+ "Answers should be in the format:\n      "
				+ "1 Answer\n      2 Answer\n      etc.");
		instructions.setWrapStyleWord(true);
		instructions.setToolTipText("");
		instructions.setBackground(new Color(255, 255, 255));
		instructions.setBounds(23, 71, 448, 118);
		instructions.setEditable(false);
		frame.getContentPane().add(instructions);


		JScrollPane scrollPane = new JScrollPane(label);
		scrollPane.setBounds(23, 205, 448, 219);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(scrollPane);
		frame.setResizable(false);

		JLabel surveyTitle = new JLabel(currentSurvey.getSurveyName());
		surveyTitle.setBounds(23, 6, 429, 48);
		frame.getContentPane().add(surveyTitle);
		surveyTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		surveyTitle.setHorizontalAlignment(SwingConstants.CENTER);

		resultButton = new JButton("Get Survey Results");
		resultButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayResults newResults = new DisplayResults(currentSurvey);
				newResults.newDisplayResultsBuilder();
				System.out.println("\nFetching Email data. . . .");
				currentSurvey.getSurveyEmailData();
				currentSurvey.separateAnswers(currentSurvey.getEmailList(), currentSurvey.getQuestionList());
				currentSurvey.printReport();
				frame.dispose();
			}
		});
		resultButton.setBounds(280, 432, 191, 29);
		frame.getContentPane().add(resultButton);

		Background = new JLabel("");
		Background.setIcon(new ImageIcon(SurveyDisplay.class.getResource("/resources/arcticsea.jpg")));
		Background.setBounds(0, 0, 500, 478);
		frame.getContentPane().add(Background);
	}
}
