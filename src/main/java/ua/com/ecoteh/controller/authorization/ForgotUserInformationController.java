package ua.com.ecoteh.controller.authorization;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.config.DefaultConfig;
import ua.com.ecoteh.entity.Company;
import ua.com.ecoteh.entity.User;
import ua.com.ecoteh.service.captcha.CaptchaService;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.UserService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.sender.SenderService;

import javax.servlet.http.HttpServletRequest;

import static ua.com.ecoteh.util.validator.ObjectValidator.isEmpty;

/**
 * The class implements a set of methods for forgot password.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public class ForgotUserInformationController {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(ForgotUserInformationController.class);

    /**
     *
     */
    private final static String BLANK_EMAIL_MESSAGE = "Incoming E-mail is blank!";

    /**
     *
     */
    private final static String FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE =
            "Can`t find object of the %s class by incoming E-mail %s!";

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link User} class.
     */
    private final UserService userService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Company} class.
     */
    private final CompanyService companyService;

    /**
     * The implementation of the interface describes
     * a set of methods for working with E-mail.
     */
    private final SenderService senderService;

    /**
     * The implementation of the {@link CaptchaService} interface.
     */
    private final CaptchaService captchaService;

    /**
     * Constructor.
     *
     * @param fabric         a implementation of the {@link MainMVFabric} interface.
     * @param userService    a implementation of the {@link UserService} interface.
     * @param companyService a implementation of the {@link CompanyService} interface.
     * @param senderService  a implementation of the {@link SenderService} interface.
     * @param captchaService a implementation of the {@link CaptchaService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ForgotUserInformationController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final UserService userService,
            final CompanyService companyService,
            final SenderService senderService,
            final CaptchaService captchaService
    ) {
        this.fabric = fabric;
        this.userService = userService;
        this.companyService = companyService;
        this.senderService = senderService;
        this.captchaService = captchaService;
    }

    /**
     * Get page to forgot user information.
     * Request mapping: /forgot_password
     * Method: GET
     *
     * @return The page path.
     */
    @RequestMapping(
            value = "/forgot_password",
            method = RequestMethod.GET
    )
    public ModelAndView getForgotPage() {
        return createModelAndView(null, null, null);
    }

    /**
     * Seeking a user by user name or E-mail.
     * and sends information about him to e-mail.
     * Request mapping: /forgot
     * Method: POST
     *
     * @param username a user name or E-mail for whom to remind information.
     * @param request  a implementation of the interface to provide
     *                 request information for HTTP servlets.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/forgot",
            method = RequestMethod.POST
    )
    public ModelAndView forgotUserByUsername(
            @RequestParam(value = "username") final String username,
            final HttpServletRequest request
    ) {
        boolean isForgot = false;
        final boolean isCaptcha = this.captchaService.isVerify(request);
        if (isCaptcha) {
            try {
                searchByLoginAndSend(username);
                isForgot = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                LOGGER.error(ex.getMessage(), ex);
                try {
                    searchByEmailAndSend(username);
                    isForgot = true;
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                    LOGGER.error(ex2.getMessage(), ex2);
                    try {
                        searchByPhoneAndSend(username);
                        isForgot = true;
                    } catch (Exception ex4) {
                        ex4.printStackTrace();
                        LOGGER.error(ex4.getMessage(), ex4);
                        try {
                            searchInMainCompanyAndSend(username);
                            isForgot = true;
                        } catch (Exception ex5) {
                            ex5.printStackTrace();
                            LOGGER.error(ex5.getMessage(), ex5);
                            isForgot = false;
                        }
                    }
                }
            }
        }
        return createModelAndView(username, isCaptcha, isForgot);
    }

    /**
     * Redirects to "/login".
     * Request mapping: /forgot
     * Method: GET
     *
     * @return The redirect string.
     */
    @RequestMapping(
            value = "/forgot",
            method = RequestMethod.GET
    )
    public String forgotUserByUsername() {
        return "redirect:/login";
    }

    /**
     * Seeking a user by username and sends information about him to e-mail.
     *
     * @param username a user name for whom to remind information.
     */
    private void searchByLoginAndSend(final String username) {
        final User user = this.userService.getByLogin(username);
        sendUserInformationToEmail(user, user.getContacts().getEmail());
    }

    /**
     * Seeking a user by E-mail and sends information about him to e-mail.
     *
     * @param email a user E-mail for whom to remind information.
     */
    private void searchByEmailAndSend(final String email) {
        final User user = this.userService.getByEmail(email);
        sendUserInformationToEmail(user, user.getContacts().getEmail());
    }

    /**
     * Seeking a user by email and sends information about him to e-mail.
     *
     * @param phone a user phone for whom to remind information.
     */
    private void searchByPhoneAndSend(final String phone) {
        final User user = this.userService.getByPhone(phone);
        sendUserInformationToEmail(user, user.getContacts().getEmail());
    }

    /**
     * Seeking a default user by equals email from main company email
     * and sends information about him to e-mail.
     *
     * @param email a user email for whom to remind information.
     * @throws IllegalArgumentException Throw exception when parameter login is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     */
    private void searchInMainCompanyAndSend(final String email)
            throws IllegalArgumentException, NullPointerException {
        if (isEmpty(email)) {
            throw new IllegalArgumentException(BLANK_EMAIL_MESSAGE);
        }
        final String mainEmail = this.companyService.getMainCompany().getContacts().getEmail();
        if (!mainEmail.equalsIgnoreCase(email)) {
            throw new NullPointerException(
                    String.format(
                            FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE,
                            Company.class.getSimpleName(), email
                    )
            );
        }
        sendUserInformationToEmail(DefaultConfig.getDefaultAdmin(), mainEmail);
    }

    /**
     * Sends user information to the user e-mail.
     *
     * @param user           a user for whom to remind information.
     * @param recipientEmail a recipient email.
     */
    private void sendUserInformationToEmail(
            final User user,
            final String recipientEmail
    ) {
        final Company company = this.companyService.getMainCompany();
        this.senderService.send(
                "Напоминание пароля | " + company.getTitle(),
                getMessageText(user), recipientEmail,
                company.getSenderEmail(), company.getSenderPass()
        );
    }

    /**
     * Gets message to the user.
     *
     * @param user a user for whom to send message.
     * @return The message.
     */
    private static String getMessageText(final User user) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Информация о пользователе\n\n")
                .append(user.getName())
                .append("\nLogin: ").append(user.getLogin())
                .append("\nPassword: ").append(user.getPassword());
        if (user.isLocked()) {
            sb.append("\n\nПОЛЬЗОВАТЕЛЬ ЗАБЛОКИРОВАН");
        }
        sb.append("\n\nПосле прочтения этого письма, ")
                .append("рекомендуется удалить его, ")
                .append("во избежании потери Ваших ")
                .append("персональных даных.");
        return sb.toString();
    }

    /**
     * Creates ModelAndView object and returns it.
     *
     * @param username  a user name for whom to remind information.
     * @param isCaptcha a result of the captcha verification.
     * @param isForgot  a result of the search.
     * @return The ready object of class ModelAndView.
     */
    private ModelAndView createModelAndView(
            final String username,
            final Boolean isCaptcha,
            final Boolean isForgot
    ) {
        ModelAndView modelAndView;
        try {
            modelAndView = this.fabric.getDefaultModelAndView();
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView = new ModelAndView();
        }
        modelAndView.addObject("username", username);
        modelAndView.addObject("is_captcha", isCaptcha);
        modelAndView.addObject("is_forgot", isForgot);
        modelAndView.setViewName("login/forgot_form");
        return modelAndView;
    }
}
