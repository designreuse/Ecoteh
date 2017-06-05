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
import ua.com.ecoteh.util.comparator.ArticleComparator;
import ua.com.ecoteh.util.time.Time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, implements a set of methods
 * for working with objects of the {@link ArticleEntity} class.
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
public final class ArticleServiceImpl
        extends ContentServiceImpl<Article, ArticleEntity>
        implements ArticleService {

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
     * Returns articleEntity with the incoming number.
     * If a incoming number is null or empty then throws IllegalArgumentException.
     * If can`t find articleEntity by incoming number then throws NullPointerException.
     *
     * @param number  the number of an articleEntity to return.
     * @param isValid is get valid articleEntity or not.
     * @return The articleEntity with the incoming number (newer null).
     * @throws IllegalArgumentException Throw exception when incoming number is blank.
     * @throws NullPointerException     Throw exception when articleEntity with parameter id is not exist.
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
        final ArticleEntity articleEntity = this.repository.findByNumber(number);
        if (isNotValidated(articleEntity, isValid)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), number
            );
        }
        articleEntity.getCategoryEntity().getArticleEntities();
        return articleEntity.convert();
    }

    /**
     * Returns articleEntity with the incoming categoryEntity id.
     *
     * @param id the categoryEntity id of articleEntities to return.
     * @return The list of articleEntities or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Article> getByCategoryId(final long id) {
        final Collection<ArticleEntity> entities = this.repository.findByCategoryEntityId(id);
        return convertToModels(entities);
    }

    /**
     * Returns articleEntity with the categoryEntity title.
     * If a incoming title is null or empty then throws IllegalArgumentException.
     *
     * @param title the categoryEntity title of the articleEntities to return.
     * @return The list of articleEntities or empty list (newer null).
     * @throws IllegalArgumentException Throw exception when parameter categoryTitle is blank.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Article> getByCategoryTitle(final String title)
            throws IllegalArgumentException {
        if (isEmpty(title)) {
            throw getIllegalArgumentException(ExceptionMessage.BLANK_CATEGORY_TITLE_MESSAGE);
        }
        final Collection<ArticleEntity> entities = this.repository.findByCategoryEntityTitle(title);
        return convertToModels(entities);
    }

    /**
     * Sorts and returns articleEntities by number.
     * For sorting used {@link ArticleComparator.ByNumber} comparator.
     *
     * @param articleEntities the articleEntities to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articleEntities or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> sortByNumber(
            final Collection<Article> articleEntities,
            final boolean revers
    ) {
        return sort(articleEntities, new ArticleComparator.ByNumber(), revers);
    }

    /**
     * Sorts and returns incoming articleEntities by date.
     * For sorting used {@link ArticleComparator.ByDate} comparator.
     *
     * @param articleEntities the articleEntities to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articleEntities or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> sortByDate(
            final Collection<Article> articleEntities,
            final boolean revers
    ) {
        return sort(articleEntities, new ArticleComparator.ByDate(), revers);
    }

    /**
     * Sorts and returns all articleEntities by number.
     * For sorting used {@link ArticleComparator.ByNumber} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articleEntities or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getAndSortByNumber(final boolean revers) {
        return sortByNumber(getAll(), revers);
    }

    /**
     * Sorts and returns all articleEntities by date.
     * For sorting used {@link ArticleComparator.ByDate} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articleEntities or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getAndSortByDate(final boolean revers) {
        return sortByDate(getAll(), revers);
    }

    /**
     * Filters and returns incoming articleEntities by the date.
     * Return empty list if responses is empty.
     * Return back responses list if dates are null
     * or start date is equals to end finish date.
     * <pre>
     *     filterByDate(null, ..., ...) = empty ArrayList()
     *     filterByDate(new ArrayList(), ..., ...) = empty ArrayList()
     *
     *     Collection collection = new ArrayList();
     *     collection.add(new ArticleEntity());
     *     filterByDate(collection, null, null) = collection
     *     filterByDate(collection, new Date(), null) = collection
     *     filterByDate(collection, null, new Date()) = collection
     *
     *     Date startDate = new Date();
     *     Date finishDate = new Date();
     *     if startDate greater than finishDate:
     *     filterByDate(collection, startDate, finishDate) = collection
     *     if finishDate greater than startDate:
     *     filterByDate(collection, startDate, finishDate) = filtered list of articleEntities
     * </pre>
     *
     * @param articles   the articleEntities to filter.
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return The filtered / no filtered list of articleEntities or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> filterByDate(
            final Collection<Article> articles,
            final Date startDate,
            final Date finishDate
    ) {
        final List<Article> result = new ArrayList<>();
        if (isNotEmpty(articles)) {
            if (checkDate(startDate, finishDate)) {
                result.addAll(
                        articles.stream()
                                .filter(article -> timeFilter(article, startDate, finishDate))
                                .collect(Collectors.toList())
                );
            } else {
                result.addAll(articles);
            }
        }
        return result;
    }

    /**
     * Filters and returns articleEntities by the incoming categoryEntity.
     * <pre>
     *     filterByCategory(null, null) = empty ArrayList()
     *     filterByCategory(null, new CategoryEntity()) = empty ArrayList()
     *
     *     Collection articleEntities = new ArrayList();
     *     articleEntities.add(new ArticleEntity());
     *     filterByCategory(articleEntities, new CategoryEntity()) = filtered list of articleEntities
     * </pre>
     *
     * @param articles the articleEntities to filter.
     * @param category the categoryEntity filtering.
     * @return The filtered list of articleEntities or empty list (newer null).
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
     * Filters and returns articleEntities by the categories.
     * Returns empty list if articleEntities is empty.
     * Returns back articleEntities if categories is empty.
     * <pre>
     *     filterByCategories(null, null) = empty ArrayList()
     *
     *     Collection categories = new ArrayList();
     *     categories.add(new CategoryEntity());
     *     filterByCategories(null, categories) = empty ArrayList()
     *
     *     Collection articleEntities = new ArrayList();
     *     articleEntities.add(new ArticleEntity());
     *     filterByCategories(articleEntities, null) = articleEntities
     *
     *     filterByCategories(articleEntities, categories) = filtered list of articleEntities
     * </pre>
     *
     * @param articles   the articleEntities to filter.
     * @param categories the categories filtering.
     * @return The filtered list of articleEntities or empty list (newer null).
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
     * Filters and returns articleEntities by the date.
     * <pre>
     *     getAndFilterByDate(null, null) = all articleEntities
     *     getAndFilterByDate(new Date(), null) = all articleEntities
     *     getAndFilterByDate(null, new Date()) = all articleEntities
     *
     *     Date startDate = new Date();
     *     Date finishDate = new Date();
     *     if startDate greater than finishDate:
     *     filterByDate(startDate, finishDate) = all articleEntities
     *     if finishDate greater than startDate:
     *     filterByDate(startDate, finishDate) = filtered list of articleEntities
     * </pre>
     *
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return The filtered list of articleEntities or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Article> getAndFilterByDate(final Date startDate, final Date finishDate) {
        return filterByDate(getAll(), startDate, finishDate);
    }

    /**
     * Filters and returns articleEntities by the incoming categoryEntity.
     * <pre>
     *     getAndFilterByCategory(null) = all articleEntities
     *     getAndFilterByCategory(new CategoryEntity()) = filtered list of articleEntities
     * </pre>
     *
     * @param category the categoryEntity filtering.
     * @return The filtered list of articleEntities or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Article> getAndFilterByCategory(final Category category) {
        return filterByCategory(getAll(), category);
    }

    /**
     * Filters and returns articleEntities by the categories.
     * <pre>
     *     getAndFilterByCategories(null) = all articleEntities
     *     getAndFilterByCategories(new ArrayList()) = all articleEntities
     *
     *     Collection categories = new ArrayList();
     *     categories.add(new CategoryEntity());
     *     getAndFilterByCategories(categories) = filtered list of articleEntities
     * </pre>
     *
     * @param categories the categories filtering.
     * @return The filtered list of articleEntities or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Article> getAndFilterByCategories(final Collection<Category> categories) {
        return filterByCategories(getAll(), categories);
    }

    /**
     * Returns a list valid articleEntities.
     * Returns empty list if articleEntities is empty.
     * <pre>
     *     filteredByValid(null) = empty ArrayList()
     *     filteredByValid(new ArrayList()) = empty ArrayList()
     *
     *     Collection articleEntities = new ArrayList();
     *     ArticleEntity articleEntity = new ArticleEntity();
     *     articleEntity.setValidated(false);
     *     articleEntities.add(articleEntity);
     *     filteredByValid(articleEntities) = empty ArrayList()
     *
     *     articleEntity.setValidated(true);
     *     filteredByValid(articleEntities) = filtered list of articleEntities
     * </pre>
     *
     * @param articles the articleEntities to filter.
     * @return The filtered list of articleEntities or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Article> filteredByValid(final Collection<Article> articles) {
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
     * Removes articleEntity with incoming id.
     *
     * @param id the id of an articleEntity to remove.
     */
    @Override
    @Transactional
    public void remove(final long id) {
        remove(get(id));
    }

    /**
     * Removes articleEntity with the incoming title.
     * Removes content if title is not blank.
     *
     * @param title the title of an articleEntity to remove.
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotEmpty(title)) {
            remove(getByTitle(title, false));
        }
    }

    /**
     * Removes articleEntity with the incoming URL.
     * Removes articleEntity if the incoming URL
     * is not null and not empty.
     *
     * @param url the URL of a articleEntity to remove.
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotEmpty(url)) {
            remove(getByUrl(url, false));
        }
    }

    /**
     * Removes articleEntity with the incoming number.
     * Removes articleEntity if the incoming number
     * is not null and not empty.
     *
     * @param number the number of a articleEntity to remove.
     */
    @Override
    @Transactional
    public void removeByNumber(final String number) {
        if (isNotEmpty(number)) {
            remove(getByNumber(number, false));
        }
    }

    /**
     * Removes all articleEntities.
     */
    @Override
    @Transactional
    public void removeAll() {
        getAll(false).forEach(this::remove);
    }

    /**
     * Return Class object of {@link ArticleEntity} class.
     *
     * @return The Class object of {@link ArticleEntity} class.
     */
    @Override
    protected Class<Article> getModelClass() {
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
     * Filters articleEntity object date with input dates.
     * Used the checkTime() method of the {@link Time} class.
     *
     * @param article    the articleEntity to filter.
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return true if time is correct, false otherwise.
     */
    private boolean timeFilter(final Article article, final Date startDate, final Date finishDate) {
        return Time.checkTime(article.getDate(), startDate, finishDate);
    }

    /**
     * Filters articleEntity by the incoming categoryEntity.
     * Incoming articleEntity must be not null.
     *
     * @param article  the articleEntity to filter.
     * @param category the categoryEntity filtering.
     * @return true if articleEntity categoryEntity equals to incoming categoryEntity,
     * false otherwise.
     */
    private boolean categoryFilter(final Article article, final Category category) {
        return isNotNull(article) && isNotNull(article.getCategory()) &&
                article.getCategory().equals(category);
    }
}
