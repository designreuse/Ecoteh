package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.ModelEditor;
import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserEditor;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for editing an objects of the {@link Message} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Message
 */
public final class MessageEditor extends ModelEditor<Message, MessageEditor> {

    /**
     * The message to edit.
     */
    private final Message message;

    /**
     * The new message user.
     */
    private User user;

    /**
     * The new subject of the message.
     */
    private String subject;

    /**
     * The new text of the message.
     */
    private String text;

    /**
     * The new date the message.
     */
    private Date date;

    /**
     * Constructor.
     *
     * @param message the message to edit.
     */
    MessageEditor(final Message message) {
        super(message);
        this.message = message;
    }

    /**
     * Updates and returns a new message.
     *
     * @return The updated message.
     */
    @Override
    public Message update() {
        final MessageBuilder builder = Message.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addSubject(getSubject())
                .addText(getText())
                .addDate(getDate())
                .addUser(getUser());
        return builder.build();
    }

    /**
     * Copies the incoming message.
     *
     * @param message the message to copy.
     * @return the message editor.
     */
    @Override
    public MessageEditor copy(final Message message) {
        if (isNotNull(message)) {
            super.copy(message)
                    .addSubject(message.getSubject())
                    .addText(message.getText())
                    .addDate(message.getDate())
                    .addUser(message.getUser());
        }
        return this;
    }

    /**
     * Adds a new user to the message.
     *
     * @param user the new user to the message.
     * @return the message editor.
     */
    public MessageEditor addUser(final User user) {
        this.user = user;
        return this;
    }

    /**
     * Adds a new subject to the message.
     *
     * @param subject the new subject to the message.
     * @return the message editor.
     */
    public MessageEditor addSubject(final String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Adds a new text to the message.
     *
     * @param text the new text to the message.
     * @return the message editor.
     */
    public MessageEditor addText(final String text) {
        this.text = text;
        return this;
    }

    /**
     * Adds a new date to the message.
     *
     * @param date the new date to the message.
     * @return the message editor.
     */
    public MessageEditor addDate(final Date date) {
        this.date = date;
        return this;
    }

    /**
     * Returns a new subject of the message.
     * Returns the message subject if the subject is null.
     *
     * @return The subject (newer null).
     */
    private String getSubject() {
        return isNotEmpty(this.subject) ? this.subject : this.message.getSubject();
    }

    /**
     * Returns a new text of the message.
     * Returns the message text if the text is null.
     *
     * @return The text (newer null).
     */
    private String getText() {
        return isNotEmpty(this.text) ? this.text : this.message.getText();
    }

    /**
     * Returns a new date of the message.
     * Returns the message date if the date is null.
     *
     * @return The date (newer null).
     */
    private Date getDate() {
        return isNotNull(this.date) ? this.date : this.message.getDate();
    }

    /**
     * Returns a new user of the message.
     * Returns the message user if the user is null.
     *
     * @return The user (newer null).
     * @see UserEditor
     */
    private User getUser() {
        final UserEditor userEditor = this.message.getUser().getEditor();
        userEditor.copy(this.user);
        return userEditor.update();
    }
}
