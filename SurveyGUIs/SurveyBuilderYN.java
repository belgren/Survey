import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SurveyBuilderYN {

	private JFrame frame;
	private JTextField textField;
	private Survey survey;

	/**
	 * Launch the application.
	 */
	public void newYNQuestion(Survey survey) {
		this.survey = survey;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SurveyBuilderYN window = new SurveyBuilderYN();
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
	public SurveyBuilderYN() {
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
		
		textField = new JTextField();
		textField.setBounds(44, 190, 365, 127);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton backButton = new JButton("Go Back to Main Creator Screen");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SurveyBuilderMain mainMenu = new SurveyBuilderMain();
				mainMenu.newMainBuilder(survey);
				frame.dispose();
				
				
			}
		});
		backButton.setBounds(163, 507, 244, 44);
		frame.getContentPane().add(backButton);
		
		JButton btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.setBounds(163, 370, 117, 29);
		frame.getContentPane().add(btnAddQuestion);
		
		JLabel descriptorTest = new JLabel("What would you like your yes/no question to say?");
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
