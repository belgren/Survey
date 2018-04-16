import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class ErrorWindow {

	private JFrame frame;
	private String errorMessageString;
	private final JLabel background = new JLabel("");

	/**
	 * Launch the application.
	 */
	public void newErrorBuilder() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorWindow window = new ErrorWindow(errorMessageString);
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
	public ErrorWindow(String errorMessageString) {
		this.errorMessageString = errorMessageString;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(139, 0, 0));
		frame.setBounds(100, 100, 540, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea errorMessage = new JTextArea("\n      " 
												+ errorMessageString);
		errorMessage.setBounds(67, 71, 405, 207);
		frame.getContentPane().add(errorMessage);
		
		JLabel errorTitle = new JLabel("Error");
		errorTitle.setHorizontalAlignment(SwingConstants.CENTER);
		errorTitle.setForeground(new Color(0, 0, 0));
		errorTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		errorTitle.setBounds(174, 22, 177, 47);
		frame.getContentPane().add(errorTitle);
		background.setIcon(new ImageIcon(ErrorWindow.class.getResource("/resources/error.jpg")));
		background.setBounds(0, 0, 540, 338);
		frame.getContentPane().add(background);
	}

}
