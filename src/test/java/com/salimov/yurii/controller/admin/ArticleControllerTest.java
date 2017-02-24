package com.salimov.yurii.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.yurii.mocks.controller.MockAdminController.getArticleController;

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
                "admin/article/new_page",
                new String[]{"main_company", "categories"}
        );
    }

    @Test
    public void whenAddArticleByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.addArticle(
                        TITLE, DESCRIPTION, TEXT,
                        KEYWORDS, NUMBER, URL, null, true,
                        new ModelAndView()
                ),
                "redirect:/article/" + URL,
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddArticleByGetMethodThenThrowsIllegalMappingException() {
        controller.addArticle();
    }

    @Test
    public void whenGetEditArticlePageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editArticle(URL),
                "admin/article/edit_page",
                new String[]{"main_company", "categories", "article"}
        );
    }

    @Test
    public void whenUpdateArticleByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.updateArticle(
                        URL, TITLE,
                        DESCRIPTION, TEXT,
                        KEYWORDS, NUMBER,
                        URL, null, true,
                        new ModelAndView()
                ),
                "redirect:/article/" + URL,
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateArticleByGetMethodThenThrowsIllegalMappingException() {
        controller.updateArticle();
    }

    @Test
    public void whenDeleteArticleByUrlMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteArticleByUrl(URL, new ModelAndView()),
                "redirect:/",
                null
        );
    }

    @Test
    public void whenDeleteAllArticlesMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteAllArticles(new ModelAndView()),
                "redirect:/",
                null
        );
    }
}
