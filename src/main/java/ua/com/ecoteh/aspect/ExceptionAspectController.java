package ua.com.ecoteh.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Class implements cross-cutting concerns - is the catching of exceptions.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Aspect
@Component
public class ExceptionAspectController {

    /**
     * The object for logging information.
     */
    private final static Logger LOGGER = Logger.getLogger(ExceptionAspectController.class);

    /**
     * The method will be called in the event of exceptional situations,
     * logged information about the exception.
     *
     * @param exception the object of the Exception class or any subclasses.
     */
    @AfterThrowing(
            pointcut = "execution(* ua.com.ecoteh..controller..*(..))",
            throwing = "exception"
    )
    public void afterThrowingAdvice(final Exception exception) {
        LOGGER.error("EXCEPTION IN METHOD -> " + exception.getClass());
    }
}
