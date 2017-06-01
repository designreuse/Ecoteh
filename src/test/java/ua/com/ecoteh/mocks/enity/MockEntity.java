package ua.com.ecoteh.mocks.enity;

import org.junit.Ignore;
import ua.com.ecoteh.entity.address.AddressEntity;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.contacts.ContactsEntity;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.message.MessageEntity;
import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.entity.user.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ua.com.ecoteh.mocks.MockConstants.*;

@Ignore
public final class MockEntity {

    private final static Random RANDOM = new Random(System.nanoTime());

    private static AddressEntity addressEntity;
    private static ArticleEntity articleEntity;
    private static CategoryEntity categoryEntity;
    private static CompanyEntity companyEntity;
    private static FileEntity fileEntity;
    private static MessageEntity messageEntity;
    private static ResponseEntity responseEntity;
    private static UserEntity userEntity;

    private static List<AddressEntity> addressEntities;
    private static List<ArticleEntity> articleEntities;
    private static List<CategoryEntity> categories;
    private static List<CompanyEntity> companies;
    private static List<FileEntity> fileEntities;
    private static List<MessageEntity> messageEntities;
    private static List<ResponseEntity> responseEntities;
    private static List<UserEntity> userEntities;

    public static AddressEntity getAddressEntity() {
        if (addressEntity == null) {
            initAddress();
        }
        return addressEntity.clone();
    }

    public static ArticleEntity getArticleEntity() {
        if (articleEntity == null) {
            initArticle();
        }
        return articleEntity.clone();
    }

    public static CategoryEntity getCategoryEntity() {
        if (categoryEntity == null) {
            initCategory();
        }
        return categoryEntity.clone();
    }

    public static CompanyEntity getCompanyEntity() {
        if (companyEntity == null) {
            initCompany();
        }
        return companyEntity.clone();
    }

    public static MessageEntity getMessageEntity() {
        if (messageEntity == null) {
            initMessage();
        }
        return messageEntity.clone();
    }

    public static FileEntity getFileEntity() {
        if (fileEntity == null) {
            initFile();
        }
        return fileEntity.clone();
    }

    public static ResponseEntity getResponseEntity() {
        if (responseEntity == null) {
            initResponse();
        }
        return responseEntity.clone();
    }

    public static UserEntity getUserEntity() {
        if (userEntity == null) {
            initUser();
        }
        return userEntity.clone();
    }

    public static List<AddressEntity> getAddressEntities() {
        if (addressEntities == null || addressEntities.isEmpty()) {
            initAddresses();
        }
        return new ArrayList<>(addressEntities);
    }

    public static List<ArticleEntity> getArticleEntities() {
        if (articleEntities == null || articleEntities.isEmpty()) {
            initArticles();
        }
        return new ArrayList<>(articleEntities);
    }

    public static List<CategoryEntity> getCategories() {
        if (categories == null || categories.isEmpty()) {
            initCategories();
        }
        return new ArrayList<>(categories);
    }

    public static List<CompanyEntity> getCompanies() {
        if (companies == null || companies.isEmpty()) {
            initCompanies();
        }
        return new ArrayList<>(companies);
    }

    public static List<MessageEntity> getMessageEntities() {
        if (messageEntities == null || messageEntities.isEmpty()) {
            initMessages();
        }
        return new ArrayList<>(messageEntities);
    }

    public static List<FileEntity> getFileEntities() {
        if (fileEntities == null || fileEntities.isEmpty()) {
            initFiles();
        }
        return new ArrayList<>(fileEntities);
    }

    public static List<ResponseEntity> getResponseEntities() {
        if (responseEntities == null || responseEntities.isEmpty()) {
            initResponses();
        }
        return new ArrayList<>(responseEntities);
    }

