package com.salimov.yurii.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.yurii.mocks.controller.MockAdminController.getCompanyController;

public class CompanyControllerTest {

    private static CompanyController controller;

    @BeforeClass
    public static void setUp() {
        controller = getCompanyController();
    }

    @Test
    public void whenEditMainCompanyThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editMainCompany(),
                "admin/company/edit_page",
                new String[]{"main_company", "categories", "company", "slides", "main"}
        );
    }

    @Test
    public void whenUpdateMainCompanyByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.updateMainCompany(
                        TITLE, DOMAIN, TAGLINE, DESCRIPTION, INFORMATION, KEYWORDS,
                        TIME, TIME, PHONE, PHONE, PHONE, EMAIL, EMAIL, PASSWORD,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE, ADDRESS, GOOGLE_MAPS,
                        null, new ModelAndView()
                ),
                "redirect:/company/main",
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateMainCompanyByGetMethodThenThrowsIllegalMappingException() {
        controller.updateMainCompany();
    }

    @Test
    public void whenNewPartnerPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.newPartnerPage(),
                "admin/company/new_page",
                new String[]{"main_company", "categories"}
        );
    }

    @Test
    public void whenAddPartnerByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.addPartner(
                        TITLE, DOMAIN, TAGLINE, DESCRIPTION, INFORMATION, KEYWORDS,
                        PHONE, PHONE, PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        ADDRESS, GOOGLE_MAPS, null, true,
                        new ModelAndView()
                ),
                "redirect:/company/" + URL,
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddPartnerByGetMethodThenThrowsIllegalMappingException() {
        controller.addPartner();
    }

    @Test
    public void whenEditPartnerByUrlThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editPartnerByUrl(URL),
                "admin/company/edit_page",
                new String[]{"main_company", "categories", "company", "main"}
        );
    }

    @Test
    public void whenUpdatePartnerCompanyByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.updatePartnerCompany(
                        URL, TITLE, DOMAIN, TAGLINE, DESCRIPTION, INFORMATION, KEYWORDS,
                        PHONE, PHONE, PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        ADDRESS, GOOGLE_MAPS, null, true,
                        new ModelAndView()
                ),
                "redirect:/company/" + URL,
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdatePartnerCompanyByGetMethodThenThrowsIllegalMappingException() {
        controller.updatePartnerCompany();
    }

    @Test
    public void whenDeletePartnerByUrlThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deletePartnerByUrl(URL, new ModelAndView()),
                "redirect:/",
                null
        );
    }

    @Test
    public void whenDeleteAllPartnerByUrlThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteAllPartners(new ModelAndView()),
                "redirect:/",
                null
        );
    }
}
