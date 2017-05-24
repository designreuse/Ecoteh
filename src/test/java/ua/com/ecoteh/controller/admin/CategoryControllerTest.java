package ua.com.ecoteh.controller.admin;

import ua.com.ecoteh.util.translator.Translator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import ua.com.ecoteh.mocks.MockConstants;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.controller.MockAdminController.getCategoryController;
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
                "category/add",
                new String[] { "main_company", "categories" }
        );
    }

    @Test
    public void whenAddCategoryByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.addCategory(
                MockConstants.TITLE, MockConstants.DESCRIPTION,
                MockConstants.KEYWORDS, null, true
        );
        Assert.assertEquals(viewName, "redirect:/category/" + Translator.fromCyrillicToLatin(MockConstants.TITLE));
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddCategoryByGetMethodThenThrowsIllegalMappingException() {
        controller.addCategory();
    }

    @Test
    public void whenGetEditCategoryPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editCategory(MockConstants.URL),
                "category/edit",
                new String[] { "main_company", "categories", "category" }
        );
    }

    @Test
    public void whenUpdateCategoryByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.updateCategory(
                MockConstants.URL, MockConstants.TITLE, MockConstants.DESCRIPTION, MockConstants.KEYWORDS,
                null, true
        );
        assertEquals(viewName, "redirect:/category/" + Translator.fromCyrillicToLatin(MockConstants.TITLE));
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateCategoryByGetMethodThenThrowsIllegalMappingException() {
        controller.updateCategory();
    }

    @Test
    public void whenDeleteCategoryByUrlMethodThenReturnSomeModelAndView() {
        String viewName = controller.deleteCategoryByUrl(MockConstants.URL);
        assertEquals(viewName, "redirect:/");
    }

    @Test
    public void whenDeleteAllCategoriesMethodThenReturnSomeModelAndView() {
        String viewName = controller.deleteAllCategories();
        assertEquals(viewName, "redirect:/");
    }
}
