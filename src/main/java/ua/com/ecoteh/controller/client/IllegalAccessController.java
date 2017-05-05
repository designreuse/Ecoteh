package ua.com.ecoteh.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Illegal access controller.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
public class IllegalAccessController {

    /**
     * The message that a user does not have sufficient permissions to access this page.
     */
    private final static String ILLEGAL_ACCESS_MESSAGE =
            "You do not have sufficient permissions to access this page.";

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
        throw new IllegalAccessException(ILLEGAL_ACCESS_MESSAGE);
    }
}
