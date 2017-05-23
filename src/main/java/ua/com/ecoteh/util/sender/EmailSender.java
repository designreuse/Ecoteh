package ua.com.ecoteh.util.sender;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods for sending letters by E-mail.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class EmailSender implements Sender {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(EmailSender.class);

    /**
     * The sender charset.
     */
    private static final String CHARSET = "UTF-8";

    /**
     * The sender encoding.
     */
    private static final String ENCODING = "Q";

    /**
     * The sender subject.
     */
    private final String subject;

    /**
     * The sender text.
     */
    private final String text;

    /**
     * The recipient E-mail.
     */
    private final String recipientEmail;

    /**
     * The sender E-mail.
     */
    private final String senderEmail;

    /**
     * The sender E-mail password.
     */
    private final String senderEmailPass;

    /**
     * Default constructor.
     *
     * @param subject         the sender subject.
     * @param text            the sender text.
     * @param recipientEmail  the recipient E-mail.
     * @param senderEmail     the sender E-mail.
     * @param senderEmailPass the sender E-mail password.
     */
    public EmailSender(
            final String subject,
            final String text,
            final String recipientEmail,
            final String senderEmail,
            final String senderEmailPass
    ) {
        this.subject = subject;
        this.text = text;
        this.recipientEmail = recipientEmail;
        this.senderEmail = senderEmail;
        this.senderEmailPass = senderEmailPass;
    }

    /**
     * Sends a message to an E-mail in a new thread.
     */
    @Override
    public void send() {
        if (isValidated()) {
            final Thread thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
        }
    }

    /**
     * Used to create a EmailSender thread, starting the thread causes
     * the object's run method to be called in that separately executing
     * thread.
     */
    @Override
    public void run() {
        try {
            prepareAndSend(
                    this.subject, this.text,
                    this.recipientEmail,
                    this.senderEmail, this.senderEmailPass
            );
        } catch (UnsupportedEncodingException | MessagingException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    /**
     * Validates parameters to create sender: subject, text, recipient E-mail,
     * sender E-mail, sender E-mail password.
     *
     * @return true if all parameters are valid to create sender,
     * false otherwise.
     */
    private boolean isValidated() {
        return isNotEmpty(this.subject) &&
                isNotEmpty(this.text) &&
                isNotEmpty(this.recipientEmail) &&
                isNotEmpty(this.senderEmail) &&
                isNotEmpty(this.senderEmailPass);
    }

    /**
     * Creates and prepares sender to recipient from sender.
     *
     * @param subject         the sender subject.
     * @param text            the sender text.
     * @param recipientEmail  the recipient e-mail.
     * @param senderEmail     the sender E-mail.
     * @param senderEmailPass the sender E-mail password.
     * @throws MessagingException           If the parse failed
     *                                      in InternetAddress.
     * @throws UnsupportedEncodingException If the encoding fails
     *                                      in MimeUtility.encodeText(...).
     */
    private static void prepareAndSend(
            final String subject,
            final String text,
            final String recipientEmail,
            final String senderEmail,
            final String senderEmailPass
    ) throws UnsupportedEncodingException, MessagingException {
        try {
            doWork(
                    getTLSProperties(),
                    subject, text,
                    recipientEmail,
                    senderEmail, senderEmailPass
            );
        } catch (UnsupportedEncodingException | MessagingException ex) {
            LOGGER.error(ex.getMessage(), ex);
            doWork(
                    getSSLProperties(),
                    subject, text,
                    recipientEmail,
                    senderEmail, senderEmailPass
            );
        }
    }

    /**
     * Creates and send sender to recipient from sender from
     * a persistent set of TLS or SSL properties.
     *
     * @param properties      the Properties object represents
     *                        the persistent set of TLS or SSL properties.
     * @param subject         the sender subject.
     * @param text            the sender text.
     * @param recipientEmail  the recipient E-mail.
     * @param senderEmail     the sender E-mail.
     * @param senderEmailPass the sender E-mail password.
     * @throws MessagingException           If the parse failed
     *                                      in InternetAddress.
     * @throws UnsupportedEncodingException If the encoding fails
     *                                      in MimeUtility.encodeText(...).
     */
    private static void doWork(
            final Properties properties,
            final String subject,
            final String text,
            final String recipientEmail,
            final String senderEmail,
            final String senderEmailPass
    ) throws UnsupportedEncodingException, MessagingException {
        sendMessage(
                generateMessage(
                        getSession(properties, senderEmail, senderEmailPass),
                        subject, text,
                        recipientEmail, senderEmail
                )
        );
    }

    /**
     * Sends ready sender.
     *
     * @param message the sender to recipient from sender.
     * @throws MessagingException If the parse failed in Transport.send(...).
     */
    private static void sendMessage(final Message message) throws MessagingException {
        Transport.send(message);
    }

    /**
     * Create and return sender.
     *
     * @param session        the Session object represents a mail session.
     * @param subject        the sender subject.
     * @param text           the sender text.
     * @param recipientEmail the recipient E-mail.
     * @param senderEmail    the sender E-mail.
     * @return The Message object.
     * @throws MessagingException           If the parse failed
     *                                      in InternetAddress.
     * @throws UnsupportedEncodingException If the encoding fails
     *                                      in MimeUtility.encodeText(...).
     */
    private static Message generateMessage(
            final Session session,
            final String subject,
            final String text,
            final String recipientEmail,
            final String senderEmail
    ) throws MessagingException, UnsupportedEncodingException {
        final Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipientEmail)
        );
        message.setSubject(
                MimeUtility.encodeText(
                        subject, CHARSET, ENCODING
                )
        );
        message.setContent(text, getContent());
        message.setSentDate(new Date());
        return message;
    }

    /**
     * Returns a content.
     *
     * @return The content.
     */
    private static String getContent() {
        return "text/plain;charset=" + CHARSET;
    }

    /**
     * Creates and returns Session object which represents a mail session.
     *
     * @param properties      the Properties object represents
     *                        the persistent set of TLS or SSL properties
     * @param senderEmail     the sender E-mail.
     * @param senderEmailPass the sender E-mail password.
     * @return The Session object (newer null).
     */
    private static Session getSession(
            final Properties properties,
            final String senderEmail,
            final String senderEmailPass
    ) {
        return Session.getDefaultInstance(
                properties,
                getAuthenticator(senderEmail, senderEmailPass)
        );
    }

    /**
     * Creates and returns Authenticator.
     *
     * @param senderEmail     the sender E-mail.
     * @param senderEmailPass the sender E-mail password.
     * @return The Authenticator object (newer null).
     */
    private static Authenticator getAuthenticator(
            final String senderEmail,
            final String senderEmailPass
    ) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderEmailPass);
            }
        };
    }

    /**
     * Creates and returns TLS properties.
     *
     * @return The Properties object.
     */
    private static Properties getTLSProperties() {
        final Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    /**
     * Creates and returns SSL properties.
     *
     * @return The Properties object.
     */
    private static Properties getSSLProperties() {
        final Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }
}
