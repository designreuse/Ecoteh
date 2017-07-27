package ua.com.ecoteh.mocks.repository.tests;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.post.PostEntity;
import ua.com.ecoteh.repository.PostRepository;

import java.util.Collection;

import static ua.com.ecoteh.mocks.enity.MockModelEntities.getPostEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getPostEntity;
import static ua.com.ecoteh.mocks.repository.MockRepository.getPostRepository;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockPostRepositoryTest extends MockContentRepositoryTest<PostEntity> {

    private static PostEntity post;
    private static Collection<PostEntity> posts;
    private static PostRepository repository;

    @BeforeClass
    public static void beforeClass() {
        post = getPostEntity();
        posts = getPostEntities();
        repository = getPostRepository();
    }

    @Override
    protected PostRepository getRepository() {
        return repository;
    }

    @Override
    protected PostEntity getObject() {
        return post;
    }

    @Override
    protected Collection<PostEntity> getObjects() {
        return posts;
    }
}
