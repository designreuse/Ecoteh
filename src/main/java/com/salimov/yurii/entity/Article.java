package com.salimov.yurii.entity;

import javax.persistence.*;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Article} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Content
 * @see Model
 */
@Entity
@Table(name = "articles")
public final class Article extends Content<Long> {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The number of an article.
     */
    @Column(
            name = "number",
            nullable = false,
            unique = true
    )
    private String number;

    /**
     * The text of an article.
     */
    @Column(name = "text")
    private String text;

    /**
     * The date of an article.
     */
    @Column(
            name = "date",
            nullable = false
    )
    private Date date;

    /**
     * The category of an article.
     *
     * @see Category
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private Category category;

    /**
     * The main photo of an article.
     *
     * @see Photo
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "main_photo_id",
            referencedColumnName = "id"
    )
    private Photo mainPhoto;

    /**
     * The set of slides (photos).
     *
     * @see Photo
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "article_photo",
            joinColumns = {
                    @JoinColumn(
                            name = "article_id",
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "photo_id",
                            referencedColumnName = "id"
                    )
            }
    )
    private Set<Photo> slides = new HashSet<>();

    /**
     * The set of videos.
     *
     * @see Video
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "article_video",
            joinColumns = {
                    @JoinColumn(
                            name = "article_id",
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "video_id",
                            referencedColumnName = "id"
                    )
            }
    )
    private Set<Video> videos = new HashSet<>();

    /**
     * Default constructor.
     * Initializes date and number.
     */
    public Article() {
        setDate(new Date());
        newNumber();
    }

