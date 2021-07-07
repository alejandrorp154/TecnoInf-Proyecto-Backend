package com.javaee.pryectoBack.util;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class EnviarNotificacion {
	
	public EnviarNotificacion()
	{
	}
	
	@SuppressWarnings("restriction")
	public boolean enviarEmailNotificacion(String mensaje, String email, String titulo) {
		boolean res = false;
		String to = email;

		// Sender's email ID needs to be mentioned soportetravelpack
		String from = "soportetravelpack@gmail.com";
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtps.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtps.auth", "true");

		props.put("mail.smtps.quitwait", "false");

		Session session = Session.getInstance(props, null);

		// -- Create a new message --
		final MimeMessage msg = new MimeMessage(session);

		try {
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

			msg.setSubject(titulo);
			msg.setText(mensaje, "utf-8");
			msg.setSentDate(new Date());

			SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

			final String username = "soportetravelpack@gmail.com";
			final String password = "proyecto2021";

			t.connect("smtp.gmail.com", username, password);
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			res = true;
		} catch (Exception exception) {
			return res;
		}
		return res;
	}
	
	public boolean enviarPushNotificacion(String mensaje, String idPersona, String titulo) {
		boolean res = false;
		try {
			
		} catch (Exception exception) {
			return res;
		}
		return res;
	}
}
