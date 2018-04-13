import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

/**
 * Baseline code for fetching email messages using Javamail
 */
public class EmailReader {
	
	private String surveyName;
	public static ArrayList<Email> arrayOfEmails;
	
	/**
	 * Constructor initializes survey name
	 * @param name
	 */
	public EmailReader(String name) {
		surveyName = name;
	}
	 
	/**
	 * Returns a Properties object which is configured for a POP3 or IMAP server
	 * @param protocol either "imap" or "pop3"
	 * @param host - email host: gmail, yahoo, etc. 
	 * @param port
	 * @return a Properties object
	 */
	private Properties getServerProperties(String protocol, String host, String port) {
		Properties properties = new Properties();

		// server setting
		//format replaces "%s" with the string version of the protocol object
		properties.put(String.format("mail.%s.host", protocol), host);
		properties.put(String.format("mail.%s.port", protocol), port);

		// SSL (secure sockets layer) setting
		properties.setProperty(String.format("mail.%s.socketFactory.class", protocol),"javax.net.ssl.SSLSocketFactory");
		properties.setProperty(String.format("mail.%s.socketFactory.fallback", protocol),"false");
		properties.setProperty(String.format("mail.%s.socketFactory.port", protocol),String.valueOf(port));

		return properties;
	}
	

	/**
	 * Downloads new messages and fetches details for each message.
	 * @param protocol
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 */
	public ArrayList<Email> downloadEmails(String protocol, String host, String port,String userName, String password) {
		arrayOfEmails = new ArrayList<Email>();
		//Setting up email fetching
		Properties serverProperties = getServerProperties(protocol, host, port);
		Session emailSession = Session.getDefaultInstance(serverProperties);

		try {
			// connects to the message store
			Store messageStore = emailSession.getStore(protocol);
			messageStore.connect(userName, password);

			// opens the inbox folder
			Folder inboxFolder = messageStore.getFolder("INBOX");
			inboxFolder.open(Folder.READ_ONLY);

			// fetches new messages from server
			Message[] messages = inboxFolder
					.getMessages();

			for (int i = 0; i < messages.length; i++) {
				Message msg = messages[i];
				Address[] fromAddress = msg.getFrom();
				String from = fromAddress[0].toString();
				String subject = msg.getSubject();
				String sentDate = msg.getSentDate().toString();
				String contentType = msg.getContentType();
				String messageContent = "";
				
				
				if (contentType.contains("TEXT/PLAIN")) {
					try {
						Object content = msg.getContent();
						if (content != null) {
							messageContent = content.toString();
						}
					} catch (Exception ex) {
						messageContent = "Error downloading massage content";
						ex.printStackTrace();
					}
				}
				else if (msg.isMimeType("multipart/*")) {
					try {
			        MimeMultipart mimeMultipart = (MimeMultipart) msg.getContent();
			        messageContent = getTextFromMimeMultipart(mimeMultipart);
					}
					catch(Exception ex) {
						messageContent = "Error downloading massage content";
						ex.printStackTrace();
					}
			    }

				if(subject.equalsIgnoreCase(surveyName)) {
					Email savedEmail = new Email(subject, from, sentDate, messageContent);
					arrayOfEmails.add(savedEmail);
				}
			}
			 

			// disconnect
			inboxFolder.close(false);
			messageStore.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider for protocol: " + protocol);
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store");
			ex.printStackTrace();
		}
		return arrayOfEmails;

	}
	
	/**
	 * Message that converts a MimeMultipart body to a string
	 * @param mimeMultipart
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart emailBody = mimeMultipart.getBodyPart(i);
	        if (emailBody.isMimeType("text/plain")) {
	            result = result + emailBody.getContent();
	            break; 
	        } else if (emailBody.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)emailBody.getContent());
	        }
	    }
	    return result;   
	}
}
