/**
 * Class to create email objects.
 * @author Jordan
 *
 */
public class Email {

	private String subject;
	private String fromAddress;
	private String sentDate;
	private String messageContent;
	
	/**
	 * Constructor initializes email with attributes: subject, fromAddress, sentDate, and messageContent
	 * @param subject
	 * @param fromAddress
	 * @param sentDate
	 * @param messageContent
	 */
	public Email(String subject, String fromAddress, String sentDate, String messageContent) {
		this.subject = subject;
		this.fromAddress = fromAddress;
		this.sentDate = sentDate;
		this.messageContent = messageContent;
	}
	
	public String getMessage() {
		return messageContent;
	}
}
