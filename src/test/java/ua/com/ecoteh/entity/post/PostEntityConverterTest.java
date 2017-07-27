package ua.com.ecoteh.entity.post;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.content.ContentEntityConverterTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getPostEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class PostEntityConverterTest extends ContentEntityConverterTest<PostEntity, Post> {

    private static PostEntityConverter converter;
    private static PostEntity entity;

    @BeforeClass
    public static void beforeClass() {
        entity = getPostEntity();
        converter = new PostEntityConverter(entity);
    }

    @Override
    protected void checkEntity(final Post post) {
        super.checkEntity(post);
        assertEquals(post.getDate(), entity.getDate());
    }

    @Override
    protected PostEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected PostEntity getEntity() {
        return entity;
    }
}
