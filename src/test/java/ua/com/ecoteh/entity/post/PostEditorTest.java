package ua.com.ecoteh.entity.post;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.content.ContentEditorTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.enity.MockModels.getPost;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class PostEditorTest extends ContentEditorTest<Post> {

    private PostEditor editor;
    private Post post;

    @Before
    public void beforeTests() {
        this.post = getPost();
        this.editor = new PostEditor(this.post);
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Post updatedPost = this.editor.update();
        assertEquals(updatedPost.getDate(), this.post.getDate());
    }

    @Test
    public void whenAddDateThenBuildWithIt() {
        Post post;
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.editor.addDate(date);
            post = this.editor.update();
            assertEquals(post.getDate(), date);
        }
    }

    @Test
    public void whenAddNullDateThenBuildWithEmptyIt() {
        this.editor.addDate(null);
        final Post post = this.editor.update();
        assertEquals(post.getDate(), this.post.getDate());
    }

    @Override
    protected PostEditor getEditor() {
        return this.editor;
    }

    @Override
    protected Post getModel() {
        return this.post;
    }
}
