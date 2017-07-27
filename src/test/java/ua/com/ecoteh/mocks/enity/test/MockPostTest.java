package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.post.Post;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModels.getPost;
import static ua.com.ecoteh.mocks.enity.MockModels.getPosts;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockPostTest extends MockContentTest<Post> {

    private static Post post;
    private static Collection<Post> posts;

    @BeforeClass
    public static void beforeClass() {
        post = getPost();
        posts = getPosts();
    }

    @Test
    public void whenGetDateThenReturnNotNull() {
        assertNotNull(post.getDate());
    }

    @Ignore
    @Override
    protected Post getObject() {
        return post;
    }

    @Ignore
    @Override
    protected Collection<Post> getObjects() {
        return posts;
    }
}
