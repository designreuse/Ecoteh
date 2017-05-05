package ua.com.ecoteh.controller.advice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.ecoteh.exception.DuplicateException;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import javax.servlet.http.HttpServletRequest;

/**
 * Class intercepts exceptions that are not specified in the controller.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@ControllerAdvice
@ComponentScan(basePackages = "ua.com.ecoteh.service.fabrica")
@SuppressWarnings("SpringMVCViewInspection")
public class AdviceController {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(AdviceController.class);

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * Constructor.
     *
     * @param fabric the implementation of the {@link MainMVFabric} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AdviceController(@Qualifier("cacheMVFabricImpl") final MainMVFabric fabric) {
        this.fabric = fabric;
    }

    /**
     * Intercepts and handles NoHandlerFoundException.
     *
     * @param ex      the intercepted exception.
     * @param request to provide a requested information for HTTP servlets.
     * @return The ModelAndView object with an information about exception.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView noHandlerFoundException(
            final NoHandlerFoundException ex,
            final HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.NOT_FOUND);
    }

    /**
     * Intercepts and handles NullPointerException.
     *
     * @param ex      the intercepted exception.
     * @param request to provide a requested information for HTTP servlets.
     * @return The ModelAndView object with an information about exception.
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView nullPointerException(
            final NullPointerException ex,
            final HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }

    /**
     * Intercepts and handles IllegalArgumentException.
     *
     * @param ex      the intercepted exception.
     * @param request to provide a requested information for HTTP servlets.
     * @return The ModelAndView object with an information about exception.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ModelAndView illegalArgumentException(
            final IllegalArgumentException ex,
            final HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Intercepts and handles HttpRequestMethodNotSupportedException.
     *
     * @param ex      the intercepted exception.
     * @param request to provide a requested information for HTTP servlets.
     * @return The ModelAndView object with an information about exception.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ModelAndView httpRequestMethodNotSupportedException(
            final HttpRequestMethodNotSupportedException ex,
            final HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Intercepts and handles IllegalAccessException.
     *
     * @param ex      the intercepted exception.
     * @param request to provide a requested information for HTTP servlets.
     * @return The ModelAndView object with an information about exception.
     */
    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView illegalAccessException(
            final IllegalAccessException ex,
            final HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.FORBIDDEN);
    }

    /**
     * Intercepts and handles IllegalMappingException.
     *
     * @param ex      the intercepted exception.
     * @param request to provide a requested information for HTTP servlets.
     * @return The ModelAndView object with an information about exception.
     */
    @ExceptionHandler(IllegalMappingException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ModelAndView illegalMappingException(
            final IllegalMappingException ex,
            final HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Intercepts and handles DuplicateException.
     *
     * @param ex      the intercepted exception.
     * @param request to provide a requested information for HTTP servlets.
     * @return The ModelAndView object with an information about exception.
     */
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView duplicateException(
            final DuplicateException ex,
            final HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.CONFLICT);
    }

    /**
     * Intercepts and handles all other Exception.
     *
     * @param ex      the intercepted exception.
     * @param request to provide a requested information for HTTP servlets.
     * @return The ModelAndView object with an information about exception.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView otherException(
            final Exception ex,
            final HttpServletRequest request
    ) {
        return handleException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles all other Exception.
     *
     * @param ex      the intercepted exception.
     * @param request to provide a requested information for HTTP servlets.
     * @param status  the HTTP status.
     * @return The ModelAndView object with an information about exception.
     */
    private ModelAndView handleException(
            final Exception ex,
            final HttpServletRequest request,
            final HttpStatus status
    ) {
        logRequest(request);
        logException(ex);
        return prepareModelAndView(status, createExceptionMessage(ex));
    }

    /**
     * Creates and return ModelAndView object.
     *
     * @param status  the HTTP status.
     * @param message the message of a exception.
     * @return The ModelAndView object with an information about exception.
     */
    private ModelAndView prepareModelAndView(
            final HttpStatus status,
            final String message
    ) {
        ModelAndView modelAndView;
        try {
            modelAndView = this.fabric.getDefaultModelAndView();
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView = new ModelAndView();
        }
        modelAndView.addObject("status", status.value());
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }

    /**
     * Request logging.
     *
     * @param request to provide a requested information for HTTP servlets.
     */
    private static void logRequest(final HttpServletRequest request) {
        LOGGER.error(createRequestMessage(request));
    }

    /**
     * Error logging.
     *
     * @param ex the intercepted exception.
     */
    private static void logException(final Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

    /**
     * Creates and returns a exception message to log.
     * Returns exception class name and message.
     *
     * @param ex the intercepted exception.
     * @return The exception message.
     */
    private static String createExceptionMessage(final Exception ex) {
        return ex.getClass().getSimpleName() + " : " + ex.getMessage();
    }

    /**
     * Creates and returns a request message to log.
     *
     * @param request to provide a requested information for HTTP servlets.
     * @return The request message.
     */
    private static String createRequestMessage(final HttpServletRequest request) {
        return request.getRemoteAddr() + " : " + request.getRequestURL();
    }
}
