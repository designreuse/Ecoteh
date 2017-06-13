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
        final MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(this.message.getId());
        messageEntity.setValidated(this.message.isValidated());
        messageEntity.setSubject(this.message.getSubject());
        messageEntity.setText(this.message.getText());
        messageEntity.setDate(this.message.getDate());
        messageEntity.setUserEntity(this.message.getUser().convert());
        return messageEntity;
    }
}
