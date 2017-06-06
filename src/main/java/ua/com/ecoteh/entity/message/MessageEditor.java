package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.ModelEditor;
import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserEditor;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class MessageEditor extends ModelEditor<Message, MessageEditor> {

    private final Message message;

    /**
     * The subject of a messageEntity.
     */
    private String subject;

    /**
     * The text of a messageEntity.
     */
    private String text;

    /**
     * The date of created messageEntity.
     */
    private Date date;

    /**
     * The messageEntity userEntity.
     */
    private User user;

    /**
     * Constructor.
     * @param message
     */
    MessageEditor(final Message message) {
        super(message);
        this.message = message;
    }

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

    public MessageEditor addUser(final User user) {
        this.user = user;
        return this;
    }

    public MessageEditor addSubject(final String subject) {
        this.subject = subject;
        return this;
    }

    public MessageEditor addText(final String text) {
        this.text = text;
        return this;
    }

    public MessageEditor addDate(final Date date) {
        this.date = date;
        return this;
    }

    private String getSubject() {
        return isNotEmpty(this.subject) ? this.subject : this.message.getSubject();
    }

    private String getText() {
        return isNotEmpty(this.text) ? this.text : this.message.getText();
    }

    private Date getDate() {
        return isNotNull(this.date) ? this.date : this.message.getDate();
    }

    private User getUser() {
        final UserEditor userRedactor = this.message.getUser().getEditor();
        userRedactor.copy(this.user);
        return userRedactor.update();
    }
}
