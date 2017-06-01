package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.ModelBuilder;
import ua.com.ecoteh.entity.user.User;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class MessageBuilder extends ModelBuilder<Message, MessageBuilder> {

    /**
     * The messageEntity userEntity.
     */
    private User user;

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

    MessageBuilder() {
    }

    @Override
    public Message build() {
        return null;
    }

    public MessageBuilder addUser(User user) {
        this.user = user;
        return this;
    }

    public MessageBuilder addSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MessageBuilder addText(String text) {
        this.text = text;
        return this;
    }

    public MessageBuilder addDate(Date date) {
        this.date = date;
        return this;
    }

    private User getUser() {
        return isNotNull(this.user) ? this.user : User.getBuilder().build();
    }

    private String getSubject() {
        return isNotEmpty(this.subject) ? this.subject : "";
    }

    private String getText() {
        return isNotEmpty(this.text) ? this.text : "";
    }

    private Date getDate() {
        return isNotNull(this.date) ? this.date : new Date();
    }
}
