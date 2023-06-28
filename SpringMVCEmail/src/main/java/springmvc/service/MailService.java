package springmvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service
public class MailService {	
	
	//Send Simple Mail 
	public void sendMail(String sender, String receiver, String subject, String message ) throws MessagingException {
		System.out.println("Inside Send Mail Service");
		String host="smtp.gmail.com";
		
		//Step 1 : Define Prop
		
		//Get SYs properties
		Properties properties=new Properties();
		
		//Setting Properties
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");

		properties.put("mail.smtp.starttls.enable", "false");
		
		properties.put("mail.smtp.ssl.trust", "*");
		
		
		// Step 2: Get Session & Password Auth
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("shrutikamamdyal456@gmail.com","dezrluowhkpmrxqv");
			}
		});
		
		session.setDebug(true);
 		
		//Step 3: compose mail
		MimeMessage mimeMsg = new MimeMessage(session);
		
		//Sender
		mimeMsg.setFrom(sender);
		
		//Rec
		mimeMsg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
		
		//Adding Subject
		mimeMsg.setSubject(subject);
		
		mimeMsg.setText(message);
		
		//Step 4 : send the message using Transport class
		Transport.send(mimeMsg);
		
	}
	
	//Send Mail with attachment
	public void sendMail(List<String> to, List<String> cc,String Subject,MimeMultipart mimeMultipart) throws MessagingException {
		
		//Declare Host
		String host = "smtpcorp.netcore.co.in";
		//String host="smtp.gmail.com";
		
		//Declare username & password
		final String username = "smtp_corp_relay@coreintegra.com";
		final String password = "#CoreMail$2525@";
		//Declare From
		String from = "ctrlf@coreintegra.com";
		
		Properties properties = new Properties();

		// Step 1 : Properties
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable", "false");		
		properties.put("mail.smtp.ssl.trust", "*");
		
		//Step 2: Session & AUth
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		session.setDebug(true);
		
		MimeMessage message = new MimeMessage(session);
		
		
		
		//Set 3: Compose Mail Set to,from,cc
		message.setFrom(new InternetAddress(from));
		message.setSubject(Subject);
		
		//Set to
		InternetAddress[] toAdds = new InternetAddress[to.size()];
		
		int i=0;
		for(String to1 : to) {
			toAdds[i]=new InternetAddress(to1);
			i++;
		}
		message.setRecipients(Message.RecipientType.TO, toAdds);
		
		//Set CC
		InternetAddress[] ccAdds = new InternetAddress[cc.size()];;
		i=0;
		for(String cc1 : cc) {
			ccAdds[i]=new InternetAddress(cc1);
			i++;
		}
		message.setRecipients(Message.RecipientType.CC, ccAdds);
		
		//Set Multipart/Message COntent
		message.setContent(mimeMultipart);
		
		//Step 4 : Send Mail
		Transport.send(message);
		
	}
}
