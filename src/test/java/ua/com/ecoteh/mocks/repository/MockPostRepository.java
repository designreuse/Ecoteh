package ua.com.ecoteh.mocks.repository;

import ua.com.ecoteh.entity.post.PostEntity;
import ua.com.ecoteh.repository.PostRepository;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getPostEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getPostEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class MockPostRepository extends MockContentRepository<PostEntity> {

    private final PostRepository repository;
    private final PostEntity entity;
    private final Collection<PostEntity> entities;

    MockPostRepository() {
        this.repository = mock(PostRepository.class);
        this.entity = getPostEntity();
        this.entities = getPostEntities();
    }

    @Override
    PostRepository create() {
        super.create();
        return this.repository;
    }

    @Override
    PostRepository getRepository() {
        return this.repository;
    }

    @Override
    PostEntity getEntity() {
        return this.entity;
    }

    @Override
    Collection<PostEntity> getEntities() {
        return this.entities;
    }
}
