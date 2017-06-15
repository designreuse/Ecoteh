package ua.com.ecoteh.controller.advice;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.ecoteh.exception.DuplicateException;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

public class AdviceControllerTest {

    private final static String VIEW_NAME = "error/error";
    private final static String[] KEYS = { "main_company", "categories", "favicon", "status", "message" };

    private static AdviceController controller;
    private static HttpServletRequest httpServletRequest;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        controller = new AdviceController(fabric);
        httpServletRequest = mock(HttpServletRequest.class);
    }

    @Test
    public void whenNoHandlerFoundExceptionThenReturnSomeModelAndView() {
        final HttpHeaders headers = mock(HttpHeaders.class);
        final NoHandlerFoundException exception = new NoHandlerFoundException("httpMethod", "requestURL", headers);
        final ModelAndView modelAndView = controller.noHandlerFoundException(exception, httpServletRequest);
        checkModelAndView(modelAndView, VIEW_NAME, KEYS);
    }

    @Test
    public void whenNullPointerExceptionThenReturnSomeModelAndView() {
        final NullPointerException exception = new NullPointerException();
        final ModelAndView modelAndView = controller.nullPointerException(exception, httpServletRequest);
        checkModelAndView(modelAndView, VIEW_NAME, KEYS);
    }

    @Test
    public void whenIllegalArgumentExceptionThenReturnSomeModelAndView() {
        final IllegalArgumentException exception = new IllegalArgumentException();
        final ModelAndView modelAndView = controller.illegalArgumentException(exception, httpServletRequest);
        checkModelAndView(modelAndView, VIEW_NAME, KEYS);
    }

    @Test
    public void whenHttpRequestMethodNotSupportedExceptionThenReturnSomeModelAndView() {
        final HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException(ANY_STRING);
        final ModelAndView modelAndView = controller
                .httpRequestMethodNotSupportedException(exception, httpServletRequest);
        checkModelAndView(modelAndView, VIEW_NAME, KEYS);
    }

    @Test
    public void whenIllegalAccessExceptionThenReturnSomeModelAndView() {
        final IllegalAccessException exception = new IllegalAccessException();
        final ModelAndView modelAndView = controller.illegalAccessException(exception, httpServletRequest);
        checkModelAndView(modelAndView, VIEW_NAME, KEYS);
    }

    @Test
    public void whenIllegalMappingExceptionThenReturnSomeModelAndView() {
        final IllegalMappingException exception = new IllegalMappingException(ANY_STRING);
        final ModelAndView modelAndView = controller.illegalMappingException(exception, httpServletRequest);
        checkModelAndView(modelAndView, VIEW_NAME, KEYS);
    }

    @Test
    public void whenDuplicateExceptionThenReturnSomeModelAndView() {
        final DuplicateException exception = new DuplicateException();
        final ModelAndView modelAndView = controller.duplicateException(exception, httpServletRequest);
        checkModelAndView(modelAndView, VIEW_NAME, KEYS);
    }

    @Test
    public void whenOtherExceptionThenReturnSomeModelAndView() {
        final Exception exception = new Exception();
        final ModelAndView modelAndView = controller.otherException(exception, httpServletRequest);
        checkModelAndView(modelAndView, VIEW_NAME, KEYS);
    }
}
