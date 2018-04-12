
public class Email {

	private String subject;
	private String fromAddress;
	private String sentDate;
	private String messageContent;
	
	public Email(String subject, String fromAddress, String sentDate, String messageContent) {
		this.subject = subject;
		this.fromAddress = fromAddress;
		this.sentDate = sentDate;
		this.messageContent = messageContent;
	}
	
	public String toString() {
		String stringEmail = "";
		stringEmail += "From: " + fromAddress;
		stringEmail += "\nDate: " + sentDate;
		stringEmail += "\nSubject: " + subject;
		stringEmail += "\nMessage content: " + messageContent;
		return stringEmail;
	}
	
	public String getMessage() {
		return messageContent;
	}
}
