package com.salimov.ecoteh.controller.admin;

import com.salimov.ecoteh.util.translator.Translator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockAdminController.getCompanyController;
import static org.junit.Assert.assertEquals;

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
                "admin/company/edit",
                new String[] { "main_company", "categories", "company", "favicon", "main" }
        );
    }

    @Test
    public void whenUpdateMainCompanyByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.updateMainCompany(
                TITLE, DOMAIN, TAGLINE, DESCRIPTION, INFORMATION, KEYWORDS,
                TIME, TIME, PHONE, PHONE, PHONE, EMAIL, EMAIL, PASSWORD,
                VKONTAKTE, FACEBOOK, TWITTER, SKYPE, ADDRESS, GOOGLE_MAPS,
                null);
        assertEquals(viewName, "redirect:/company/main");
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateMainCompanyByGetMethodThenThrowsIllegalMappingException() {
        controller.updateMainCompany();
    }

    @Test
    public void whenNewPartnerPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.newPartnerPage(),
                "admin/company/add",
                new String[] { "main_company", "categories" }
        );
    }

    @Test
    public void whenAddPartnerByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.addPartner(
                TITLE, DOMAIN, TAGLINE, DESCRIPTION, INFORMATION, KEYWORDS,
                PHONE, PHONE, PHONE, EMAIL,
                VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                ADDRESS, GOOGLE_MAPS, null, true
        );
        assertEquals(viewName, "redirect:/company/" + Translator.fromCyrillicToLatin(TITLE));
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddPartnerByGetMethodThenThrowsIllegalMappingException() {
        controller.addPartner();
    }

    @Test
    public void whenEditPartnerByUrlThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editPartnerByUrl(URL),
                "admin/company/edit",
                new String[] { "main_company", "categories", "company", "main" }
        );
    }

    @Test
    public void whenUpdatePartnerCompanyByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.updatePartnerCompany(
                URL, TITLE, DOMAIN, TAGLINE, DESCRIPTION, INFORMATION, KEYWORDS,
                PHONE, PHONE, PHONE, EMAIL,
                VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                ADDRESS, GOOGLE_MAPS, null, true
        );
        assertEquals(viewName, "redirect:/company/" + Translator.fromCyrillicToLatin(TITLE));
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdatePartnerCompanyByGetMethodThenThrowsIllegalMappingException() {
        controller.updatePartnerCompany();
    }

    @Test
    public void whenDeletePartnerByUrlThenReturnSomeModelAndView() {
        String viewName = controller.deletePartnerByUrl(URL);
        assertEquals(viewName, "redirect:/");
    }

    @Test
    public void whenDeleteAllPartnerByUrlThenReturnSomeModelAndView() {
        String viewName = controller.deleteAllPartners();
        assertEquals(viewName, "redirect:/");
    }
}
