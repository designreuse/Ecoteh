package com.salimov.ecoteh.service.fabrica.impl;

import com.salimov.ecoteh.service.fabrica.interfaces.MainMVFabric;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static com.salimov.ecoteh.mocks.MockConstants.NUMBER;
import static com.salimov.ecoteh.mocks.MockConstants.URL;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.service.data.MockServices.*;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MainMVFabricImplTest {

    private MainMVFabric fabric;

    @Before
    public void beforeTests() {
        this.fabric = new MainMVFabricImpl(
                getArticleService(),
                getCategoryService(),
                getCompanyService(),
                getFileService(),
                getResponseService(),
                getUserService()
        );
    }

    @Test
    public void whenGetHomepageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.homePage(),
                "client/main/index",
                new String[]{"main_company", "categories", "company",
                        "partners", "print_partners", "responses",
                        "print_responses"}
        );
    }

    @Test
    public void whenGetAllArticlesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.homePage(),
                "client/main/index",
                new String[]{"main_company", "categories"}
        );
    }

    @Test
    public void whenGetAllSortArticlesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.allSortArticlesPage("title", true),
                "client/article/all",
                new String[]{"main_company", "categories", "articles_list",
                        "sort", "revers"}
        );
        checkModelAndView(
                this.fabric.allSortArticlesPage("date", true),
                "client/article/all",
                new String[]{"main_company", "categories", "articles_list",
                        "sort", "revers"}
        );
        checkModelAndView(
                this.fabric.allSortArticlesPage("number", true),
                "client/article/all",
                new String[]{"main_company", "categories", "articles_list",
                        "sort", "revers"}
        );
        checkModelAndView(
                this.fabric.allSortArticlesPage("unknown", true),
                "client/article/all",
                new String[]{"main_company", "categories", "articles_list",
                        "sort", "revers"}
        );
    }

    @Test
    public void whenGetAboutCompanyPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.aboutCompanyPage(),
                "client/company/main",
                new String[]{"main_company", "categories", "company", "users_list"}
        );
    }

    @Test
    public void whenGetContactsPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.contactsPage(),
                "client/company/main_contacts",
                new String[]{"main_company", "categories", "company", "favicon"}
        );
    }

    @Test
    public void whenGetAllPartnersPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.allPartnersPage(),
                "client/company/all",
                new String[]{"main_company", "categories", "partners_list", "revers"}
        );
    }

    @Test
    public void whenGetAllSortPartnersByTitlePageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.allSortPartnersByTitlePage(true),
                "client/company/all",
                new String[]{"main_company", "categories", "partners_list", "revers"}
        );
        checkModelAndView(
                this.fabric.allSortPartnersByTitlePage(false),
                "client/company/all",
                new String[]{"main_company", "categories", "partners_list", "revers"}
        );
    }

    @Test
    public void whenGetCategoryPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.categoryPage(URL),
                "client/category/one",
                new String[]{"main_company", "categories"}
        );
    }

    @Test
    public void whenGetCategoryWithSortArticlesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "title", true),
                "client/category/one",
                new String[]{"main_company", "categories"}
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "title", false),
                "client/category/one",
                new String[]{"main_company", "categories"}
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "date", true),
                "client/category/one",
                new String[]{"main_company", "categories"}
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "date", false),
                "client/category/one",
                new String[]{"main_company", "categories", "articles_list"}
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "number", true),
                "client/category/one",
                new String[]{"main_company", "categories", "articles_list"}
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "number", false),
                "client/category/one",
                new String[]{"main_company", "categories", "articles_list"}
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "unknown", true),
                "client/category/one",
                new String[]{"main_company", "categories", "articles_list"}
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "unknown", false),
                "client/category/one",
                new String[]{"main_company", "categories", "articles_list"}
        );
    }

    @Test
    public void whenGetArticleByUrlPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.articleByUrlPage(URL),
                "client/article/one",
                new String[]{"main_company", "categories", "article"}
        );
    }

    @Test
    public void whenGetArticleByNumberPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.articleByNumberPage(NUMBER),
                "client/article/one",
                new String[]{"main_company", "categories", "article"}
        );
    }

    @Test
    public void whenGetPartnerPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.partnerPage(URL),
                "client/company/one",
                new String[]{"main_company", "categories", "company"}
        );
    }

    @Test
    public void whenGetAllResponsesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.allResponsesPage(),
                "client/response/all",
                new String[]{"main_company", "categories", "responses_list"}
        );
    }

    @Test
    public void whenGetAllSortResponsesByDatePageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.allSortResponsesByDatePage(true),
                "client/response/all",
                new String[]{"main_company", "categories", "responses_list"}
        );
        checkModelAndView(
                this.fabric.allSortResponsesByDatePage(false),
                "client/response/all",
                new String[]{"main_company", "categories", "responses_list"}
        );
    }

    @Test
    public void whenGetDefaultModelAndViewThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.getDefaultModelAndView(),
                null, new String[]{"main_company", "categories"}
        );
    }

    @Test
    public void whenAddAuthUserPageWithNullModelAndView() {
        this.fabric.addAuthUser(null);
    }

    @Test
    public void whenAddAuthUserPage() {
        this.fabric.addAuthUser(new ModelAndView());
    }

    @Ignore
    public void setFabric(MainMVFabric fabric) {
        this.fabric = fabric;
    }

    @Ignore
    public MainMVFabric getFabric() {
        return this.fabric;
    }
}