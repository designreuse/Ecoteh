package com.salimov.yurii.util.sender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * The class implements a set of methods for sending letters by e-mail.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class SenderImpl implements Sender {

    /**
     * The sender charset.
     */
    private static final String CHARSET = "UTF-8";

    /**
     * The sender encoding.
     */
    private static final String ENCODING = "Q";

    /**
     * The Session object represents a mail session.
     * It collects together properties and defaults used by the mail API's.
     */
    private Session session;

    /**
     * The Properties object represents a persistent set of TLS properties.
     */
    private Properties tlsProperties;

    /**
     * The Properties object represents a persistent set of SSL properties.
     */
    private Properties sslProperties;

    /**
     * The sender subject.
     */
    private final String subject;

    /**
     * The sender text.
     */
    private final String text;

    /**
     * The recipient e-mail.
     */
    private final String recipientEmail;

    /**
     * The sender e-mail.
     */
    private final String senderEmail;

    /**
     * The sender e-mail password.
     */
    private final String senderEmailPass;

    /**
     * Default constructor.
     *
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipientEmail  a recipient e-mail.
     * @param senderEmail     a sender e-mail.
     * @param senderEmailPass a sender e-mail password.
     */
    public SenderImpl(
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
     * Sends a message to an e-mail in new thread.
     */
    public void send() {
        if (validate()) {
            final Thread thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
        }
    }

    /**
     * Used to create a SenderImpl thread, starting the thread causes
     * the object's run method to be called in that separately executing
     * thread.
     */
    @Override
    public void run() {
        try {
            prepareAndSend(
                    this.subject,
                    this.text,
                    this.recipientEmail,
                    this.senderEmail,
                    this.senderEmailPass
            );
        } catch (UnsupportedEncodingException | MessagingException ex) {
            ex.printStackTrace();
            destroyObject();
        }
    }

    /**
     * Validates parameters to create sender: subject, text, recipient e-mail,
     * sender e-mail, sender e-mail password.
     *
     * @return {@code true} if all parameters are valid to create sender,
     * {@code false} otherwise.
     */
    private boolean validate() {
        return !(isBlank(this.subject)
                || isBlank(this.text)
                || isBlank(this.recipientEmail)
                || isBlank(this.senderEmail)
                || isBlank(this.senderEmailPass));
    }

    /**
     * Creates and prepares sender to recipient from sender.
     *
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipientEmail  a recipient e-mail.
     * @param senderEmail     a sender e-mail.
     * @param senderEmailPass a sender e-mail password.
     * @throws MessagingException           If the parse failed
     *                                      in InternetAddress.
     * @throws UnsupportedEncodingException If the encoding fails
     *                                      in MimeUtility.encodeText(...).
     */
    private void prepareAndSend(
            final String subject,
            final String text,
            final String recipientEmail,
            final String senderEmail,
            final String senderEmailPass
    ) throws UnsupportedEncodingException, MessagingException {
        try {
            doWork(
                    getTLSProperties(),
                    subject,
                    text,
                    recipientEmail,
                    senderEmail,
                    senderEmailPass
            );
        } catch (UnsupportedEncodingException | MessagingException ex) {
            ex.printStackTrace();
            doWork(
                    getSSLProperties(),
                    subject,
                    text,
                    recipientEmail,
                    senderEmail,
                    senderEmailPass
            );
        }
    }

    /**
     * Creates and send sender to recipient from sender from
     * a persistent set of TLS or SSL properties.
     *
     * @param properties      a Properties object represents
     *                        a persistent set of TLS or SSL properties.
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipientEmail  a recipient e-mail.
     * @param senderEmail     a sender e-mail.
     * @param senderEmailPass a sender e-mail password.
     * @throws MessagingException           If the parse failed
     *                                      in InternetAddress.
     * @throws UnsupportedEncodingException If the encoding fails
     *                                      in MimeUtility.encodeText(...).
     */
    private void doWork(
            final Properties properties,
            final String subject,
            final String text,
            final String recipientEmail,
            final String senderEmail,
            final String senderEmailPass
    ) throws UnsupportedEncodingException, MessagingException {
        final Session session = getSession(
                properties,
                senderEmail,
                senderEmailPass
        );
        final Message message = generateMessage(
                session,
                subject,
                text,
                recipientEmail,
                senderEmail
        );
        sendMessage(message);
    }

    /**
     * Sends ready sender.
     *
     * @param message a sender to recipient from sender.
     * @throws MessagingException If the parse failed in Transport.send(...).
     */
    private void sendMessage(final Message message) throws MessagingException {
        Transport.send(message);
    }

    /**
     * Create and return sender.
     *
     * @param session        a Session object represents a mail session.
     * @param subject        a sender subject.
     * @param text           a sender text.
     * @param recipientEmail a recipient e-mail.
     * @param senderEmail    a sender e-mail.
     * @return The Message object.
     * @throws MessagingException           If the parse failed
     *                                      in InternetAddress.
     * @throws UnsupportedEncodingException If the encoding fails
     *                                      in MimeUtility.encodeText(...).
     */
    private Message generateMessage(
            final Session session,
            final String subject,
            final String text,
            final String recipientEmail,
            final String senderEmail
    ) throws MessagingException, UnsupportedEncodingException {
        final Message message = new MimeMessage(session);
        message.setFrom(
                new InternetAddress(senderEmail)
        );
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipientEmail)
        );
        message.setSubject(
                MimeUtility.encodeText(
                        subject,
                        SenderImpl.CHARSET,
                        SenderImpl.ENCODING
                )
        );
        message.setContent(
                text,
                "text/plain;charset=" + SenderImpl.CHARSET
        );
        message.setSentDate(
                new Date()
        );
        return message;
    }

    /**
     * Creates and returns Session object which represents a mail session.
     *
     * @param properties      a Properties object represents
     *                        a persistent set of TLS or SSL properties
     * @param senderEmail     a sender e-mail.
     * @param senderEmailPass a sender e-mail password.
     * @return The Session object.
     */
    private Session getSession(
            final Properties properties,
            final String senderEmail,
            final String senderEmailPass
    ) {
        this.session = Session.getDefaultInstance(
                properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication
                    getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                senderEmail,
                                senderEmailPass
                        );
                    }
                });
        return this.session;
    }

    /**
     * Creates and returns TLS properties.
     *
     * @return The Properties object.
     */
    private Properties getTLSProperties() {
        if (this.tlsProperties == null) {
            this.tlsProperties = new Properties();
            this.tlsProperties.put(
                    "mail.smtp.auth",
                    "true"
            );
            this.tlsProperties.put(
                    "mail.smtp.starttls.enable",
                    "true"
            );
            this.tlsProperties.put(
                    "mail.smtp.host",
                    "smtp.gmail.com"
            );
            this.tlsProperties.put(
                    "mail.smtp.port",
                    "587"
            );
        }
        return this.tlsProperties;
    }

    /**
     * Creates and returns SSL properties.
     *
     * @return The Properties object.
     */
    private Properties getSSLProperties() {
        if (this.sslProperties == null) {
            this.sslProperties = new Properties();
            this.sslProperties.put(
                    "mail.smtp.host",
                    "smtp.gmail.com"
            );
            this.sslProperties.put(
                    "mail.smtp.socketFactory.port",
                    "465"
            );
            this.sslProperties.put(
                    "mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory"
            );
            this.sslProperties.put(
                    "mail.smtp.auth",
                    "true"
            );
            this.sslProperties.put(
                    "mail.smtp.port",
                    "465"
            );
        }
        return this.sslProperties;
    }

    /**
     * Resets session and properties.
     */
    private void destroyObject() {
        this.tlsProperties = null;
        this.sslProperties = null;
        this.session = null;
    }
}
