package com.salimov.ecoteh.controller.admin;

import com.salimov.ecoteh.util.translator.Translator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockAdminController.getCategoryController;
import static org.junit.Assert.assertEquals;

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
                "admin/category/add",
                new String[] { "main_company", "categories" }
        );
    }

    @Test
    public void whenAddCategoryByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.addCategory(
                TITLE, DESCRIPTION,
                KEYWORDS, null, true
        );
        assertEquals(viewName, "redirect:/category/" + Translator.fromCyrillicToLatin(TITLE));
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddCategoryByGetMethodThenThrowsIllegalMappingException() {
        controller.addCategory();
    }

    @Test
    public void whenGetEditCategoryPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editCategory(URL),
                "admin/category/edit",
                new String[] { "main_company", "categories", "category" }
        );
    }

    @Test
    public void whenUpdateCategoryByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.updateCategory(
                URL, TITLE, DESCRIPTION, KEYWORDS,
                null, true
        );
        assertEquals(viewName, "redirect:/category/" + Translator.fromCyrillicToLatin(TITLE));
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateCategoryByGetMethodThenThrowsIllegalMappingException() {
        controller.updateCategory();
    }

    @Test
    public void whenDeleteCategoryByUrlMethodThenReturnSomeModelAndView() {
        String viewName = controller.deleteCategoryByUrl(URL);
        assertEquals(viewName, "redirect:/");
    }

    @Test
    public void whenDeleteAllCategoriesMethodThenReturnSomeModelAndView() {
        String viewName = controller.deleteAllCategories();
        assertEquals(viewName, "redirect:/");
    }
}
