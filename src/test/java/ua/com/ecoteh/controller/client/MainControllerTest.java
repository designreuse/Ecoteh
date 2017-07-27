package ua.com.ecoteh.controller.client;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;

public abstract class MainControllerTest {

    @Test
    public void whenGetHomePageThenReturnSomeModelAndView() {
        final MainController controller = getController();
        final String viewName = "home/index";
        final String[] keys = { "main_company", "categories", "company",
                "print_companies", "print_responses", "favicon" };
        final ModelAndView modelAndView = controller.getHomePage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetHomePageWithCategoryThenReturnSomeModelAndView() {
        final MainController controller = getController();
        final String viewName = "home/index";
        final String[] keys = { "main_company", "categories", "company",
                "print_companies", "print_responses", "favicon", "category", "articles" };
        final ModelAndView modelAndView = controller.homeCategory(URL);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllCategoriesPageThenReturnSomeModelAndView() {
        final MainController controller = getController();
        final String viewName = "category/all";
        final String[] keys = { "main_company", "categories", "favicon" };
        final ModelAndView modelAndView = controller.getAllCategoriesPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetCategoryPageThenReturnSomeModelAndViewWithTheCategory() {
        final MainController controller = getController();
        final String viewName = "category/one";
        final String[] keys = { "main_company", "categories", "favicon", "category", "articles" };
        final ModelAndView modelAndView = controller.getCategoryPage(URL);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetCategoryWithSortArticlePageThenReturnSomeModelAndViewWithTheCategory() {
        final MainController controller = getController();
        final String viewName = "category/one";
        final String[] keys = { "main_company", "categories", "favicon", "category",
                "articles", "sort", "revers" };
        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("type")).thenReturn("title");
        when(request.getParameter("revers")).thenReturn("true");
        final ModelAndView modelAndView = controller.getCategoryWithSortArticlePage(URL, request);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetArticlePageThenReturnSomeModelAndViewWithTheArticle() {
        final MainController controller = getController();
        final String viewName = "article/one";
        final String[] keys = { "main_company", "categories", "favicon", "article" };
        final ModelAndView modelAndView = controller.getArticlePage(URL);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetArticleByNumberPageThenReturnSomeModelAndViewWithTheArticle() {
        final MainController controller = getController();
        final String viewName = "article/one";
        final String[] keys = { "main_company", "categories", "favicon", "article" };
        final ModelAndView modelAndView = controller.getArticleByNumberPage(NUMBER);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllArticlesPageThenReturnSomeModelAndViewWithAllArticles() {
        final MainController controller = getController();
        final String viewName = "article/all";
        final String[] keys = { "main_company", "categories", "favicon", "articles", "sort", "revers" };
        final ModelAndView modelAndView = controller.getAllArticlesPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllSortArticlesPageThenReturnSomeModelAndViewWithAllArticles() {
        final MainController controller = getController();
        final String viewName = "article/all";
        final String[] keys = { "main_company", "categories", "favicon", "articles", "sort", "revers" };
        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("type")).thenReturn("title");
        when(request.getParameter("revers")).thenReturn("true");
        final ModelAndView modelAndView = controller.getAllSortArticlesPage(request);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAboutCompanyPageThenReturnSomeModelAndViewWithInformationAboutMainCompany() {
        final MainController controller = getController();
        final String viewName = "company/main";
        final String[] keys = { "main_company", "categories", "favicon", "users" };
        final ModelAndView modelAndView = controller.getAboutCompanyPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetContactsPageThenReturnSomeModelAndViewWithTheContacts() {
        final MainController controller = getController();
        final String viewName = "company/main_contacts";
        final String[] keys = { "main_company", "categories", "favicon", "company", "is_captcha" };
        final ModelAndView modelAndView = controller.getContactsPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetPartnersPageThenReturnSomeModelAndViewWithAllPartners() {
        final MainController controller = getController();
        final String viewName = "company/all";
        final String[] keys = { "main_company", "categories", "favicon", "companies", "revers" };
        final ModelAndView modelAndView = controller.getPartnersPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetSortPartnersPageThenReturnSomeModelAndViewWithAllPartners() {
        final MainController controller = getController();
        final String viewName = "company/all";
        final String[] keys = { "main_company", "categories", "favicon", "companies", "revers" };
        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("type")).thenReturn("title");
        when(request.getParameter("revers")).thenReturn("true");
        final ModelAndView modelAndView = controller.getSortPartnersPage(request);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetPartnerPageThenReturnSomeModelAndViewWithThePartner() {
        final MainController controller = getController();
        final String viewName = "company/one";
        final String[] keys = { "main_company", "categories", "favicon", "company" };
        final ModelAndView modelAndView = controller.getPartnerPage(URL);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetResponsesPageThenReturnSomeModelAndViewWithAllResponse() {
        final MainController controller = getController();
        final String viewName = "response/all";
        final String[] keys = { "main_company", "categories", "favicon", "responses", "revers", "is_captcha" };
        final ModelAndView modelAndView = controller.getResponsesPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetSortResponsesPageThenReturnSomeModelAndViewWithAllResponse() {
        final MainController controller = getController();
        final String viewName = "response/all";
        final String[] keys = { "main_company", "categories", "favicon", "responses", "revers", "is_captcha" };
        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("revers")).thenReturn("true");
        final ModelAndView modelAndView = controller.getSortResponsesByDatePage(request);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetPostPageThenReturnSomeModelAndViewWithThePost() {
        final MainController controller = getController();
        final String viewName = "post/one";
        final String[] keys = { "main_company", "categories", "favicon", "post" };
        final ModelAndView modelAndView = controller.getPostPage(URL);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetBlogPageThenReturnSomeModelAndViewWithPosts() {
        final MainController controller = getController();
        final String viewName = "post/all";
        final String[] keys = { "main_company", "categories", "favicon", "posts" };
        final ModelAndView modelAndView = controller.getBlogPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetAllSortPostsPageThenReturnSomeModelAndViewWithAllPosts() {
        final MainController controller = getController();
        final String viewName = "post/all";
        final String[] keys = { "main_company", "categories", "favicon", "posts", "sort", "revers" };
        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("type")).thenReturn("title");
        when(request.getParameter("revers")).thenReturn("true");
        final ModelAndView modelAndView = controller.getSortBlogPage(request);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetControllerThenReturnNotNull() {
        final MainController controller = getController();
        assertNotNull(controller);
    }

    @Ignore
    protected abstract MainController getController();
}
