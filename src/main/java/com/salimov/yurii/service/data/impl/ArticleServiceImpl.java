package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.service.data.interfaces.ArticleService;
import com.salimov.yurii.service.data.interfaces.VideoService;
import com.salimov.yurii.util.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.salimov.yurii.dao.interfaces.ArticleDao;
import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.Photo;
import com.salimov.yurii.entity.Video;
import com.salimov.yurii.service.data.interfaces.PhotoService;
import com.salimov.yurii.util.comparator.ArticleComparator;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class of the service layer, implements a set of methods
 * for working with objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
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
     */
    private final ArticleDao dao;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link Photo}.
     *
     * @see PhotoService
     */
    private final PhotoService photoService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link Video}.
     *
     * @see VideoService
     */
    private final VideoService videoService;

    /**
     * Constructor.
     *
     * @param dao          a implementation of
     *                     the {@link ArticleDao} interface.
     * @param photoService a implementation of
     *                     the {@link PhotoService} interface.
     * @param videoService a implementation of
     *                     the {@link VideoService} interface.
     * @see ArticleDao
     * @see PhotoService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ArticleServiceImpl(
            final ArticleDao dao,
            final PhotoService photoService,
            final VideoService videoService
    ) {
        super(dao);
        this.dao = dao;
        this.photoService = photoService;
        this.videoService = videoService;
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
     * @param mainPhoto   a photo of the new article.
     * @param slides      a slides of the new article.
     * @param videos      a videos of the new article.
     * @param isValid     a value of validations of the model.
     * @return The new saving article.
     * @see Article
     * @see Category
     * @see Photo
     * @see Video
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
            final MultipartFile mainPhoto,
            final MultipartFile[] slides,
            final String videos,
            final boolean isValid
    ) {
        final Article article = new Article();
        article.initialize(
                title,
                description,
                text,
                keywords,
                number,
                category
        );
        article.setValidated(isValid);
        article.setMainPhoto(
                updatePhoto(
                        new Photo(),
                        mainPhoto,
                        title
                )
        );
        updateSlides(
                article,
                slides,
                title
        );
        addVideos(article, videos);
        return add(article);
    }

    /**
     * Initializes, updates and returns article with parameter id.
     * Returns {@code null} if id is {@code null}.
     *
     * @param url          a url of the article to update.
     * @param title        a new title to the article.
     * @param description  a new description to the article.
     * @param text         a new text to the article.
     * @param keywords     a new keywords to the article.
     * @param number       a new number to the article.
     * @param category     a new category to the article.
     * @param mainFile     a new photo to the article.
     * @param photoAction  a action on the main photo.
     * @param slideFiles   a new slides to the article.
     * @param slidesAction a action on the slides.
     * @param videoUrls    a new videos to the article.
     * @param isValid      a validated of the article.
     * @return The updating article with parameter id or {@code null}.
     * @see Article
     * @see Category
     * @see Photo
     * @see Video
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
            final MultipartFile mainFile,
            final String photoAction,
            final MultipartFile[] slideFiles,
            final String slidesAction,
            final String videoUrls,
            final boolean isValid
    ) {
        final Article article = getByUrl(url, false);
        final Photo mainPhoto = article.getMainPhoto();
        updatesMainPhoto(
                article,
                mainFile,
                title,
                photoAction
        );
        final List<Photo> slides = new ArrayList<>(article.getSlides());
        updatesSlides(
                article,
                title,
                slidesAction,
                slideFiles
        );
        final Collection<Video> videos = new ArrayList<>(article.getVideos());
        updateVideos(
                article,
                videoUrls,
                title
        );
        article.initialize(
                title,
                description,
                text,
                keywords,
                number,
                category
        );
        article.setValidated(isValid);
        final Article upArticle = update(article);
        removeMainPhoto(mainPhoto, photoAction);
        removeSlides(
                article,
                slidesAction,
                slides
        );
        this.videoService.remove(videos);
        return upArticle;
    }

    /**
     * Returns article with the parameter url.
     *
     * @param url     a url of the article to return.
     * @param isValid is get valid article or not.
     * @return The article with the parameter url.
     * @see Article
     */
    @Override
    @Transactional(readOnly = true)
    public Article getByUrl(final String url, final boolean isValid) {
        final Article article = super.getByUrl(url, isValid);
        article.getSlides();
        article.getVideos();
        return article;
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
    public Article getByNumber(final String number, final boolean isValid)
            throws IllegalArgumentException, NullPointerException {
        if (isBlank(number)) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " number is blank!"
            );
        }
        final Article article = this.dao.getByNumber(number);
        if (article == null || (isValid && !article.isValidated())) {
            throw new NullPointerException(
                    "Can`t find article by number \"" + number + "\"!"
            );
        }
        article.getSlides();
        article.getVideos();
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
        return sort(
                articles,
                new ArticleComparator.ByNumber(),
                revers
        );
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
        return sort(
                articles,
                new ArticleComparator.ByDate(),
                revers
        );
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
        List<Article> result = new ArrayList<>();
        if (articles != null && !articles.isEmpty()) {
            if (checkDate(startDate, finishDate)) {
                result.addAll(
                        articles.stream()
                                .filter(
                                        article -> checkTime(
                                                article.getDate(),
                                                startDate,
                                                finishDate
                                        )
                                ).collect(Collectors.toList()));
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
        if (articles != null && !articles.isEmpty()) {
            if (categories != null && !categories.isEmpty()) {
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
        return filterByDate(
                getAll(),
                startDate,
                finishDate
        );
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
        return filterByCategory(
                getAll(),
                category
        );
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
        return filterByCategories(
                getAll(),
                categories
        );
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
    public List<Article> filteredByValid(final Collection<Article> articles) {
        List<Article> result = new ArrayList<>();
        if (articles != null && !articles.isEmpty()) {
            result.addAll(
                    articles.stream()
                            .filter(
                                    article -> (article != null) &&
                                            (article.isValidated())
                            ).collect(Collectors.toList())
            );
        }
        return result;
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
            remove(
                    getByNumber(number, false)
            );
        }
    }

    /**
     * Removes article. Removes article if it is not {@code null}.
     *
     * @param article the article to remove.
     * @see Article
     */
    @Override
    @Transactional
    public void remove(final Article article) {
        if (article != null) {
            removeMainPhoto(article);
            removeSlides(article);
            article.setCategory(null);
            super.remove(article);
        }
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
     * Updates article slides.
     *
     * @param article    the articles to update.
     * @param slideFiles a slides to update.
     * @param title      a slide title.
     */
    private void updateSlides(
            final Article article,
            final MultipartFile[] slideFiles,
            final String title
    ) {
        if (slideFiles != null && slideFiles.length > 0) {
            for (MultipartFile slideFile : slideFiles) {
                if (slideFile != null && !slideFile.isEmpty()) {
                    article.addSlide(
                            updateSlide(
                                    new Photo(),
                                    slideFile,
                                    title
                            )
                    );
                }
            }
        }
    }

    /**
     * Adds video to article.
     *
     * @param article   the articles to update.
     * @param videoUrls a videos to update.
     */
    private static void addVideos(
            final Article article,
            final String videoUrls
    ) {
        if (isNotBlank(videoUrls)) {
            for (String url : videoUrls.replace(" ", "").split(",")) {
                article.addVideo(
                        new Video(null, url)
                );
            }
        }
    }

    /**
     * Remove main photo in selected article.
     *
     * @param article a selected article.
     */
    private void removeMainPhoto(final Article article) {
        if (article.getMainPhoto() != null) {
            this.photoService.deleteFile(
                    article.getMainPhoto().getUrl()
            );
        }
    }

    /**
     * Remove slides in selected article.
     *
     * @param article a selected article.
     */
    private void removeSlides(final Article article) {
        if (!article.getSlides().isEmpty()) {
            for (Photo slide : article.getSlides()) {
                this.photoService.deleteFile(
                        slide.getUrl()
                );
            }
        }
    }

    /**
     * Checks on the correct date.
     *
     * @param startDate  a initial date.
     * @param finishDate a final date.
     * @return {@code true} if dates are correct, {@code false} otherwise.
     */
    private static boolean checkDate(
            final Date startDate,
            final Date finishDate
    ) {
        return (startDate != null) &&
                (finishDate != null) &&
                (startDate.getTime() <= finishDate.getTime());
    }

    /**
     * Checks whether the current time is included in the interval.
     *
     * @param currentTime a time to checks.
     * @param startDate   a initial date.
     * @param finishDate  a final date.
     * @return {@code true} if time is correct, {@code false} otherwise.
     */
    private static boolean checkTime(
            final Date currentTime,
            final Date startDate,
            final Date finishDate
    ) {
        final long time = currentTime.getTime();
        final long startTime = startDate.getTime();
        final long finishTime = finishDate.getTime();
        return (time >= startTime) && (time <= finishTime);
    }

    /**
     * Updates the article main photo.
     *
     * @param article     the articles to update.
     * @param title       a photo title.
     * @param photoAction a action on the main photo.
     * @param file        a photo file.
     */
    private void updatesMainPhoto(
            final Article article,
            final MultipartFile file,
            final String title,
            final String photoAction
    ) {
        switch (photoAction) {
            case "replace":
                article.setMainPhoto(
                        updatePhoto(
                                article.getMainPhoto(),
                                file,
                                title
                        )
                );
                break;
            case "delete":
                article.setMainPhoto(null);
                break;
        }
    }

    /**
     * Updates the article slides.
     *
     * @param article the articles to update.
     * @param title   a slide title.
     * @param action  a action on the slides.
     * @param files   a slide files.
     */
    private void updatesSlides(
            final Article article,
            final String title,
            final String action,
            final MultipartFile[] files
    ) {
        switch (action) {
            case "replace":
                replaceSlides(
                        article,
                        title,
                        files
                );
                break;
            case "add":
                updateSlides(
                        article,
                        files,
                        title
                );
                break;
            case "delete":
                article.setSlides(null);
                break;
        }
    }

    /**
     * Replaces the article slides.
     *
     * @param article the articles to update.
     * @param title   a slide title.
     * @param files   a slide files.
     */
    private void replaceSlides(
            final Article article,
            final String title,
            final MultipartFile[] files
    ) {
        if (files != null && files.length > 0) {
            final List<Photo> slides = new ArrayList<>();
            for (MultipartFile slideFile : files) {
                if (slideFile != null && !slideFile.isEmpty()) {
                    slides.add(
                            updateSlide(
                                    null,
                                    slideFile,
                                    title
                            )
                    );
                }
            }
            if (!slides.isEmpty()) {
                article.setSlides(slides);
            }
        }
    }

    /**
     * Updates the photo.
     *
     * @param photo the photo to update.
     * @param file  a photo file.
     * @param title a photo title.
     * @return The updating photo.
     */
    private Photo updatePhoto(
            final Photo photo,
            final MultipartFile file,
            final String title
    ) {
        return updatePhotoFile(
                photo,
                file,
                Translator.fromCyrillicToLatin(title) + " photo"
        );
    }

    /**
     * Updates slide.
     *
     * @param slide the slide to update.
     * @param file  a slide file.
     * @param title a slide title.
     * @return The updating slide.
     */
    private Photo updateSlide(
            final Photo slide,
            final MultipartFile file,
            final String title
    ) {
        return updatePhotoFile(
                slide,
                file,
                Translator.fromCyrillicToLatin(title) + " slide"
        );
    }

    /**
     * Updates photo.
     *
     * @param photo the photo to updates.
     * @param file  a photo file.
     * @param title a photo title.
     * @return The updating photo.
     */
    private Photo updatePhotoFile(
            final Photo photo,
            final MultipartFile file,
            final String title
    ) {
        return this.photoService.updatePhoto(
                photo != null ? photo : new Photo(),
                file,
                title,
                "articles"
        );
    }

    /**
     * Removes main photo if action equals "delete".
     *
     * @param photo  the photo to remove.
     * @param action a action on the photo.
     */
    private void removeMainPhoto(
            final Photo photo,
            final String action
    ) {
        if (action.equals("delete")) {
            this.photoService.remove(photo);
        }
    }

    /**
     * Removes the slides if are contains in the article.
     *
     * @param article      the articles to update.
     * @param slidesAction a action on the slides.
     * @param slides       the slides to remove.
     */
    private void removeSlides(
            final Article article,
            final String slidesAction,
            final List<Photo> slides
    ) {
        if (slidesAction.equals("delete") || slidesAction.equals("replace")) {
            slides.stream()
                    .filter(slide -> !article.containsSlide(slide))
                    .forEach(this.photoService::remove);
        }
    }

    /**
     * Updates the article videos.
     *
     * @param article   the articles to update.
     * @param videoUrls a video urls.
     * @param title     a video title.
     */
    private static void updateVideos(
            final Article article,
            final String videoUrls,
            final String title
    ) {
        if (isNotBlank(videoUrls)) {
            Collection<Video> videos = new ArrayList<>();
            for (String videoUrl : videoUrls.replace(" ", "").split(",")) {
                videos.add(
                        new Video(title, videoUrl)
                );
            }
            if (!videos.isEmpty()) {
                article.setVideos(videos);
            }
        }
    }
}
