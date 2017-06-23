package ua.com.ecoteh.exception;

/**
 * Exception messages.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface ExceptionMessage {

    /**
     * The message that a incoming object is null.
     */
    String INCOMING_OBJECT_IS_NULL_MESSAGE = "Incoming object of the %s class is null!";

    /**
     * The message that a incoming type is null.
     */
    String INCOMING_TYPE_IS_NULL_MESSAGE = "Incoming %s type is null!";

    /**
     * The message that a saving object is null.
     */
    String SAVING_OBJECT_IS_NULL_MESSAGE = "Saving object of the %s class is null!";

    /**
     * The message that a incoming company domain is null or empty.
     */
    String BLANK_DOMAIN_MESSAGE = "Incoming %s domain is null or empty!";

    /**
     * The message that a incoming title is null or empty.
     */
    String BLANK_TITLE_MESSAGE = "Incoming %s title is null or empty!";

    /**
     * The message that a incoming user name is null or empty.
     */
    String BLANK_NAME_MESSAGE = "Incoming %s name is null or empty!";

    /**
     * The message that a incoming user login is null or empty.
     */
    String BLANK_LOGIN_MESSAGE = "Incoming %s login is null or empty!";

    /**
     * The message that a incoming user E-mail is null or empty.
     */
    String BLANK_EMAIL_MESSAGE = "Incoming %s E-mail is null or empty!";

    /**
     * The message that a incoming user phone is null or empty.
     */
    String BLANK_PHONE_MESSAGE = "Incoming %s phone is null or empty!";

    /**
     * The message that a incoming URL is null or empty.
     */
    String BLANK_URL_MESSAGE = "Incoming %s URL is null or empty!";

    /**
     * The message that a incoming number is null or empty.
     */
    String BLANK_NUMBER_MESSAGE = "Incoming %s number is null or empty!";

    /**
     * The message that a incoming category title is null or empty.
     */
    String BLANK_CATEGORY_TITLE_MESSAGE =
            "Incoming category title is null or empty!";

    /**
     * The message that a service cannot find object by incoming id.
     */
    String FINDING_BY_ID_OBJECT_IS_NULL_MESSAGE =
            "Can`t find a object of the %s class by the incoming id \"%d\"!";

    /**
     * The message that a service cannot find content by incoming title.
     */
    String FINDING_BY_TITLE_OBJECT_IS_NULL_MESSAGE =
            "Can`t find a object of the %s class by the incoming title \"%s\"!";

    /**
     * The message that a service cannot find content by incoming URL.
     */
    String FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE =
            "Can`t find a object of the %s class by the incoming URL \"%s\"!";

    /**
     * The message that a service cannot find article by incoming number.
     */
    String FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE =
            "Can`t find a object of the %s class by the incoming number \"%s\"!";

    /**
     * The message that a service cannot find user by incoming name.
     */
    String FINDING_BY_NAME_OBJECT_IS_NULL_MESSAGE =
            "Can`t find a object of the %s class by the incoming name \"%s\"!";

    /**
     * The message that a service cannot find user by incoming login.
     */
    String FINDING_BY_LOGIN_OBJECT_IS_NULL_MESSAGE =
            "Can`t find a object of the %s class by the incoming login \"%s\"!";
    /**
     * The message that a service cannot find user by incoming E-mail.
     */
    String FINDING_BY_EMAIL_OBJECT_IS_NULL_MESSAGE =
            "Can`t find a object of the %s class by the incoming E-mail \"%s\"!";

    /**
     * The message that a service cannot find user by incoming phone.
     */
    String FINDING_BY_PHONE_OBJECT_IS_NULL_MESSAGE =
            "Can`t find a object of the %s class by the incoming phone \"%s\"!";

    /**
     * The message that a static files are forbidden to remove.
     */
    String FORBIDDEN_STATIC_FILE_MESSAGE = "Static files are forbidden to remove!";

    /**
     * The message that a incoming multipart file is null or empty.
     */
    String MULTIPART_FILE_IS_EMPTY_MESSAGE =
            "Incoming multipart file is null or empty!";

    /**
     * The message that a incoming multipart file has size greater
     * than max size.
     */
    String MAX_FILE_SIZE_MESSAGE =
            "Maximum file size must be no larger than %d bytes. " +
                    "Size of the incoming file is %d bytes!";

    /**
     * The message that a get method is not supported.
     */
    String GET_METHOD_NOT_SUPPORTED_MESSAGE = "GET method in \"%s\" is not supported!";

    /**
     * The message that a user does not have sufficient permissions to access this page.
     */
    String ILLEGAL_ACCESS_MESSAGE =
            "You do not have sufficient permissions to access this page.";

    /**
     * The message that a operation (method) is not supported by some class.
     */
    String UNSUPPORTED_OPERATION_MESSAGE = "Not supported by %s class!";
}
