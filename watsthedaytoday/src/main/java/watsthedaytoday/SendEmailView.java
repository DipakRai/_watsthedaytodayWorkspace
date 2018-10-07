package watsthedaytoday;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.annotation.Resources;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
@ManagedBean
public class SendEmailView {
    
    private String email="";
        
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addToSubscriptionList() { //TODO adding to subscription List
		System.out.println(" sfasfse "+email);
		String subscribedEmail = email;
		String summary = "Your email has been registered. You would get a reminder of this day on your email!";
		setEmail(null);
	    setEmail("");
	    email="";
	    System.out.println("@#$#@ email =");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        writeToFile(subscribedEmail);
    }
	
	private void writeToFile(String email){
		Writer writer = null;
		System.out.println("@#@ Inside write ...="+email);
		PrintWriter printWriter = null;
		try {
			 /*ClassLoader classLoader = getClass().getClassLoader();
    		 File file = new File(classLoader.getResource("sendReminders.txt").getFile());
    		 System.out.println(" did i find the file ? ="+file.exists());
    		 writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));*/
			ClassLoader classLoader = getClass().getClassLoader();
			//File file = new File(classLoader.getResource("./src/main/resources/sendReminders.txt").getFile());
   		 	File file = new File(classLoader.getResource("sendReminders.txt").getFile());
			System.out.println(" did i find the file ? =" + file.exists() + " write = " + file.canWrite() + " read = "
					+ file.canRead());
    		printWriter = new PrintWriter(file);
    	    printWriter.write(email);
    	    
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				printWriter.close();
			} catch (Exception ex) {

			}
		}
	}
	
	private void sendEmail(String from, String password, String to, String sub, String msg){
		// Get properties object
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				//props.put("mail.smtp.host", "smtp.mail.yahoo.com"); for yahoo
				props.put("mail.smtp.socketFactory.port", "465");
				//props.put("mail.smtp.socketFactory.port", "25"); for yahoo
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");
				//props.put("mail.smtp.port", "25"); for yahoo
				// get Session
				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from, password);
					}
				});
				// compose message
				try {
					MimeMessage message = new MimeMessage(session);
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject(sub);
					message.setText(msg);
					// send message
					Transport.send(message);
					System.out.println("message sent successfully");
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
	}
}

