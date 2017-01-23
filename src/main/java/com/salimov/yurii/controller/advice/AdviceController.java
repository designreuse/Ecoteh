package com.salimov.yurii.controller.advice;

import com.salimov.yurii.exception.DisableException;
import com.salimov.yurii.exception.DuplicateException;
import com.salimov.yurii.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.yurii.service.fabrica.interfaces.ClientMVFabric;
import com.salimov.yurii.service.fabrica.interfaces.MainMVFabric;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Class intercepts exceptions that are not specified in the controller.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see DisableException
 * @see DuplicateException
 */
@ControllerAdvice
public class AdviceController {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER
            = Logger.getLogger(AdviceController.class);

    /**
     * No handler fount exception message.
     */
    private static final String NO_HANDLER_FOUND_MESSAGE
            = "Ошибка 404. Не найдено!";

    /**
     * Null pointer exception message.
     */
    private final static String NULL_POINTER_MESSAGE
            = "Объект не найден!";

    /**
     * Illegal argument exception message.
     */
    private final static String ILLEGAL_ARGUMENT_MESSAGE
            = "Ошибка аргументов!";

    /**
     * Http request method not supported exception message.
     */
    private final static String HTTP_REQUEST_METHOD_NOT_SUPPORTED_MESSAGE
            = "Ошибка в запросе!";

    /**
     * Illegal access exception message.
     */
    private final static String ILLEGAL_ACCESS_MESSAGE
            = "У Вас нет прав для доступа " +
            "к этой странице.";

    /**
     * Illegal mapping exception message.
     */
    private final static String ILLEGAL_MAPPING_MESSAGE
            = "Запрещенный запрос!";

    /**
     * Duplicate exception message;
     */
    private final static String DUPLICATE_MESSAGE
            = "Объект уже существует!";

    /**
     * Disable exception message.
     */
    private final static String DISABLE_MESSAGE
            = "Сайт отключен " +
            "по техническим причинам!";

    /**
     * All other exception message.
     */
    private final static String OTHER_MESSAGE
            = "Временные неполадки с сервером... " +
            "Приносим свои извинения!";

    /**
     * Unknown exception message.
     */
    private final static String UNKNOWN_MESSAGE
            = "Неизвестная ошибка...";

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     *
     * @see MainMVFabric
     */
    private final MainMVFabric fabric;

    /**
     * Constructor.
     *
     * @param fabric a implementation
     *               of the {@link ClientMVFabric} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AdviceController(final ClientMVFabric fabric) {
        this.fabric = new CacheMVFabricImpl(fabric);
    }

    /**
     * Intercepts and handles NoHandlerFoundException.
     *
     * @param exception an intercepted exception.
     * @param request   to provide requested information for HTTP servlets.
     * @return The ModelAndView object with information about exception.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView Exception(
            final NoHandlerFoundException exception,
            final HttpServletRequest request
    ) {
        return handleException(
                exception,
                request,
                NO_HANDLER_FOUND_MESSAGE
        );
    }

    /**
     * Intercepts and handles NullPointerException.
     *
     * @param exception an intercepted exception.
     * @param request   to provide requested information for HTTP servlets.
     * @return The ModelAndView object with information about exception.
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView nullPointerException(
            final NullPointerException exception,
            final HttpServletRequest request
    ) {
        return handleException(
                exception,
                request,
                NULL_POINTER_MESSAGE
        );
    }

    /**
     * Intercepts and handles IllegalArgumentException.
     *
     * @param exception an intercepted exception.
     * @param request   to provide requested information for HTTP servlets.
     * @return The ModelAndView object with information about exception.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView illegalArgumentException(
            final IllegalArgumentException exception,
            final HttpServletRequest request
    ) {
        return handleException(
                exception,
                request,
                ILLEGAL_ARGUMENT_MESSAGE
        );
    }

    /**
     * Intercepts and handles HttpRequestMethodNotSupportedException.
     *
     * @param exception an intercepted exception.
     * @param request   to provide requested information for HTTP servlets.
     * @return The ModelAndView object with information about exception.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView httpRequestMethodNotSupportedException(
            final HttpRequestMethodNotSupportedException exception,
            final HttpServletRequest request
    ) {
        return handleException(
                exception,
                request,
                HTTP_REQUEST_METHOD_NOT_SUPPORTED_MESSAGE
        );
    }

    /**
     * Intercepts and handles IllegalAccessException.
     *
     * @param exception an intercepted exception.
     * @param request   to provide requested information for HTTP servlets.
     * @return The ModelAndView object with information about exception.
     */
    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView illegalAccessException(
            final IllegalAccessException exception,
            final HttpServletRequest request
    ) {
        return handleException(
                exception,
                request,
                ILLEGAL_ACCESS_MESSAGE
        );
    }

