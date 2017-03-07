package com.salimov.yurii.controller.client;

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
                "client/main/index",
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
                "client/category/all",
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
                "client/category/one",
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
                "client/article/one",
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
                "client/article/one",
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
                "client/article/all",
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
                "client/company/main",
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
                new String[]{"main_company", "categories"}
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
                        "company"
                }
        );
    }

    @Test
    public void whenGetResponsesPageThenReturnSomeModelAndViewWithAllResponse() {
        checkModelAndView(
                getController()
                        .getAllResponsesPage(),
                "client/response/all",
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
