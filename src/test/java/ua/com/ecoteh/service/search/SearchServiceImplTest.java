package ua.com.ecoteh.service.search;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.data.*;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.*;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

public class SearchServiceImplTest {

    private final static String[] KEYWORDS = new String[] {
            "main_company", "categories", "in_categories", "in_articles",
            "in_companies", "all", "is_search",
    };

    private static SearchService searchService;

    @BeforeClass
    public static void beforeClass() {
        final MainMVFabric fabric = getMainMVFabric();
        final CategoryService categoryService = getCategoryService();
        final ArticleService articleService = getArticleService();
        final CompanyService companyService = getCompanyService();
        final UserService userService = getUserService();
        final PostService postService = getPostService();
        searchService = new SearchServiceImpl(
                fabric, categoryService, articleService,
                companyService, userService, postService
        );
    }

    @Test
    public void whenSearchWithNullKeywordsAndContent() {
        ModelAndView modelAndView = searchService.search(null, null);
        final String viewName = "search/search";
        checkModelAndView(modelAndView, viewName, KEYWORDS);
        modelAndView = searchService.search(null, null);
        checkModelAndView(modelAndView, viewName, KEYWORDS);
    }

    @Test
    public void whenSearchWithEmptyKeywordsAndContent() {
        ModelAndView modelAndView = searchService.search("", "");
        final String viewName = "search/search";
        checkModelAndView(modelAndView, viewName, KEYWORDS);
        modelAndView = searchService.search("", "");
        checkModelAndView(modelAndView, viewName, KEYWORDS);
    }

    @Test
    public void whenSearchByHomeKeywordsThenRedirectToHome() {
        ModelAndView modelAndView;
        final String viewName = "redirect:/home";
        final String[] keywords = new String[] { "домой", "главная", "index", "home" };
        for (String keyword : keywords) {
            modelAndView = searchService.search(keyword, "");
            checkModelAndView(modelAndView, viewName, null);
        }
    }

    @Test
    public void whenSearchByCategoriesKeywordsThenRedirectToAllCategories() {
        ModelAndView modelAndView;
        final String viewName = "redirect:/category/all";
        final String[] keywords = new String[] { "все категории", "all categories" };
        for (String keyword : keywords) {
            modelAndView = searchService.search(keyword, "in_categories");
            checkModelAndView(modelAndView, viewName, null);
        }
    }

    @Test
    public void whenSearchByArticlesKeywordsThenRedirectToAllArticles() {
        ModelAndView modelAndView;
        final String viewName = "redirect:/article/all";
        final String[] keywords = new String[] { "товары", "products" };
        for (String keyword : keywords) {
            modelAndView = searchService.search(keyword, "in_articles");
            checkModelAndView(modelAndView, viewName, null);
        }
    }

    @Test
    public void whenSearchByBlogKeywordsThenRedirectToAllArticles() {
        ModelAndView modelAndView;
        final String viewName = "redirect:/blog";
        final String[] keywords = new String[] { "статьи", "blog" };
        for (String keyword : keywords) {
            modelAndView = searchService.search(keyword, "in_blog");
            checkModelAndView(modelAndView, viewName, null);
        }
    }

    @Test
    public void whenSearchByAboutCompanyKeywordsThenRedirectToMainCompany() {
        ModelAndView modelAndView;
        final String viewName = "redirect:/company/main";
        final String[] keywords = new String[] { "о компании", "описание", "main company",
                "about company", "about main company" };
        for (String keyword : keywords) {
            modelAndView = searchService.search(keyword, "in_companies");
            checkModelAndView(modelAndView, viewName, null);
        }
    }

    @Test
    public void whenSearchByContactKeywordsThenRedirectToContacts() {
        ModelAndView modelAndView;
        final String viewName = "redirect:/contacts";
        final String[] keywords = new String[] { "контакты", "позвонить", "номер телефона", "адресс",
                "как доехать", "почта", "электронная почта", "e-mail",
                "contacts", "address" };
        for (String keyword : keywords) {
            modelAndView = searchService.search(keyword, "");
            checkModelAndView(modelAndView, viewName, null);
        }
    }

    @Test
    public void whenSearchByCompanyKeywordsThenRedirectToCompanies() {
        ModelAndView modelAndView;
        final String viewName = "redirect:/company/all";
        final String[] keywords = new String[] { "все партнеры", "все компании", "all company", "all partners" };
        for (String keyword : keywords) {
            modelAndView = searchService.search(keyword, "");
            checkModelAndView(modelAndView, viewName, null);
        }
    }

    @Test
    public void whenSearchByResponsesKeywordsThenRedirectToResponses() {
        ModelAndView modelAndView;
        final String viewName = "redirect:/responses";
        final String[] keywords = new String[] { "все отзывы", "all responses" };
        for (String keyword : keywords) {
            modelAndView = searchService.search(keyword, "");
            checkModelAndView(modelAndView, viewName, null);
        }
    }

    @Test
    public void whenSearchByUsersKeywordsThenRedirectToUsers() {
        ModelAndView modelAndView;
        final String viewName = "redirect:/company/main";
        final String[] keywords = new String[] { "персонал", "работники" };
        for (String keyword : keywords) {
            modelAndView = searchService.search(keyword, "");
            checkModelAndView(modelAndView, viewName, null);
        }
    }
}
