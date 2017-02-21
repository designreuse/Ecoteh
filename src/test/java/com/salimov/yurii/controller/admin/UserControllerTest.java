package com.salimov.yurii.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.yurii.mocks.controller.MockAdminController.getUserController;

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
                "admin/user/all_page",
                new String[]{"main_company", "categories", "users_list"}
        );
    }

    @Test
    public void whenNewUserPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.newUserPage(),
                "admin/user/new_page",
                new String[]{"main_company", "categories"}
        );
    }

    @Test
    public void whenAddUserByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.addUser(
                        NAME, LOGIN, PASSWORD,
                        DESCRIPTION, PHONE, PHONE, PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        PHOTO_URL, true, true, false,
                        new ModelAndView()
                ),
                "redirect:/admin/user/all",
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddUserByPostMethodThenThrowsIllegalMappingException() {
        controller.addUser();
    }

    @Test
    public void whenEditUserByUrlThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editUserByUrl(URL),
                "admin/user/edit_page",
                new String[]{"main_company", "categories", "user"}
        );
    }

    @Test
    public void whenUpdateUserByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.updateUser(
                        URL, NAME, LOGIN, PASSWORD,
                        DESCRIPTION, PHONE, PHONE, PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        PHOTO_URL, true, true, false,
                        new ModelAndView()
                ),
                "redirect:/admin/user/all",
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateUserByGetMethodThenThrowsIllegalMappingException() {
        controller.updateUser();
    }

    @Test
    public void whenDeleteUserByUrlThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteUserByUrl(URL, new ModelAndView()),
                "redirect:/admin/user/all",
                null
        );
    }

    @Test
    public void whenDeleteAllUsersThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteAllUsers(new ModelAndView()),
                "redirect:/admin/user/all",
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whensendMessageForPersonnelByGetMethodThenThrowsIllegalMappingException() {
        controller.sendMessageForPersonnel();
    }
}