    /**
     * Intercepts and handles IllegalMappingException.
     *
     * @param exception an intercepted exception.
     * @param request   to provide requested information for HTTP servlets.
     * @return The ModelAndView object with information about exception.
     */
    @ExceptionHandler(IllegalMappingException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ModelAndView illegalMappingException(
            final IllegalMappingException exception,
            final HttpServletRequest request
    ) {
        return handleException(
                exception,
                request,
                ILLEGAL_MAPPING_MESSAGE
        );
    }

    /**
     * Intercepts and handles DuplicateException.
     *
     * @param exception an intercepted exception.
     * @param request   to provide requested information for HTTP servlets.
     * @return The ModelAndView object with information about exception.
     * @see DuplicateException
     */
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView duplicateException(
            final DuplicateException exception,
            final HttpServletRequest request
    ) {
        return handleException(
                exception,
                request,
                DUPLICATE_MESSAGE
        );
    }

    /**
     * Intercepts and handles DisableException.
     *
     * @param exception an intercepted exception.
     * @param request   to provide requested information for HTTP servlets.
     * @return The ModelAndView object with information about exception.
     * @see DisableException
     */
    @ExceptionHandler(DisableException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView disableException(
            final DisableException exception,
            final HttpServletRequest request
    ) {
        return handleException(
                exception,
                request,
                DISABLE_MESSAGE
        );
    }

    /**
     * Intercepts and handles all other Exception.
     *
     * @param exception an intercepted exception.
     * @param request   to provide requested information for HTTP servlets.
     * @return The ModelAndView object with information about exception.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView otherException(
            final Exception exception,
            final HttpServletRequest request
    ) {
        return handleException(
                exception,
                request,
                OTHER_MESSAGE
        );
    }

    /**
     * Handles all other Exception.
     *
     * @param exception an intercepted exception.
     * @param request   to provide requested information for HTTP servlets.
     * @param text      a text of the exception.
     * @return The ModelAndView object with information about exception.
     */
    private ModelAndView handleException(
            final Exception exception,
            final HttpServletRequest request,
            final String text
    ) {
        logRequest(request);
        logException(exception);
        return prepareModelAndView(
                isNotBlank(text) ? text : UNKNOWN_MESSAGE,
                exception.getClass().getSimpleName()
                        + " : "
                        + exception.getMessage()
                        + "\n"
                        + Arrays.toString(exception.getStackTrace())
        );
    }

    /**
     * Creates and return ModelAndView object.
     *
     * @param textError    a text of the exception.
     * @param messageError a sender of the exception.
     * @return The ModelAndView object with information about exception.
     */
    @SuppressWarnings("SpringMVCViewInspection")
    private ModelAndView prepareModelAndView(
            final String textError,
            final String messageError
    ) {
        ModelAndView modelAndView;
        try {
            modelAndView = this.fabric.getDefaultModelAndView();
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView = new ModelAndView();
        }
        modelAndView.addObject("text", textError);
        modelAndView.addObject("message", messageError);
        modelAndView.setViewName("error/error_page");
        return modelAndView;
    }

    /**
     * Request logging.
     *
     * @param request to provide requested information for HTTP servlets.
     */
    private static void logRequest(final HttpServletRequest request) {
        if (request != null) {
            LOGGER.error(
                    request.getRemoteAddr()
                            + " : "
                            + request.getRequestURL()
            );
        }
    }

    /**
     * Error logging.
     *
     * @param exception an intercepted exception.
     */
    private static void logException(final Exception exception) {
        LOGGER.error(
                exception.getMessage(),
                exception
        );
        exception.printStackTrace();
    }
}
