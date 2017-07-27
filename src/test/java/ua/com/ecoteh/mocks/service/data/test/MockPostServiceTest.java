package ua.com.ecoteh.mocks.service.data.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.post.Post;
import ua.com.ecoteh.service.data.PostService;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.DATE;
import static ua.com.ecoteh.mocks.enity.MockModels.getPost;
import static ua.com.ecoteh.mocks.enity.MockModels.getPosts;
import static ua.com.ecoteh.mocks.service.data.MockServices.getPostService;

public final class MockPostServiceTest extends MockContentServiceTest<Post> {

    private static PostService service;
    private static Post post;
    private static Collection<Post> posts;

    @BeforeClass
    public static void beforeClass() {
        service = getPostService();
        post = getPost();
        posts = getPosts();
    }

    @Test
    public void whenSortByDateThenReturnNotEmptyCollection() {
        Collection<Post> sortedPosts = service.sortByDate(posts, true);
        assertFalse(sortedPosts.isEmpty());
        sortedPosts = service.sortByDate(posts, false);
        assertFalse(sortedPosts.isEmpty());
    }

    @Test
    public void whenSortByDateThenReturnCollectionWithNotNullObjects() {
        Collection<Post> sortedPosts = service.sortByDate(posts, true);
        sortedPosts.forEach(Assert::assertNotNull);
        sortedPosts = service.sortByDate(posts, false);
        sortedPosts.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortEmptyCollectionByDateThenReturnEmptyCollection() {
        Collection<Post> sortedPosts = service.sortByDate(new ArrayList<>(), true);
        assertTrue(sortedPosts.isEmpty());
        sortedPosts = service.sortByDate(new ArrayList<>(), false);
        assertTrue(sortedPosts.isEmpty());
    }

    @Test
    public void whenSortNullCollectionByDateThenReturnEmptyCollection() {
        Collection<Post> sortedPosts = service.sortByDate(null, true);
        assertTrue(sortedPosts.isEmpty());
        sortedPosts = service.sortByDate(null, false);
        assertTrue(sortedPosts.isEmpty());
    }

    @Test
    public void whenGetAndSortByDateThenReturnNotEmptyCollection() {
        Collection<Post> sortedPosts = service.getAndSortByDate(true);
        assertFalse(sortedPosts.isEmpty());
        sortedPosts = service.getAndSortByDate(false);
        assertFalse(sortedPosts.isEmpty());
    }

    @Test
    public void whenFilterByDateThenReturnNotEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(posts, DATE, DATE);
        assertFalse(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullStartDateThenReturnNotEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(posts, null, DATE);
        assertFalse(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullEndDateThenReturnNotEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(posts, DATE, null);
        assertFalse(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullDatesThenReturnNotEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(posts, null, null);
        assertFalse(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(new ArrayList<>(), DATE, DATE);
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateWithNullStartDateThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(new ArrayList<>(), null, DATE);
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateWithNullEndDateThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(new ArrayList<>(), DATE, null);
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateWithNullDatesThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(new ArrayList<>(), null, null);
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(null, DATE, DATE);
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateWithNullStartDateThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(null, null, DATE);
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateWithNullEndDateThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(null, DATE, null);
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateWithNullDatesThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByDate(null, null, null);
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateThenReturnNotEmptyCollection() {
        final Collection<Post> filteredPosts = service.getAndFilterByDate(DATE, DATE);
        assertFalse(filteredPosts.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateThenReturnCollectionWithNotNullObjects() {
        final Collection<Post> filteredPosts = service.getAndFilterByDate(DATE, DATE);
        filteredPosts.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByDateWithNullStartDateThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.getAndFilterByDate(null, DATE);
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullEndDateThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.getAndFilterByDate(DATE, null);
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullDatesThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.getAndFilterByDate(null, null);
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterByValidThenReturnNotEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByValid(posts);
        assertFalse(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByValidThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByValid(new ArrayList<>());
        assertTrue(filteredPosts.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByValidThenReturnEmptyCollection() {
        final Collection<Post> filteredPosts = service.filterByValid(null);
        assertTrue(filteredPosts.isEmpty());
    }

    @Ignore
    @Override
    protected PostService getService() {
        return service;
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
