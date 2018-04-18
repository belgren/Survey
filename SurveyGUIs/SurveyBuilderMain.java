import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * GUI class that displays the main survey building menu
 *
 */
public class SurveyBuilderMain {

	private JFrame frame;
	private Survey currentSurvey;
	/**
	 * Method which launches the window
	 */
	public void newDisplayBuilder() {
		try {
			SurveyBuilderMain window = new SurveyBuilderMain(currentSurvey);
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	/**
	 * Constructor which takes in the survey, to allow the survey object to be edited/used 
	 */
	public SurveyBuilderMain(Survey survey) {
		currentSurvey = null;
		currentSurvey = survey;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 0, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		
		JButton qqButton = new JButton("Add Short Answer");
		qqButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SurveyBuilderSA newSA = new SurveyBuilderSA(currentSurvey);
				newSA.newQQuestion();
				frame.dispose();
			}
		});
		qqButton.setBounds(44, 370, 365, 52);
		frame.getContentPane().add(qqButton);
		
		
		//Takes user to the add YN question window and closes the current window
		JButton ynButton = new JButton("Add Yes/No Question");
		ynButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SurveyBuilderYN newYN = new SurveyBuilderYN(currentSurvey);
				newYN.newDisplayBuilder();
				frame.dispose();
			}
		});
		ynButton.setBounds(44, 134, 365, 52);
		frame.getContentPane().add(ynButton);
		
		
		//Takes user to the add MC question window and closes the current window
		JButton mcButton = new JButton("Add Multiple Choice Question");
		mcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SurveyBuilderMC newMC = new SurveyBuilderMC(currentSurvey);
				newMC.newDisplayBuilder();
				frame.dispose();
			}
		});
		mcButton.setBounds(44, 250, 365, 52);
		frame.getContentPane().add(mcButton);
		
		JLabel surveyMakerTitle = new JLabel("Survey Creator");
		surveyMakerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		surveyMakerTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		surveyMakerTitle.setBounds(44, 6, 365, 86);
		frame.getContentPane().add(surveyMakerTitle);
		
		
		//Takes user to the survey display window and gets rid of the current window
		JButton btnCreateSurvey = new JButton("Create Survey");
		btnCreateSurvey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SurveyDisplay newSurvey = new SurveyDisplay(currentSurvey);
				newSurvey.newDisplayBuilder();
				frame.dispose();
			}
		});
		btnCreateSurvey.setBounds(255, 499, 152, 52);
		frame.getContentPane().add(btnCreateSurvey);
		
		JLabel lblPoweredByKwaltrix = new JLabel("<html> Powered by Kwaltrix<sup>TM</sup> </html>");
		lblPoweredByKwaltrix.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblPoweredByKwaltrix.setBounds(6, 556, 121, 16);
		frame.getContentPane().add(lblPoweredByKwaltrix);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(SurveyBuilderMain.class.getResource("/resources/SurveyMaker.png")));
		background.setBounds(0, 0, 450, 578);
		frame.getContentPane().add(background);
	}
}