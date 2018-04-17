import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * GUI class that displays the Yes/No question building window
 * @author darrylfilmore
 *
 */
public class SurveyBuilderSA {

	private JFrame frame;
	private JTextField textField;
	private Survey currentSurvey;
	private String questionText;

	/**
	 * Method which launches the window.
	 */
	public void newQQuestion() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SurveyBuilderSA window = new SurveyBuilderSA(currentSurvey);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor which takes in the survey, to allow the survey object to be edited/used 
	 */
	public SurveyBuilderSA(Survey survey) {
		currentSurvey = survey;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 0, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextArea textBox = new JTextArea();
		textBox.setBounds(49, 190, 355, 127);
		textBox.setBackground(UIManager.getColor("window"));
		frame.getContentPane().add(textBox);

		//Takes the user back to the main creation screen without adding a question, gets rid of current screen
		JButton backButton = new JButton("Go Back to Main Creator Screen");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SurveyBuilderMain mainMenu = new SurveyBuilderMain(currentSurvey);
				mainMenu.newDisplayBuilder();
				frame.dispose();


			}
		});
		backButton.setBounds(163, 507, 244, 44);
		frame.getContentPane().add(backButton);

		//Creates a new short answer question if the question field is full, otherwise, takes user to error message
		JButton btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.setBounds(163, 370, 117, 29);
		frame.getContentPane().add(btnAddQuestion);
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionText = textBox.getText();
				if (questionText.equals("")) {
					ErrorWindow noSurveyNameError = new ErrorWindow("No question was given.");
					noSurveyNameError.newDisplayBuilder();
					frame.dispose();
				}
				else {
					currentSurvey.addShortAnswerQuestion(questionText);;
					SurveyBuilderMain mainMenu = new SurveyBuilderMain(currentSurvey);
					mainMenu.newDisplayBuilder();
					frame.dispose();
				}
			}
		});

		JLabel descriptorTest = new JLabel("Enter short answer question:");
		descriptorTest.setHorizontalAlignment(SwingConstants.CENTER);
		descriptorTest.setBounds(44, 154, 365, 36);
		frame.getContentPane().add(descriptorTest);

		JLabel surveyMakerTitle = new JLabel("Survey Creator");
		surveyMakerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		surveyMakerTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		surveyMakerTitle.setBounds(44, 6, 365, 86);
		frame.getContentPane().add(surveyMakerTitle);

		JLabel whiteBackground = new JLabel("New label");
		whiteBackground.setIcon(new ImageIcon(SurveyBuilderMain.class.getResource("/resources/white background.jpg")));
		whiteBackground.setBounds(44, 138, 365, 349);
		frame.getContentPane().add(whiteBackground);

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(SurveyBuilderMain.class.getResource("/resources/SurveyMaker.png")));
		background.setBounds(0, 0, 450, 578);
		frame.getContentPane().add(background);
	}
}
