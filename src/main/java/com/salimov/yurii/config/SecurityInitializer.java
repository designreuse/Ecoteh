package com.salimov.yurii.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * The class is needed in order to ensure that security settings
 * are included in the main application context
 * (them saw and inhaled the Root Application Context).
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see SecurityConfig
 * @see RootConfig
 * @see WebConfig
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
