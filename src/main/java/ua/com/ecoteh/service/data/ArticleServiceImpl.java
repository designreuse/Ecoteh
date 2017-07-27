package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.ArticleRepository;
import ua.com.ecoteh.service.data.comparator.ArticleComparator;
import ua.com.ecoteh.util.time.Time;

import java.util.*;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, implements a set of methods
 * for working with objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.service.data"
        }
)
public final class ArticleServiceImpl extends ContentServiceImpl<Article, ArticleEntity> implements ArticleService {

    /**
     * The interface provides a set of standard methods
     * for working {@link ArticleEntity} objects a the database.
     */
    private final ArticleRepository repository;

    /**
     * Constructor.
     *
     * @param repository  the implementation of the {@link ArticleRepository} interface.
     * @param fileService the implementation of the {@link FileService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ArticleServiceImpl(
            final ArticleRepository repository,
            final FileService fileService
    ) {
        super(repository, fileService);
        this.repository = repository;
    }

    /**
     * Returns article with the incoming number.
     * If a incoming number is null or empty then throws IllegalArgumentException.
     * If can`t find article by incoming number then throws NullPointerException.
     *
     * @param number  the number of an article to return.
     * @param isValid is get valid article or not.
     * @return The article with the incoming number (newer null).
     * @throws IllegalArgumentException Throw exception when incoming number is blank.
     * @throws NullPointerException     Throw exception when article with parameter id is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public Article getByNumber(final String number, final boolean isValid)
            throws IllegalArgumentException, NullPointerException {
        if (isEmpty(number)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_NUMBER_MESSAGE,
                    getClassSimpleName()
            );
        }
        final ArticleEntity article = this.repository.findByNumber(number);
        if (isNotValidated(article, isValid)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), number
            );
        }
        article.getCategoryEntity().getArticleEntities();
        return convertToModel(article);
    }

    /**
     * Returns article with the incoming category id.
     *
     * @param id the category id of articles to return.
     * @return The list of articles or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Article> getByCategoryId(final long id) {
        final Collection<ArticleEntity> entities = this.repository.findByCategoryId(id);
        return convertToModels(entities);
    }

    /**
     * Returns article with the category title.
     * If a incoming title is null or empty then throws IllegalArgumentException.
     *
     * @param title the category title of the articles to return.
     * @return The list of articles or empty list (newer null).
     * @throws IllegalArgumentException Throw exception when parameter categoryTitle is blank.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Article> getByCategoryTitle(final String title) throws IllegalArgumentException {
        if (isEmpty(title)) {
            throw getIllegalArgumentException(ExceptionMessage.BLANK_CATEGORY_TITLE_MESSAGE);
        }
        final Collection<ArticleEntity> entities = this.repository.findByCategoryTitle(title);
        return convertToModels(entities);
    }

    /**
     * Sorts and returns articles by number.
     * For sorting used {@link ArticleComparator.ByNumber} comparator.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> sortByNumber(final Collection<Article> articles, final boolean revers) {
        final Comparator<Article> comparator = new ArticleComparator.ByNumber();
        return sort(articles, comparator, revers);
    }

    /**
     * Sorts and returns incoming articles by date.
     * For sorting used {@link ArticleComparator.ByDate} comparator.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> sortByDate(final Collection<Article> articles, final boolean revers) {
        final Comparator<Article> comparator = new ArticleComparator.ByDate();
        return sort(articles, comparator, revers);
    }

    /**
     * Sorts and returns incoming articles by price.
     * For sorting used {@link ArticleComparator.ByPrice} comparator.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles or empty list (newer null).
     * @see ArticleComparator.ByPrice
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> sortByPrice(final Collection<Article> articles, final boolean revers) {
        final Comparator<Article> comparator = new ArticleComparator.ByPrice();
        return sort(articles, comparator, revers);
    }

    /**
     * Sorts and returns all articles by number.
     * For sorting used {@link ArticleComparator.ByNumber} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles or empty list (newer null).
     * @see ArticleComparator.ByNumber
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getAndSortByNumber(final boolean revers) {
        final Collection<Article> articles = getAll();
        return sortByNumber(articles, revers);
    }

    /**
     * Sorts and returns all articles by date.
     * For sorting used {@link ArticleComparator.ByDate} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles or empty list (newer null).
     * @see ArticleComparator.ByDate
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getAndSortByDate(final boolean revers) {
        final Collection<Article> articles = getAll();
        return sortByDate(articles, revers);
    }

    /**
     * Sorts and returns all articles by price.
     * For sorting used {@link ArticleComparator.ByPrice} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles or empty list (newer null).
     * @see ArticleComparator.ByPrice
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getAndSortByPrice(final boolean revers) {
        final Collection<Article> articles = getAll();
        return sortByPrice(articles, revers);
    }

    /**
     * Filters and returns incoming articles by the date.
     * Return empty list if responses is empty.
     * Return back responses list if dates are null
     * or start date is equals to end finish date.
     * <pre>
     *     filterByDate(null, ..., ...) = empty ArrayList()
     *     filterByDate(new ArrayList(), ..., ...) = empty ArrayList()
     *
     *     Collection collection = new ArrayList();
     *     collection.add(new Article());
     *     filterByDate(collection, null, null) = collection
     *     filterByDate(collection, new Date(), null) = collection
     *     filterByDate(collection, null, new Date()) = collection
     *
     *     Date start = new Date();
     *     Date finish = new Date();
     *     if start date greater than finish date:
     *     filterByDate(collection, start, finish) = collection
     *     if finish date greater than start date:
     *     filterByDate(collection, start, finish) = filtered list of articles
     * </pre>
     *
     * @param articles the articles to filter.
     * @param start    the initial date.
     * @param finish   the final date.
     * @return The filtered / no filtered list of articles or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> filterByDate(
            final Collection<Article> articles,
            final Date start, final Date finish
    ) {
        final List<Article> result = new ArrayList<>();
        if (isNotEmpty(articles)) {
            if (checkDate(start, finish)) {
                result.addAll(
                        articles.stream()
                                .filter(article -> timeFilter(article, start, finish))
                                .collect(Collectors.toList())
                );
            } else {
                result.addAll(articles);
            }
        }
        return result;
    }

    /**
     * Filters and returns articles by the incoming category.
     * <pre>
     *     filterByCategory(null, null) = empty ArrayList()
     *     filterByCategory(null, new Category()) = empty ArrayList()
     *
     *     Collection articles = new ArrayList();
     *     articles.add(new Article());
     *     filterByCategory(articles, new Category()) = filtered list of articles
     * </pre>
     *
     * @param articles the articles to filter.
     * @param category the category filtering.
     * @return The filtered list of articles or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Article> filterByCategory(final Collection<Article> articles, final Category category) {
        final List<Category> categories = new ArrayList<>(1);
        if (isNotNull(category)) {
            categories.add(category);
        }
        return filterByCategories(articles, categories);
    }

    /**
     * Filters and returns articles by the categories.
     * Returns empty list if articles is empty.
     * Returns back articles if categories is empty.
     * <pre>
     *     filterByCategories(null, null) = empty ArrayList()
     *
     *     Collection categories = new ArrayList();
     *     categories.add(new Category());
     *     filterByCategories(null, categories) = empty ArrayList()
     *
     *     Collection articles = new ArrayList();
     *     articles.add(new Article());
     *     filterByCategories(articles, null) = articles
     *
     *     filterByCategories(articles, categories) = filtered list of articles
     * </pre>
     *
     * @param articles   the articles to filter.
     * @param categories the categories filtering.
     * @return The filtered list of articles or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Article> filterByCategories(
            final Collection<Article> articles,
            final Collection<Category> categories
    ) {
        final List<Article> result = new ArrayList<>();
        if (isNotEmpty(articles)) {
            if (isNotEmpty(categories)) {
                for (Article article : articles) {
                    result.addAll(
                            categories.stream()
                                    .filter(category -> categoryFilter(article, category))
                                    .map(category -> article)
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
     * <pre>
     *     getAndFilterByDate(null, null) = all articles
     *     getAndFilterByDate(new Date(), null) = all articles
     *     getAndFilterByDate(null, new Date()) = all articles
     *
     *     Date startDate = new Date();
     *     Date finishDate = new Date();
     *     if start date greater than finish date:
     *     filterByDate(start, finish) = all articles
     *     if finish date greater than start date:
     *     filterByDate(start, finish) = filtered list of articles
     * </pre>
     *
     * @param start  the initial date.
     * @param finish the final date.
     * @return The filtered list of articles or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Article> getAndFilterByDate(final Date start, final Date finish) {
        final Collection<Article> articles = getAll();
        return filterByDate(articles, start, finish);
    }

    /**
     * Filters and returns articles by the incoming category.
     * <pre>
     *     getAndFilterByCategory(null) = all articles
     *     getAndFilterByCategory(new Category()) = filtered list of articles
     * </pre>
     *
     * @param category the category filtering.
     * @return The filtered list of articles or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Article> getAndFilterByCategory(final Category category) {
        final Collection<Article> articles = getAll();
        return filterByCategory(articles, category);
    }

    /**
     * Filters and returns articles by the categories.
     * <pre>
     *     getAndFilterByCategories(null) = all articles
     *     getAndFilterByCategories(new ArrayList()) = all articles
     *
     *     Collection categories = new ArrayList();
     *     categories.add(new Category());
     *     getAndFilterByCategories(categories) = filtered list of articles
     * </pre>
     *
     * @param categories the categories filtering.
     * @return The filtered list of articles or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Article> getAndFilterByCategories(final Collection<Category> categories) {
        final Collection<Article> articles = getAll();
        return filterByCategories(articles, categories);
    }

    /**
     * Returns a list valid articles.
     * Returns empty list if articles is empty.
     * <pre>
     *     filteredByValid(null) = empty ArrayList()
     *     filteredByValid(new ArrayList()) = empty ArrayList()
     *
     *     Collection articles = new ArrayList();
     *     Article article = new Article();
     *     article.setValidated(false);
     *     articles.add(article);
     *     filteredByValid(articles) = empty ArrayList()
     *
     *     article.setValidated(true);
     *     filteredByValid(articles) = filtered list of articles
     * </pre>
     *
     * @param articles the articles to filter.
     * @return The filtered list of articles or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Article> filterByValid(final Collection<Article> articles) {
        final List<Article> result = new ArrayList<>();
        if (isNotEmpty(articles)) {
            result.addAll(
                    articles.stream()
                            .filter(this::isValidated)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Removes article with the incoming number.
     * Removes article if the incoming number
     * is not null and not empty.
     *
     * @param number the number of a article to remove.
     * @return true if model is deleted, false otherwise.
     */
    @Override
    @Transactional
    public boolean removeByNumber(final String number) {
        final boolean result = isNotEmpty(number);
        if (result) {
            this.repository.deleteByNumber(number);
        }
        return result;
    }

