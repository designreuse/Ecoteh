package com.salimov.yurii.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.yurii.mocks.controller.MockAdminController.getCategoryController;

public class CategoryControllerTest {

    private static CategoryController controller;

    @BeforeClass
    public static void setUp() {
        controller = getCategoryController();
    }

    @Test
    public void whenGetNewCategoryPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getNewCategoryPage(),
                "admin/category/new_page",
                new String[]{"main_company", "categories"}
        );
    }

    @Test
    public void whenAddCategoryByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.addCategory(
                        TITLE, DESCRIPTION,
                        KEYWORDS, null, true,
                        new ModelAndView()
                ),
                "redirect:/category/" + URL,
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddCategoryByGetMethodThenThrowsIllegalMappingException() {
        controller.addCategory();
    }

    @Test
    public void whenGetEditCategoryPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editCategory(URL),
                "admin/category/edit_page",
                new String[]{"main_company", "categories", "category"}
        );
    }

    @Test
    public void whenUpdateCategoryByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.updateCategory(
                        URL, TITLE, DESCRIPTION, KEYWORDS,
                        null, true,
                        new ModelAndView()
                ),
                "redirect:/category/" + URL,
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateCategoryByGetMethodThenThrowsIllegalMappingException() {
        controller.updateCategory();
    }

    @Test
    public void whenDeleteCategoryByUrlMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteCategoryByUrl(URL, new ModelAndView()),
                "redirect:/",
                null
        );
    }

    @Test
    public void whenDeleteAllCategoriesMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteAllCategories(new ModelAndView()),
                "redirect:/",
                null
        );
    }
}
