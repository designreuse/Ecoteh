package ua.com.ecoteh.entity.message;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelEntityTest;
import ua.com.ecoteh.entity.user.UserEntity;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getUserEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MessageEntityTest extends ModelEntityTest {

    private MessageEntity message;

    @Before
    public void beforeTests() {
        this.message = new MessageEntity();
        this.message.setId(ID);
        this.message.setValidated(VALIDATION);
        this.message.setSubject(TITLE);
        this.message.setText(TEXT);
        this.message.setDate(DATE);
        this.message.setUserEntity(getUserEntity());
    }

    @Test
    @Override
    public void toStringTest() {
        final String toStringTest = "MessageEntity{" +
                "ModelEntity{" +
                "id=" + this.message.getId() +
                ", validated=" + this.message.isValidated() +
                '}' +
                ", userEntity=" + this.message.getUserEntity() +
                ", subject='" + this.message.getSubject() + '\'' +
                ", text='" + this.message.getText() + '\'' +
                ", date=" + this.message.getDate() +
                '}';
        assertEquals(this.message.toString(), toStringTest);
    }

    @Test
    public void whenSetUserEntityThenGetIt() {
        final UserEntity user = getUserEntity();
        this.message.setUserEntity(user);
        assertEquals(this.message.getUserEntity(), user);
    }

    @Test
    public void whenSetSubjectThenGetIt() {
        String subject;
        for (int i = 0; i < 5; i++) {
            subject = TITLE + i;
            this.message.setSubject(subject);
            assertEquals(this.message.getSubject(), subject);
        }
    }

    @Test
    public void whenSetTextThenGetIt() {
        String text;
        for (int i = 0; i < 5; i++) {
            text = TEXT + i;
            this.message.setText(text);
            assertEquals(this.message.getText(), text);
        }
    }

    @Test
    public void whenSetDateThenGetIt() {
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.message.setDate(date);
            assertEquals(this.message.getDate(), date);
        }
    }

    @Test
    public void whenConvertThenReturnValidModel() {
        final Message message = this.message.convert();
        assertEquals(message.getId(), this.message.getId());
        assertEquals(message.isValidated(), this.message.isValidated());
        assertEquals(message.getSubject(), this.message.getSubject());
        assertEquals(message.getText(), this.message.getText());
        assertEquals(message.getDate(), this.message.getDate());
        assertNotNull(message.getUser());
    }

    @Override
    protected MessageEntity getInstance() {
        return this.message;
    }
}