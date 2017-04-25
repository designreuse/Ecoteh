package ua.com.ecoteh.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * The class is needed in order to ensure that security settings
 * are included in the main application context
 * (them saw and inhaled the Root Application Context).
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
