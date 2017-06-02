package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MessageConverter extends ModelConverter<Message, MessageEntity> {

    MessageConverter(final Message model) {
        super(model);
    }

    @Override
    public MessageEntity convert() {
        final MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(this.model.getId());
        messageEntity.setValidated(this.model.isValidated());
        messageEntity.setSubject(this.model.getSubject());
        messageEntity.setText(this.model.getText());
        messageEntity.setDate(this.model.getDate());
        messageEntity.setUserEntity(this.model.getUser().convert());
        return null;
    }
}
