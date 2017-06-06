package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MessageConverter extends ModelConverter<Message, MessageEntity> {

    private final Message message;

    /**
     * Constructor.
     * @param message
     */
    MessageConverter(final Message message) {
        super(message);
        this.message = message;
    }

    @Override
    public MessageEntity convert() {
        final MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(this.message.getId());
        messageEntity.setValidated(this.message.isValidated());
        messageEntity.setSubject(this.message.getSubject());
        messageEntity.setText(this.message.getText());
        messageEntity.setDate(this.message.getDate());
        messageEntity.setUserEntity(this.message.getUser().convert());
        return null;
    }
}
