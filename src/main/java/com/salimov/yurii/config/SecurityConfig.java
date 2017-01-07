package com.salimov.yurii.config;

import com.salimov.yurii.entity.User;
import com.salimov.yurii.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Spring Security Configuration class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.service.data.interfaces.UserService
 * @see User
 * @see SecurityInitializer
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.salimov.yurii.service.data")
@PropertySource("classpath:security.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Request prefix for main administrators.
     */
    private static final String MAIN_ADMIN_REQUEST_URL = "/superman/**";

    /**
     * Request prefix for administrators.
     */
    @Value("${request.admin}")
    private String requestAdmin;

    /**
     * Request for authorization.
     */
    @Value("${request.login}")
    private String requestLogin;

    /**
     * Request access denied page.
     */
    @Value("${request.access-denied-page}")
    private String requestAccessDeniedPage;

    /**
     * Request default success.
     */
    @Value("${request.default-success}")
    private String requestDefaultSuccess;

    /**
     * It is always use default success request.
     */
    @Value("${request.default-success.always}")
    private boolean alwaysUseDefaultSuccess;

    /**
     * Parameter username title.
     */
    @Value("${parameter.username}")
    private String parameterUsername;

    /**
     * Parameter password title.
     */
    @Value("${parameter.password}")
    private String parameterPassword;

    /**
     * The object of service for working with registered users.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Setting user access rights to the pages of the site.
     * To get to these pages, you need to pass authorization.
     * Override this method to configure the HttpSecurity.
     * Typically subclasses should not invoke this method by calling
     * super as it may override their configuration.
     *
     * @param httpSecurity a object of the HttpSecurity class.
     * @throws Exception Exception maybe throws HttpSecurity class methods.
     */
    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .logout()
                .invalidateHttpSession(false)
                .and()
                .authorizeRequests()
                .antMatchers(MAIN_ADMIN_REQUEST_URL)
                .hasRole(
                        UserRole.SUPERMAN.name()
                )
                .antMatchers(this.requestAdmin)
                .hasAnyRole(
                        UserRole.ADMIN.name(),
                        UserRole.SUPERMAN.name()
                )
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage(this.requestLogin)
                .usernameParameter(this.parameterUsername)
                .passwordParameter(this.parameterPassword)
                .defaultSuccessUrl(
                        this.requestDefaultSuccess,
                        this.alwaysUseDefaultSuccess
                )
                .and().exceptionHandling()
                .accessDeniedPage(
                        this.requestAccessDeniedPage
                )
                .and()
                .csrf().disable();
    }

    /**
     * Setting up users with their roles. Users will be loaded from a database
     * using UserDetailsService implementation of interface methods.
     *
     * @param builder a object of the AuthenticationManagerBuilder class.
     * @throws Exception Exception maybe throws AuthenticationManagerBuilder
     * class methods.
     */
    @Override
    protected void configure(
            final AuthenticationManagerBuilder builder
    ) throws Exception {
        builder.userDetailsService(this.userDetailsService);
    }
}
