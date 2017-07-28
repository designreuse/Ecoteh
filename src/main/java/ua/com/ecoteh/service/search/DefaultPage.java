package ua.com.ecoteh.service.search;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
interface DefaultPage {

    /**
     * The keywords of home page.
     */
    String HOME_KEYWORDS = "домой, главная, на главную, index, home, to home";

    /**
     * The home page URL.
     */
    String HOME_URL = "redirect:/home";

    /**
     * The keywords of page with all categories.
     */
    String ALL_CATEGORIES_KEYWORDS = "категории, все категории, all categories";

    /**
     * The URL of the page with all categories.
     */
    String ALL_CATEGORIES_URL = "redirect:/category/all";

    /**
     * The keywords of page with all articles.
     */
    String ALL_ARTICLES_KEYWORDS = "товары, все товары, продукция, products";

    /**
     * The URL of the page with all articles.
     */
    String ALL_ARTICLES_URL = "redirect:/article/all";

    /**
     * The keywords of page with information about main company.
     */
    String ABOUT_COMPANY_KEYWORDS = "компания, о компании, описание, main company, " +
            "about company, about main company";

    /**
     * The URL of the page with information about main company.
     */
    String ABOUT_COMPANY_URL = "redirect:/company/main";

    /**
     * The keywords of page with contacts of main company.
     */
    String CONTACTS_KEYWORDS = "контакты, позвонить, номер телефона, адресс, " +
            "как доехать, как роехать, почта, электронная почта, e-mail, contacts, address";

    /**
     * The URL of page with contacts of main company.
     */
    String CONTACTS_URL = "redirect:/contacts";

    /**
     * The keywords of page with all companies (partners).
     */
    String COMPANY_KEYWORDS = "партнеры, все партнеры, компании, все компании, " +
            "all company, all partners";

    /**
     * The URL of page with all companies (partners).
     */
    String COMPANY_URL = "redirect:/company/all";

    /**
     * The keywords of page with all responses.
     */
    String RESPONSES_KEYWORDS = "отзывы, все отзывы, all responses";

    /**
     * The URL of page with all responses.
     */
    String RESPONSES_URL = "redirect:/responses";

    /**
     * The keywords of page with personnel.
     */
    String USERS_KEYWORDS = "персонал, работники, personnel";

    /**
     * The URL of page with personnel.
     */
    String USERS_URL = "redirect:/company/main";

    /**
     * The keywords of blog page.
     */
    String BLOG_KEYWORDS = "блог, статьи, все статьи, all articles, blog";

    /**
     * The URL of blog page.
     */
    String BLOG_URL = "redirect:/blog";
}
