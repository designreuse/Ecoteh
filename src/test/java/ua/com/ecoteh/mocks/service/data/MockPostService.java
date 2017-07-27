package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.entity.post.Post;
import ua.com.ecoteh.entity.post.PostEditor;
import ua.com.ecoteh.service.data.PostService;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.DATE;
import static ua.com.ecoteh.mocks.enity.MockModels.getPost;
import static ua.com.ecoteh.mocks.enity.MockModels.getPosts;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockPostService extends MockContentService<Post> {

    private final PostService service;
    private final Post post;
    private final Collection<Post> posts;

    MockPostService() {
        this.service = mock(PostService.class);
        this.post = getPost();
        this.posts = getPosts();
    }

    private void initAdd() {
        final PostEditor editor = this.post.getEditor();
        final Post post = editor.update();
        when(service.add(post)).thenReturn(post);
    }

    @Override
    PostService create() {
        super.create();
        initAdd();
        initSortByDate();
        initGetAndSortByDate();
        initFilterByDate();
        initGetAndFilterByDate();
        initFilterByValid();
        return this.service;
    }

    @Override
    PostService getService() {
        return this.service;
    }

    @Override
    Post getModel() {
        return this.post;
    }

    @Override
    Collection<Post> getModels() {
        return this.posts;
    }

    private void initSortByDate() {
        when(this.service.sortByDate(this.posts, true)).thenReturn(new ArrayList<>(this.posts));
        when(this.service.sortByDate(this.posts, false)).thenReturn(new ArrayList<>(this.posts));
        when(this.service.sortByDate(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(this.service.sortByDate(new ArrayList<>(), false)).thenReturn(new ArrayList<>());
        when(this.service.sortByDate(null, true)).thenReturn(new ArrayList<>());
        when(this.service.sortByDate(null, false)).thenReturn(new ArrayList<>());
    }

    private void initGetAndSortByDate() {
        when(this.service.getAndSortByDate(true)).thenReturn(new ArrayList<>(this.posts));
        when(this.service.getAndSortByDate(false)).thenReturn(new ArrayList<>(this.posts));
    }

    private void initFilterByDate() {
        when(this.service.filterByDate(this.posts, DATE, DATE)).thenReturn(new ArrayList<>(this.posts));
        when(this.service.filterByDate(this.posts, null, null)).thenReturn(new ArrayList<>(this.posts));
        when(this.service.filterByDate(this.posts, DATE, null)).thenReturn(new ArrayList<>(this.posts));
        when(this.service.filterByDate(this.posts, null, DATE)).thenReturn(new ArrayList<>(this.posts));
        when(this.service.filterByDate(new ArrayList<>(), DATE, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(new ArrayList<>(), null, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(new ArrayList<>(), DATE, null)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(new ArrayList<>(), null, null)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, DATE, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, null, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, DATE, null)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, null, null)).thenReturn(new ArrayList<>());
    }

    private void initGetAndFilterByDate() {
        when(this.service.getAndFilterByDate(DATE, DATE)).thenReturn(new ArrayList<>(this.posts));
        when(this.service.getAndFilterByDate(DATE, null)).thenReturn(new ArrayList<>());
        when(this.service.getAndFilterByDate(null, DATE)).thenReturn(new ArrayList<>());
        when(this.service.getAndFilterByDate(null, null)).thenReturn(new ArrayList<>());
    }

    private void initFilterByValid() {
        when(this.service.filterByValid(this.posts)).thenReturn(new ArrayList<>(this.posts));
        when(this.service.filterByValid(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(this.service.filterByValid(null)).thenReturn(new ArrayList<>());
    }
}
