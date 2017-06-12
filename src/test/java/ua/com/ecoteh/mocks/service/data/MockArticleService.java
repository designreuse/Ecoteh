package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.service.data.ArticleService;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockArticleService extends MockContentService<Article> {
    
    private final ArticleService service;
    private final Article article;
    private final Collection<Article> articles;
    private final Category category;
    private final Collection<Category> categories;

    MockArticleService() {
        this.service = mock(ArticleService.class);
        this.article = getArticle();
        this.articles = getArticles();
        this.category = getCategory();
        this.categories = getCategories();
    }

    @Override
    ArticleService create() {
        super.create();
        initGetByNumber();
        initGetByCategoryId();
        initGetByCategoryTitle();
        initSortByNumber();
        initGetAndSortByNumber();
        initSortByDate();
        initGetAndSortByDate();
        initSortByPrice();
        initGetAndSortByPrice();
        initFilterByDate();
        initGetAndFilterByDate();
        initFilterByCategory();
        initGetAndFilterByCategory();
        initFilterByCategories();
        initGetAndFilterByCategories();
        initFilteredByValid();
        return this.service;
    }
    
    @Override
    ArticleService getService() {
        return this.service;
    }

    @Override
    Article getModel() {
        return this.article;
    }

    @Override
    Collection<Article> getModels() {
        return this.articles;
    }

    private void initGetByNumber() {
        when(this.service.getByNumber(NUMBER, true)).thenReturn(article);
        when(this.service.getByNumber(NUMBER, false)).thenReturn(article);
        when(this.service.getByNumber(null, true)).thenThrow(new IllegalArgumentException());
        when(this.service.getByNumber(null, false)).thenThrow(new IllegalArgumentException());
        when(this.service.getByNumber("", true)).thenThrow(new IllegalArgumentException());
        when(this.service.getByNumber("", false)).thenThrow(new IllegalArgumentException());
        when(this.service.getByNumber(ANY_STRING, true)).thenThrow(new NullPointerException());
        when(this.service.getByNumber(ANY_STRING, false)).thenThrow(new NullPointerException());
    }

    private void initGetByCategoryId() {
        when(this.service.getByCategoryId(ID)).thenReturn(this.articles);
        when(this.service.getByCategoryId(UNKNOWN_ID)).thenReturn(new ArrayList<>());
    }

    private void initGetByCategoryTitle() {
        when(this.service.getByCategoryTitle(TITLE)).thenReturn(this.articles);
        when(this.service.getByCategoryTitle("")).thenThrow(new IllegalArgumentException());
        when(this.service.getByCategoryTitle(null)).thenThrow(new IllegalArgumentException());
    }

    private void initSortByNumber() {
        when(this.service.sortByNumber(this.articles, true)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.sortByNumber(this.articles, false)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.sortByNumber(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(this.service.sortByNumber(new ArrayList<>(), false)).thenReturn(new ArrayList<>());
        when(this.service.sortByNumber(null, true)).thenReturn(new ArrayList<>());
        when(this.service.sortByNumber(null, false)).thenReturn(new ArrayList<>());
    }

    private void initGetAndSortByNumber() {
        when(this.service.getAndSortByNumber(true)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.getAndSortByNumber(false)).thenReturn(new ArrayList<>(this.articles));
    }

    private void initSortByDate() {
        when(this.service.sortByDate(this.articles, true)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.sortByDate(this.articles, false)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.sortByDate(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(this.service.sortByDate(new ArrayList<>(), false)).thenReturn(new ArrayList<>());
        when(this.service.sortByDate(null, true)).thenReturn(new ArrayList<>());
        when(this.service.sortByDate(null, false)).thenReturn(new ArrayList<>());
    }

    private void initGetAndSortByDate() {
        when(this.service.getAndSortByDate(true)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.getAndSortByDate(false)).thenReturn(new ArrayList<>(this.articles));
    }

    private void initSortByPrice() {
        when(this.service.sortByPrice(this.articles, true)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.sortByPrice(this.articles, false)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.sortByPrice(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(this.service.sortByPrice(new ArrayList<>(), false)).thenReturn(new ArrayList<>());
        when(this.service.sortByPrice(null, true)).thenReturn(new ArrayList<>());
        when(this.service.sortByPrice(null, false)).thenReturn(new ArrayList<>());
    }

    private void initGetAndSortByPrice() {
        when(this.service.getAndSortByPrice(true)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.getAndSortByPrice(false)).thenReturn(new ArrayList<>(this.articles));
    }

    private void initFilterByDate() {
        when(this.service.filterByDate(this.articles, DATE, DATE)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.filterByDate(this.articles, null, null)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.filterByDate(this.articles, DATE, null)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.filterByDate(this.articles, null, DATE)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.filterByDate(new ArrayList<>(), DATE, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(new ArrayList<>(), null, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(new ArrayList<>(), DATE, null)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(new ArrayList<>(), null, null)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, DATE, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, null, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, DATE, null)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, null, null)).thenReturn(new ArrayList<>());
    }

    private void initGetAndFilterByDate() {
        when(this.service.getAndFilterByDate(DATE, DATE)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.getAndFilterByDate(DATE, null)).thenReturn(new ArrayList<>());
        when(this.service.getAndFilterByDate(null, DATE)).thenReturn(new ArrayList<>());
        when(this.service.getAndFilterByDate(null, null)).thenReturn(new ArrayList<>());
    }

    private void initFilterByCategory() {
        when(this.service.filterByCategory(this.articles, this.category)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.filterByCategory(this.articles, null)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.filterByCategory(new ArrayList<>(), this.category)).thenReturn(new ArrayList<>());
        when(this.service.filterByCategory(new ArrayList<>(), null)).thenReturn(new ArrayList<>());
        when(this.service.filterByCategory(null, this.category)).thenReturn(new ArrayList<>());
        when(this.service.filterByCategory(null, null)).thenReturn(new ArrayList<>());
        when(this.service.getAndFilterByCategory(this.category)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.getAndFilterByCategory(null)).thenReturn(new ArrayList<>());
    }

    private void initGetAndFilterByCategory() {
        when(this.service.getAndFilterByCategory(this.category)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.getAndFilterByCategory(null)).thenReturn(new ArrayList<>());
    }

    private void initFilterByCategories() {
        when(this.service.filterByCategories(this.articles, this.categories)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.filterByCategories(this.articles, null)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.filterByCategories(this.articles, new ArrayList<>())).thenReturn(new ArrayList<>(this.articles));
        when(this.service.filterByCategories(new ArrayList<>(), this.categories)).thenReturn(new ArrayList<>());
        when(this.service.filterByCategories(null, this.categories)).thenReturn(new ArrayList<>());
        when(this.service.filterByCategories(null, null)).thenReturn(new ArrayList<>());
        when(this.service.filterByCategories(null, new ArrayList<>())).thenReturn(new ArrayList<>());
        when(this.service.filterByCategories(new ArrayList<>(), new ArrayList<>())).thenReturn(new ArrayList<>());
    }

    private void initGetAndFilterByCategories() {
        when(this.service.getAndFilterByCategories(this.categories)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.getAndFilterByCategories(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(this.service.getAndFilterByCategories(null)).thenReturn(new ArrayList<>());
    }
    
    private void initFilteredByValid() {
        when(this.service.filteredByValid(this.articles)).thenReturn(new ArrayList<>(this.articles));
        when(this.service.filteredByValid(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(this.service.filteredByValid(null)).thenReturn(new ArrayList<>());
    }
}
