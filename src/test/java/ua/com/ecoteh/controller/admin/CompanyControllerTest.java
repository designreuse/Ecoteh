package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.FileService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.getCompanyService;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

public class CompanyControllerTest {

    private static CompanyController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        final CompanyService companyService = getCompanyService();
        final FileService fileService = getFileService();
        controller = new CompanyController(fabric, companyService, fileService);
    }

    @Test
    public void whenEditMainCompanyThenReturnSomeModelAndView() {
        final String viewName = "company/edit";
        final String[] keys = { "main_company", "categories", "company", "favicon", "main" };
        final ModelAndView modelAndView = controller.editMainCompany();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateMainCompanyByGetMethodThenThrowsIllegalMappingException() {
        controller.updateMainCompany();
    }

    @Test
    public void whenNewPartnerPageThenReturnSomeModelAndView() {
        final String viewName = "company/add";
        final String[] keys = { "main_company", "categories", "favicon" };
        final ModelAndView modelAndView = controller.newPartnerPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddPartnerByGetMethodThenThrowsIllegalMappingException() {
        controller.addPartner();
    }

    @Test
    public void whenEditPartnerByUrlThenReturnSomeModelAndView() {
       final String viewName = "company/edit";
        final String[] keys = { "main_company", "categories", "company", "favicon", "main" };
        final ModelAndView modelAndView = controller.editPartnerByUrl(URL);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdatePartnerCompanyByGetMethodThenThrowsIllegalMappingException() {
        controller.updatePartnerCompany();
    }

    @Test
    public void whenDeletePartnerByUrlThenReturnSomeModelAndView() {
        final String expectedRedirect = "redirect:/";
        final String actualRedirect =  controller.deletePartnerByUrl(URL);
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test
    public void whenDeleteAllPartnerByUrlThenReturnSomeModelAndView() {
        final String expectedRedirect = "redirect:/";
        final String actualRedirect =  controller.deleteAllPartners();
        assertEquals(actualRedirect, expectedRedirect);
    }
}
