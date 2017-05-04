package ua.com.ecoteh.controller.advice;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.ecoteh.exception.DuplicateException;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.controller.MockAdviceController.getAdviceController;

public class AdviceControllerTest {

    private static AdviceController controller;

    @BeforeClass
    public static void setUp() {
        controller = getAdviceController();
    }

    @Test
    public void whenNoHandlerFoundExceptionThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.noHandlerFoundException(
                        new NoHandlerFoundException(
                                "httpMethod", "requestURL",
                                mock(HttpHeaders.class)
                        ),
                        mock(HttpServletRequest.class)
                ),
                "error/error",
                new String[] { "main_company", "categories", "status", "message" }
        );
    }

    @Test
    public void whenNullPointerExceptionThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.nullPointerException(
                        new NullPointerException(),
                        mock(HttpServletRequest.class)
                ),
                "error/error",
                new String[] { "main_company", "categories", "status", "message" }
        );
    }

    @Test
    public void whenIllegalArgumentExceptionThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.illegalArgumentException(
                        new IllegalArgumentException(),
                        mock(HttpServletRequest.class)
                ),
                "error/error",
                new String[] { "main_company", "categories", "status", "message" }
        );
    }

    @Test
    public void whenHttpRequestMethodNotSupportedExceptionThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.httpRequestMethodNotSupportedException(
                        new HttpRequestMethodNotSupportedException(ANY_STRING),
                        mock(HttpServletRequest.class)
                ),
                "error/error",
                new String[] { "main_company", "categories", "status", "message" }
        );
    }

    @Test
    public void whenIllegalAccessExceptionThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.illegalAccessException(
                        new IllegalAccessException(),
                        mock(HttpServletRequest.class)
                ),
                "error/error",
                new String[] { "main_company", "categories", "status", "message" }
        );
    }

    @Test
    public void whenIllegalMappingExceptionThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.illegalMappingException(
                        new IllegalMappingException(ANY_STRING),
                        mock(HttpServletRequest.class)
                ),
                "error/error",
                new String[] { "main_company", "categories", "status", "message" }
        );
    }

    @Test
    public void whenDuplicateExceptionThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.duplicateException(
                        new DuplicateException(),
                        mock(HttpServletRequest.class)
                ),
                "error/error",
                new String[] { "main_company", "categories", "status", "message" }
        );
    }

    @Test
    public void whenOtherExceptionThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.otherException(
                        new Exception(),
                        mock(HttpServletRequest.class)
                ),
                "error/error",
                new String[] { "main_company", "categories", "status", "message" }
        );
    }
}
