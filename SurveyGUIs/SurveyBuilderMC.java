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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
/**
 * GUI class that displays the Multiple Choice question building window
 * @author darrylfilmore
 *
 */
public class SurveyBuilderMC {

	private JFrame frame;
	private JTextField textField;
	private Survey currentSurvey;
	private String questionText;

	/**
	 * Method which launches the window.
	 */
	public void newMCQuestion() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SurveyBuilderMC window = new SurveyBuilderMC(currentSurvey);
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
	public SurveyBuilderMC(Survey survey) {
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
		textBox.setBounds(49, 171, 355, 58);
		textBox.setBackground(UIManager.getColor("window"));
		frame.getContentPane().add(textBox);
		
		
		JLabel optionALabel = new JLabel("Enter option A:");
		optionALabel.setHorizontalAlignment(SwingConstants.CENTER);
		optionALabel.setBounds(49, 241, 179, 36);
		frame.getContentPane().add(optionALabel);
		
		JTextArea optionA = new JTextArea();
		optionA.setBackground(SystemColor.window);
		optionA.setBounds(49, 270, 179, 29);
		frame.getContentPane().add(optionA);
		
		JLabel optionBLabel = new JLabel("Enter option B:");
		optionBLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optionBLabel.setBounds(230, 241, 174, 36);
		frame.getContentPane().add(optionBLabel);
		
		JTextArea optionB = new JTextArea();
		optionB.setBackground(SystemColor.window);
		optionB.setBounds(230, 270, 174, 29);
		frame.getContentPane().add(optionB);
		
		JLabel optionCLabel = new JLabel("Enter option C:");
		optionCLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optionCLabel.setBounds(49, 324, 174, 36);
		frame.getContentPane().add(optionCLabel);
		
		JTextArea optionC = new JTextArea();
		optionC.setBackground(SystemColor.window);
		optionC.setBounds(49, 353, 179, 29);
		frame.getContentPane().add(optionC);
		
		JLabel optionDLabel = new JLabel("Enter option D:");
		optionDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optionDLabel.setBounds(230, 324, 174, 36);
		frame.getContentPane().add(optionDLabel);
		
		JTextArea optionD = new JTextArea();
		optionD.setBackground(SystemColor.window);
		optionD.setBounds(230, 353, 174, 29);
		frame.getContentPane().add(optionD);
		
		
		//Takes the user back to the main creation screen without adding a question, gets rid of current screen 
		JButton backButton = new JButton("Go Back to Main Creator Screen");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SurveyBuilderMain mainMenu = new SurveyBuilderMain(currentSurvey);
				mainMenu.newMainBuilder();
				frame.dispose();
				
				
			}
		});
		backButton.setBounds(163, 507, 244, 44);
		frame.getContentPane().add(backButton);
		
		//Creates a new Multiple choice question if all fields are filled, otherwise, takes user to error message
		JButton btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.setBounds(163, 406, 117, 29);
		frame.getContentPane().add(btnAddQuestion);
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionText = textBox.getText();
				if ((questionText.equals("")) || (optionA.getText().equals(""))|| (optionB.getText().equals("")) || (optionC.getText().equals(""))|| (optionD.getText().equals(""))) {
					ErrorWindow noSurveyNameError = new ErrorWindow("A question field was not filled out.");
					noSurveyNameError.newErrorBuilder();
					frame.dispose();
				}
				else {
					ArrayList<String> options = new ArrayList<String>();
					options.add(optionA.getText());
					options.add(optionB.getText());
					options.add(optionC.getText());
					options.add(optionD.getText());
					currentSurvey.addMultChoiceQuestion(questionText, options);
					SurveyBuilderMain mainMenu = new SurveyBuilderMain(currentSurvey);
					mainMenu.newMainBuilder();
					frame.dispose();
				}
			}
		});
		
		JLabel MCtitle = new JLabel("Enter multiple choice question:");
		MCtitle.setHorizontalAlignment(SwingConstants.CENTER);
		MCtitle.setBounds(49, 138, 370, 52);
		frame.getContentPane().add(MCtitle);
		
		JLabel surveyMakerTitle = new JLabel("Survey Creator");
		surveyMakerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		surveyMakerTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		surveyMakerTitle.setBounds(44, 6, 365, 86);
		frame.getContentPane().add(surveyMakerTitle);
		
		JLabel whiteBackground = new JLabel("");
		whiteBackground.setIcon(new ImageIcon(SurveyBuilderMain.class.getResource("/resources/white background.jpg")));
		whiteBackground.setBounds(44, 138, 365, 349);
		frame.getContentPane().add(whiteBackground);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(SurveyBuilderMain.class.getResource("/resources/SurveyMaker.png")));
		background.setBounds(0, 0, 450, 578);
		frame.getContentPane().add(background);
	}
}
