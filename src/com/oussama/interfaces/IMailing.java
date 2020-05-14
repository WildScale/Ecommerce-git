package com.oussama.interfaces;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface IMailing {

	void envoyerMail(String message, String objet, String emetteur) throws AddressException, MessagingException;
	
}
