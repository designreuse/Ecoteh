package com.salimov.yurii.mocks.enity;

import com.salimov.yurii.entity.*;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.salimov.yurii.mocks.MockConstants.*;

@Ignore
public final class MockEntity {

    private final static Random RANDOM = new Random(System.nanoTime());

    private static Article article;
    private static Category category;
    private static Company company;
    private static File file;
    private static Message message;
    private static Response response;
    private static User user;

    private static List<Article> articles;
    private static List<Category> categories;
    private static List<Company> companies;
    private static List<File> files;
    private static List<Message> messages;
    private static List<Response> responses;
    private static List<User> users;

    public static Article getArticle() {
        if (article == null) {
            initArticle();
        }
        return article.clone();
    }

    public static Category getCategory() {
        if (category == null) {
            initCategory();
        }
        return category.clone();
    }

    public static Company getCompany() {
        if (company == null) {
            initCompany();
        }
        return company.clone();
    }

    public static Message getMessage() {
        if (message == null) {
            initMessage();
        }
        return message.clone();
    }

    public static File getFile() {
        if (file == null) {
            initFile();
        }
        return file.clone();
    }

    public static Response getResponse() {
        if (response == null) {
            initResponse();
        }
        return response.clone();
    }

    public static User getUser() {
        if (user == null) {
            initUser();
        }
        return user.clone();
    }

    public static List<Article> getArticles() {
        if (articles == null || articles.isEmpty()) {
            initArticles();
        }
        return new ArrayList<>(articles);
    }

    public static List<Category> getCategories() {
        if (categories == null || categories.isEmpty()) {
            initCategories();
        }
        return new ArrayList<>(categories);
    }

    public static List<Company> getCompanies() {
        if (companies == null || companies.isEmpty()) {
            initCompanies();
        }
        return new ArrayList<>(companies);
    }

    public static List<Message> getMessages() {
        if (messages == null || messages.isEmpty()) {
            initMessages();
        }
        return new ArrayList<>(messages);
    }

    public static List<File> getFiles() {
        if (files == null || files.isEmpty()) {
            initFiles();
        }
        return new ArrayList<>(files);
    }

    public static List<Response> getResponses() {
        if (responses == null || responses.isEmpty()) {
            initResponses();
        }
        return new ArrayList<>(responses);
    }

    public static List<User> getUsers() {
        if (users == null || users.isEmpty()) {
            initUsers();
        }
        return new ArrayList<>(users);
    }

    public static int getRandomInt(final int length) {
        int number = 0;
        for (int i = 0; i < (length > 0 ? length : 10); i++) {
            number += (RANDOM.nextInt(8) + 1) * (int) Math.pow(10, i);
        }
        return number;
    }

    public static int getRandomInt() {
        return getRandomInt(10);
    }

    private static void initArticle() {
        article = new Article(
                TITLE, DESCRIPTION,
                TEXT, KEYWORDS, NUMBER
        );
        article.setId(ID);
        article.setUrl(URL);
    }

    private static void initCategory() {
        category = new Category(
                TITLE, DESCRIPTION, KEYWORDS
        );
        category.setId(ID);
        category.setUrl(URL);
        category.setPhotoUrl(PHOTO_URL);
        category.addArticle(getArticle());
    }

    private static void initCompany() {
        company = new Company();
        company.setTitle(TITLE);
        company.setDescription(DESCRIPTION);
        company.setInformation(INFORMATION);
        company.setKeywords(KEYWORDS);
        company.setDomain(DOMAIN);
        company.setTagline(TAGLINE);
        company.setLogoUrl(PHOTO_URL);
        company.setId(ID);
        company.setUrl(URL);
        company.setWorkTimeFrom(TIME);
        company.setWorkTimeTo(TIME);
        company.setContacts(
                new Contacts(
                        EMAIL, PHONE, PHONE, PHONE,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE
                )
        );
        company.setAddress(
                new Address(ADDRESS, GOOGLE_MAPS)
        );
    }

    private static void initMessage() {
        message = new Message(getUser(), ANY_STRING, TEXT);
        message.setId(ID);
    }

    private static void initFile() {
        file = new File(TITLE, URL);
        file.setId(ID);
    }

    private static void initResponse() {
        response = new Response(NAME, TEXT);
        response.setId(ID);
        response.setValidated(true);
    }

    private static void initUser() {
        user = new User(
                NAME, DESCRIPTION,
                new Contacts(
                        EMAIL, PHONE, PHONE, PHONE,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE
                )
        );
        user.setId(ID);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        user.setRole(USER_ROLE);
    }

    private static void initArticles() {
        articles = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final Article article = getArticle();
            article.setId((long) i);
            article.setTitle(
                    article.getTitle() + " " + getRandomInt()
            );
            article.newNumber();
            articles.add(article);
        }
    }

    private static void initCategories() {
        categories = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final Category category = getCategory();
            category.setId((long) i);
            category.setTitle(
                    category.getTitle() + " " + getRandomInt()
            );
            categories.add(category);
        }
    }
    private static void initCompanies() {
        companies = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final Company company = getCompany();
            company.setId((long) i);
            company.setTitle(
                    company.getTitle() + " " + getRandomInt()
            );
            companies.add(company);
        }
    }
    private static void initMessages() {
        messages = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final Message message = getMessage();
            message.setId((long) i);
            message.setSubject(
                    message.getSubject() + " " + getRandomInt()
            );
            messages.add(message);
        }
    }
    private static void initFiles() {
        files = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final File file = getFile();
            file.setId((long) i);
            file.setTitle(
                    file.getTitle() + " " + getRandomInt()
            );
            files.add(file);
        }
    }

    private static void initResponses() {
        responses = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final Response response = getResponse();
            response.setId((long) i);
            response.setUsername(
                    response.getUsername() + " " + getRandomInt()
            );
            responses.add(response);
        }
    }

    private static void initUsers() {
        users = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final User user = getUser();
            user.setId((long) i);
            user.setName(
                    user.getName() + " " + getRandomInt()
            );
            user.setLogin(
                    user.getLogin() + " " + getRandomInt()
            );
            user.setPassword(
                    user.getPassword() + " " + getRandomInt()
            );
            users.add(user);
        }
    }
}
