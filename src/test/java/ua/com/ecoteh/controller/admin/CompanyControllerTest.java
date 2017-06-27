package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.FileService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.enity.MockModels.getCompany;
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

    @Test
    public void whenUpdateMainCompanyThenReturnItRedirect() {
        final Company company = getCompany();
        final Contacts contacts = company.getContacts();
        final Address address = company.getAddress();
        final File file = company.getLogo();
        final String actualRedirect = controller.updateMainCompany(
                company.getTitle(), company.getDomain(), company.getTagline(),
                company.getText(), company.getDescription(), company.getKeywords(),
                company.getWorkTimeFrom(), company.getWorkTimeTo(),
                contacts.getMobilePhone(), contacts.getLandlinesPhone(),
                contacts.getFax(), contacts.getEmail(),
                company.getSenderEmail(), company.getSenderPass(),
                contacts.getVkontakte(), contacts.getFacebook(),
                contacts.getTwitter(), contacts.getSkype(),
                address.getPostAddress(), address.getGoogleMaps(),
                file.getMultipartFile()
        );
        final String expectedRedirect = "redirect:/company/main";
        assertEquals(actualRedirect, expectedRedirect);
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

    @Test
    public void whenAddCompanyThenReturnAllCompanyPage() {
        final Company company = getCompany();
        final Contacts contacts = company.getContacts();
        final Address address = company.getAddress();
        final File file = company.getLogo();
        final String actualRedirect = controller.addPartner(
                company.getTitle(), company.getDomain(),
                company.getTagline(), company.getText(),
                company.getDescription(), company.getKeywords(),
                contacts.getMobilePhone(), contacts.getLandlinesPhone(),
                contacts.getFax(), contacts.getEmail(),
                contacts.getVkontakte(), contacts.getFacebook(),
                contacts.getTwitter(), contacts.getSkype(),
                address.getPostAddress(), address.getGoogleMaps(),
                file.getMultipartFile(), company.isValidated()
        );
        final String expectedRedirect = "redirect:/company/all";
        assertEquals(actualRedirect, expectedRedirect);
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

    @Test
    public void whenUpdateCompanyThenReturnAllCompanyPage() {
        final Company company = getCompany();
        final Contacts contacts = company.getContacts();
        final Address address = company.getAddress();
        final File file = company.getLogo();
        final String actualRedirect = controller.updatePartnerCompany(
                company.getUrl(), company.getTitle(), company.getDomain(),
                company.getTagline(), company.getText(),
                company.getDescription(), company.getKeywords(),
                contacts.getMobilePhone(), contacts.getLandlinesPhone(),
                contacts.getFax(), contacts.getEmail(),
                contacts.getVkontakte(), contacts.getFacebook(),
                contacts.getTwitter(), contacts.getSkype(),
                address.getPostAddress(), address.getGoogleMaps(),
                file.getMultipartFile(), company.isValidated()
        );
        final String expectedRedirect = "redirect:/company/all";
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdatePartnerCompanyByGetMethodThenThrowsIllegalMappingException() {
        controller.updatePartnerCompany();
    }

    @Test
    public void whenDeletePartnerByUrlThenReturnSomeModelAndView() {
        final String expectedRedirect = "redirect:/";
        final String actualRedirect = controller.deletePartnerByUrl(URL);
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test
    public void whenDeleteAllPartnerByUrlThenReturnSomeModelAndView() {
        final String expectedRedirect = "redirect:/";
        final String actualRedirect = controller.deleteAllPartners();
        assertEquals(actualRedirect, expectedRedirect);
    }
}
