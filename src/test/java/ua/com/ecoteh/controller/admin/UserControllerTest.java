package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.UserService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.sender.SenderService;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.getCompanyService;
import static ua.com.ecoteh.mocks.service.data.MockServices.getUserService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;
import static ua.com.ecoteh.mocks.service.message.MockSenderService.getSenderService;

public class UserControllerTest {

    private static UserController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        final UserService userService = getUserService();
        final CompanyService companyService = getCompanyService();
        final SenderService senderService = getSenderService();
        controller = new UserController(fabric, userService, companyService, senderService);
    }

    @Test
    public void whenGetAllUsersPageThenReturnSomeModelAndView() {
        final String viewName = "user/all";
        final String[] keys = { "main_company", "categories", "favicon", "users" };
        final ModelAndView modelAndView = controller.getAllUsersPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenNewUserPageThenReturnSomeModelAndView() {
        final String viewName = "user/add";
        final String[] keys = { "main_company", "categories", "favicon" };
        final ModelAndView modelAndView = controller.newUserPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddUserByPostMethodThenThrowsIllegalMappingException() {
        controller.addUser();
    }

    @Test
    public void whenEditUserByUrlThenReturnSomeModelAndView() {
        final String viewName = "user/edit";
        final String[] keys = { "main_company", "categories", "favicon", "user" };
        final ModelAndView modelAndView = controller.editUserByUrl(URL);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateUserByGetMethodThenThrowsIllegalMappingException() {
        controller.updateUser();
    }

    @Test
    public void whenDeleteUserByUrlThenReturnSomeModelAndView() {
        final String expectedRedirect = "redirect:/admin/user/all";
        final String actualRedirect = controller.deleteUserByUrl(URL);
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test
    public void whenDeleteAllUsersThenReturnSomeModelAndView() {
        final String expectedRedirect = "redirect:/admin/user/all";
        final String actualRedirect = controller.deleteAllUsers();
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenSendMessageForPersonnelByGetMethodThenThrowsIllegalMappingException() {
        controller.sendMessageForPersonnel();
    }
}
