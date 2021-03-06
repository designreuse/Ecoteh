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
import ua.com.ecoteh.config.DefaultAccounts;
import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.exception.ExceptionMessage;
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
 */
@Controller
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public final class ForgotUserInformationController {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(ForgotUserInformationController.class);

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link UserEntity} class.
     */
    private final UserService userService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link CompanyEntity} class.
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
     * @param fabric         the implementation of the {@link MainMVFabric} interface.
     * @param userService    the implementation of the {@link UserService} interface.
     * @param companyService the implementation of the {@link CompanyService} interface.
     * @param senderService  the implementation of the {@link SenderService} interface.
     * @param captchaService the implementation of the {@link CaptchaService} interface.
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
     * Get a page to forgot userEntity information.
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
     * Seeking a userEntity by name or E-mail.
     * and sends information about him to E-mail.
     * Request mapping: /forgot
     * Method: POST
     *
     * @param username the userEntity name or E-mail for whom to remind information.
     * @param request  the implementation of the interface to provide
     *                 request information for HTTP servlets.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/forgot",
            method = RequestMethod.POST
    )
    public ModelAndView forgotUserByUsername(
            @RequestParam(value = "username", defaultValue = "") final String username,
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
     * Seeking a userEntity by username and sends information about him to E-mail.
     *
     * @param username the userEntity name for whom to remind information.
     */
    private void searchByLoginAndSend(final String username) {
        final User user = this.userService.getByLogin(username);
        sendUserInformationToEmail(user, user.getContacts().getEmail());
    }

    /**
     * Seeking a userEntity by E-mail and sends information about him to E-mail.
     *
     * @param email the userEntity E-mail for whom to remind information.
     */
    private void searchByEmailAndSend(final String email) {
        final User user = this.userService.getByEmail(email);
        sendUserInformationToEmail(user, user.getContacts().getEmail());
    }

    /**
     * Seeking a userEntity by email and sends information about him to E-mail.
     *
     * @param phone the userEntity phone for whom to remind information.
     */
    private void searchByPhoneAndSend(final String phone) {
        final User user = this.userService.getByPhone(phone);
        sendUserInformationToEmail(user, user.getContacts().getEmail());
    }

    /**
     * Seeking a default userEntity by equals email from main company email
     * and sends information about him to E-mail.
     *
     * @param email the userEntity email for whom to remind information.
     * @throws IllegalArgumentException Throw exception when parameter login is blank.
     * @throws NullPointerException     Throws exception if userEntity is absent.
     */
    private void searchInMainCompanyAndSend(final String email)
            throws IllegalArgumentException, NullPointerException {
        if (isEmpty(email)) {
            throw new IllegalArgumentException(ExceptionMessage.BLANK_EMAIL_MESSAGE);
        }
        final Company mainCompany = this.companyService.getMainCompany();
        final String mainEmail = mainCompany.getContacts().getEmail();
        if (!mainEmail.equalsIgnoreCase(email)) {
            final String message = String.format(
                    ExceptionMessage.FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE,
                    CompanyEntity.class.getSimpleName(), email
            );
            throw new NullPointerException(message);
        }
        final User defaultAdmin = DefaultAccounts.getDefaultAdmin();
        sendUserInformationToEmail(defaultAdmin, mainEmail);
    }

    /**
     * Sends userEntity information to the incoming userEntity e-mail.
     *
     * @param user           the userEntity for whom to remind information.
     * @param recipientEmail the recipient email.
     */
    private void sendUserInformationToEmail(final User user, final String recipientEmail) {
        final Company mainCompany = this.companyService.getMainCompany();
        final String subject = "Напоминание пароля | " + mainCompany.getTitle();
        final String text = getMessageText(user);
        final String senderEmail = mainCompany.getSenderEmail();
        final String senderPass = mainCompany.getSenderPass();
        this.senderService.send(subject, text, recipientEmail, senderEmail, senderPass);
    }

    /**
     * Gets message to the userEntity.
     *
     * @param user the userEntity for whom to send message.
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
        sb.append("\n\nПосле прочтения этого письма, рекомендуется удалить его, ")
                .append("во избежании потери Ваших персональных даных.");
        return sb.toString();
    }

    /**
     * Creates ModelAndView object and returns it.
     *
     * @param username  the userEntity name for whom to remind information.
     * @param isCaptcha the result of the captcha verification.
     * @param isForgot  the result of the search.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView createModelAndView(final String username, final Boolean isCaptcha, final Boolean isForgot) {
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
