package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MessageEntityConverter extends ModelEntityConverter<MessageEntity, Message> {

    private final MessageEntity entity;

    /**
     * Constructor.
     * @param entity
     */
    MessageEntityConverter(final MessageEntity entity) {
        super(entity);
        this.entity = entity;
    }

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
