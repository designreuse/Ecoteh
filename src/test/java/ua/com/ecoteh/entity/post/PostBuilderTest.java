package ua.com.ecoteh.entity.post;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.content.ContentBuilderTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class PostBuilderTest extends ContentBuilderTest<Post> {

    private PostBuilder builder;

    @Before
    public void beforeTests() {
        this.builder = new PostBuilder();
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Post post = this.builder.build();
        assertNotNull(post.getDate());
    }

    @Test
    public void whenAddDateThenBuildWithIt() {
        Post post;
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.builder.addDate(date);
            post = this.builder.build();
            assertEquals(post.getDate(), date);
        }
    }

    @Test
    public void whenAddNullDateThenBuildWithEmptyIt() {
        this.builder.addDate(null);
        final Post post = this.builder.build();
        assertNotNull(post.getDate());
    }

    @Override
    protected PostBuilder getBuilder() {
        return this.builder;
    }
}
