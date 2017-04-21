package com.global.console.utils;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

import com.global.console.configuration.MailConfiguration;

import java.util.*;

/**
 * The Class MailConfig.
 */
public class MailConfig {

	/** The mail config. */
	private static MailConfig mailConfig;

	private MailConfiguration mailConfiguration;

	/** The props. */
	private Properties props;

	/**
	 * Instantiates a new mail config.
	 */
	public MailConfig(MailConfiguration mailConfiguration) {
		this.mailConfiguration = mailConfiguration;
		props = new Properties();
		props.put(ApiConstants.MAIL_HOST, mailConfiguration.getHOST());
		props.put(ApiConstants.MAIL_PORT, mailConfiguration.getPORT());
		props.put(ApiConstants.MAIL_USER, mailConfiguration.getUSER());
		props.put(ApiConstants.MAIL_AUTH, mailConfiguration.getAUTH());
		props.put(ApiConstants.MAIL_STARTTLS, mailConfiguration.getSTARTTLS());
		props.put(ApiConstants.MAIL_DEBUG, mailConfiguration.getDEBUG());
		props.put(ApiConstants.MAIL_SOCKETFACTORY_PORT, mailConfiguration.getPORT());
		props.put(ApiConstants.MAIL_SOCKETFACTORY_CLASS, mailConfiguration.getSOCKET_FACTORY());
		props.put(ApiConstants.MAIL_SOCKETFACTORY_FALLBACK, mailConfiguration.getFALLBACK());
	}

	/**
	 * Gets the single instance of MailConfig.
	 *
	 * @return single instance of MailConfig
	 */
	public static MailConfig getInstance(MailConfiguration mailConfiguration) {
		if (mailConfig == null) {
			synchronized (MailConfig.class) {
				if (mailConfig == null) {
					mailConfig = new MailConfig(mailConfiguration);
				}
			}
		}
		return mailConfig;
	}

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
	public synchronized void send(String to, String subject, String text) {
		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			message.setText(text, "UTF-8", "html");
			message.setSubject(subject);
			message.setFrom(new InternetAddress(mailConfiguration.getFROM()));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(mailConfiguration.getHOST(), mailConfiguration.getUSER(),
					mailConfiguration.getPASSWORD());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
