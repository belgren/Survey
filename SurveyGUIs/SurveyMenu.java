import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 * GUI class that displays the survey creation menu This file runs all the other
 * files!
 *
 */
public class SurveyMenu {

	private JFrame frame;
	private JTextField surveyName;
	private String surveyNameInput;
	private Survey survey;
	public static boolean writeFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SurveyMenu window = new SurveyMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean getWriteFile() {
		return writeFile;
	}

	public void newDisplayBuilder() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SurveyMenu window = new SurveyMenu();
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
	public SurveyMenu() {
		surveyNameInput = "";
		writeFile = false;
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.setBackground(Color.WHITE);
		frame.setBounds(0, 0, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		// Sends the results to a file upon completion of the survey
		JButton sendToFile = new JButton("Send To File");
		sendToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surveyNameInput = surveyName.getText();
				if (surveyNameInput.equals("")) {
					ErrorWindow noSurveyNameError = new ErrorWindow("No survey name was given.");
					noSurveyNameError.newDisplayBuilder();
				} else {
					writeFile = true;
					surveyNameInput = surveyName.getText();
					survey = Survey.getInstance(surveyNameInput);
					SurveyBuilderMain homeScreen = new SurveyBuilderMain(survey);
					homeScreen.newDisplayBuilder();
					frame.dispose();
				}
			}
		});

		surveyName = new JTextField();
		surveyName.setBounds(80, 247, 297, 37);
		frame.getContentPane().add(surveyName);
		surveyName.setColumns(10);

		JLabel nameText = new JLabel("What would you like to name your survey?");
		nameText.setHorizontalAlignment(SwingConstants.CENTER);
		nameText.setBounds(6, 210, 438, 25);
		frame.getContentPane().add(nameText);

		JLabel title = new JLabel("Survey Maker");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		title.setBounds(102, 0, 287, 72);
		frame.getContentPane().add(title);

		JLabel surveyResultsLbl = new JLabel("How would you like to save the survey results?");
		surveyResultsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		surveyResultsLbl.setBounds(6, 296, 438, 25);
		frame.getContentPane().add(surveyResultsLbl);
		sendToFile.setBackground(new Color(238, 238, 238));
		sendToFile.setBounds(72, 335, 138, 37);
		frame.getContentPane().add(sendToFile);

		// Button that tells the survey to display results in a new window and sends the
		// user to the next step of the survey making process
		// Only works if the survey has a name, if not, pops up and error message
		JButton displayOnScreen = new JButton("Display On Screen");
		displayOnScreen.setBounds(239, 335, 138, 37);
		frame.getContentPane().add(displayOnScreen);
		displayOnScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surveyNameInput = surveyName.getText();
				if (surveyNameInput.equals("")) {
					ErrorWindow noSurveyNameError = new ErrorWindow("No survey name was given.");
					noSurveyNameError.newDisplayBuilder();
				} else {
					survey = Survey.getInstance(surveyNameInput);
					SurveyBuilderMain homeScreen = new SurveyBuilderMain(survey);
					homeScreen.newDisplayBuilder();
					frame.dispose();
				}
			}
		});

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(SurveyMenu.class.getResource("/resources/Background.png")));
		background.setBounds(0, 0, 450, 378);
		frame.getContentPane().add(background);
	}
}
