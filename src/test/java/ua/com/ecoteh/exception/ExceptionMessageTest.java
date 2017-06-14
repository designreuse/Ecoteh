package ua.com.ecoteh.exception;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ExceptionMessageTest {

    @Test
    public void IncomingObjectIsNullMessageIsNotEmpty() {
        final String message = ExceptionMessage.INCOMING_OBJECT_IS_NULL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void IncomingTypeIsNullMessageIsNotEmpty() {
        final String message = ExceptionMessage.INCOMING_TYPE_IS_NULL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void SavingObjectIsNullMessageIsNotEmpty() {
        final String message = ExceptionMessage.SAVING_OBJECT_IS_NULL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void BlankDomainMessageIsNotEmpty() {
        final String message = ExceptionMessage.BLANK_DOMAIN_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void BlankTitleMessageIsNotEmpty() {
        final String message = ExceptionMessage.BLANK_TITLE_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void BlankNameMessageIsNotEmpty() {
        final String message = ExceptionMessage.BLANK_NAME_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void BlankLoginMessageIsNotEmpty() {
        final String message = ExceptionMessage.BLANK_LOGIN_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void BlankEmailMessageIsNotEmpty() {
        final String message = ExceptionMessage.BLANK_EMAIL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void BlankPhoneMessageIsNotEmpty() {
        final String message = ExceptionMessage.BLANK_PHONE_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void BlankUrlMessageIsNotEmpty() {
        final String message = ExceptionMessage.BLANK_URL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void BlankNumberMessageIsNotEmpty() {
        final String message = ExceptionMessage.BLANK_NUMBER_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void BlankCategoryTitleMessageIsNotEmpty() {
        final String message = ExceptionMessage.BLANK_CATEGORY_TITLE_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void FindingByIdObjectIsNullMessageIsNotEmpty() {
        final String message = ExceptionMessage.FINDING_BY_ID_OBJECT_IS_NULL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void FindingByTitleObjectIsNullMessageIsNotEmpty() {
        final String message = ExceptionMessage.FINDING_BY_TITLE_OBJECT_IS_NULL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void FindingByEmailObjectIsNullMessageIsNotEmpty() {
        final String message = ExceptionMessage.FINDING_BY_EMAIL_OBJECT_IS_NULL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void FindingByUrlObjectIsNullMessageIsNotEmpty() {
        final String message = ExceptionMessage.FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void FindingByLoginObjectIsNullMessageIsNotEmpty() {
        final String message = ExceptionMessage.FINDING_BY_LOGIN_OBJECT_IS_NULL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void FindingByNumberObjectIsNullMessageIsNotEmpty() {
        final String message = ExceptionMessage.FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void FindingByNameObjectIsNullMessageIsNotEmpty() {
        final String message = ExceptionMessage.FINDING_BY_NAME_OBJECT_IS_NULL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void FindingByPhoneObjectIsNullMessageIsNotEmpty() {
        final String message = ExceptionMessage.FINDING_BY_PHONE_OBJECT_IS_NULL_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void ForbiddenStaticFileMessageIsNotEmpty() {
        final String message = ExceptionMessage.FORBIDDEN_STATIC_FILE_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void MultipartFileIsEmptyMessageIsNotEmpty() {
        final String message = ExceptionMessage.MULTIPART_FILE_IS_EMPTY_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void MaxFileSizeMessageIsNotEmpty() {
        final String message = ExceptionMessage.MULTIPART_FILE_IS_EMPTY_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void IllegalAccesMessageIsNotEmpty() {
        final String message = ExceptionMessage.ILLEGAL_ACCESS_MESSAGE;
        assertFalse(message.isEmpty());
    }

    @Test
    public void UnsupportedOperationMessageIsNotEmpty() {
        final String message = ExceptionMessage.UNSUPPORTED_OPERATION_MESSAGE;
        assertFalse(message.isEmpty());
    }
}
