package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.post.PostEntity;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getPostEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getPostEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockPostEntityTest extends MockContentEntityTest<PostEntity> {

    private static PostEntity postEntity;
    private static Collection<PostEntity> postEntities;

    @BeforeClass
    public static void beforeClass() {
        postEntity = getPostEntity();
        postEntities = getPostEntities();
    }

    @Test
    public void whenGetDateThenReturnNotNull() {
        assertNotNull(postEntity.getDate());
    }

    @Ignore
    @Override
    protected PostEntity getObject() {
        return postEntity;
    }

    @Ignore
    @Override
    protected Collection<PostEntity> getObjects() {
        return postEntities;
    }
}
