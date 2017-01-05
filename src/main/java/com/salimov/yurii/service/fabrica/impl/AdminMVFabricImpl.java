package com.salimov.yurii.service.fabrica.impl;

import com.salimov.yurii.entity.User;
import com.salimov.yurii.service.data.interfaces.*;
import com.salimov.yurii.service.fabrica.interfaces.AdminMVFabric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.salimov.yurii.config.DefaultConfig;
import com.salimov.yurii.exception.DisableException;

/**
 * The class implements a set of standard methods for creates
 * and returns the main modelAndViews for admins.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see AdminMVFabric
 * @see MainMVFabricImpl
 */
@Service
@ComponentScan(basePackages = "com.salimov.yurii.service.data")
public final class AdminMVFabricImpl
        extends MainMVFabricImpl
        implements AdminMVFabric {

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link User}.
     */
    private final UserService userService;

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
     * @param sectionService  a implementation
     *                        of the {@link SectionService} interface.
     * @param userService     a implementation
     *                        of the {@link UserService} interface.
     * @param responseService a implementation
     *                        of the {@link ResponseService} interface.
     * @see ArticleService
     * @see CategoryService
     * @see CompanyService
     * @see SectionService
     * @see UserService
     * @see ResponseService
     */
    @Autowired
    public AdminMVFabricImpl(
            final ArticleService articleService,
            final CategoryService categoryService,
            final CompanyService companyService,
            final SectionService sectionService,
            final UserService userService,
            final ResponseService responseService
    ) {
        super(
                articleService,
                categoryService,
                companyService,
                sectionService,
                userService,
                responseService
        );
        this.userService = userService;
    }

    /**
     * Creates and returns default modelAndView for admins.
     *
     * @return The ready object of class ModelAndView.
     * @throws DisableException Throws exception if site is disabled.
     * @see DisableException
     */
    @Override
    public ModelAndView getDefaultModelAndView() throws DisableException {
        if (!DefaultConfig.isAdminEnabled()) {
            throw new DisableException("Site is disabled!");
        }
        final ModelAndView modelAndView = super.getDefaultModelAndView();
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Validates output objects.
     *
     * @return Returns {@code true} if need to return valid objects,
     * {@code false} otherwise.
     */
    @Override
    protected boolean isValidContent() {
        return false;
    }

    /**
     * Return fabric name which working.
     *
     * @return The fabric class name.
     */
    @Override
    public String getFabricName() {
        return AdminMVFabric.class.getSimpleName();
    }

    /**
     * Adds authenticated user to the modelAndView.
     *
     * @param modelAndView a model and view to update.
     * @see User
     */
    @Override
    public void addAuthUser(ModelAndView modelAndView) {
        if (modelAndView != null) {
            modelAndView.addObject(
                    "authorized_user",
                    this.userService.getAuthenticatedUser()
            );
        }
    }
}
