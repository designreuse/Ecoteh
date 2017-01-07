package com.salimov.yurii.controller.other;

import com.salimov.yurii.entity.Company;
import com.salimov.yurii.entity.User;
import com.salimov.yurii.service.captcha.CaptchaService;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import com.salimov.yurii.service.data.interfaces.UserService;
import com.salimov.yurii.service.sender.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * The class implements a set of methods for forgot password.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see CaptchaService
 * @see CompanyService
 * @see UserService
 * @see SenderService
 */
@Controller
@SuppressWarnings("SpringMVCViewInspection")
public class ForgotPasswordController {

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
     * The implementation of the interface describes a set of methods
     * for working with E-mail.
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
    public ForgotPasswordController(
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
     * Sends password to the user e-mail.
     *
     * @param username     a user name for whom to remind password.
     * @param request      a implementation of the interface to provide
     *                     request information for HTTP servlets.
     * @param modelAndView a model and view to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(value = "/forgot_password", method = RequestMethod.POST)
    public ModelAndView forgotPassword(
            @RequestParam(value = "username") final String username,
            final HttpServletRequest request,
            final ModelAndView modelAndView
    ) {
        boolean isForgot = false;
        final boolean isCaptcha = this.captchaService.isVerify(request);
        if (isCaptcha) {
            try {
                sendForgotPasswordToEmail(
                        this.userService.getByLogin(username)
                );
                isForgot = true;
            } catch (IllegalArgumentException | NullPointerException ex) {
                ex.printStackTrace();
                try {
                    sendForgotPasswordToEmail(
                            this.userService.getByEmail(username)
                    );
                    isForgot = true;
                } catch (IllegalArgumentException | NullPointerException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        modelAndView.addObject("username", username);
        modelAndView.addObject("is_captcha", isCaptcha);
        modelAndView.addObject("is_forgot", isForgot);
        modelAndView.setViewName("login/login_page");
        return modelAndView;
    }

    /**
     * Sends password to the user e-mail.
     *
     * @param user a user for whom to remind password.
     */
    private void sendForgotPasswordToEmail(final User user) {
        System.out.println("USER = " + user);
        final Company company = this.companyService.getMainCompany();
        this.senderService.send(
                "Напоминание пароля | " + company.getTitle(),
                getMessageText(user),
                user.getEmail(),
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
        sb.append("\n\nПосле прочтения этого, ")
                .append("рекомендуется удалить его, ")
                .append("во избежании потери Ваших ")
                .append("персональных даных.");
        return sb.toString();
    }
}
