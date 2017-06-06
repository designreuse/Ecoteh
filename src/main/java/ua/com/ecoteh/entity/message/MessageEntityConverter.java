package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * The class implements a set of methods
 * for converting message entities to messages.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see MessageEntity
 * @see Message
 */
final class MessageEntityConverter extends ModelEntityConverter<MessageEntity, Message> {

    /**
     * The message entity for converting to message.
     */
    private final MessageEntity entity;

    /**
     * Constructor.
     *
     * @param entity the message entity for converting to message.
     */
    MessageEntityConverter(final MessageEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Prepares and returns a message builder for creating
     * a new converted message.
     *
     * @return the prepared message builder.
     */
    @Override
    protected MessageBuilder prepareBuilder() {
        final MessageBuilder builder = Message.getBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addSubject(this.entity.getSubject())
                .addText(this.entity.getText())
                .addDate(this.entity.getDate())
                .addUser(this.entity.getUserEntity().convert());
        return builder;
    }
}
