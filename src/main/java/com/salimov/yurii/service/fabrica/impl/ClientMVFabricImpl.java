package com.salimov.yurii.service.fabrica.impl;

import com.salimov.yurii.config.DefaultConfig;
import com.salimov.yurii.exception.DisableException;
import com.salimov.yurii.service.data.interfaces.*;
import com.salimov.yurii.service.fabrica.interfaces.ClientMVFabric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of standard methods for creates
 * and returns the main modelAndViews for clients.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see ClientMVFabric
 * @see MainMVFabricImpl
 */
@Service
@ComponentScan(basePackages = "com.salimov.yurii.service.data")
public final class ClientMVFabricImpl
        extends MainMVFabricImpl
        implements ClientMVFabric {

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param articleService  a implementation
     *                        of the {@link ArticleService} interface.
     * @param categoryService a implementation
     *                        of the {@link CategoryService} interface.
     * @param companyService  a implementation
     *                        of the {@link CompanyService} interface.
     * @param userService     a implementation
     *                        of the {@link UserService} interface.
     * @param responseService a implementation
     *                        of the {@link ResponseService} interface.
     * @see ArticleService
     * @see CategoryService
     * @see CompanyService
     * @see UserService
     * @see ResponseService
     */
    @Autowired
    public ClientMVFabricImpl(
            final ArticleService articleService,
            final CategoryService categoryService,
            final CompanyService companyService,
            final UserService userService,
            final ResponseService responseService
    ) {
        super(
                articleService, categoryService,
                companyService, userService,
                responseService
        );
    }

    /**
     * Creates and returns default modelAndView for clients.
     *
     * @return The ready object of class ModelAndView.
     * @throws DisableException Throws exception if site is disabled.
     * @see DisableException
     */
    @Override
    public ModelAndView getDefaultModelAndView() throws DisableException {
        if (!DefaultConfig.isClientEnabled()) {
            throw new DisableException("Site is disabled!");
        }
        return super.getDefaultModelAndView();
    }

    /**
     * Validates output objects.
     *
     * @return Returns {@code true} if need to return valid objects,
     * {@code false} otherwise.
     */
    @Override
    protected boolean isValidContent() {
        return true;
    }

    /**
     * Return fabric name which working.
     *
     * @return The fabric class name.
     */
    @Override
    public String getFabricName() {
        return ClientMVFabric.class.getSimpleName();
    }

    /**
     * Adds authenticated user to the modelAndView.
     *
     * @param modelAndView a model and view to update.
     */
    @Override
    public void addAuthUser(final ModelAndView modelAndView) {
    }
}
