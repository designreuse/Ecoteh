package com.salimov.ecoteh.controller.client;

import org.junit.Ignore;
import org.junit.Test;

import static com.salimov.ecoteh.mocks.MockConstants.NUMBER;
import static com.salimov.ecoteh.mocks.MockConstants.URL;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;

public abstract class MainControllerTest {

    @Test
    public void whenGetHomePageThenReturnSomeModelAndView() {
        checkModelAndView(
                getController()
                        .getHomePage(),
                "client/main/index",
                new String[]{
                        "main_company",
                        "categories",
                        "company",
                        "print_partners",
                        "print_responses",
                        "favicon"
                }
        );
    }

    @Test
    public void whenGetAllCategoriesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                getController()
                        .getAllCategoriesPage(),
                "client/category/all",
                new String[]{
                        "main_company",
                        "categories",
                        "favicon"
                }
        );
    }

    @Test
    public void whenGetCategoryPageThenReturnSomeModelAndViewWithTheCategory() {
        checkModelAndView(
                getController()
                        .getCategoryPage(URL),
                "client/category/one",
                new String[]{
                        "main_company",
                        "categories",
                        "category",
                        "articles_list",
                        "favicon"
                }
        );
    }

    @Test
    public void whenGetArticlePageThenReturnSomeModelAndViewWithTheArticle() {
        checkModelAndView(
                getController()
                        .getArticlePage(URL),
                "client/article/one",
                new String[]{
                        "main_company",
                        "categories",
                        "article",
                        "favicon"
                }
        );
    }

    @Test
    public void whenGetArticleByNumberPageThenReturnSomeModelAndViewWithTheArticle() {
        checkModelAndView(
                getController()
                        .getArticleByNumberPage(NUMBER),
                "client/article/one",
                new String[]{
                        "main_company",
                        "categories",
                        "article",
                        "favicon"
                }
        );
    }

    @Test
    public void whenGetAllArticlesPageThenReturnSomeModelAndViewWithAllArticles() {
        checkModelAndView(
                getController()
                        .getAllArticlesPage(),
                "client/article/all",
                new String[]{
                        "articles_list",
                        "main_company",
                        "categories",
                        "favicon"
                }
        );
    }

    @Test
    public void whenGetAboutCompanyPageThenReturnSomeModelAndViewWithInformationAboutMainCompany() {
        checkModelAndView(
                getController()
                        .getAboutCompanyPage(),
                "client/company/main",
                new String[]{
                        "main_company",
                        "categories",
                        "company",
                        "users_list",
                        "favicon"
                }
        );
    }

    @Test
    public void whenGetContactsPageThenReturnSomeModelAndViewWithTheContacts() {
        checkModelAndView(
                getController().getContactsPage(),
                "client/company/main_contacts",
                new String[]{"is_captcha", "company", "main_company", "categories", "favicon"}
        );
    }

    @Test
    public void whenGetPartnersPageThenReturnSomeModelAndViewWithAllPartners() {
        checkModelAndView(
                getController()
                        .getPartnersPage(),
                "client/company/all",
                new String[]{"main_company", "categories", "favicon"}
        );
    }

    @Test
    public void whenGetPartnerPageThenReturnSomeModelAndViewWithThePartner() {
        checkModelAndView(
                getController().getPartnerPage(URL),
                "client/company/one",
                new String[]{
                        "main_company",
                        "categories",
                        "company",
                        "favicon"
                }
        );
    }

    @Test
    public void whenGetResponsesPageThenReturnSomeModelAndViewWithAllResponse() {
        checkModelAndView(
                getController().getAllResponsesPage(),
                "client/response/all",
                new String[]{
                        "is_captcha",
                        "responses",
                        "revers",
                        "main_company",
                        "categories",
                        "favicon"
                }
        );
    }

    @Ignore
    protected abstract MainController getController();
}
