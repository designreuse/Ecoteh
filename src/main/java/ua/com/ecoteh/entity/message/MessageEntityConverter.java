package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MessageEntityConverter extends ModelEntityConverter<Message> {

    private final MessageEntity entity;

    MessageEntityConverter(final MessageEntity entity) {
        this.entity = entity;
    }

    @Override
    protected MessageBuilder prepareBuilder() {
        final MessageBuilder builder = new MessageBuilder();
        if (isNotNull(this.entity)) {
            builder.addId(this.entity.getId())
                    .addValidated(this.entity.isValidated())
                    .addSubject(this.entity.getSubject())
                    .addText(this.entity.getText())
                    .addDate(this.entity.getDate())
                    .addUser(this.entity.getUserEntity().convert());
        }
        return builder;
    }
}