    /**
     * Return Class object of {@link Article} class.
     *
     * @return The Class object of {@link Article} class.
     */
    @Override
    Class<Article> getModelClass() {
        return Article.class;
    }

    /**
     * Checks a dates to correction.
     * Dates must be not null and not equals between themselves.
     * Used the checkDate() method of the {@link Time} class.
     *
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return true if dates are correct, false otherwise.
     */
    private boolean checkDate(final Date startDate, final Date finishDate) {
        return Time.checkDate(startDate, finishDate);
    }

    /**
     * Filters article object date with input dates.
     * Used the checkTime() method of the {@link Time} class.
     *
     * @param article    the article to filter.
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return true if time is correct, false otherwise.
     */
    private boolean timeFilter(final Article article, final Date startDate, final Date finishDate) {
        final Date articleDate = article.getDate();
        return Time.checkTime(articleDate, startDate, finishDate);
    }

    /**
     * Filters article by the incoming category.
     * <pre>
     *  categoryFilter(null, not null) - false
     *  categoryFilter(not null, null) - false
     *
     *  If article category is null:
     *  categoryFilter(not null, not null) - false
     *
     *  If article category is not null and not equals incoming category:
     *  categoryFilter(not null, not null) - false
     *
     *  If article category is not null and equals incoming category:
     *  categoryFilter(not null, not null) - true
     * </pre>
     *
     * @param article  the article to filter.
     * @param category the category filtering.
     * @return true if the article category equals to the incoming category,
     * false otherwise.
     */
    private boolean categoryFilter(final Article article, final Category category) {
        boolean result = isNotNull(article);
        if (result) {
            final Category articleCategory = article.getCategory();
            result = isNotNull(articleCategory) && articleCategory.equals(category);
        }
        return result;
    }
}
