package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.data.CategoryService;
import ua.com.ecoteh.service.data.FileService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.getCategoryService;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

public class CategoryControllerTest {

    private static CategoryController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        final CategoryService categoryService = getCategoryService();
        final FileService fileService = getFileService();
        controller = new CategoryController(fabric, categoryService, fileService);
    }

    @Test
    public void whenGetNewCategoryPageThenReturnSomeModelAndView() {
        final String viewName = "category/add";
        final String[] keys = {"main_company", "categories", "favicon"};
        final ModelAndView modelAndView = controller.getNewCategoryPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddCategoryByGetMethodThenThrowsIllegalMappingException() {
        controller.addCategory();
    }

    @Test
    public void whenGetEditCategoryPageThenReturnSomeModelAndView() {
        final String viewName = "category/edit";
        final String[] keys = { "main_company", "categories", "favicon", "category" };
        final ModelAndView modelAndView = controller.editCategory(URL);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateCategoryByGetMethodThenThrowsIllegalMappingException() {
        controller.updateCategory();
    }

    @Test
    public void whenDeleteCategoryByUrlMethodThenReturnSomeModelAndView() {
        final String expectedViewName = "redirect:/";
        final String actualViewName = controller.deleteCategoryByUrl(URL);
        assertEquals(actualViewName, expectedViewName);
    }

    @Test
    public void whenDeleteAllCategoriesMethodThenReturnSomeModelAndView() {
        final String expectedViewName = "redirect:/";
        final String actualViewName = controller.deleteAllCategories();
        assertEquals(actualViewName, expectedViewName);
    }
}
