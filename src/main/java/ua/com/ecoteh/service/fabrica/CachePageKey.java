package ua.com.ecoteh.service.fabrica;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
interface CachePageKey {

    /**
     * The home page key.
     */
    String HOME_PAGE_KEY = "Home";

    /**
     * The all categories page key.
     */
    String ALL_CATEGORIES_KEY = "All Categories";

    /**
     * The all articles page key.
     */
    String ALL_ARTICLES_KEY = "All Articles";

    /**
     * The all sort articles page key.
     */
    String ALL_SORT_ARTICLES_KEY = "All sort Articles";

    /**
     * The main company page key.
     */
    String MAIN_COMPANY_KEY = "Main CompanyEntity";

    /**
     * The contacts (address) page key.
     */
    String CONTACTS_KEY = "ContactsEntity";

    /**
     * The all partners companies page key.
     */
    String ALL_PARTNERS_KEY = "All Partners";

    /**
     * The all sort partners companies page key.
     */
    String ALL_SORT_PARTNERS_KEY = "All Sort Partners";

    /**
     * The category by URL page key.
     */
    String CATEGORY_BY_URL_KEY = "CategoryEntity By URL ";

    /**
     * The category by RUL with sort articles page key.
     */
    String CATEGORY_BY_URL_WITH_SORT_ARTICLES_KEY = "CategoryEntity By URL with sort articles";

    /**
     * The article by URL page key.
     */
    String ARTICLE_BY_URL_KEY = "ArticleEntity By URL";

    /**
     * The article by number page key.
     */
    String ARTICLE_BY_NUMBER_KEY = "ArticleEntity By Number";

    /**
     * The company by URL page key.
     */
    String COMPANY_BY_URL_KEY = "CompanyEntity by URL";

    /**
     * The all responses page key.
     */
    String ALL_RESPONSES_KEY = "All Responses";

    /**
     * The all sort responses page key.
     */
    String ALL_SORT_RESPONSES_KEY = "All Sort Responses";

    /**
     * The default modelAndView key.
     */
    String DEFAULT_MAV_KEY = "Default ModelAndView";
}
