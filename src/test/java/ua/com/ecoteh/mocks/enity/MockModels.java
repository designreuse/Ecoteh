package ua.com.ecoteh.mocks.enity;

import org.junit.Ignore;
import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.address.AddressBuilder;
import ua.com.ecoteh.entity.address.AddressEditor;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.article.ArticleBuilder;
import ua.com.ecoteh.entity.article.ArticleEditor;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.category.CategoryBuilder;
import ua.com.ecoteh.entity.category.CategoryEditor;
import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.entity.company.CompanyBuilder;
import ua.com.ecoteh.entity.company.CompanyEditor;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.contacts.ContactsBuilder;
import ua.com.ecoteh.entity.contacts.ContactsEditor;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileBuilder;
import ua.com.ecoteh.entity.file.FileEditor;
import ua.com.ecoteh.entity.message.Message;
import ua.com.ecoteh.entity.message.MessageBuilder;
import ua.com.ecoteh.entity.message.MessageEditor;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.entity.response.ResponseBuilder;
import ua.com.ecoteh.entity.response.ResponseEditor;
import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserBuilder;
import ua.com.ecoteh.entity.user.UserEditor;
import ua.com.ecoteh.entity.user.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ua.com.ecoteh.mocks.MockConstants.*;

@Ignore
public final class MockModels {

    private final static Random RANDOM = new Random();

    private static Address address;
    private static Article article;
    private static Category category;
    private static Company company;
    private static Contacts contacts;
    private static File file;
    private static Message message;
    private static Response response;
    private static User user;

    private static List<Address> addresses;
    private static List<Article> articles;
    private static List<Category> categories;
    private static List<Company> companies;
    private static List<Contacts> contactses;
    private static List<File> files;
    private static List<Message> messages;
    private static List<Response> responses;
    private static List<User> users;

    public static Address getAddress() {
        if (address == null) {
            initAddress();
        }
        return address.clone();
    }

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

