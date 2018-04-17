import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
/**
 * GUI class that displays the results of the survey (this class is not fully functional yet).
 *
 */
public class DisplayResults {

	private JFrame frame;
	private Survey currentSurvey;
	private JTextArea label;
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
	public void newDisplayBuilder() {
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
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 946, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		label = new JTextArea();
		label.append(results);
		frame.getContentPane().setLayout(null);
		label.setEditable(false);


		JScrollPane scrollPane = new JScrollPane(label);
		scrollPane.setBounds(91, 83, 764, 491);
		frame.getContentPane().add(scrollPane);
		frame.setResizable(false);

		JLabel surveyTitle = new JLabel(currentSurvey.getSurveyName() + " Results");
		surveyTitle.setBounds(258, 6, 429, 48);
		surveyTitle.setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().add(surveyTitle);
		surveyTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		surveyTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(DisplayResults.class.getResource("/resources/26_arctic.jpg")));
		lblNewLabel.setBounds(0, 0, 946, 750);
		frame.getContentPane().add(lblNewLabel);
	}
}
