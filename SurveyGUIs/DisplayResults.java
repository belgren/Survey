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
/**
 * GUI class that displays the results of the survey (this class is not fully functional yet).
 * @author darrylfilmore
 *
 */
public class DisplayResults {

	private JFrame frame;
	private Survey currentSurvey;
	private JTextArea label;
	private JLabel Background;
	private String results;


	/**
	 * Constructor which takes in the survey, to allow the survey object to be edited/used 
	 */
	public DisplayResults(Survey survey, String report) {
		this.results = report;
		currentSurvey = null;
		currentSurvey = survey;
		initialize();
	}

	
	/**
	 * Method which launches the window
	 */
	public void newDisplayResultsBuilder() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayResults window = new DisplayResults(currentSurvey, results);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//Eventually this will display the answers in the text area
		label = new JTextArea();
		//for(QuestionStrategy q : currentSurvey.getQuestionList()) {
			label.append(results);
		//}
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