    public static Contacts getContacts() {
        if (contacts == null) {
            initContacts();
        }
        return contacts.clone();
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

    public static List<Address> getAddresses() {
        if (addresses == null || addresses.isEmpty()) {
            initAddresses();
        }
        return new ArrayList<>(addresses);
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

    public static List<Contacts> getContactses() {
        if (contactses == null || contactses.isEmpty()) {
            initContactses();
        }
        return new ArrayList<>(contactses);
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

    private static int getRandomInt(final int length) {
        int number = 0;
        for (int i = 0; i < (length > 0 ? length : 10); i++) {
            number += (RANDOM.nextInt(8) + 1) * (int) Math.pow(10, i);
        }
        return number;
    }

    private static int getRandomInt() {
        return getRandomInt(10);
    }

    private static void initAddress() {
        final AddressBuilder builder = Address.getBuilder();
        builder.addId(ID).addValidated(VALIDATION)
                .addPostAddress(ADDRESS).addGoogleMaps(GOOGLE_MAPS);
        address = builder.build();
    }

    private static void initArticle() {
        final ArticleBuilder builder = Article.getBuilder();
        builder.addId(ID).addValidated(VALIDATION)
                .addTitle(TITLE).addUrl(URL).addDescription(DESCRIPTION)
                .addText(TEXT).addKeywords(KEYWORDS).addNumber(NUMBER)
                .addPrice(PRICE).addCurrency(CURRENCY)
                .addLogo(getFile());
        article = builder.build();
    }

    private static void initCategory() {
        final CategoryBuilder builder = Category.getBuilder();
        builder.addId(ID).addValidated(VALIDATION)
                .addTitle(TITLE).addUrl(URL).addDescription(DESCRIPTION)
                .addKeywords(KEYWORDS).addLogo(getFile()).addArticles(getArticles());
        category = builder.build();
    }

    private static void initCompany() {
        final CompanyBuilder builder = Company.getBuilder();
        builder.addId(ID).addValidated(VALIDATION)
                .addTitle(TITLE).addUrl(URL).addDescription(DESCRIPTION)
                .addInformation(INFORMATION).addKeywords(KEYWORDS)
                .addDomain(DOMAIN).addTagline(TAGLINE).addLogo(getFile())
                .addWorkTimeFrom(TIME).addWorkTimeTo(TIME).addType(COMPANY_TYPE)
                .addSenderEmail(EMAIL).addSenderPass(PASSWORD)
                .addContacts(getContacts()).addAddress(getAddress());
        company = builder.build();
    }

    private static void initContacts() {
        final ContactsBuilder builder = Contacts.getBuilder();
        builder.addId(ID).addValidated(VALIDATION).addEmail(EMAIL)
                .addMobilePhone(PHONE).addLandlinesPhone(PHONE).addFax(PHONE)
                .addVkontakte(VKONTAKTE).addFacebook(FACEBOOK)
                .addTwitter(TWITTER).addSkype(SKYPE);
        contacts = builder.build();
    }

    private static void initMessage() {
        final MessageBuilder builder = Message.getBuilder();
        builder.addId(ID).addValidated(VALIDATION)
                .addSubject(TITLE).addText(ANY_STRING).addUser(getUser());
        message = builder.build();
    }

    private static void initFile() {
        final FileBuilder builder = File.getBuilder();
        builder.addId(ID).addValidated(VALIDATION)
                .addTitle(TITLE).addUrl(URL);
        file = builder.build();
    }

    private static void initResponse() {
        final ResponseBuilder builder = Response.getBuilder();
        builder.addId(ID).addValidated(VALIDATION)
                .addUsername(NAME).addText(TEXT);
        response = builder.build();
    }

    private static void initUser() {
        final UserBuilder builder = User.getBuilder();
        builder.addId(ID).addValidated(VALIDATION).addLocked(!VALIDATION)
                .addName(NAME).addUrl(URL).addDescription(DESCRIPTION)
                .addLogin(LOGIN).addPassword(PASSWORD)
                .addRole(USER_ROLE).addContacts(getContacts());
        user = builder.build();
    }

    private static void initAddresses() {
        addresses = new ArrayList<>();
        final Address address = getAddress();
        final AddressEditor editor = address.getEditor();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            editor.addId((long) i);
            addresses.add(editor.update());
        }
    }

    private static void initArticles() {
        articles = new ArrayList<>();
        final Article article = getArticle();
        final ArticleEditor editor = article.getEditor();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            editor.addId((long) i);
            articles.add(editor.update());
        }
    }

    private static void initCategories() {
        categories = new ArrayList<>();
        final Category category = getCategory();
        final CategoryEditor editor = category.getEditor();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            editor.addId((long) i);
            categories.add(editor.update());
        }
    }

    private static void initCompanies() {
        companies = new ArrayList<>();
        final Company company = getCompany();
        final CompanyEditor editor = company.getEditor();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            editor.addId((long) i);
            companies.add(editor.update());
        }
    }

    private static void initContactses() {
        contactses = new ArrayList<>();
        final Contacts contacts = getContacts();
        final ContactsEditor editor = contacts.getEditor();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            editor.addId((long) i);
            contactses.add(editor.update());
        }
    }

    private static void initMessages() {
        messages = new ArrayList<>();
        final Message message = getMessage();
        final MessageEditor editor = message.getEditor();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            editor.addId((long) i);
            messages.add(editor.update());
        }
    }

    private static void initFiles() {
        files = new ArrayList<>();
        final File file = getFile();
        final FileEditor editor = file.getEditor();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            editor.addId((long) i);
            files.add(editor.update());
        }
    }

    private static void initResponses() {
        responses = new ArrayList<>();
        final Response response = getResponse();
        final ResponseEditor editor = response.getEditor();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            editor.addId((long) i);
            responses.add(editor.update());
        }
    }

    private static void initUsers() {
        users = new ArrayList<>();
        final User user = getUser();
        final UserEditor editor = user.getEditor();
        for (UserRole role : UserRole.values()) {
            editor.addId(user.getId() + 1)
                    .addRole(role)
                    .addName(user.getName() + " " + getRandomInt())
                    .addLogin(user.getLogin() + " " + getRandomInt())
                    .addPassword(user.getPassword() + " " + getRandomInt());
            users.add(editor.update());
        }
    }
}
