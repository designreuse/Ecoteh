package ua.com.ecoteh.service.data;

import org.junit.Assert;
import org.junit.Test;
import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.service.data.comparator.ContentComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.TITLE;
import static ua.com.ecoteh.mocks.MockConstants.URL;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentServiceImplTest<T extends Content> extends DataServiceImplTest<T> {

    @Test
    public void whenGetByTitleThenReturnNotNull() {
        final ContentService<T> service = getService();
        T content = service.getByTitle(TITLE, true);
        assertNotNull(content);
        content = service.getByTitle(TITLE, false);
        assertNotNull(content);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullTitleThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByTitle(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyTitleThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByTitle("", true);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownTitleThenThrowNullPointerException() {
        final ContentService<T> service = getService();
        service.getByTitle(ANY_STRING, true);
    }

    @Test
    public void whenGetByUrlThenReturnNotNull() {
        final ContentService<T> service = getService();
        T content = service.getByUrl(URL, true);
        assertNotNull(content);
        content = service.getByUrl(URL, false);
        assertNotNull(content);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullUrlThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByUrl(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyUrlThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByUrl("", true);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownUrlThenThrowNullPointerException() {
        final ContentService<T> service = getService();
        service.getByUrl(ANY_STRING, true);
    }

    @Test
    public void whenRemoveByTitleThenDoIt() {
        final ContentService<T> service = getService();
        service.removeByTitle(TITLE);
    }

    @Test
    public void whenRemoveByNullTitleThenDoNothing() {
        final ContentService<T> service = getService();
        service.removeByTitle(null);
    }

    @Test
    public void whenRemoveByEmptyTitleThenDoNothing() {
        final ContentService<T> service = getService();
        service.removeByTitle("");
    }

    @Test
    public void whenRemoveByUrlThenDoIt() {
        final ContentService<T> service = getService();
        service.removeByUrl(URL);
    }

    @Test
    public void whenRemoveByNullUrlThenDoNothing() {
        final ContentService<T> service = getService();
        service.removeByUrl(null);
    }

    @Test
    public void whenRemoveByEmptyUrlThenDoNothing() {
        final ContentService<T> service = getService();
        service.removeByUrl("");
    }

    @Test
    public void whenSortByTitleThenReturnNotEmptyCollection() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getModels();
        Collection<T> sortedContents = service.sortByTitle(contents, true);
        assertFalse(sortedContents.isEmpty());
        sortedContents = service.sortByTitle(contents, false);
        assertFalse(sortedContents.isEmpty());
    }

    @Test
    public void whenSortByTitleThenReturnCollectionWithNotNullObjects() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getModels();
        Collection<T> sortedContents = service.sortByTitle(contents, true);
        sortedContents.forEach(Assert::assertNotNull);
        sortedContents = service.sortByTitle(contents, false);
        sortedContents.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortNullCollectionByTitleThenReturnEmptyCollection() {
        final ContentService<T> service = getService();
        final Collection<T> contents = null;
        Collection<T> sortedContents = service.sortByTitle(contents, true);
        assertTrue(sortedContents.isEmpty());
        sortedContents = service.sortByTitle(contents, false);
        assertTrue(sortedContents.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionByTitleThenReturnEmptyCollection() {
        final ContentService<T> service = getService();
        final Collection<T> contents = new ArrayList<>();
        Collection<T> sortedContents = service.sortByTitle(contents, true);
        assertTrue(sortedContents.isEmpty());
        sortedContents = service.sortByTitle(contents, false);
        assertTrue(sortedContents.isEmpty());
    }

    @Test
    public void whenSortByUrlThenReturnNotEmptyCollection() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getModels();
        Collection<T> sortedContents = service.sortByUrl(contents, true);
        assertFalse(sortedContents.isEmpty());
        sortedContents = service.sortByUrl(contents, false);
        assertFalse(sortedContents.isEmpty());
    }

    @Test
    public void whenSortByUrlThenReturnCollectionWithNotNullObjects() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getModels();
        Collection<T> sortedContents = service.sortByUrl(contents, true);
        sortedContents.forEach(Assert::assertNotNull);
        sortedContents = service.sortByUrl(contents, false);
        sortedContents.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortNullCollectionByUrlThenReturnEmptyCollection() {
        final ContentService<T> service = getService();
        final Collection<T> contents = null;
        Collection<T> sortedContents = service.sortByUrl(contents, true);
        assertTrue(sortedContents.isEmpty());
        sortedContents = service.sortByUrl(contents, false);
        assertTrue(sortedContents.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionByUrlThenReturnEmptyCollection() {
        final ContentService<T> service = getService();
        final Collection<T> contents = new ArrayList<>();
        Collection<T> sortedContents = service.sortByUrl(contents, true);
        assertTrue(sortedContents.isEmpty());
        sortedContents = service.sortByUrl(contents, false);
        assertTrue(sortedContents.isEmpty());
    }

    @Test
    public void whenGetAndSortByTitleThenReturnNotEmptyCollection() {
        final ContentService<T> service = getService();
        Collection<T> sortedContents = service.getAndSortByTitle(true);
        assertFalse(sortedContents.isEmpty());
        sortedContents = service.getAndSortByTitle(false);
        assertFalse(sortedContents.isEmpty());
    }

    @Test
    public void whenGetAndSortByTitleThenReturnCollectionWithNotNullObjects() {
        final ContentService<T> service = getService();
        Collection<T> sortedContents = service.getAndSortByTitle(true);
        sortedContents.forEach(Assert::assertNotNull);
        sortedContents = service.getAndSortByTitle(false);
        sortedContents.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndSortByUrlThenReturnNotEmptyCollection() {
        final ContentService<T> service = getService();
        Collection<T> sortedContents = service.getAndSortByUrl(true);
        assertFalse(sortedContents.isEmpty());
        sortedContents = service.getAndSortByUrl(false);
        assertFalse(sortedContents.isEmpty());
    }

    @Test
    public void whenGetAndSortByUrlThenReturnCollectionWithNotNullObjects() {
        final ContentService<T> service = getService();
        Collection<T> sortedContents = service.getAndSortByUrl(true);
        sortedContents.forEach(Assert::assertNotNull);
        sortedContents = service.getAndSortByUrl(false);
        sortedContents.forEach(Assert::assertNotNull);
    }

    @Override
    protected Comparator<T> getComparator() {
        return new ContentComparator.ByTitle<>();
    }

    @Override
    protected abstract ContentService<T> getService();
}