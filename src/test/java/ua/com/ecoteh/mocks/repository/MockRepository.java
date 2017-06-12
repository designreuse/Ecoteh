package ua.com.ecoteh.mocks.repository;

import org.junit.Ignore;
import ua.com.ecoteh.repository.*;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Ignore
public final class MockRepository {

    private static ArticleRepository articleRepository;
    private static CategoryRepository categoryRepository;
    private static CompanyRepository companyRepository;
    private static FileRepository fileRepository;
    private static MessageRepository messageRepository;
    private static ResponseRepository responseRepository;
    private static UserRepository userRepository;

    public static ArticleRepository getArticleRepository() {
        if (articleRepository == null) {
            final MockArticleRepository mockArticleRepository = new MockArticleRepository();
            articleRepository = mockArticleRepository.create();
        }
        return articleRepository;
    }

    public static CategoryRepository getCategoryRepository() {
        if (categoryRepository == null) {
            final MockCategoryRepository mockCategoryRepository = new MockCategoryRepository();
            categoryRepository = mockCategoryRepository.create();
        }
        return categoryRepository;
    }

    public static CompanyRepository getCompanyRepository() {
        if (companyRepository == null) {
            final MockCompanyRepository mockCompanyRepository = new MockCompanyRepository();
            companyRepository = mockCompanyRepository.create();
        }
        return companyRepository;
    }

    public static FileRepository getFileRepository() {
        if (fileRepository == null) {
            final MockFileRepository mockFileRepository = new MockFileRepository();
            fileRepository = mockFileRepository.create();
        }
        return fileRepository;
    }

    public static MessageRepository getMessageRepository() {
        if (messageRepository == null) {
            final MockMessageRepository mockMessageRepository = new MockMessageRepository();
            messageRepository = mockMessageRepository.create();
        }
        return messageRepository;
    }

    public static ResponseRepository getResponseRepository() {
        if (responseRepository == null) {
            final MockResponseRepository mockResponseRepository = new MockResponseRepository();
            responseRepository = mockResponseRepository.create();
        }
        return responseRepository;
    }

    public static UserRepository getUserRepository() {
        if (userRepository == null) {
            final MockUserRepository mockUserRepository = new MockUserRepository();
            userRepository = mockUserRepository.create();
        }
        return userRepository;
    }
}
