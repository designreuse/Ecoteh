package com.salimov.yurii.controller.other;

import com.salimov.yurii.controller.client.MainController;
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
                        "company",
                        "print_partners",
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
                        "categories"
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
                        "categories"
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
                        "categories"
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
                        "company",
                        "map"
                }
        );
    }

    @Test
    public void whenGetResponsesPageThenReturnSomeModelAndViewWithAllResponse() {
        checkModelAndView(
                getController()
                        .getAllResponsesPage(),
                "client/response/all_page",
                new String[]{
                        "responses_list",
                        "main_company",
                        "categories"
                }
        );
    }

    @Ignore
    protected abstract MainController getController();
}
