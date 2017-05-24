package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import ua.com.ecoteh.mocks.MockConstants;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.controller.MockAdminController.getUserController;
import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    private static UserController controller;

    @BeforeClass
    public static void setUp() {
        controller = getUserController();
    }

    @Test
    public void whenGetAllUsersPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getAllUsersPage(),
                "user/all",
                new String[] { "main_company", "categories", "users_list" }
        );
    }

    @Test
    public void whenNewUserPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.newUserPage(),
                "user/add",
                new String[] { "main_company", "categories" }
        );
    }

    @Test
    public void whenAddUserByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.addUser(
                MockConstants.NAME, MockConstants.LOGIN, MockConstants.PASSWORD,
                MockConstants.DESCRIPTION, MockConstants.PHONE, MockConstants.PHONE, MockConstants.PHONE, MockConstants.EMAIL,
                MockConstants.VKONTAKTE, MockConstants.FACEBOOK, MockConstants.TWITTER, MockConstants.SKYPE,
                null, true, true, false
        );
        assertEquals(viewName, "redirect:/admin/user/all");
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddUserByPostMethodThenThrowsIllegalMappingException() {
        controller.addUser();
    }

    @Test
    public void whenEditUserByUrlThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editUserByUrl(MockConstants.URL),
                "user/edit",
                new String[] { "main_company", "categories", "user" }
        );
    }

    @Test
    public void whenUpdateUserByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.updateUser(
                MockConstants.URL, MockConstants.NAME, MockConstants.LOGIN, MockConstants.PASSWORD,
                MockConstants.DESCRIPTION, MockConstants.PHONE, MockConstants.PHONE, MockConstants.PHONE, MockConstants.EMAIL,
                MockConstants.VKONTAKTE, MockConstants.FACEBOOK, MockConstants.TWITTER, MockConstants.SKYPE,
                null, true, true, false
        );
        assertEquals(viewName, "redirect:/admin/user/all");
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateUserByGetMethodThenThrowsIllegalMappingException() {
        controller.updateUser();
    }

    @Test
    public void whenDeleteUserByUrlThenReturnSomeModelAndView() {
        String viewName = controller.deleteUserByUrl(MockConstants.URL);
        assertEquals(viewName, "redirect:/admin/user/all");
    }

    @Test
    public void whenDeleteAllUsersThenReturnSomeModelAndView() {
        String viewName = controller.deleteAllUsers();
        assertEquals(viewName, "redirect:/admin/user/all");
    }

    @Test(expected = IllegalMappingException.class)
    public void whensendMessageForPersonnelByGetMethodThenThrowsIllegalMappingException() {
        controller.sendMessageForPersonnel();
    }
}
