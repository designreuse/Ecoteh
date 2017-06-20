package ua.com.ecoteh.controller.authorization;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.ecoteh.exception.ExceptionMessage;

/**
 * Illegal access controller.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
public final class IllegalAccessController {

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
     * "You do not have sufficient permissions to access this page."
     * Request mapping: /illegal_access_exception
     * Method: POST
     *
     * @throws IllegalAccessException throws when the not authorized user
     *                                is tries to use private methods.
     */
    @RequestMapping(
            value = "/illegal_access_exception",
            method = RequestMethod.GET
    )
    public void getIllegalAccessException() throws IllegalAccessException {
        throw new IllegalAccessException(ExceptionMessage.ILLEGAL_ACCESS_MESSAGE);
    }
}
