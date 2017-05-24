package ua.com.ecoteh.controller.client;

import org.junit.Ignore;
import org.junit.Test;

import static ua.com.ecoteh.mocks.MockConstants.NUMBER;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;

public abstract class MainControllerTest {

    @Test
    public void whenGetHomePageThenReturnSomeModelAndView() {
        checkModelAndView(
                getController()
                        .getHomePage(),
                "home/index",
                new String[] { "main_company", "categories", "company",
                        "print_partners", "print_responses", "favicon" }
        );
    }

    @Test
    public void whenGetAllCategoriesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                getController()
                        .getAllCategoriesPage(),
                "category/all",
                new String[] { "main_company", "categories", "favicon" }
        );
    }

    @Test
    public void whenGetCategoryPageThenReturnSomeModelAndViewWithTheCategory() {
        checkModelAndView(
                getController().getCategoryPage(URL),
                "category/one",
                new String[] { "main_company", "categories", "category", "articles_list", "favicon" }
        );
    }

    @Test
    public void whenGetArticlePageThenReturnSomeModelAndViewWithTheArticle() {
        checkModelAndView(
                getController()
                        .getArticlePage(URL),
                "article/one",
                new String[] { "main_company", "categories", "article", "favicon" }
        );
    }

    @Test
    public void whenGetArticleByNumberPageThenReturnSomeModelAndViewWithTheArticle() {
        checkModelAndView(
                getController().getArticleByNumberPage(NUMBER),
                "article/one",
                new String[] { "main_company", "categories", "article", "favicon" }
        );
    }

    @Test
    public void whenGetAllArticlesPageThenReturnSomeModelAndViewWithAllArticles() {
        checkModelAndView(
                getController().getAllArticlesPage(),
                "article/all",
                new String[] { "articles_list", "main_company", "categories", "favicon" }
        );
    }

    @Test
    public void whenGetAboutCompanyPageThenReturnSomeModelAndViewWithInformationAboutMainCompany() {
        checkModelAndView(
                getController()
                        .getAboutCompanyPage(),
                "company/main",
                new String[] { "main_company", "categories", "company", "users_list", "favicon" }
        );
    }

    @Test
    public void whenGetContactsPageThenReturnSomeModelAndViewWithTheContacts() {
        checkModelAndView(
                getController().getContactsPage(),
                "company/main_contacts",
                new String[] { "is_captcha", "company", "main_company", "categories", "favicon" }
        );
    }

    @Test
    public void whenGetPartnersPageThenReturnSomeModelAndViewWithAllPartners() {
        checkModelAndView(
                getController()
                        .getPartnersPage(),
                "company/all",
                new String[] { "main_company", "categories", "favicon" }
        );
    }

    @Test
    public void whenGetPartnerPageThenReturnSomeModelAndViewWithThePartner() {
        checkModelAndView(
                getController().getPartnerPage(URL),
                "company/one",
                new String[] { "main_company", "categories", "company", "favicon" }
        );
    }

    @Test
    public void whenGetResponsesPageThenReturnSomeModelAndViewWithAllResponse() {
        checkModelAndView(
                getController().getAllResponsesPage(),
                "response/all",
                new String[] { "is_captcha", "responses", "revers",
                        "main_company", "categories", "favicon" }
        );
    }

    @Ignore
    protected abstract MainController getController();
}
