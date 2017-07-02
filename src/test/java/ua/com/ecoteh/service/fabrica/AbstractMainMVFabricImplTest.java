package ua.com.ecoteh.service.fabrica;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.NUMBER;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class AbstractMainMVFabricImplTest {

    @Test
    public void whenGetHomePageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.homePage();
        final String viewName = "home/index";
        final String[] keys = { "main_company", "categories", "favicon", "company",
                "companies", "print_companies", "responses", "print_responses" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetHomePageWithCategryThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.homeCategory(URL);
        final String viewName = "home/index";
        final String[] keys = { "main_company", "categories", "favicon", "company", "companies",
                "print_companies", "responses", "print_responses", "category", "articles" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllCategoriesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.allCategoriesPage();
        final String viewName = "category/all";
        final String[] keys = { "main_company", "categories", "favicon" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllArticlesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.allArticlesPage();
        final String viewName = "article/all";
        final String[] keys = { "main_company", "categories", "favicon", "articles", "sort", "revers" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllSortByPriceArticlesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.allSortArticlesPage("price", true);
        final String viewName = "article/all";
        final String[] keys = { "main_company", "categories", "favicon", "articles", "sort", "revers" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllSortByTitleArticlesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.allSortArticlesPage("title", true);
        final String viewName = "article/all";
        final String[] keys = { "main_company", "categories", "favicon", "articles", "sort", "revers" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllSortByDateArticlesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.allSortArticlesPage("date", true);
        final String viewName = "article/all";
        final String[] keys = { "main_company", "categories", "favicon", "articles", "sort", "revers" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllSortByNumberArticlesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.allSortArticlesPage("number", true);
        final String viewName = "article/all";
        final String[] keys = { "main_company", "categories", "favicon", "articles", "sort", "revers" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllSortByOtherArticlesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.allSortArticlesPage("unknown", true);
        final String viewName = "article/all";
        final String[] keys = { "main_company", "categories", "favicon", "articles", "sort", "revers" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAboutCompanyPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.aboutCompanyPage();
        final String viewName = "company/main";
        final String[] keys = { "main_company", "categories", "favicon", "company", "users" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetContactsPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.contactsPage();
        final String viewName = "company/main_contacts";
        final String[] keys = { "main_company", "categories", "favicon", "company" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllPartnersPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.allPartnersPage();
        final String viewName = "company/all";
        final String[] keys = { "main_company", "categories", "favicon", "companies", "revers" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllSortPartnersByTitlePageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final String viewName = "company/all";
        final String[] keys = { "main_company", "categories", "favicon", "companies", "revers" };
        ModelAndView modelAndView = fabric.allSortPartnersByTitlePage(true);
        checkModelAndView(modelAndView, viewName, keys);
        modelAndView = fabric.allSortPartnersByTitlePage(false);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetCategoryPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.categoryPage(URL);
        final String viewName = "category/one";
        final String[] keys = { "main_company", "categories", "favicon", "category" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetCategoryWithSortByPriceArticlesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final String viewName = "category/one";
        final String[] keys = { "main_company", "categories", "favicon", "category", "articles", "sort", "revers" };
        ModelAndView modelAndView = fabric.categoryWithSortArticlesPage(URL, "price", true);
        checkModelAndView(modelAndView, viewName, keys);
        modelAndView = fabric.categoryWithSortArticlesPage(URL, "title", false);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetCategoryWithSortByTitleArticlesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final String viewName = "category/one";
        final String[] keys = { "main_company", "categories", "favicon", "category", "articles", "sort", "revers" };
        ModelAndView modelAndView = fabric.categoryWithSortArticlesPage(URL, "title", true);
        checkModelAndView(modelAndView, viewName, keys);
        modelAndView = fabric.categoryWithSortArticlesPage(URL, "title", false);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetCategoryWithSortByDateArticlesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final String viewName = "category/one";
        final String[] keys = { "main_company", "categories", "favicon", "category", "articles", "sort", "revers" };
        ModelAndView modelAndView = fabric.categoryWithSortArticlesPage(URL, "date", true);
        checkModelAndView(modelAndView, viewName, keys);
        modelAndView = fabric.categoryWithSortArticlesPage(URL, "date", false);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetCategoryWithSortByNumberArticlesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final String viewName = "category/one";
        final String[] keys = { "main_company", "categories", "favicon", "category", "articles", "sort", "revers" };
        ModelAndView modelAndView = fabric.categoryWithSortArticlesPage(URL, "number", true);
        checkModelAndView(modelAndView, viewName, keys);
        modelAndView = fabric.categoryWithSortArticlesPage(URL, "number", false);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetCategoryWithSortByUnknownArticlesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final String viewName = "category/one";
        final String[] keys = { "main_company", "categories", "favicon", "category", "articles", "sort", "revers" };
        ModelAndView modelAndView = fabric.categoryWithSortArticlesPage(URL, "unknown", true);
        checkModelAndView(modelAndView, viewName, keys);
        modelAndView = fabric.categoryWithSortArticlesPage(URL, "unknown", false);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetArticleByUrlPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.articleByUrlPage(URL);
        final String viewName = "article/one";
        final String[] keys = { "main_company", "categories", "favicon", "article" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetArticleByNumberPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.articleByNumberPage(NUMBER);
        final String viewName = "article/one";
        final String[] keys = { "main_company", "categories", "favicon", "article" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetPartnerPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.partnerPage(URL);
        final String viewName = "company/one";
        final String[] keys = { "main_company", "categories", "favicon", "company" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllResponsesPageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.allResponsesPage();
        final String viewName = "response/all";
        final String[] keys = { "main_company", "categories", "favicon", "responses", "revers" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllSortResponsesByDatePageThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final String viewName = "response/all";
        final String[] keys = { "main_company", "categories", "favicon", "responses", "revers" };
        ModelAndView modelAndView = fabric.allSortResponsesByDatePage(true);
        checkModelAndView(modelAndView, viewName, keys);
        modelAndView = fabric.allSortResponsesByDatePage(false);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetDefaultModelAndViewThenReturnSomeModelAndView() {
        final MainMVFabric fabric = getFabric();
        final ModelAndView modelAndView = fabric.getDefaultModelAndView();
        final String viewName = null;
        final String[] keys = { "main_company", "categories", "favicon" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenAddAuthUserPageWithNullModelAndView() {
        final MainMVFabric fabric = getFabric();
        fabric.addAuthUser(null);
    }

    @Test
    public void whenAddAuthUserPage() {
        final MainMVFabric fabric = getFabric();
        fabric.addAuthUser(new ModelAndView());
    }

    @Test
    public void whenGetFabricThenReturnNotNull() {
        final MainMVFabric fabric = getFabric();
        assertNotNull(fabric);
    }

    protected abstract MainMVFabric getFabric();
}
