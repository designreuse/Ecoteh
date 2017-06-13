package ua.com.ecoteh.entity.message;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelBuilderTest;
import ua.com.ecoteh.entity.user.User;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.TEXT;
import static ua.com.ecoteh.mocks.MockConstants.TITLE;
import static ua.com.ecoteh.mocks.enity.MockModels.getUser;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MessageBuilderTest extends ModelBuilderTest<Message> {

    private MessageBuilder builder;

    @Before
    public void beforeTests() {
        this.builder = new MessageBuilder();
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Message message = this.builder.build();
        assertNotNull(message.getUser());
        assertNotNull(message.getSubject());
        assertNotNull(message.getText());
        assertNotNull(message.getDate());
    }

    @Test
    public void whenAddSubjectThenBuildWithIt() {
        Message message;
        String subject;
        for (int i = 0; i < 5; i++) {
            subject = TITLE + i;
            this.builder.addSubject(subject);
            message = this.builder.build();
            assertEquals(message.getSubject(), subject);
        }
    }

    @Test
    public void whenAddNullSubjectThenBuildWithEmptyIt() {
        this.builder.addSubject(null);
        final Message message = this.builder.build();
        assertTrue(message.getSubject().isEmpty());
    }

    @Test
    public void whenAddTextThenBuildWithIt() {
        Message message;
        String text;
        for (int i = 0; i < 5; i++) {
            text = TEXT + i;
            this.builder.addText(text);
            message = this.builder.build();
            assertEquals(message.getText(), text);
        }
    }

    @Test
    public void whenAddNullTextThenBuildWithEmptyIt() {
        this.builder.addText(null);
        final Message message = this.builder.build();
        assertTrue(message.getText().isEmpty());
    }

    @Test
    public void whenAddDateThenBuildWithIt() {
        Message message;
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.builder.addDate(date);
            message = this.builder.build();
            assertEquals(message.getDate(), date);
        }
    }

    @Test
    public void whenAddNullDateThenBuildWithNewIt() {
        this.builder.addDate(null);
        final Message message = this.builder.build();
        assertNotNull(message.getDate());
    }

    @Test
    public void whenAddUserThenBuildWithIt() {
        final User user = getUser();
        this.builder.addUser(user);
        final Message message = builder.build();
        assertEquals(message.getUser(), user);
    }

    @Test
    public void whenAddNullUserThenBuildWithNewIt() {
        this.builder.addUser(null);
        final Message message = this.builder.build();
        assertNotNull(message.getUser());
    }

    @Override
    protected MessageBuilder getBuilder() {
        return this.builder;
    }
}