package ua.com.ecoteh.service.fabrica;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static ua.com.ecoteh.mocks.MockConstants.NUMBER;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MainMVFabricImplTest {

    private MainMVFabric fabric;

    @Before
    public void beforeTests() {
        this.fabric = getMainMVFabric();
    }

    @Test
    public void whenGetHomepageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.homePage(),
                "home/index",
                new String[] { "main_company", "categories", "company",
                        "partners", "print_partners", "responses",
                        "print_responses" }
        );
    }

    @Test
    public void whenGetAllArticlesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.homePage(),
                "home/index",
                new String[] { "main_company", "categories" }
        );
    }

    @Test
    public void whenGetAllSortArticlesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.allSortArticlesPage("title", true),
                "article/all",
                new String[] { "main_company", "categories", "articles_list",
                        "sort", "revers" }
        );
        checkModelAndView(
                this.fabric.allSortArticlesPage("date", true),
                "article/all",
                new String[] { "main_company", "categories", "articles_list",
                        "sort", "revers" }
        );
        checkModelAndView(
                this.fabric.allSortArticlesPage("number", true),
                "article/all",
                new String[] { "main_company", "categories", "articles_list",
                        "sort", "revers" }
        );
        checkModelAndView(
                this.fabric.allSortArticlesPage("unknown", true),
                "article/all",
                new String[] { "main_company", "categories", "articles_list",
                        "sort", "revers" }
        );
    }

    @Test
    public void whenGetAboutCompanyPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.aboutCompanyPage(),
                "company/main",
                new String[] { "main_company", "categories", "company", "users_list" }
        );
    }

    @Test
    public void whenGetContactsPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.contactsPage(),
                "company/main_contacts",
                new String[] { "main_company", "categories", "company", "favicon" }
        );
    }

    @Test
    public void whenGetAllPartnersPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.allPartnersPage(),
                "company/all",
                new String[] { "main_company", "categories", "partners_list", "revers" }
        );
    }

    @Test
    public void whenGetAllSortPartnersByTitlePageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.allSortPartnersByTitlePage(true),
                "company/all",
                new String[] { "main_company", "categories", "partners_list", "revers" }
        );
        checkModelAndView(
                this.fabric.allSortPartnersByTitlePage(false),
                "company/all",
                new String[] { "main_company", "categories", "partners_list", "revers" }
        );
    }

    @Test
    public void whenGetCategoryPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.categoryPage(URL),
                "category/one",
                new String[] { "main_company", "categories" }
        );
    }

    @Test
    public void whenGetCategoryWithSortArticlesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "title", true),
                "category/one",
                new String[] { "main_company", "categories" }
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "title", false),
                "category/one",
                new String[] { "main_company", "categories" }
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "date", true),
                "category/one",
                new String[] { "main_company", "categories" }
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "date", false),
                "category/one",
                new String[] { "main_company", "categories", "articles_list" }
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "number", true),
                "category/one",
                new String[] { "main_company", "categories", "articles_list" }
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "number", false),
                "category/one",
                new String[] { "main_company", "categories", "articles_list" }
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "unknown", true),
                "category/one",
                new String[] { "main_company", "categories", "articles_list" }
        );
        checkModelAndView(
                this.fabric.categoryWithSortArticlesPage(URL, "unknown", false),
                "category/one",
                new String[] { "main_company", "categories", "articles_list" }
        );
    }

    @Test
    public void whenGetArticleByUrlPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.articleByUrlPage(URL),
                "article/one",
                new String[] { "main_company", "categories", "article" }
        );
    }

    @Test
    public void whenGetArticleByNumberPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.articleByNumberPage(NUMBER),
                "article/one",
                new String[] { "main_company", "categories", "article" }
        );
    }

    @Test
    public void whenGetPartnerPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.partnerPage(URL),
                "company/one",
                new String[] { "main_company", "categories", "company" }
        );
    }

    @Test
    public void whenGetAllResponsesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.allResponsesPage(),
                "response/all",
                new String[] { "main_company", "categories", "responses" }
        );
    }

    @Test
    public void whenGetAllSortResponsesByDatePageThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.allSortResponsesByDatePage(true),
                "response/all",
                new String[] { "main_company", "categories", "responses" }
        );
        checkModelAndView(
                this.fabric.allSortResponsesByDatePage(false),
                "response/all",
                new String[] { "main_company", "categories", "responses" }
        );
    }

    @Test
    public void whenGetDefaultModelAndViewThenReturnSomeModelAndView() {
        checkModelAndView(
                this.fabric.getDefaultModelAndView(),
                null, new String[] { "main_company", "categories" }
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
}