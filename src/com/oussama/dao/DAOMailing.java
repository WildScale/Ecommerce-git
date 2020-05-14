package com.oussama.dao;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.oussama.interfaces.IMailing;

public class DAOMailing implements IMailing {

	@Override
	public void envoyerMail(String messageString, String objet, String emetteur) throws AddressException, MessagingException {
		 // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
// *** BEGIN CHANGE
        properties.put("mail.smtp.user", "ecommerce.aroma666@gmail.com");

        // creates a new session, no Authenticator (will connect() later)
        Session session = Session.getDefaultInstance(properties);
// *** END CHANGE

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress("ecommerce.aroma666@gmail.com"));
        InternetAddress[] toAddresses = { new InternetAddress("aromashop3@gmail.com") };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject("objet");
        
        msg.setText("Message de la part de "+ emetteur + " \n"+messageString);

// *** BEGIN CHANGE
        // sends the e-mail
        Transport t = session.getTransport("smtp");
        t.connect("ecommerce.aroma666@gmail.com", "aroma12345");
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();

	}
}
