package ua.com.ecoteh.mocks.enity;

import org.junit.Ignore;
import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.address.AddressEntity;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.contacts.ContactsEntity;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.message.Message;
import ua.com.ecoteh.entity.message.MessageEntity;
import ua.com.ecoteh.entity.model.Model;
import ua.com.ecoteh.entity.model.ModelEntity;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ua.com.ecoteh.mocks.enity.MockModels.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Ignore
public final class MockModelEntities {

    private static AddressEntity addressEntity;
    private static ArticleEntity articleEntity;
    private static CategoryEntity categoryEntity;
    private static CompanyEntity companyEntity;
    private static ContactsEntity contactsEntity;
    private static FileEntity fileEntity;
    private static MessageEntity messageEntity;
    private static ResponseEntity responseEntity;
    private static UserEntity userEntity;

    private static List<AddressEntity> addressEntities;
    private static List<ArticleEntity> articleEntities;
    private static List<CategoryEntity> categoryEntities;
    private static List<CompanyEntity> companyEntities;
    private static List<ContactsEntity> contactsEntities;
    private static List<FileEntity> fileEntities;
    private static List<MessageEntity> messageEntities;
    private static List<ResponseEntity> responsEntities;
    private static List<UserEntity> userEntities;

    public static AddressEntity getAddressEntity() {
        if (addressEntity == null) {
            final Address address = getAddress();
            addressEntity = address.convert();
        }
        return addressEntity.clone();
    }

    public static ArticleEntity getArticleEntity() {
        if (articleEntity == null) {
            final Article article = getArticle();
            articleEntity = article.convert();
        }
        return articleEntity.clone();
    }

    public static CategoryEntity getCategoryEntity() {
        if (categoryEntity == null) {
            final Category category = getCategory();
            categoryEntity = category.convert();
            final Collection<ArticleEntity> articles = getArticleEntities();
            categoryEntity.setArticleEntities(articles);
        }
        return categoryEntity.clone();
    }

    public static CompanyEntity getCompanyEntity() {
        if (companyEntity == null) {
            final Company company = getCompany();
            companyEntity = company.convert();
        }
        return companyEntity.clone();
    }

    public static ContactsEntity getContactsEntity() {
        if (contactsEntity == null) {
            final Contacts contacts = getContacts();
            contactsEntity = contacts.convert();
        }
        return contactsEntity.clone();
    }

    public static MessageEntity getMessageEntity() {
        if (messageEntity == null) {
            final Message message = getMessage();
            messageEntity = message.convert();
        }
        return messageEntity.clone();
    }

    public static FileEntity getFileEntity() {
        if (fileEntity == null) {
            final File file = getFile();
            fileEntity = file.convert();
        }
        return fileEntity.clone();
    }

    public static ResponseEntity getResponseEntity() {
        if (responseEntity == null) {
            final Response response = getResponse();
            responseEntity = response.convert();
        }
        return responseEntity.clone();
    }

    public static UserEntity getUserEntity() {
        if (userEntity == null) {
            final User user = getUser();
            userEntity = user.convert();
        }
        return userEntity.clone();
    }

    public static List<AddressEntity> getAddressEntities() {
        if (addressEntities == null || addressEntities.isEmpty()) {
            final List<Address> addresses = getAddresses();
            addressEntities = convert(addresses);
        }
        return new ArrayList<>(addressEntities);
    }

    public static List<ArticleEntity> getArticleEntities() {
        if (articleEntities == null || articleEntities.isEmpty()) {
            final List<Article> articles = getArticles();
            articleEntities = convert(articles);
        }
        return new ArrayList<>(articleEntities);
    }

    public static List<CategoryEntity> getCategoryEntities() {
        if (categoryEntities == null || categoryEntities.isEmpty()) {
            final List<Category> categories = getCategories();
            categoryEntities = convert(categories);
        }
        return new ArrayList<>(categoryEntities);
    }

    public static List<CompanyEntity> getCompanyEntities() {
        if (companyEntities == null || companyEntities.isEmpty()) {
            final List<Company> companies = getCompanies();
            companyEntities = convert(companies);
        }
        return new ArrayList<>(companyEntities);
    }

    public static List<ContactsEntity> getContactsEntities() {
        if (contactsEntities == null || contactsEntities.isEmpty()) {
            final List<Contacts> contactses = getContactses();
            contactsEntities = convert(contactses);
        }
        return new ArrayList<>(contactsEntities);
    }

    public static List<MessageEntity> getMessageEntities() {
        if (messageEntities == null || messageEntities.isEmpty()) {
            final List<Message> messages = getMessages();
            messageEntities = convert(messages);
        }
        return new ArrayList<>(messageEntities);
    }

    public static List<FileEntity> getFileEntities() {
        if (fileEntities == null || fileEntities.isEmpty()) {
            final List<File> files = getFiles();
            fileEntities = convert(files);
        }
        return new ArrayList<>(fileEntities);
    }

    public static List<ResponseEntity> getResponseEntities() {
        if (responsEntities == null || responsEntities.isEmpty()) {
            final List<Response> responses = getResponses();
            responsEntities = convert(responses);
        }
        return new ArrayList<>(responsEntities);
    }

    public static List<UserEntity> getUserEntities() {
        if (userEntities == null || userEntities.isEmpty()) {
            final List<User> users = getUsers();
            userEntities = convert(users);
        }
        return new ArrayList<>(userEntities);
    }

    private static <T extends Model, E extends ModelEntity> List<E> convert(final List<T> models) {
        final List<E> entities = new ArrayList<>();
        for (T model : models) {
            entities.add((E) model.convert());
        }
        return entities;
    }
}
