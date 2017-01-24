package com.salimov.yurii.service.sender;

import com.salimov.yurii.entity.User;
import com.salimov.yurii.util.sender.SenderImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * The class implements a set of methods for working with E-mail.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see SenderService
 */
@Service
public final class SenderServiceImpl implements SenderService {

    /**
     * Creates and sends sender to recipient.
     *
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipientEmail  a recipient e-mail.
     * @param senderEmail     a sender e-mail.
     * @param senderEmailPass a sender e-mail password.
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final String recipientEmail,
            final String senderEmail,
            final String senderEmailPass
    ) {
        new SenderImpl(
                subject,
                text,
                recipientEmail,
                senderEmail,
                senderEmailPass
        ).send();
    }

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipientEmails a recipients e-mails.
     * @param senderEmail     a sender e-mail.
     * @param senderEmailPass a sender e-mail password.
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final String[] recipientEmails,
            final String senderEmail,
            final String senderEmailPass
    ) {
        if (recipientEmails != null) {
            for (String email : recipientEmails) {
                send(
                        subject,
                        text,
                        email,
                        senderEmail,
                        senderEmailPass
                );
            }
        }
    }

    /**
     * Creates and sends sender to recipient.
     *
     * @param subject        a sender subject.
     * @param text           a sender text.
     * @param recipientEmail a recipient e-mail.
     * @param sender         a sender user.
     * @see User
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final String recipientEmail,
            final User sender
    ) {
        if (sender != null) {
            send(
                    subject,
                    text,
                    recipientEmail,
                    sender.getEmail(),
                    sender.getPassword()
            );
        }
    }

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipientEmails a recipients e-mails.
     * @param sender          a sender user.
     * @see User
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final String[] recipientEmails,
            final User sender
    ) {
        if (sender != null) {
            send(
                    subject,
                    text,
                    recipientEmails,
                    sender.getEmail(),
                    sender.getPassword()
            );
        }
    }

    /**
     * Creates and sends sender to recipient.
     *
     * @param subject   a sender subject.
     * @param text      a sender text.
     * @param recipient a recipient user.
     * @param sender    a sender user.
     * @see User
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final User recipient,
            final User sender
    ) {
        if ((recipient != null) && recipient.isMailing()) {
            send(
                    subject,
                    text,
                    recipient.getEmail(),
                    sender
            );
        }
    }

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject    a sender subject.
     * @param text       a sender text.
     * @param recipients a recipients users.
     * @param sender     a sender user.
     * @see User
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final Collection<User> recipients,
            final User sender
    ) {
        if ((recipients != null) && !recipients.isEmpty()) {
            for (User recipient : recipients) {
                send(
                        subject,
                        text,
                        recipient,
                        sender
                );
            }
        }
    }

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipients      a recipients users.
     * @param senderEmail     a sender e-mail.
     * @param senderEmailPass a sender e-mail password.
     * @see User
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final Collection<User> recipients,
            final String senderEmail,
            final String senderEmailPass
    ) {
        if ((recipients != null) && !recipients.isEmpty()) {
            recipients.stream()
                    .filter(User::isMailing)
                    .forEach(
                            recipient -> send(
                                    subject,
                                    text,
                                    recipient.getEmail(),
                                    senderEmail,
                                    senderEmailPass
                            )
                    );
        }
    }
}
