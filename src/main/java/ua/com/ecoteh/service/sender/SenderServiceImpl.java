package ua.com.ecoteh.service.sender;

import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.util.sender.EmailSender;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods for working with E-mail.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
public final class SenderServiceImpl implements SenderService {

    /**
     * Creates and sends sender to recipient.
     *
     * @param subject         the sender subject.
     * @param text            the sender text.
     * @param recipientEmail  the recipient e-mail.
     * @param senderEmail     the sender e-mail.
     * @param senderEmailPass the sender e-mail password.
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final String recipientEmail,
            final String senderEmail,
            final String senderEmailPass
    ) {
        new EmailSender(
                subject, text, recipientEmail,
                senderEmail, senderEmailPass
        ).send();
    }

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject         the sender subject.
     * @param text            the sender text.
     * @param recipientEmails the recipients e-mails.
     * @param senderEmail     the sender e-mail.
     * @param senderEmailPass the sender e-mail password.
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
     * @param subject        the sender subject.
     * @param text           the sender text.
     * @param recipientEmail the recipient e-mail.
     * @param sender         the sender user.
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final String recipientEmail,
            final UserEntity sender
    ) {
        if (isNotNull(sender)) {
            send(
                    subject, text, recipientEmail,
                    sender.getContactsEntity().getEmail(), sender.getPassword()
            );
        }
    }

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject         the sender subject.
     * @param text            the sender text.
     * @param recipientEmails the recipients e-mails.
     * @param sender          the sender user.
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final String[] recipientEmails,
            final UserEntity sender
    ) {
        if (isNotNull(sender)) {
            send(
                    subject, text, recipientEmails,
                    sender.getContactsEntity().getEmail(), sender.getPassword()
            );
        }
    }

    /**
     * Creates and sends sender to recipient.
     *
     * @param subject   the sender subject.
     * @param text      the sender text.
     * @param recipient the recipient user.
     * @param sender    the sender user.
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final UserEntity recipient,
            final UserEntity sender
    ) {
        if (isMailingRecipient(recipient)) {
            send(subject, text, recipient.getContactsEntity().getEmail(), sender);
        }
    }

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject    the sender subject.
     * @param text       the sender text.
     * @param recipients the recipients users.
     * @param sender     the sender user.
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final Collection<UserEntity> recipients,
            final UserEntity sender
    ) {
        if (isNotEmpty(recipients)) {
            for (UserEntity recipient : recipients) {
                send(subject, text, recipient, sender);
            }
        }
    }

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject         the sender subject.
     * @param text            the sender text.
     * @param recipients      the recipients users.
     * @param senderEmail     the sender E-mail.
     * @param senderEmailPass the sender E-mail password.
     */
    @Override
    public void send(
            final String subject,
            final String text,
            final Collection<UserEntity> recipients,
            final String senderEmail,
            final String senderEmailPass
    ) {
        if (isNotEmpty(recipients)) {
            recipients.stream()
                    .filter(this::isMailingRecipient)
                    .forEach(
                            recipient -> send(
                                    subject, text, recipient.getContactsEntity().getEmail(),
                                    senderEmail, senderEmailPass
                            )
                    );
        }
    }

    /**
     * Validated a incoming recipient.
     * Recipient is valid if it is not null and it is mailing.
     * <pre>
     *     isMailingRecipient(null) = false
     *
     *     UserEntity recipient = new UserEntity();
     *     recipient.setMailing(false);
     *     isMailingRecipient(model) = false
     *
     *     model.setMailing(true);
     *     isMailingRecipient(model) = true
     * </pre>
     *
     * @param recipient the recipient to check.
     * @return true if the recipient is not null and it is mailing.
     */
    private boolean isMailingRecipient(final UserEntity recipient) {
        return isNotNull(recipient) && recipient.isMailing();
    }
}
