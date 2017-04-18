package com.salimov.ecoteh.controller.admin;

import com.salimov.ecoteh.util.translator.Translator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockAdminController.getArticleController;
import static org.junit.Assert.assertEquals;

public class ArticleControllerTest {

    private static ArticleController controller;

    @BeforeClass
    public static void setUp() {
        controller = getArticleController();
    }

    @Test
    public void whenGetNewArticlePageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getNewArticlePage(),
                "admin/article/add",
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
                "admin/article/edit",
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
