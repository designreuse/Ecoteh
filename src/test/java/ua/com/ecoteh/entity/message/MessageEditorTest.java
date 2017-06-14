package ua.com.ecoteh.entity.message;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelEditorTest;
import ua.com.ecoteh.entity.user.User;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.TEXT;
import static ua.com.ecoteh.mocks.MockConstants.TITLE;
import static ua.com.ecoteh.mocks.enity.MockModels.getMessage;
import static ua.com.ecoteh.mocks.enity.MockModels.getUser;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MessageEditorTest extends ModelEditorTest<Message> {

    private MessageEditor editor;
    private Message message;

    @Before
    public void beforeTests() {
        this.message = getMessage();
        this.editor = new MessageEditor(this.message);
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Message updatedMessage = this.editor.update();
        assertEquals(updatedMessage.getUser(), this.message.getUser());
        assertEquals(updatedMessage.getSubject(), this.message.getSubject());
        assertEquals(updatedMessage.getText(), this.message.getText());
        assertEquals(updatedMessage.getDate(), this.message.getDate());
    }

    @Test
    public void whenAddSubjectThenBuildWithIt() {
        Message message;
        String subject;
        for (int i = 0; i < 5; i++) {
            subject = TITLE + i;
            this.editor.addSubject(subject);
            message = this.editor.update();
            assertEquals(message.getSubject(), subject);
        }
    }

    @Test
    public void whenAddNullSubjectThenBuildWithEmptyIt() {
        this.editor.addSubject(null);
        final Message message = this.editor.update();
        assertEquals(message.getSubject(), this.message.getSubject());
    }

    @Test
    public void whenAddTextThenBuildWithIt() {
        Message message;
        String text;
        for (int i = 0; i < 5; i++) {
            text = TEXT + i;
            this.editor.addText(text);
            message = this.editor.update();
            assertEquals(message.getText(), text);
        }
    }

    @Test
    public void whenAddNullTextThenBuildWithEmptyIt() {
        this.editor.addText(null);
        final Message message = this.editor.update();
        assertEquals(message.getText(), this.message.getText());
    }

    @Test
    public void whenAddDateThenBuildWithIt() {
        Message message;
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.editor.addDate(date);
            message = this.editor.update();
            assertEquals(message.getDate(), date);
        }
    }

    @Test
    public void whenAddNullDateThenBuildWithNewIt() {
        this.editor.addDate(null);
        final Message message = this.editor.update();
        assertEquals(message.getDate(), this.message.getDate());
    }

    @Test
    public void whenAddUserThenBuildWithIt() {
        final User user = getUser();
        this.editor.addUser(user);
        final Message message = editor.update();
        assertEquals(message.getUser(), user);
    }

    @Test
    public void whenAddNullUserThenBuildWithNewIt() {
        this.editor.addUser(null);
        final Message message = this.editor.update();
        assertEquals(message.getUser(), this.message.getUser());
    }

    @Override
    protected MessageEditor getEditor() {
        return this.editor;
    }

    @Override
    protected Message getModel() {
        return this.message;
    }
}