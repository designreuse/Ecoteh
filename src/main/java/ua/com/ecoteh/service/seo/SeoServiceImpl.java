package ua.com.ecoteh.service.seo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.service.data.ArticleService;
import ua.com.ecoteh.service.data.CategoryService;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.util.cache.Cache;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class of the service layer, implements a set of methods
 * to adjust the search engine optimization (SEO).
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(basePackages = "ua.com.ecoteh.service")
public final class SeoServiceImpl implements SeoService {

    /**
     * The default lifetime of objects (milliseconds).
     * 86400000 milliseconds = 1 day
     */
    private static final long DEFAULT_TIMEOUT = 86400000L;

    /**
     * The robots txt key.
     */
    private final static String ROBOTS_KEY = "SEO robots";

    /**
     * The sitemap xml key.
     */
    private final static String SITEMAP_KEY = "SEO sitemap";

    /**
     * The describes a set of methods for working
     * with objects of the {@link Company} class.
     */
    private final CompanyService companyService;

    /**
     * The describes a set of methods for working
     * with objects of the {@link Category} class.
     */
    private final CategoryService categoryService;

    /**
     * The describes a set of methods for working
     * with objects of the {@link Article} class.
     */
    private final ArticleService articleService;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param companyService  the implementation of the {@link CompanyService} interface.
     * @param categoryService the implementation of the {@link CategoryService} interface.
     * @param articleService  the implementation of the {@link ArticleService} interface.
     */
    @Autowired
    public SeoServiceImpl(
            final CompanyService companyService,
            final CategoryService categoryService,
            final ArticleService articleService
    ) {
        this.companyService = companyService;
        this.categoryService = categoryService;
        this.articleService = articleService;
    }

    /**
     * Returns information about the site for the search engines.
     *
     * @return Information about the site for the search engines.
     */
    @Override
    public ModelAndView getRobotsTxt() {
        ModelAndView robotsTxt = (ModelAndView) Cache.get(ROBOTS_KEY);
        if (isNull(robotsTxt)) {
            robotsTxt = createRobotsTxt();
            Cache.put(ROBOTS_KEY, robotsTxt, DEFAULT_TIMEOUT);
        }
        return robotsTxt;
    }

    /**
     * Creates and returns a sitemap. This is a list of all pages on the site,
     * which consists of the links to these pages.
     *
     * @return Information about links on the site to search engines.
     */
    @Override
    public ModelAndView getSiteMapXml() {
        ModelAndView sitemap = (ModelAndView) Cache.get(SITEMAP_KEY);
        if (isNull(sitemap)) {
            sitemap = createSitemap();
            Cache.put(SITEMAP_KEY, sitemap, DEFAULT_TIMEOUT);
        }
        return sitemap;
    }

    /**
     * Creates and returns information about the site for the search engines.
     *
     * @return The information about the site for the search engines.
     */
    private ModelAndView createRobotsTxt() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("domain", getMainCompanyDomain());
        modelAndView.setViewName("seo/robots");
        return modelAndView;
    }

    /**
     * Creates and returns a sitemap.
     *
     * @return The sitemap information.
     */
    private ModelAndView createSitemap() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("domain", getMainCompanyDomain());
        modelAndView.addObject("categories", this.categoryService.getAll());
        modelAndView.addObject("articles", this.articleService.getAll());
        modelAndView.setViewName("seo/sitemap");
        return modelAndView;
    }

    /**
     * Returns a domain of the main company.
     *
     * @return The domain.
     */
    private String getMainCompanyDomain() {
        final Company mainCompany = this.companyService.getMainCompany();
        final String domain = mainCompany.getDomain();
        return domain.replace("http://", "").replace("https://", "");
    }
}
