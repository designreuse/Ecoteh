package ua.com.ecoteh.service.seo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.data.ArticleService;
import ua.com.ecoteh.service.data.CategoryService;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.PostService;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.*;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class SeoServiceImplTest {

    private SeoService seoService;

    @Before
    public void beforeTests() {
        final CompanyService companyService = getCompanyService();
        final CategoryService categoryService = getCategoryService();
        final ArticleService articleService = getArticleService();
        final PostService postService = getPostService();
        this.seoService = new SeoServiceImpl(companyService, categoryService, articleService, postService);
    }

    @Test
    public void whenGetRobotsTxtThenReturnsSomeModelAndView() {
        final ModelAndView modelAndView = this.seoService.getRobotsTxt();
        final String viewName = "seo/robots";
        final String[] keys = { "domain" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetSitemapThenReturnsSomeModelAndView() {
        final ModelAndView modelAndView = this.seoService.getSiteMapXml();
        final String viewName = "seo/sitemap";
        final String[] keys = { "domain", "categories", "articles", "posts" };
        checkModelAndView(modelAndView, viewName, keys);
    }
}
