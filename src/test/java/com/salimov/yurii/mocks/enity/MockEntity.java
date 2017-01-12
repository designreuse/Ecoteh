package com.salimov.yurii.mocks.enity;

import com.salimov.yurii.entity.*;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.salimov.yurii.mocks.MockConstants.*;

@Ignore
public final class MockEntity {

    private final static Random random = new Random(System.nanoTime());

    public static Article getArticle() {
        final Article article = new Article(
                TITLE, DESCRIPTION,
                TEXT, KEYWORDS, NUMBER
        );
        article.setId(ID);
        article.setUrl(URL);
        return article;
    }

    public static Category getCategory() {
        final Category category = new Category(TITLE, DESCRIPTION, KEYWORDS);
        category.setId(ID);
        category.setUrl(URL);
        return category;
    }

    public static Company getCompany() {
        final Company company = new Company();
        company.initialize(
                TITLE, DOMAIN, TAGLINE, DESCRIPTION,
                INFORMATION, ADVANTAGES, PHONE, PHONE,
                PHONE, EMAIL, EMAIL, PASSWORD, VKONTAKTE,
                FACEBOOK, TWITTER, SKYPE, ADDRESS, KEYWORDS,
                GOOGLE_MAPS, null, null
        );
        company.setUrl(URL);
        company.setWorkTimeFrom(TIME);
        company.setWorkTimeTo(TIME);
        return company;
    }

    public static Message getMessage() {
        final Message message = new Message(
                NAME, EMAIL, PHONE,
                ANY_STRING, TEXT
        );
        message.setId(ID);
        return message;
    }

    public static Photo getPhoto() {
        final Photo photo = new Photo(TITLE, URL);
        photo.setId(ID);
        return photo;
    }

    public static Response getResponse() {
        final Response response = new Response(NAME, TEXT);
        response.setId(ID);
        return response;
    }

    public static User getUser() {
        final User user = new User();
        user.setId(ID);
        user.initialize(
                NAME, LOGIN, PASSWORD, EMAIL, PHONE,
                VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                DESCRIPTION
        );
        user.setRole(USER_ROLE);
        return user;
    }

    public static Video getVideo() {
        final Video video = new Video(TITLE, URL);
        video.setId(ID);
        return video;
    }

    public static List<Article> getArticles() {
        return getArticles(DEFAULT_SIZE);
    }

    public static List<Article> getArticles(final int size) {
        final List<Article> articles = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final Article article = getArticle();
            article.setId((long) i);
            article.setTitle(article.getTitle() + " " + getRandomInt());
            article.newNumber();
            articles.add(article);
        }
        return articles;
    }

    public static List<Category> getCategories() {
        return getCategories(DEFAULT_SIZE);
    }

    public static List<Category> getCategories(final int size) {
        final List<Category> categories = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final Category category = getCategory();
            category.setId((long) i);
            category.setTitle(category.getTitle() + " " + getRandomInt());
            categories.add(category);
        }
        return categories;
    }

    public static List<Company> getCompanies() {
        return getCompanies(DEFAULT_SIZE);
    }

    public static List<Company> getCompanies(final int size) {
        final List<Company> companies = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final Company company = getCompany();
            company.setId((long) i);
            company.setTitle(company.getTitle() + " " + getRandomInt());
            companies.add(company);
        }
        return companies;
    }

    public static List<Message> getMessages() {
        return getMessages(DEFAULT_SIZE);
    }

    public static List<Message> getMessages(final int size) {
        final List<Message> messages = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final Message message = getMessage();
            message.setId((long) i);
            message.setSubject(message.getSubject() + " " + getRandomInt());
            messages.add(message);
        }
        return messages;
    }

    public static List<Photo> getPhotos() {
        return getPhotos(DEFAULT_SIZE);
    }

    public static List<Photo> getPhotos(final int size) {
        final List<Photo> photos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final Photo photo = getPhoto();
            photo.setId((long) i);
            photo.setTitle(photo.getTitle() + " " + getRandomInt());
            photos.add(photo);
        }
        return photos;
    }

    public static List<Response> getResponses() {
        return getResponses(DEFAULT_SIZE);
    }

    public static List<Response> getResponses(final int size) {
        final List<Response> responses = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final Response response = getResponse();
            response.setId((long) i);
            response.setUsername(response.getUsername() + " " + getRandomInt());
            responses.add(response);
        }
        return responses;
    }

    public static List<User> getUsers() {
        return getUsers(DEFAULT_SIZE);
    }

    public static List<User> getUsers(final int size) {
        final List<User> users = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final User user = getUser();
            user.setId((long) i);
            user.setName(user.getName() + " " + getRandomInt());
            user.setLogin(user.getLogin() + " " + getRandomInt());
            user.setPassword(user.getPassword() + " " + getRandomInt());
            users.add(user);
        }
        return users;
    }

    public static List<Video> getVideos() {
        return getVideos(DEFAULT_SIZE);
    }

    public static List<Video> getVideos(final int size) {
        final List<Video> videos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final Video video = getVideo();
            video.setId((long) i);
            video.setTitle(video.getTitle() + " " + getRandomInt());
            videos.add(video);
        }
        return videos;
    }

    public static int getRandomInt(final int length) {
        int number = 0;
        for (int i = 0; i < (length > 0 ? length : 10); i++) {
            number += (random.nextInt(8) + 1) * (int) Math.pow(10, i);
        }
        return number;
    }

    public static int getRandomInt() {
        return getRandomInt(10);
    }
}
