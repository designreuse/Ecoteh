package com.salimov.ecoteh.mocks.repository;

import com.salimov.ecoteh.entity.*;
import com.salimov.ecoteh.repository.*;
import com.salimov.ecoteh.util.encryption.Encryptor;

import java.util.ArrayList;
import java.util.Collection;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.enity.MockEntity.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MockRepository {

    private static ArticleRepository articleRepository;
    private static CategoryRepository categoryRepository;
    private static CompanyRepository companyRepository;
    private static FileRepository fileRepository;
    private static MessageRepository messageRepository;
    private static ResponseRepository responseRepository;
    private static UserRepository userRepository;

    public static ArticleRepository getArticleRepository() {
        if (articleRepository == null) {
            initArticleRepository();
        }
        return articleRepository;
    }

    public static CategoryRepository getCategoryRepository() {
        if (categoryRepository == null) {
            initCategoryRepository();
        }
        return categoryRepository;
    }

    public static CompanyRepository getCompanyRepository() {
        if (companyRepository == null) {
            initCompanyRepository();
        }
        return companyRepository;
    }

    public static FileRepository getFileRepository() {
        if (fileRepository == null) {
            initFileRepository();
        }
        return fileRepository;
    }

    public static MessageRepository getMessageRepository() {
        if (messageRepository == null) {
            initMessageRepository();
        }
        return messageRepository;
    }

    public static ResponseRepository getResponseRepository() {
        if (responseRepository == null) {
            initResponseRepository();
        }
        return responseRepository;
    }

    public static UserRepository getUserRepository() {
        if (userRepository == null) {
            initUserRepository();
        }
        return userRepository;
    }

    private static void initArticleRepository() {
        articleRepository = mock(ArticleRepository.class);
        final Article article = getArticle();
        final Collection<Article> articles = getArticles();
        initDataRepository(
                articleRepository,
                article,
                articles
        );
        when(articleRepository.findByTitle(TITLE)).thenReturn(article);
        when(articleRepository.findByTitle(ANY_STRING)).thenReturn(null);
        when(articleRepository.findByTitle(null)).thenReturn(null);
        when(articleRepository.findByUrl(URL)).thenReturn(article);
        when(articleRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(articleRepository.findByUrl(null)).thenReturn(null);
        when(articleRepository.findByNumber(NUMBER)).thenReturn(article);
        when(articleRepository.findByNumber(ANY_STRING)).thenReturn(null);
        when(articleRepository.findByNumber(null)).thenReturn(null);
        when(articleRepository.findByCategoryId(ID))
                .thenReturn(new ArrayList<>(articles));
        when(articleRepository.findByCategoryId(UNKNOWN_ID))
                .thenReturn(new ArrayList<>());
    }

    private static void initCategoryRepository() {
        categoryRepository = mock(CategoryRepository.class);
        final Category category = getCategory();
        initDataRepository(
                categoryRepository,
                category,
                getCategories()
        );
        when(categoryRepository.findByTitle(TITLE)).thenReturn(category);
        when(categoryRepository.findByTitle(ANY_STRING)).thenReturn(null);
        when(categoryRepository.findByTitle(null)).thenReturn(null);
        when(categoryRepository.findByUrl(URL)).thenReturn(category);
        when(categoryRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(categoryRepository.findByUrl(null)).thenReturn(null);
    }

    private static void initCompanyRepository() {
        final Company company = getCompany();
        companyRepository = mock(CompanyRepository.class);
        initDataRepository(
                companyRepository,
                company,
                getCompanies()
        );
        when(companyRepository.findByTitle(TITLE)).thenReturn(company);
        when(companyRepository.findByTitle(ANY_STRING)).thenReturn(null);
        when(companyRepository.findByTitle(null)).thenReturn(null);
        when(companyRepository.findByUrl(URL)).thenReturn(company);
        when(companyRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(companyRepository.findByUrl(null)).thenReturn(null);
    }

    private static void initFileRepository() {
        final File file = getFile();
        fileRepository = mock(FileRepository.class);
        initDataRepository(
                fileRepository,
                file,
                getFiles()
        );
        when(fileRepository.findByTitle(TITLE)).thenReturn(file);
        when(fileRepository.findByTitle(ANY_STRING)).thenReturn(null);
        when(fileRepository.findByTitle(null)).thenReturn(null);
        when(fileRepository.findByUrl(URL)).thenReturn(file);
        when(fileRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(fileRepository.findByUrl(null)).thenReturn(null);
    }

    private static void initMessageRepository() {
        messageRepository = mock(MessageRepository.class);
        initDataRepository(
                messageRepository,
                getMessage(),
                getMessages()
        );
    }

    private static void initResponseRepository() {
        responseRepository = mock(ResponseRepository.class);
        initDataRepository(
                responseRepository,
                getResponse(),
                getResponses()
        );
    }

    private static void initUserRepository() {
        final User user = getUser();
        userRepository = mock(UserRepository.class);
        initDataRepository(
                userRepository,
                user,
                getUsers()
        );
        when(userRepository.findByName(NAME)).thenReturn(user);
        when(userRepository.findByName(ANY_STRING)).thenReturn(null);
        when(userRepository.findByName(null)).thenReturn(null);
        when(userRepository.findByUrl(URL)).thenReturn(user);
        when(userRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(userRepository.findByUrl(null)).thenReturn(null);
        when(
                userRepository.findByEncryptedLogin(
                        new Encryptor(LOGIN).encrypt()
                )
        ).thenReturn(user);
        when(
                userRepository.findByEncryptedLogin(
                        new Encryptor(ANY_STRING).encrypt()
                )
        ).thenReturn(null);
        when(userRepository.findByEncryptedLogin(null)).thenReturn(null);
    }

    private static <T extends Model, E extends DataRepository<T>> void initDataRepository(
            final E repository,
            final T model,
            final Collection<T> models
    ) {
        when(repository.save(model)).thenReturn(model);
        when(repository.save((T) null)).thenReturn(null);
        when(repository.save(models))
                .thenReturn(new ArrayList<>(models));
        when(repository.findOne(ID)).thenReturn(model);
        when(repository.findOne(UNKNOWN_ID)).thenReturn(null);
        when(repository.findOne((Long) null)).thenReturn(null);
        when(repository.findAll())
                .thenReturn(new ArrayList<>(models));
        when(repository.exists(ID)).thenReturn(true);
        when(repository.exists(UNKNOWN_ID)).thenReturn(false);
        when(repository.exists((Long) null)).thenReturn(false);
    }
}
