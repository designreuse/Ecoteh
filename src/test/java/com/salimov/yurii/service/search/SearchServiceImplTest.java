package com.salimov.yurii.service.search;

import org.junit.Before;
import org.junit.Test;

import static com.salimov.yurii.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.yurii.mocks.service.data.MockServices.*;
import static com.salimov.yurii.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

public class SearchServiceImplTest {

    private final static String[] KEYWORDS = new String[]{
            "main_company", "categories", "in_categories", "in_articles",
            "in_companies", "all", "is_search", "how_search"
    };

    private SearchService searchService;

    @Before
    public void beforeTests() {
        this.searchService = new SearchServiceImpl(
                getMainMVFabric(),
                getCategoryService(),
                getArticleService(),
                getCompanyService(),
                getUserService()
        );
    }

    @Test
    public void whenSearchWithNullKeywordsAndContent() {
        checkModelAndView(
                this.searchService.search(null, null, true),
                "client/search/result",
                KEYWORDS
        );
        checkModelAndView(
                this.searchService.search(null, null, false),
                "client/search/result",
                KEYWORDS
        );
    }

    @Test
    public void whenSearchWithEmptyKeywordsAndContent() {
        checkModelAndView(
                this.searchService.search("", "", true),
                "client/search/result",
                KEYWORDS
        );
        checkModelAndView(
                this.searchService.search("", "", false),
                "client/search/result",
                KEYWORDS
        );
    }

    @Test
    public void whenSearchByHomeKeywordsThenRedirectToHome() {
        final String[] keywords = new String[]{"домой", "главная", "index", "home"};
        for (String keyword : keywords) {
            checkModelAndView(
                    this.searchService.search(keyword, "", true),
                    "redirect:/home", null
            );
        }
    }

    @Test
    public void whenSearchByCategoriesKeywordsThenRedirectToAllCategories() {
        final String[] keywords = new String[]{"все категории", "all categories"};
        for (String keyword : keywords) {
            checkModelAndView(
                    this.searchService.search(keyword, "in_categories", true),
                    "redirect:/category/all", null
            );
        }
    }

    @Test
    public void whenSearchByArticlesKeywordsThenRedirectToAllArticles() {
        final String[] keywords = new String[]{"все статьи", "all articles"};
        for (String keyword : keywords) {
            checkModelAndView(
                    this.searchService.search(keyword, "in_articles", true),
                    "redirect:/article/all", null
            );
        }
    }

    @Test
    public void whenSearchByAboutCompanyKeywordsThenRedirectToMainCompany() {
        final String[] keywords = new String[]{
                "о компании", "описание", "main company",
                "about company", "about main company"
        };
        for (String keyword : keywords) {
            checkModelAndView(
                    this.searchService.search(keyword, "in_companies", true),
                    "redirect:/company/main", null
            );
        }
    }

    @Test
    public void whenSearchByContactKeywordsThenRedirectToContacts() {
        final String[] keywords = new String[]{
                "контакты", "позвонить", "номер телефона", "адресс",
                "как доехать", "почта", "электронная почта", "e-mail",
                "contacts", "address"
        };
        for (String keyword : keywords) {
            checkModelAndView(
                    this.searchService.search(keyword, "", true),
                    "redirect:/contacts", null
            );
        }
    }

    @Test
    public void whenSearchByCompanyKeywordsThenRedirectToCompanies() {
        final String[] keywords = new String[]{
                "все партнеры", "все компании", "all company", "all partners"
        };
        for (String keyword : keywords) {
            checkModelAndView(
                    this.searchService.search(keyword, "all", true),
                    "redirect:/company/all", null
            );
        }
    }

    @Test
    public void whenSearchByResponsesKeywordsThenRedirectToResponses() {
        final String[] keywords = new String[]{"все отзывы", "all responses"};
        for (String keyword : keywords) {
            checkModelAndView(
                    this.searchService.search(keyword, "", true),
                    "redirect:/responses", null
            );
        }
    }

    @Test
    public void whenSearchByUsersKeywordsThenRedirectToUsers() {
        final String[] keywords = new String[]{
                "персонал", "работники"
        };
        for (String keyword : keywords) {
            checkModelAndView(
                    this.searchService.search(keyword, "", true),
                    "redirect:/company/main", null
            );
        }
    }
}
