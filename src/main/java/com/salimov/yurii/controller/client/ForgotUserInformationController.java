package com.salimov.yurii.controller.client;

import com.salimov.yurii.config.DefaultConfig;
import com.salimov.yurii.entity.Company;
import com.salimov.yurii.entity.User;
import com.salimov.yurii.service.captcha.CaptchaService;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import com.salimov.yurii.service.data.interfaces.UserService;
import com.salimov.yurii.service.sender.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * The class implements a set of methods for forgot password.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see CaptchaService
 * @see CompanyService
 * @see UserService
 * @see SenderService
 */
@Controller
@ComponentScan(basePackages = "com.salimov.yurii.service")
@SuppressWarnings("SpringMVCViewInspection")
public class ForgotUserInformationController {

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link User} class.
     *
     * @see UserService
     */
    private final UserService userService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Company} class.
     *
     * @see CompanyService
     */
    private final CompanyService companyService;

    /**
     * The implementation of the interface describes
     * a set of methods for working with E-mail.
     *
     * @see SenderService
     */
    private final SenderService senderService;

    /**
     * The implementation of the {@link CaptchaService} interface.
     */
    private final CaptchaService captchaService;

    /**
     * Constructor.
     *
     * @param userService    a implementation
     *                       of the {@link UserService} interface.
     * @param companyService a implementation
     *                       of the {@link CompanyService} interface.
     * @param senderService  a implementation
     *                       of the {@link SenderService} interface.
     * @param captchaService a implementation
     *                       of the {@link CaptchaService} interface.
     * @see CompanyService
     * @see UserService
     * @see SenderService
     * @see CaptchaService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ForgotUserInformationController(
            final UserService userService,
            final CompanyService companyService,
            final SenderService senderService,
            final CaptchaService captchaService
    ) {
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
    public String getForgotPage() {
        return "login/forgot_page";
    }

    /**
     * Seeking a user by username
     * and sends information about him to e-mail.
     * Request mapping: /forgot
     * Method: POST
     *
     * @param username a user name for whom to remind information.
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
            } catch (IllegalArgumentException | NullPointerException ex) {
                ex.printStackTrace();
                try {
                    searchByEmailAndSend(username);
                    isForgot = true;
                } catch (IllegalArgumentException | NullPointerException ex2) {
                    ex2.printStackTrace();
                    searchInMainCompanyAndSend(username);
                    isForgot = true;
                }
            }
        }
        return createModelAndView(
                username,
                isCaptcha,
                isForgot
        );
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
     * Seeking a user by username
     * and sends information about him to e-mail.
     *
     * @param username a user name for whom to remind information.
     */
    private void searchByLoginAndSend(final String username) {
        final User user = this.userService.getByLogin(username);
        sendUserInformationToEmail(
                user,
                user.getEmail()
        );
    }

    /**
     * Seeking a user by email
     * and sends information about him to e-mail.
     *
     * @param email a user email for whom to remind information.
     */
    private void searchByEmailAndSend(final String email) {
        final User user = this.userService.getByEmail(email);
        sendUserInformationToEmail(
                user,
                user.getEmail()
        );
    }

    /**
     * Seeking a default user by equals email from main company email
     * and sends information about him to e-mail.
     *
     * @param email a user email for whom to remind information.
     */
    private void searchInMainCompanyAndSend(final String email) {
        final String mainEmail = this.companyService
                .getMainCompany()
                .getEmail();
        if (mainEmail.equalsIgnoreCase(email)) {
            sendUserInformationToEmail(
                    DefaultConfig.getDefaultAdmin(),
                    mainEmail
            );
        }
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
                getMessageText(user),
                recipientEmail,
                company.getSenderEmail(),
                company.getSenderPass()
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
                .append(user.toString())
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
            final boolean isCaptcha,
            final boolean isForgot
    ) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username", username);
        modelAndView.addObject("is_captcha", isCaptcha);
        modelAndView.addObject("is_forgot", isForgot);
        modelAndView.setViewName("login/forgot_page");
        return modelAndView;
    }
}
