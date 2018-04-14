import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JLabel;

public class SurveyDisplay {

	private JFrame frame;
	private Survey survey;

	public void newSurveyDisplay(Survey survey) {
		this.survey = survey;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SurveyDisplay window = new SurveyDisplay();
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
	public SurveyDisplay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(479, 6, 15, 666);
		frame.getContentPane().add(scrollBar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(6, 6, 461, 666);
		frame.getContentPane().add(lblNewLabel);
	}
}