    public static List<UserEntity> getUserEntities() {
        if (userEntities == null || userEntities.isEmpty()) {
            initUsers();
        }
        return new ArrayList<>(userEntities);
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

    private static void initAddress() {
        addressEntity = new AddressEntity(ADDRESS, GOOGLE_MAPS);
        addressEntity.setId(ID);
        addressEntity.setValidated(true);
    }

    private static void initArticle() {
        articleEntity = new ArticleEntity(
                TITLE, DESCRIPTION,
                TEXT, KEYWORDS, NUMBER
        );
        articleEntity.setId(ID);
        articleEntity.setUrl(URL);
        articleEntity.setValidated(true);
    }

    private static void initCategory() {
        categoryEntity = new CategoryEntity(
                TITLE, DESCRIPTION, KEYWORDS
        );
        categoryEntity.setId(ID);
        categoryEntity.setUrl(URL);
        categoryEntity.setLogoEntity(getFileEntity());
        categoryEntity.addArticle(getArticleEntity());
        categoryEntity.setValidated(true);
    }

    private static void initCompany() {
        companyEntity = new CompanyEntity();
        companyEntity.setTitle(TITLE);
        companyEntity.setDescription(DESCRIPTION);
        companyEntity.setInformation(INFORMATION);
        companyEntity.setKeywords(KEYWORDS);
        companyEntity.setDomain(DOMAIN);
        companyEntity.setTagline(TAGLINE);
        companyEntity.setLogoEntity(getFileEntity());
        companyEntity.setId(ID);
        companyEntity.setUrl(URL);
        companyEntity.setWorkTimeFrom(TIME);
        companyEntity.setWorkTimeTo(TIME);
        companyEntity.setContactsEntity(
                new ContactsEntity(
                        EMAIL, PHONE, PHONE, PHONE,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE
                )
        );
        companyEntity.setAddressEntity(
                new AddressEntity(ADDRESS, GOOGLE_MAPS)
        );
        companyEntity.setValidated(true);
    }

    private static void initMessage() {
        messageEntity = new MessageEntity(getUserEntity(), ANY_STRING, TEXT);
        messageEntity.setId(ID);
        messageEntity.setValidated(true);
    }

    private static void initFile() {
        fileEntity = new FileEntity(TITLE, URL);
        fileEntity.setId(ID);
        fileEntity.setValidated(true);
    }

    private static void initResponse() {
        responseEntity = new ResponseEntity(NAME, TEXT);
        responseEntity.setId(ID);
        responseEntity.setValidated(true);
        responseEntity.setValidated(true);
    }

    private static void initUser() {
        userEntity = new UserEntity(
                NAME, DESCRIPTION,
                new ContactsEntity(
                        EMAIL, PHONE, PHONE, PHONE,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE
                )
        );
        userEntity.setId(ID);
        userEntity.setLogin(LOGIN);
        userEntity.setPassword(PASSWORD);
        userEntity.setRole(USER_ROLE);
        userEntity.setValidated(true);
    }

    private static void initAddresses() {
        addressEntities = new ArrayList<>();
        for (int i =0; i < DEFAULT_SIZE; i++) {
            final AddressEntity addressEntity = getAddressEntity();
            addressEntity.setId((long) i);
            addressEntities.add(addressEntity);
        }
    }

    private static void initArticles() {
        articleEntities = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final ArticleEntity articleEntity = getArticleEntity();
            articleEntity.setId((long) i);
            articleEntity.setTitle(articleEntity.getTitle() + " " + getRandomInt());
            articleEntity.newNumber();
            articleEntity.setCategoryEntity(getCategoryEntity());
            articleEntities.add(articleEntity);
        }
    }

    private static void initCategories() {
        categories = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final CategoryEntity categoryEntity = getCategoryEntity();
            categoryEntity.setId((long) i);
            categoryEntity.setTitle(categoryEntity.getTitle() + " " + getRandomInt());
            categories.add(categoryEntity);
        }
    }
    private static void initCompanies() {
        companies = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final CompanyEntity companyEntity = getCompanyEntity();
            companyEntity.setId((long) i);
            companyEntity.setTitle(companyEntity.getTitle() + " " + getRandomInt());
            companies.add(companyEntity);
        }
    }
    private static void initMessages() {
        messageEntities = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final MessageEntity messageEntity = getMessageEntity();
            messageEntity.setId((long) i);
            messageEntity.setSubject(messageEntity.getSubject() + " " + getRandomInt());
            messageEntities.add(messageEntity);
        }
    }
    private static void initFiles() {
        fileEntities = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final FileEntity fileEntity = getFileEntity();
            fileEntity.setId((long) i);
            fileEntity.setTitle(fileEntity.getTitle() + " " + getRandomInt());
            fileEntities.add(fileEntity);
        }
    }

    private static void initResponses() {
        responseEntities = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final ResponseEntity responseEntity = getResponseEntity();
            responseEntity.setId((long) i);
            responseEntity.setUsername(responseEntity.getUsername() + " " + getRandomInt());
            responseEntities.add(responseEntity);
        }
    }

    private static void initUsers() {
        userEntities = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            final UserEntity userEntity = getUserEntity();
            userEntity.setId((long) i);
            userEntity.setName(userEntity.getName() + " " + getRandomInt());
            userEntity.setLogin(userEntity.getLogin() + " " + getRandomInt());
            userEntity.setPassword(userEntity.getPassword() + " " + getRandomInt());
            userEntities.add(userEntity);
        }
    }
}
