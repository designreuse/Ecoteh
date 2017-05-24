package ua.com.ecoteh.controller.admin;

import ua.com.ecoteh.util.translator.Translator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import ua.com.ecoteh.mocks.MockConstants;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.controller.MockAdminController.getCompanyController;
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
                "company/edit",
                new String[] { "main_company", "categories", "company", "favicon", "main" }
        );
    }

    @Test
    public void whenUpdateMainCompanyByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.updateMainCompany(
                MockConstants.TITLE, MockConstants.DOMAIN, MockConstants.TAGLINE, MockConstants.DESCRIPTION, MockConstants.INFORMATION, MockConstants.KEYWORDS,
                MockConstants.TIME, MockConstants.TIME, MockConstants.PHONE, MockConstants.PHONE, MockConstants.PHONE, MockConstants.EMAIL, MockConstants.EMAIL, MockConstants.PASSWORD,
                MockConstants.VKONTAKTE, MockConstants.FACEBOOK, MockConstants.TWITTER, MockConstants.SKYPE, MockConstants.ADDRESS, MockConstants.GOOGLE_MAPS,
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
                "company/add",
                new String[] { "main_company", "categories" }
        );
    }

    @Test
    public void whenAddPartnerByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.addPartner(
                MockConstants.TITLE, MockConstants.DOMAIN, MockConstants.TAGLINE, MockConstants.DESCRIPTION, MockConstants.INFORMATION, MockConstants.KEYWORDS,
                MockConstants.PHONE, MockConstants.PHONE, MockConstants.PHONE, MockConstants.EMAIL,
                MockConstants.VKONTAKTE, MockConstants.FACEBOOK, MockConstants.TWITTER, MockConstants.SKYPE,
                MockConstants.ADDRESS, MockConstants.GOOGLE_MAPS, null, true
        );
        Assert.assertEquals(viewName, "redirect:/company/" + Translator.fromCyrillicToLatin(MockConstants.TITLE));
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddPartnerByGetMethodThenThrowsIllegalMappingException() {
        controller.addPartner();
    }

    @Test
    public void whenEditPartnerByUrlThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editPartnerByUrl(MockConstants.URL),
                "company/edit",
                new String[] { "main_company", "categories", "company", "main" }
        );
    }

    @Test
    public void whenUpdatePartnerCompanyByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.updatePartnerCompany(
                MockConstants.URL, MockConstants.TITLE, MockConstants.DOMAIN, MockConstants.TAGLINE, MockConstants.DESCRIPTION, MockConstants.INFORMATION, MockConstants.KEYWORDS,
                MockConstants.PHONE, MockConstants.PHONE, MockConstants.PHONE, MockConstants.EMAIL,
                MockConstants.VKONTAKTE, MockConstants.FACEBOOK, MockConstants.TWITTER, MockConstants.SKYPE,
                MockConstants.ADDRESS, MockConstants.GOOGLE_MAPS, null, true
        );
        assertEquals(viewName, "redirect:/company/" + Translator.fromCyrillicToLatin(MockConstants.TITLE));
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdatePartnerCompanyByGetMethodThenThrowsIllegalMappingException() {
        controller.updatePartnerCompany();
    }

    @Test
    public void whenDeletePartnerByUrlThenReturnSomeModelAndView() {
        String viewName = controller.deletePartnerByUrl(MockConstants.URL);
        assertEquals(viewName, "redirect:/");
    }

    @Test
    public void whenDeleteAllPartnerByUrlThenReturnSomeModelAndView() {
        String viewName = controller.deleteAllPartners();
        assertEquals(viewName, "redirect:/");
    }
}
