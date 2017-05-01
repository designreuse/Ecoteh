package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.Article;
import ua.com.ecoteh.entity.Category;
import ua.com.ecoteh.repository.ArticleRepository;
import ua.com.ecoteh.util.comparator.ArticleComparator;
import ua.com.ecoteh.util.time.Time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.isEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class of the service layer, implements a set of methods
 * for working with objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.service.data"
        }
)
public final class ArticleServiceImpl extends ContentServiceImpl<Article> implements ArticleService {

    /**
     *
     */
    private final static String BLANK_NUMBER_MESSAGE = "Incoming %s number is blank!";

    /**
     *
     */
    private final static String FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE =
            "Can`t find object of the %s class by incoming number %s!";

    /**
     *
     */
    private final static String CATEGORY_TITLE_IS_BKANK_MESSAGE =
            "Incoming category title is blank!";

    /**
     * The interface provides a set of standard methods
     * for working {@link Article} objects a the database.
     */
    private final ArticleRepository repository;

    /**
     * Constructor.
     *
     * @param repository  a implementation of the {@link ArticleRepository} interface.
     * @param fileService a implementation of the {@link FileService} interface.
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
     * Returns article with the parameter number.
     *
     * @param number  a title of the article to return.
     * @param isValid is get valid article or not.
     * @return The article with the parameter number.
     * @throws IllegalArgumentException Throw exception when parameter number is blank.
     * @throws NullPointerException     Throw exception when article with parameter id is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public Article getByNumber(
            final String number,
            final boolean isValid
    ) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(number)) {
            throw new IllegalArgumentException(
                    String.format(BLANK_NUMBER_MESSAGE, getClassSimpleName())
            );
        }
        final Article article = this.repository.findByNumber(number);
        if (notValidFilter(article, isValid)) {
            throw new NullPointerException(
                    String.format(
                            FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName(), number
                    )
            );
        }
        article.getCategory().getArticles();
        return article;
    }

    /**
     * Returns article with the category id.
     *
     * @param id a category id of the article to return.
     * @return The object of class {@link Article}.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getByCategoryId(final long id) {
        return this.repository.findByCategoryId(id);
    }

    /**
     * Returns article with the category title.
     *
     * @param title a category title of the articles to return.
     * @return The object of class {@link Article}.
     * @throws IllegalArgumentException Throw exception when parameter categoryTitle is blank.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getByCategoryTitle(final String title)
            throws IllegalArgumentException {
        if (isEmpty(title)) {
            throw new IllegalArgumentException(CATEGORY_TITLE_IS_BKANK_MESSAGE);
        }
        return this.repository.findByCategoryTitle(title);
    }

    /**
     * Sorts and returns articles by number.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles.
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
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getAndSortByDate(final boolean revers) {
        return sortByDate(getAll(), revers);
    }

    /**
     * Filters and returns articles by the date.
     * Return empty list if responses is empty.
     * Return back responses list if dates are null
     * or start date is equals to end finish date.
     *
     * @param articles   the articles to filter.
     * @param startDate  a initial date.
     * @param finishDate a final date.
     * @return The filtered list of articles.
     */
    @Override
    @Transactional
    public List<Article> filterByDate(
            final Collection<Article> articles,
            final Date startDate,
            final Date finishDate
    ) {
        final List<Article> result = new ArrayList<>();
        if (isNotEmpty(articles)) {
            if (Time.checkDate(startDate, finishDate)) {
                result.addAll(
                        articles.stream()
                                .filter(
                                        article -> timeFilter(article, startDate, finishDate)
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
                                    .filter(
                                            category -> categoryFilter(article, category)
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
     */
    @Override
    @Transactional
    public List<Article> filteredByValid(
            final Collection<Article> articles
    ) {
        List<Article> result = new ArrayList<>();
        if (isNotEmpty(articles)) {
            result.addAll(
                    articles.stream()
                            .filter(ArticleServiceImpl::validFilter)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Removes article with parameter id.
     *
     * @param id a id of article to remove.
     */
    @Override
    @Transactional
    public void remove(final long id) {
        remove(get(id));
    }

    /**
     * Removes article with the parameter title.
     * Removes content if title is not blank.
     *
     * @param title a title of the article to remove.
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotEmpty(title)) {
            remove(getByTitle(title, false));
        }
    }

    /**
     * Removes article with the parameter url.
     *
     * @param url a URL of the article to remove.
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotEmpty(url)) {
            remove(getByUrl(url, false));
        }
    }

    /**
     * Removes article with the parameter number.
     * Removes article if number is not blank.
     *
     * @param number a number of the article to remove.
     */
    @Override
    @Transactional
    public void removeByNumber(final String number) {
        if (isNotEmpty(number)) {
            remove(getByNumber(number, false));
        }
    }

    /**
     * Removes article.
     * Removes article if it is not null.
     *
     * @param article the article to remove.
     */
    @Override
    @Transactional
    public void remove(final Article article) {
        if (isNotNull(article)) {
            article.setCategory(null);
            super.remove(article);
        }
    }

    /**
     * Removes all articles.
     */
    @Override
    @Transactional
    public void removeAll() {
        getAll(false).forEach(this::remove);
    }

    /**
     * Copies the object "from" to object "to".
     *
     * @param from a copied object
     * @param to   a object to copy
     */
    @Override
    protected void copy(final Article from, final Article to) {
        to.initialize(from);
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

    /**
     * Filters article object date with input dates.
     *
     * @param article    a article to filter.
     * @param startDate  a initial date.
     * @param finishDate a final date.
     * @return true if time is correct, false otherwise.
     */
    private static boolean timeFilter(
            final Article article,
            final Date startDate,
            final Date finishDate
    ) {
        return Time.checkTime(article.getDate(), startDate, finishDate);
    }

    /**
     * Filters article object category with input category.
     *
     * @param article  a article to filter.
     * @param category a category filtering.
     * @return true if article category equals to input category,
     * false otherwise.
     */
    private static boolean categoryFilter(
            final Article article,
            final Category category
    ) {
        return article.getCategory().equals(category);
    }

    /**
     * Filters article by validation.
     *
     * @param article a article to filter.
     * @return true if article is not null and is valid, false otherwise.
     */
    private static boolean validFilter(final Article article) {
        return isNotNull(article) && article.isValidated();
    }

    /**
     *
     * @param article
     * @param isValid
     * @return
     */
    private static boolean notValidFilter(final Article article, final boolean isValid) {
        return isNotNull(article) || (isValid && !article.isValidated());
    }
}
