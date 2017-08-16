package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.service.data.ArticleService;
import ua.com.ecoteh.service.data.CategoryService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.enity.MockModels.getArticle;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategory;
import static ua.com.ecoteh.mocks.service.data.MockServices.getArticleService;
import static ua.com.ecoteh.mocks.service.data.MockServices.getCategoryService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ArticleControllerTest {

    private static ArticleController controller;

    @BeforeClass
    public static void beforeClass() {
        final MainMVFabric fabric = getCacheMVFabric();
        final ArticleService articleService = getArticleService();
        final CategoryService categoryService = getCategoryService();
        controller = new ArticleController(fabric, articleService, categoryService);
    }

    @Test
    public void whenNewArticlePageThenReturnModelAndView() {
        final String viewName = "article/add";
        final String[] keys = { "main_company", "categories", "favicon" };
        final ModelAndView modelAndView = controller.getNewArticlePage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenAddArticleThenReturnArticlePage() {
        final Article article = getArticle();
        final Category category = getCategory();
        final File file = article.getLogo();
        final String actualRedirect = controller.addArticle(
                article.getTitle(), article.getText(),
                article.getDescription(), article.getKeywords(),
                article.getNumber(), article.getPrice(), article.getCurrency(),
                category.getUrl(),
                file.getMultipartFile(),
                article.isNovelty(), article.isValidated()
        );
        final String expectedRedirect = "redirect:/article/all";
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddArticleByGetMethodThenThrowIllegalMappingException() {
        controller.addArticle();
    }

    @Test
    public void whenEditArticlePageThenReturnModelAndView() {
        final String viewName = "article/edit";
        final String[] keys = { "main_company", "categories", "favicon", "article" };
        final ModelAndView modelAndView = controller.editArticle(URL);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenUpdateArticleThenReturnArticlePage() {
        final Article article = getArticle();
        final Category category = getCategory();
        final File file = article.getLogo();
        final String actualRedirect = controller.updateArticle(
                article.getUrl(), article.getTitle(), article.getText(),
                article.getDescription(), article.getKeywords(), article.getNumber(),
                article.getPrice(), article.getCurrency(),
                category.getUrl(),
                file.getMultipartFile(),
                article.isNovelty(), article.isValidated()
        );
        final String expectedRedirect = "redirect:/article/" + article.getUrl();
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateArticleByGetMethodThenThrowIllegalMappingException() {
        controller.updateArticle();
    }

    @Test
    public void whenDeleteArticleByUrlThenReturnRedirect() {
        final String expectedRedirect = "redirect:/";
        final String actualRedirect = controller.deleteArticleByUrl(URL);
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test
    public void whenDeleteAllArticleByUrlThenReturnRedirect() {
        final String expectedRedirect = "redirect:/";
        final String actualRedirect = controller.deleteAllArticles();
        assertEquals(actualRedirect, expectedRedirect);
    }
}
