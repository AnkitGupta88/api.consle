package com.global.console.utils;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.*;

/**
 * The Class GmailConfig.
 */
public class MailConfig {

	/** The host. */
	private static String HOST = "smtp.gmail.com";

	/** The user. */
	private static String USER = "lakshayswanigl@gmail.com";

	/** The password. */
	private static String PASSWORD = "yamahar15";

	/** The port. */
	private static String PORT = "465";

	/** The from. */
	private static String FROM = "GL";

	/** The starttls. */
	private static String STARTTLS = "true";

	/** The auth. */
	private static String AUTH = "true";

	/** The debug. */
	private static String DEBUG = "true";

	/** The socket factory. */
	private static String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";

	/**
	 * Send.
	 *
	 * @param to
	 *            the to
	 * @param subject
	 *            the subject
	 * @param text
	 *            the text
	 */
	public static synchronized void send(String to, String subject, String text) {
		Properties props = new Properties();

		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.user", USER);

		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.starttls.enable", STARTTLS);
		props.put("mail.smtp.debug", DEBUG);

		props.put("mail.smtp.socketFactory.port", PORT);
		props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		try {

			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);

			MimeMessage message = new MimeMessage(session);
			message.setText(text, "UTF-8", "html");
			message.setSubject(subject);
			message.setFrom(new InternetAddress(FROM));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			message.saveChanges();

			Transport transport = session.getTransport("smtp");
			transport.connect(HOST, USER, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
