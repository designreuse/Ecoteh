package ua.com.ecoteh.service.sender;

import ua.com.ecoteh.entity.User;
import ua.com.ecoteh.util.sender.SenderImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods for working with E-mail.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
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
                subject, text, recipientEmail,
                senderEmail, senderEmailPass
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
        if (isNotNull(recipientEmails)) {
            for (String email : recipientEmails) {
                send(subject, text, email, senderEmail, senderEmailPass);
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
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final String recipientEmail,
            final User sender
    ) {
        if (isNotNull(sender)) {
            send(
                    subject, text, recipientEmail,
                    sender.getContacts().getEmail(), sender.getPassword()
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
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final String[] recipientEmails,
            final User sender
    ) {
        if (isNotNull(sender)) {
            send(
                    subject, text, recipientEmails,
                    sender.getContacts().getEmail(), sender.getPassword()
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
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final User recipient,
            final User sender
    ) {
        if (validRecipient(recipient)) {
            send(subject, text, recipient.getContacts().getEmail(), sender);
        }
    }

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject    a sender subject.
     * @param text       a sender text.
     * @param recipients a recipients users.
     * @param sender     a sender user.
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final Collection<User> recipients,
            final User sender
    ) {
        if (isNotEmpty(recipients)) {
            for (User recipient : recipients) {
                send(subject, text, recipient, sender);
            }
        }
    }

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipients      a recipients users.
     * @param senderEmail     a sender E-mail.
     * @param senderEmailPass a sender E-mail password.
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final Collection<User> recipients,
            final String senderEmail,
            final String senderEmailPass
    ) {
        if (isNotEmpty(recipients)) {
            recipients.stream()
                    .filter(SenderServiceImpl::validRecipient)
                    .forEach(
                            recipient -> send(
                                    subject, text, recipient.getContacts().getEmail(),
                                    senderEmail, senderEmailPass
                            )
                    );
        }
    }

    /**
     * Validated a recipient.
     *
     * @param recipient a recipient to validate.
     * @return tru if recipient is valid, false otherwise.
     */
    private static boolean validRecipient(final User recipient) {
        return isNotNull(recipient) && recipient.isMailing();
    }
}
