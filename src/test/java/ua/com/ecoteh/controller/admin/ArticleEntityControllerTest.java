package ua.com.ecoteh.controller.admin;

import ua.com.ecoteh.util.translator.Translator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;

import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.controller.MockAdminController.getArticleController;
import static org.junit.Assert.assertEquals;

public class ArticleEntityControllerTest {

    private static ArticleController controller;

    @BeforeClass
    public static void setUp() {
        controller = getArticleController();
    }

    @Test
    public void whenGetNewArticlePageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getNewArticlePage(),
                "article/add",
                new String[]{"main_company", "categories"}
        );
    }

    @Test
    public void whenAddArticleByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.addArticle(
                        TITLE, DESCRIPTION, TEXT,
                        KEYWORDS, NUMBER, PRICE, URL,
                        null, true
                );
        assertEquals(viewName, "redirect:/article/" + Translator.fromCyrillicToLatin(TITLE));
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddArticleByGetMethodThenThrowsIllegalMappingException() {
        controller.addArticle();
    }

    @Test
    public void whenGetEditArticlePageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editArticle(URL),
                "article/edit",
                new String[]{"main_company", "categories", "article"}
        );
    }

    @Test
    public void whenUpdateArticleByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.updateArticle(
                        URL, TITLE,
                        DESCRIPTION, TEXT,
                        KEYWORDS, NUMBER, PRICE,
                        URL, null, true
                );
        assertEquals(viewName, "redirect:/article/" + Translator.fromCyrillicToLatin(TITLE));
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateArticleByGetMethodThenThrowsIllegalMappingException() {
        controller.updateArticle();
    }

    @Test
    public void whenDeleteArticleByUrlMethodThenReturnSomeModelAndView() {
        String viewName = controller.deleteArticleByUrl(URL);
        assertEquals(viewName, "redirect:/");
    }

    @Test
    public void whenDeleteAllArticlesMethodThenReturnSomeModelAndView() {
        String viewName = controller.deleteAllArticles();
        assertEquals(viewName, "redirect:/");
    }
}
