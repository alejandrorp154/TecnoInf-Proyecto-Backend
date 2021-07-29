package com.javaee.pryectoBack.util;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
			
			// This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

           // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<div> Traveller:  </div>"
            					+ "<br>    "
            					+ "<div> " +  mensaje + " </div>" 
            					+ "<br>    "
            					+ "<div> Saludos, </div>" 
            					+ "<div> TravelPack team </div>" 
            					+ "<br> " + "<img src=\"cid:image\" width=\"57\" height=\"73\">";
            messageBodyPart.setContent(htmlText, "text/html");
           // add it
            multipart.addBodyPart(messageBodyPart);

           // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds =  new FileDataSource(
           "C:\\Users\\Lu\\Desktop\\background\\unnamed.jpg");

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

           // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

           // put everything together
            msg.setContent(multipart);
			
			
			
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

			msg.setSubject(titulo);
			//msg.setText(mensaje, "utf-8");
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
