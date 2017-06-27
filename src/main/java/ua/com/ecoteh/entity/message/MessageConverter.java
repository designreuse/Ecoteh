package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * The class implements a set of methods
 * for converting messages to message entities.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Message
 * @see MessageEntity
 */
final class MessageConverter extends ModelConverter<Message, MessageEntity> {

    /**
     * The message for converting to message entity.
     */
    private final Message message;

    /**
     * Constructor.
     *
     * @param message the message for converting to message entity.
     */
    MessageConverter(final Message message) {
        super(message);
        this.message = message;
    }

    /**
     * Converts the message and returns a new message entity.
     *
     * @return The converted message entity (newer null).
     */
    @Override
    public MessageEntity convert() {
        final MessageEntity entity = new MessageEntity();
        entity.setId(this.message.getId());
        entity.setValidated(this.message.isValidated());
        entity.setSubject(this.message.getSubject());
        entity.setText(this.message.getText());
        entity.setDate(this.message.getDate());
        entity.setUserEntity(this.message.getUser().convert());
        return entity;
    }
}
