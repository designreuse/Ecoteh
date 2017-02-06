package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.ArticleDao;
import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.service.data.interfaces.ArticleService;
import com.salimov.yurii.util.comparator.ArticleComparator;
import com.salimov.yurii.util.worktime.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class of the service layer, implements a set of methods
 * for working with objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Article
 * @see ArticleService
 * @see ContentServiceImpl
 * @see DataServiceImpl
 * @see ArticleDao
 */
@Service
@ComponentScan(
        basePackages = {
                "com.salimov.yurii.dao",
                "com.salimov.yurii.service.data"
        }
)
public final class ArticleServiceImpl
        extends ContentServiceImpl<Article, Long>
        implements ArticleService {

    /**
     * The interface provides a set of standard methods
     * for working {@link Article} objects a the database.
     *
     * @see ArticleDao
     * @see Article
     */
    private final ArticleDao dao;

    /**
     * Constructor.
     *
     * @param dao a implementation of
     *            the {@link ArticleDao} interface.
     * @see ArticleDao
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ArticleServiceImpl(final ArticleDao dao) {
        super(dao);
        this.dao = dao;
    }

    /**
     * Initializes, saves and returns a new article.
     *
     * @param title       a title of the new article.
     * @param description a description of the new article.
     * @param text        a text of the new article.
     * @param keywords    a keywords of the new article.
     * @param number      a number of the new article.
     * @param category    a category of the new article.
     * @param isValid     a value of validations of the model.
     * @return The new saving article.
     * @see Article
     * @see Category
     * @see File
     */
    @Override
    @Transactional
    public Article initAndAdd(
            final String title,
            final String description,
            final String text,
            final String keywords,
            final String number,
            final Category category,
            final boolean isValid
    ) {
        final Article article = new Article();
        article.initialize(
                title, description, text,
                keywords, number, category
        );
        article.setValidated(isValid);
        return add(article);
    }

    /**
     * Initializes, updates and returns article with parameter id.
     * Returns {@code null} if id is {@code null}.
     *
     * @param url         a url of the article to update.
     * @param title       a new title to the article.
     * @param description a new description to the article.
     * @param text        a new text to the article.
     * @param keywords    a new keywords to the article.
     * @param number      a new number to the article.
     * @param category    a new category to the article.
     * @param isValid     a validated of the article.
     * @return The updating article with parameter id.
     * @see Article
     * @see Category
     * @see File
     */
    @Override
    @Transactional
    public Article initAndUpdate(
            final String url,
            final String title,
            final String description,
            final String text,
            final String keywords,
            final String number,
            final Category category,
            final boolean isValid
    ) {
        final Article article = getByUrl(url, false);
        article.initialize(
                title, description, text,
                keywords, number, category
        );
        article.setValidated(isValid);
        return update(article);
    }

    /**
     * Returns article with the parameter number.
     *
     * @param number  a title of the article to return.
     * @param isValid is get valid article or not.
     * @return The article with the parameter number.
     * @throws IllegalArgumentException Throw exception when parameter number
     *                                  is blank.
     * @throws NullPointerException     Throw exception when article
     *                                  with parameter id is not exist.
     * @see Article
     */
    @Override
    @Transactional(readOnly = true)
    public Article getByNumber(
            final String number,
            final boolean isValid
    ) throws IllegalArgumentException, NullPointerException {
        if (isBlank(number)) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " number is blank!"
            );
        }
        final Article article = this.dao.getByNumber(number);
        if ((article == null) || (isValid && !article.isValidated())) {
            throw new NullPointerException(
                    "Can`t find article by number \"" + number + "\"!"
            );
        }
        return article;
    }

    /**
     * Sorts and returns articles by number.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles.
     * @see Article
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> sortByNumber(
            final Collection<Article> articles,
            final boolean revers
    ) {
        return sort(articles, new ArticleComparator.ByNumber(), revers);
    }

    /**
     * Sorts and returns articles by date.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles.
     * @see Article
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> sortByDate(
            final Collection<Article> articles,
            final boolean revers
    ) {
        return sort(articles, new ArticleComparator.ByDate(), revers);
    }

    /**
     * Sorts and returns articles by number.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles.
     * @see Article
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getAndSortByNumber(final boolean revers) {
        return sortByNumber(getAll(), revers);
    }

    /**
     * Sorts and returns articles by date.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles.
     * @see Article
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getAndSortByDate(final boolean revers) {
        return sortByDate(getAll(), revers);
    }

    /**
     * Filters and returns articles by the date.
     * Return empty list if responses is empty.
     * Return back responses list if dates are {@code null}
     * or start date is equals to end finish date.
     *
     * @param articles   the articles to filter.
     * @param startDate  a initial date.
     * @param finishDate a final date.
     * @return The filtered list of articles.
     * @see Article
     */
    @Override
    @Transactional
    public List<Article> filterByDate(
            final Collection<Article> articles,
            final Date startDate,
            final Date finishDate
    ) {
        final List<Article> result = new ArrayList<>();
        if ((articles != null) && !articles.isEmpty()) {
            if (Time.checkDate(startDate, finishDate)) {
                result.addAll(
                        articles.stream()
                                .filter(
                                        article -> Time.checkTime(
                                                article.getDate(),
                                                startDate,
                                                finishDate
                                        )
                                ).collect(Collectors.toList())
                );
            } else {
                result.addAll(articles);
            }
        }
        return result;
    }

    /**
     * Filters and returns articles by the category.
     *
     * @param articles the articles to filter.
     * @param category a category filtering.
     * @return The filtered list of articles.
     * @see Article
     * @see Category
     */
    @Override
    @Transactional
    public List<Article> filterByCategory(
            final Collection<Article> articles,
            final Category category
    ) {
        final List<Category> categories = new ArrayList<>(1);
        categories.add(category);
        return filterByCategories(articles, categories);
    }

    /**
     * Filters and returns articles by the categories.
     * Returns empty list if articles is empty.
     * Returns back articles if categories is empty.
     *
     * @param articles   the articles to filter.
     * @param categories a categories filtering.
     * @return The filtered list of articles.
     * @see Article
     * @see Category
     */
    @Override
    @Transactional
    public List<Article> filterByCategories(
            final Collection<Article> articles,
            final Collection<Category> categories
    ) {
        final List<Article> result = new ArrayList<>();
        if ((articles != null) && !articles.isEmpty()) {
            if ((categories != null) && !categories.isEmpty()) {
                for (Article article : articles) {
                    result.addAll(
                            categories.stream()
                                    .filter(
                                            category -> article.getCategory()
                                                    .equals(category)
                                    ).map(category -> article)
                                    .collect(Collectors.toList())
                    );
                }
            } else {
                result.addAll(articles);
            }
        }
        return result;
    }

    /**
     * Filters and returns articles by the date.
     *
     * @param startDate  a initial date.
     * @param finishDate a final date.
     * @return The filtered list of articles.
     * @see Article
     */
    @Override
    @Transactional
    public List<Article> getAndFilterByDate(
            final Date startDate,
            final Date finishDate
    ) {
        return filterByDate(getAll(), startDate, finishDate);
    }

    /**
     * Filters and returns articles by the category.
     *
     * @param category a category filtering.
     * @return The filtered list of articles.
     * @see Article
     * @see Category
     */
    @Override
    @Transactional
    public List<Article> getAndFilterByCategory(final Category category) {
        return filterByCategory(getAll(), category);
    }

    /**
     * Filters and returns articles by the categories.
     *
     * @param categories a categories filtering.
     * @return The filtered list of articles.
     * @see Article
     * @see Category
     */
    @Override
    @Transactional
    public List<Article> getAndFilterByCategories(
            final Collection<Category> categories
    ) {
        return filterByCategories(getAll(), categories);
    }

    /**
     * Returns a list valid articles.
     * Returns empty list if articles is empty.
     *
     * @param articles the articles to filter.
     * @return The list of articles.
     * @see Article
     */
    @Override
    @Transactional
    public List<Article> filteredByValid(
            final Collection<Article> articles
    ) {
        List<Article> result = new ArrayList<>();
        if ((articles != null) && !articles.isEmpty()) {
            result.addAll(
                    articles.stream()
                            .filter(
                                    article -> (article != null)
                                            && article.isValidated()
                            ).collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Removes article with parameter id.
     *
     * @param id a id of article to remove.
     * @see Article
     */
    @Override
    @Transactional
    public void remove(final Long id) {
        if (id != null) {
            remove(get(id));
        }
    }

    /**
     * Removes article with the parameter title.
     * Removes content if title is not blank.
     *
     * @param title a title of the article to remove.
     * @see Article
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotBlank(title)) {
            remove(getByTitle(title, false));
        }
    }

    /**
     * Removes article with the parameter url.
     *
     * @param url a url of the article to remove.
     * @see Article
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotBlank(url)) {
            remove(getByUrl(url, false));
        }
    }

    /**
     * Removes article with the parameter number.
     * Removes article if number is not blank.
     *
     * @param number a number of the article to remove.
     * @see Article
     */
    @Override
    @Transactional
    public void removeByNumber(final String number) {
        if (isNotBlank(number)) {
            remove(getByNumber(number, false));
        }
    }

    /**
     * Removes article.
     * Removes article if it is not {@code null}.
     *
     * @param article the article to remove.
     * @see Article
     */
    @Override
    @Transactional
    public void remove(final Article article) {
        if (article != null) {
            article.setCategory(null);
            super.remove(article);
        }
    }

    /**
     * Removes all articles.
     *
     * @see Article
     */
    @Override
    @Transactional
    public void removeAll() {
        getAll(false).forEach(this::remove);
    }

    /**
     * Return Class object of {@link Article} class.
     *
     * @return The Class object of {@link Article} class.
     */
    @Override
    protected Class<Article> getModelClass() {
        return Article.class;
    }
}