    /**
     * Constructor.
     * Initializes a main article parameters.
     *
     * @param title       a title of the new article.
     * @param description a description of the new article.
     * @param text        a text of the new article.
     * @param keywords    a keywords of the new article.
     * @param number      a number of the new article.
     */
    public Article(
            final String title,
            final String description,
            final String text,
            final String keywords,
            final String number
    ) {
        super(title, description, keywords);
        setText(text);
        setDate(new Date());
        setNumber(number);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString()
                + " \nText: " + this.text
                + " \nDate: " + this.date
                + " \nNumber: " + this.number;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return {@code true} if this object is the same as the object
     * argument, {@code false} otherwise otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Article other = (Article) object;
            result = (
                    isNotBlank(this.number) ?
                            this.number.equalsIgnoreCase(other.number) :
                            isBlank(other.number)
            ) && (
                    isNotBlank(this.text) ?
                            this.text.equalsIgnoreCase(other.text) :
                            isBlank(other.text)
            );
        }
        return result;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return super.hashCode() + (
                isNotBlank(this.number) ? this.number.hashCode() : 0
        ) + (
                isNotBlank(this.text) ? this.text.hashCode() : 0
        );
    }

    /**
     * Initializes some parameter of the article.
     *
     * @param title       a new title to the article.
     * @param description a new description to the article.
     * @param text        a new text to the article.
     * @param keywords    a new keywords to the article.
     * @param number      a new number to the article.
     * @param category    a new category to the article.
     * @see Category
     */
    public void initialize(
            final String title,
            final String description,
            final String text,
            final String keywords,
            final String number,
            final Category category
    ) {
        super.initialize(title, description, keywords);
        setText(text);
        setNumber(number);
        setCategory(category);
        setDate(new Date());
    }

    /**
     * Initializes some parameter of the article.
     *
     * @param title       a new title to the article.
     * @param description a new description to the article.
     * @param text        a new text to the article.
     * @param keywords    a new keywords to the article.
     * @param number      a new number to the article.
     * @param category    a new category to the article.
     * @param mainPhoto   a new main photo pf the article.
     * @param slides      a new slides to the article.
     * @param videos      a new videos to the article.
     * @see Category
     * @see Photo
     * @see Video
     */
    public void initialize(
            final String title,
            final String description,
            final String text,
            final String keywords,
            final String number,
            final Category category,
            final Photo mainPhoto,
            final Collection<Photo> slides,
            final Collection<Video> videos
    ) {
        this.initialize(title, description, text, keywords, number, category);
        setMainPhoto(mainPhoto);
        setSlides(slides);
        setVideos(videos);
    }

    /**
     * Returns a number of the article.
     *
     * @return The article number.
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Sets new number to the article.
     * If parameter number is blank, then call method newNumber().
     *
     * @param number a new number to the article.
     */
    public void setNumber(final String number) {
        if (isNotBlank(number)) {
            this.number = number;
        } else {
            newNumber();
        }
    }

    /**
     * Generates new number to the article.
     * To generate used a pattern {@link Model#CODE_PATTERN}
     * and length {@link Model#CODE_LENGTH}.
     */
    public void newNumber() {
        this.number = createRandomString(Model.CODE_PATTERN, Model.CODE_LENGTH);
    }

    /**
     * Returns a text of the article.
     *
     * @return The article text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets a new text to the article.
     * If parameter text is blank, then sets {@code null}.
     *
     * @param text a new text to the article.
     */
    public void setText(final String text) {
        this.text = isNotBlank(text) ? text : null;
    }

    /**
     * Returns a date of the article.
     *
     * @return The article date.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets a new text to the article.
     * If parameter text is blank, then sets {@code new Date()}.
     *
     * @param date a new date to the article.
     */
    public void setDate(final Date date) {
        this.date = date != null ? date : new Date();
    }

    /**
     * Returns an article date in string format.
     *
     * @return The article string-date.
     */
    public String getDateToString() {
        return getDateToString(getDate());
    }

    /**
     * Sets a new category to the article.
     * Sets a new category if this category equals null
     * or this category not equals new category.
     * Also the article deletes from old category and adds to new category.
     *
     * @param category a new category to the article.
     * @see Category
     */
    public void setCategory(final Category category) {
        if (this.category == null || !this.category.equals(category)) {
            final Category temp = this.category;
            this.category = category;
            if (this.category != null && !this.category.containsArticle(this)) {
                this.category.addArticle(this);
            }
            if (temp != null) {
                temp.removeArticle(this);
            }
        }
    }

    /**
     * Returns a category of the article.
     *
     * @return The article category.
     * @see Category
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Returns a main photo of the article.
     *
     * @return The article main photo.
     * @see Photo
     */
    public Photo getMainPhoto() {
        return this.mainPhoto;
    }

    /**
     * Sets a new main photo to the article.
     * If parameter mainPhoto is invalid, then sets {@code null}.
     *
     * @param mainPhoto a new main photo to the article.
     * @see Photo
     */
    public void setMainPhoto(final Photo mainPhoto) {
        this.mainPhoto = Photo.isValidated(mainPhoto) ? mainPhoto : null;
    }

    /**
     * Adds new photo to the list of slides.
     * Adds a new photo, if it is valid.
     *
     * @param slide a photo to add.
     * @see Photo
     */
    public void addSlide(final Photo slide) {
        if (Photo.isValidated(slide)) {
            this.slides.add(slide);
        }
    }

    /**
     * Adds new photos to the list of slides.
     * Adds a new photos, if they are valid.
     *
     * @param slides a photos to add.
     * @see Photo
     */
    public void addSlides(final Collection<Photo> slides) {
        if (slides != null && !slides.isEmpty()) {
            slides.forEach(this::addSlide);
        }
    }

    /**
     * Removes photo from the list of slides.
     *
     * @param slide a photo to remove.
     * @see Photo
     */
    public void removeSlide(final Photo slide) {
        this.slides.remove(slide);
    }

    /**
     * Removes photos from the list of slides.
     *
     * @param slides a photos to remove.
     * @see Photo
     */
    public void removeSlides(final Collection<Photo> slides) {
        this.slides.removeAll(slides);
    }

    /**
     * Clears the list of slides.
     *
     * @see Photo
     */
    public void clearSlides() {
        this.slides = new HashSet<>();
    }

    /**
     * Returns a list of slides.
     *
     * @return The list of slides.
     * @see Photo
     */
    public List<Photo> getSlides() {
        return new ArrayList<>(this.slides);
    }

    /**
     * Sets a new slides to the article.
     * Clears the list of slides and adds new slides.
     *
     * @param slides a slides to add.
     * @see Photo
     */
    public void setSlides(final Collection<Photo> slides) {
        clearSlides();
        addSlides(slides);
    }

    /**
     * Contains slide in the list of slides.
     *
     * @param slide a photo to contain.
     * @return {@code true} if photo is contains, {@code false} otherwise.
     * @see Photo
     */
    public boolean containsSlide(final Photo slide) {
        return this.slides.contains(slide);
    }

    /**
     * Contains slides in the list of slides.
     *
     * @param slides a photos to contain.
     * @return {@code true} if photos are contains, {@code false} otherwise.
     * @see Photo
     */
    public boolean containsSlides(final Collection<Photo> slides) {
        return this.slides.containsAll(slides);
    }

    /**
     * Adds new video to the list of videos.
     * Adds a new video, if it is valid.
     *
     * @param video a video to add.
     * @see Video
     */
    public void addVideo(final Video video) {
        if (Video.isValidated(video)) {
            this.videos.add(video);
        }
    }

    /**
     * Adds new videos to the list of videos.
     * Adds a new videos, if it are valid.
     *
     * @param videos a videos to add.
     * @see Video
     */
    public void addVideos(final Collection<Video> videos) {
        if (videos != null && !videos.isEmpty()) {
            videos.forEach(this::addVideo);
        }
    }

    /**
     * Removes video from the list of videos.
     *
     * @param video a video to remove.
     * @see Video
     */
    public void removeVideo(final Video video) {
        this.videos.remove(video);
    }

    /**
     * Removes videos from the list of videos.
     *
     * @param videos a videos to remove.
     * @see Video
     */
    public void removeVideos(final Collection<Video> videos) {
        this.videos.removeAll(videos);
    }

    /**
     * Clears the list of videos.
     */
    public void clearVideos() {
        this.videos = new HashSet<>();
    }

    /**
     * Returns an list of article videos.
     *
     * @return The list of article videos.
     * @see Video
     */
    public List<Video> getVideos() {
        return new ArrayList<>(this.videos);
    }

    /**
     * Sets a new videos to the article.
     * Clears the list of videos and adds new videos.
     *
     * @param videos a videos to add.
     * @see Video
     */
    public void setVideos(final Collection<Video> videos) {
        clearVideos();
        addVideos(videos);
    }

    /**
     * Contains video in the list of videos.
     *
     * @param video a video to contain.
     * @return {@code true} if video is contains, {@code false} otherwise.
     * @see Video
     */
    public boolean containsVideo(final Video video) {
        return this.videos.contains(video);
    }

    /**
     * Contains videos in the list of videos.
     *
     * @param videos a videos to contain.
     * @return {@code true} if videos are contains, {@code false} otherwise.
     * @see Video
     */
    public boolean containsVideos(final Collection<Video> videos) {
        return this.videos.containsAll(videos);
    }

    /**
     * Statically validates the article.
     * Article is valid if it is a valid content object
     * and it has text (text is not blank).
     *
     * @param article an article to validate.
     * @return {@code true} if the article is valid, {@code false} otherwise.
     * @see Video
     */
    public static boolean isValidated(final Article article) {
        boolean result = false;
        if (Content.isValidated(article)) {
            if (isNotBlank(article.getText())) {
                result = true;
            }
        }
        return result;
    }
}
