package ua.com.ecoteh.util.sender;

import org.junit.Before;
import org.junit.Test;

public class EmailSenderTest {

    private Sender sender;

    @Before
    public void beforeTests() {
        this.sender = new EmailSender(
                "subject",
                "text",
                "recipientEmail",
                "senderEmail",
                "senderEmailPass"
        );
    }

    @Test
    public void whenSendThenDoIt() {
        this.sender.send();
    }

    @Test
    public void whenRunThenDoIt() {
        final Thread thread = new Thread(this.sender);
        thread.start();
    }
}
