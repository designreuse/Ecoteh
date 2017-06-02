package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MessageEntityConverter extends ModelEntityConverter<MessageEntity, Message> {

    MessageEntityConverter(final MessageEntity entity) {
        super(entity);
    }

    @Override
    protected MessageBuilder prepareBuilder() {
        final MessageBuilder builder = new MessageBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addSubject(this.entity.getSubject())
                .addText(this.entity.getText())
                .addDate(this.entity.getDate())
                .addUser(this.entity.getUserEntity().convert());
        return builder;
    }
}
