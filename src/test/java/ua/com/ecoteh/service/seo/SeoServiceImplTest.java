package ua.com.ecoteh.service.seo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.data.ArticleService;
import ua.com.ecoteh.service.data.CategoryService;
import ua.com.ecoteh.service.data.CompanyService;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.getArticleService;
import static ua.com.ecoteh.mocks.service.data.MockServices.getCategoryService;
import static ua.com.ecoteh.mocks.service.data.MockServices.getCompanyService;

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
        this.seoService = new SeoServiceImpl(companyService, categoryService, articleService);
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
        final String[] keys = { "domain", "categories", "articles" };
        checkModelAndView(modelAndView, viewName, keys);
    }
}
