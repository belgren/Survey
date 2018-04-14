import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SurveyBuilderMain {

	private JFrame frame;
	private Survey survey;
	
	public void newMainBuilder(Survey survey) {
		this.survey = survey;
		try {
			SurveyBuilderMain window = new SurveyBuilderMain();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	/**
	 * Create the application.
	 */
	public SurveyBuilderMain() {
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
		
		JButton qqButton = new JButton("Add Quantity Question");
		qqButton.setBounds(44, 370, 365, 52);
		frame.getContentPane().add(qqButton);
		
		JButton ynButton = new JButton("Add Yes/No Question");
		ynButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SurveyBuilderYN newYN = new SurveyBuilderYN();
				newYN.newYNQuestion(survey);
				frame.dispose();
			}
		});
		ynButton.setBounds(44, 134, 365, 52);
		frame.getContentPane().add(ynButton);
		
		JButton mcButton = new JButton("Add Multiple Choice Question");
		mcButton.setBounds(44, 250, 365, 52);
		frame.getContentPane().add(mcButton);
		
		JLabel surveyMakerTitle = new JLabel("Survey Creator");
		surveyMakerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		surveyMakerTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		surveyMakerTitle.setBounds(44, 6, 365, 86);
		frame.getContentPane().add(surveyMakerTitle);
		
		JButton btnCreateSurvey = new JButton("Create Survey");
		btnCreateSurvey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SurveyDisplay newSurvey = new SurveyDisplay();
				newSurvey.newSurveyDisplay(survey);
				frame.dispose();
			}
		});
		btnCreateSurvey.setBounds(255, 499, 152, 52);
		frame.getContentPane().add(btnCreateSurvey);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(SurveyBuilderMain.class.getResource("/resources/SurveyMaker.png")));
		background.setBounds(0, 0, 450, 578);
		frame.getContentPane().add(background);
	}
}
