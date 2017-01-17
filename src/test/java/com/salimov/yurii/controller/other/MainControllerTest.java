package com.salimov.yurii.controller.other;

import org.junit.Ignore;
import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.NUMBER;
import static com.salimov.yurii.mocks.MockConstants.URL;
import static com.salimov.yurii.mocks.ModelAndViews.checkModelAndView;

public abstract class MainControllerTest {

    @Test
    public void whenGetHomePageThenReturnSomeModelAndView() {
        checkModelAndView(
                getController()
                        .getHomePage(),
                "client/main/index_page",
                new String[]{
                        "main_company",
                        "categories",
                        "partners",
                        "company",
                        "slides",
                        "print_partners",
                        "responses",
                        "print_responses"
                }
        );
    }

    @Test
    public void whenGetAllCategoriesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                getController()
                        .getAllCategoriesPage(),
                "client/category/all_page",
                new String[]{
                        "main_company",
                        "categories",
                        "partners"
                }
        );
    }

    @Test
    public void whenGetCategoryPageThenReturnSomeModelAndViewWithTheCategory() {
        checkModelAndView(
                getController()
                        .getCategoryPage(URL),
                "client/category/one_page",
                new String[]{
                        "main_company",
                        "categories",
                        "partners",
                        "category",
                        "articles_list"
                }
        );
    }

    @Test
    public void whenGetArticlePageThenReturnSomeModelAndViewWithTheArticle() {
        checkModelAndView(
                getController()
                        .getArticlePage(URL),
                "client/article/one_page",
                new String[]{
                        "main_company",
                        "categories",
                        "partners",
                        "article"
                }
        );
    }

    @Test
    public void whenGetArticleByNumberPageThenReturnSomeModelAndViewWithTheArticle() {
        checkModelAndView(
                getController()
                        .getArticleByNumberPage(NUMBER),
                "client/article/one_page",
                new String[]{
                        "main_company",
                        "categories",
                        "partners",
                        "article"
                }
        );
    }

    @Test
    public void whenGetAllArticlesPageThenReturnSomeModelAndViewWithAllArticles() {
        checkModelAndView(
                getController()
                        .getAllArticlesPage(),
                "client/article/all_page",
                new String[]{
                        "articles_list",
                        "main_company",
                        "categories",
                        "partners"
                }
        );
    }

    @Test
    public void whenGetAboutCompanyPageThenReturnSomeModelAndViewWithInformationAboutMainCompany() {
        checkModelAndView(
                getController()
                        .getAboutCompanyPage(),
                "client/company/main_page",
                new String[]{
                        "main_company",
                        "categories",
                        "partners",
                        "company",
                        "users_list"
                }
        );
    }

    @Test
    public void whenGetContactsPageThenReturnSomeModelAndViewWithTheContacts() {
        checkModelAndView(
                getController()
                        .getContactsPage(),
                "client/company/contacts_page",
                new String[]{
                        "main_company",
                        "categories",
                        "partners",
                        "company",
                        "map"
                }
        );
    }

    @Test
    public void whenGetPartnersPageThenReturnSomeModelAndViewWithAllPartners() {
        checkModelAndView(
                getController()
                        .getPartnersPage(),
                "client/company/all_page",
                new String[]{
                        "main_company",
                        "categories",
                        "partners"
                }
        );
    }

    @Test
    public void whenGetPartnerPageThenReturnSomeModelAndViewWithThePartner() {
        checkModelAndView(
                getController()
                        .getPartnerPage(URL),
                "client/company/one_page",
                new String[]{
                        "main_company",
                        "categories",
                        "partners",
                        "company",
                        "map"
                }
        );
    }

    @Test
    public void whenGetResponsesPageThenReturnSomeModelAndViewWithAllResponse() {
        checkModelAndView(
                getController().getResponsesPage(),
                "client/response/all_page",
                new String[]{
                        "responses_list",
                        "main_company",
                        "categories",
                        "partners"
                }
        );
    }

    @Ignore
    protected abstract MainController getController();
}
