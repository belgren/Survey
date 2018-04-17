import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
/**
 * GUI class that displays an error message when the user does a "bad" action (this class is not fully functional yet)
 * If encountered, the user must re-start the survey making process.
 * @author darrylfilmore
 *
 */
public class ErrorWindow implements GUIWindow{

	private JFrame frame;
	private String errorMessageString;
	private final JLabel background = new JLabel("");

	/**
	 * Method which launches the window
	 */
	public void newDisplayBuilder() {
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
	 * Constructor which takes in an error message that is passed in when a button is pressed (if something is incorrect) 
	 */
	public ErrorWindow(String errorMessageString) {
		this.errorMessageString = errorMessageString;
		initialize();
	}

	/**
	 * Initialize the contents of the frame
	 */
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(139, 0, 0));
		frame.setBounds(100, 100, 540, 360);
		frame.getContentPane().setLayout(null);
		
		
		//Displays the appropriate error message (gets passed in when a specific button is pressed)
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
		
		JButton returnButton = new JButton("Return to Builder");
		returnButton.setBounds(192, 291, 159, 29);
		frame.getContentPane().add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		background.setIcon(new ImageIcon(ErrorWindow.class.getResource("/resources/error.jpg")));
		
		background.setBounds(0, 0, 540, 338);
		frame.getContentPane().add(background);
		
	}
}
