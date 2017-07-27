package ua.com.ecoteh.entity.post;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.content.ContentConverterTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.enity.MockModels.getPost;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class PostConverterTest extends ContentConverterTest<Post, PostEntity> {

    private static PostConverter converter;
    private static Post post;

    @BeforeClass
    public static void beforeClass() {
        post = getPost();
        converter = new PostConverter(post);
    }

    @Override
    protected void checkEntity(final PostEntity entity) {
        super.checkEntity(entity);
        assertEquals(entity.getDate(), post.getDate());
    }

    @Override
    protected PostConverter getConverter() {
        return converter;
    }

    @Override
    protected Post getModel() {
        return post;
    }
}
