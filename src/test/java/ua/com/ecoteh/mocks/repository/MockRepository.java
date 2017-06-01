package ua.com.ecoteh.mocks.repository;

import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.model.ModelEntity;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.entity.company.CompanyType;
import ua.com.ecoteh.repository.*;
import ua.com.ecoteh.util.encryption.Base64Encryptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockEntity.*;

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
        final ArticleEntity articleEntity = getArticleEntity();
        articleEntity.setCategoryEntity(getCategoryEntity());
        final Collection<ArticleEntity> articleEntities = getArticleEntities();
        initDataRepository(
                articleRepository,
                articleEntity,
                articleEntities
        );
        when(articleRepository.findByTitle(TITLE)).thenReturn(articleEntity);
        when(articleRepository.findByTitle(ANY_STRING)).thenReturn(null);
        when(articleRepository.findByTitle(null)).thenReturn(null);
        when(articleRepository.findByUrl(URL)).thenReturn(articleEntity);
        when(articleRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(articleRepository.findByUrl(null)).thenReturn(null);
        when(articleRepository.findByNumber(NUMBER)).thenReturn(articleEntity);
        when(articleRepository.findByNumber(ANY_STRING)).thenReturn(null);
        when(articleRepository.findByNumber(null)).thenReturn(null);
        when(articleRepository.findByCategoryEntityId(ID)).thenReturn(new ArrayList<>(articleEntities));
        when(articleRepository.findByCategoryEntityId(UNKNOWN_ID)).thenReturn(new ArrayList<>());
        when(articleRepository.findByCategoryEntityTitle(TITLE)).thenReturn(new ArrayList<>(articleEntities));
        when(articleRepository.findByCategoryEntityTitle(null)).thenReturn(new ArrayList<>());
        when(articleRepository.findByCategoryEntityTitle(ANY_STRING)).thenReturn(new ArrayList<>());
    }

    private static void initCategoryRepository() {
        categoryRepository = mock(CategoryRepository.class);
        final CategoryEntity categoryEntity = getCategoryEntity();
        initDataRepository(
                categoryRepository,
                categoryEntity,
                getCategories()
        );
        when(categoryRepository.findByTitle(TITLE)).thenReturn(categoryEntity);
        when(categoryRepository.findByTitle(ANY_STRING)).thenReturn(null);
        when(categoryRepository.findByTitle(null)).thenReturn(null);
        when(categoryRepository.findByUrl(URL)).thenReturn(categoryEntity);
        when(categoryRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(categoryRepository.findByUrl(null)).thenReturn(null);
    }

    private static void initCompanyRepository() {
        final CompanyEntity companyEntity = getCompanyEntity();
        List<CompanyEntity> companies = getCompanies();
        companyRepository = mock(CompanyRepository.class);
        initDataRepository(
                companyRepository,
                companyEntity,
                companies
        );
        when(companyRepository.findByTitle(TITLE)).thenReturn(companyEntity);
        when(companyRepository.findByTitle(ANY_STRING)).thenReturn(null);
        when(companyRepository.findByTitle(null)).thenReturn(null);
        when(companyRepository.findByUrl(URL)).thenReturn(companyEntity);
        when(companyRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(companyRepository.findByUrl(null)).thenReturn(null);
        when(companyRepository.findByType(CompanyType.PARTNER)).thenReturn(companies);
        when(companyRepository.findByDomain(null)).thenReturn(null);
        when(companyRepository.findByDomain("")).thenReturn(null);
        when(companyRepository.findByDomain(ANY_STRING)).thenReturn(null);
        when(companyRepository.findByDomain(DOMAIN)).thenReturn(companyEntity);
    }

    private static void initFileRepository() {
        final FileEntity fileEntity = getFileEntity();
        fileRepository = mock(FileRepository.class);
        initDataRepository(
                fileRepository,
                fileEntity,
                getFileEntities()
        );
        when(fileRepository.findByTitle(TITLE)).thenReturn(fileEntity);
        when(fileRepository.findByTitle(ANY_STRING)).thenReturn(null);
        when(fileRepository.findByTitle(null)).thenReturn(null);
        when(fileRepository.findByUrl(URL)).thenReturn(fileEntity);
        when(fileRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(fileRepository.findByUrl(null)).thenReturn(null);
    }

    private static void initMessageRepository() {
        messageRepository = mock(MessageRepository.class);
        initDataRepository(
                messageRepository,
                getMessageEntity(),
                getMessageEntities()
        );
    }

    private static void initResponseRepository() {
        responseRepository = mock(ResponseRepository.class);
        initDataRepository(
                responseRepository,
                getResponseEntity(),
                getResponseEntities()
        );
    }

    private static void initUserRepository() {
        final UserEntity userEntity = getUserEntity();
        userRepository = mock(UserRepository.class);
        initDataRepository(
                userRepository,
                userEntity,
                getUserEntities()
        );
        when(userRepository.findByName(NAME)).thenReturn(userEntity);
        when(userRepository.findByName(ANY_STRING)).thenReturn(null);
        when(userRepository.findByName(null)).thenReturn(null);
        when(userRepository.findByUrl(URL)).thenReturn(userEntity);
        when(userRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(userRepository.findByUrl(null)).thenReturn(null);
        when(
                userRepository.findByEncryptedLogin(
                        new Base64Encryptor(LOGIN).encrypt()
                )
        ).thenReturn(userEntity);
        when(
                userRepository.findByEncryptedLogin(
                        new Base64Encryptor(ANY_STRING).encrypt()
                )
        ).thenReturn(null);
        when(userRepository.findByEncryptedLogin(null)).thenReturn(null);
        when(userRepository.findByContactsEntityEmail(EMAIL)).thenReturn(userEntity);
        when(userRepository.findByContactsEntityEmail(null)).thenReturn(null);
        when(userRepository.findByContactsEntityEmail(ANY_STRING)).thenReturn(null);
        when(userRepository.findByContactsEntityMobilePhone(PHONE)).thenReturn(userEntity);
        when(userRepository.findByContactsEntityMobilePhone(null)).thenReturn(null);
        when(userRepository.findByContactsEntityMobilePhone(ANY_STRING)).thenReturn(null);
        when(userRepository.findByContactsEntityLandlinePhone(PHONE_2)).thenReturn(userEntity);
        when(userRepository.findByContactsEntityLandlinePhone(null)).thenReturn(null);
        when(userRepository.findByContactsEntityLandlinePhone(ANY_STRING)).thenReturn(null);
        when(userRepository.findByContactsEntityFax(FAX)).thenReturn(userEntity);
        when(userRepository.findByContactsEntityFax(null)).thenReturn(null);
        when(userRepository.findByContactsEntityFax(ANY_STRING)).thenReturn(null);
    }

    private static <T extends ModelEntity, E extends DataRepository<T>> void initDataRepository(
            final E repository,
            final T model,
            final Collection<T> models
    ) {
        when(repository.save(model)).thenReturn(model);
        when(repository.save((T) null)).thenReturn(null);
        when(repository.save(models)).thenReturn(new ArrayList<>(models));
        for (T _model : models) {
            when(repository.save(_model)).thenReturn(_model);
        }
        when(repository.findOne(ID)).thenReturn(model);
        when(repository.findOne(UNKNOWN_ID)).thenReturn(null);
        when(repository.findOne((Long) null)).thenReturn(null);
        when(repository.findAll()).thenReturn(new ArrayList<>(models));
        when(repository.exists(ID)).thenReturn(true);
        when(repository.exists(UNKNOWN_ID)).thenReturn(false);
        when(repository.exists((Long) null)).thenReturn(false);
    }
}
