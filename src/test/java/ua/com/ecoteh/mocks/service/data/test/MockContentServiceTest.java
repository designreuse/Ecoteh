package ua.com.ecoteh.mocks.service.data.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.service.data.ContentService;
import ua.com.ecoteh.util.comparator.ContentComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;

public abstract class MockContentServiceTest<T extends Content> extends MockDataServiceTest<T> {

    @Test
    public void whenGetByTitleThenReturnNotNull() {
        final ContentService<T> service = getService();
        T content = service.getByTitle(TITLE, true);
        assertNotNull(content);
        content = service.getByTitle(TITLE, false);
        assertNotNull(content);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetValidContentByNullTitleThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByTitle(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetInvalidContentByNullTitleThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByTitle(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetValidContentByBlankTitleThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByTitle("", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetInvalidContentByBlankTitleThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByTitle("", false);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetValidContentByUnknownTitleThenThrowNullPointerException() {
        final ContentService<T> service = getService();
        service.getByTitle(ANY_STRING, true);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetInvalidContentByUnknownTitleThenThrowNullPointerException() {
        final ContentService<T> service = getService();
        service.getByTitle(ANY_STRING, false);
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
    public void whenGetValidContentByNullUrlThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByUrl(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetInvalidContentByNullUrlThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByUrl(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetValidContentByBlankUrlThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByUrl("", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetInvalidContentByBlankUrlThenThrowIllegalArgumentException() {
        final ContentService<T> service = getService();
        service.getByUrl("", false);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetValidContentByUnknownUrlThenThrowNullPointerException() {
        final ContentService<T> service = getService();
        service.getByUrl(ANY_STRING, true);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetInvalidContentByUnknownUrlThenThrowNullPointerException() {
        final ContentService<T> service = getService();
        service.getByUrl(ANY_STRING, false);
    }

    @Test
    public void whenSortByTitleThenReturnNotEmptyCollection() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getObjects();
        Collection<T> sortedContents = service.sortByTitle(contents, true);
        assertFalse(sortedContents.isEmpty());
        sortedContents = service.sortByTitle(contents, false);
        assertFalse(sortedContents.isEmpty());
    }

    @Test
    public void whenSortByTitleThenReturnCollectionWithNotNullObjects() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getObjects();
        Collection<T> sortedContents = service.sortByTitle(contents, true);
        sortedContents.forEach(Assert::assertNotNull);
        sortedContents = service.sortByTitle(contents, false);
        sortedContents.forEach(Assert::assertNotNull);
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
    public void whenSortNullCollectionByTitleThenReturnEmptyCollection() {
        final ContentService<T> service = getService();
        final Collection<T> contents = null;
        Collection<T> sortedContents = service.sortByTitle(contents, true);
        assertTrue(sortedContents.isEmpty());
        sortedContents = service.sortByTitle(contents, false);
        assertTrue(sortedContents.isEmpty());
    }

    @Test
    public void whenSortByUrlThenReturnNotEmptyCollection() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getObjects();
        Collection<T> sortedContents = service.sortByUrl(contents, true);
        assertFalse(sortedContents.isEmpty());
        sortedContents = service.sortByUrl(contents, false);
        assertFalse(sortedContents.isEmpty());
    }

    @Test
    public void whenSortByUrlThenReturnCollectionWithNotNullObjects() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getObjects();
        Collection<T> sortedContents = service.sortByUrl(contents, true);
        sortedContents.forEach(Assert::assertNotNull);
        sortedContents = service.sortByUrl(contents, false);
        sortedContents.forEach(Assert::assertNotNull);
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
    public void whenSortNullCollectionByUrlThenReturnEmptyCollection() {
        final ContentService<T> service = getService();
        final Collection<T> contents = null;
        Collection<T> sortedContents = service.sortByUrl(contents, true);
        assertTrue(sortedContents.isEmpty());
        sortedContents = service.sortByUrl(contents, false);
        assertTrue(sortedContents.isEmpty());
    }

    @Test
    public void whenGetAndSortByTitleThenReturnNotEmptyCollection() {
        final ContentService<T> service = getService();
        Collection<T> sortedModels = service.getAndSortByTitle(true);
        assertFalse(sortedModels.isEmpty());
        sortedModels = service.getAndSortByTitle(false);
        assertFalse(sortedModels.isEmpty());
    }

    @Test
    public void whenGetAndSortByTitleThenReturnCollectionWithNotNullObjects() {
        final ContentService<T> service = getService();
        Collection<T> sortedModels = service.getAndSortByTitle(true);
        sortedModels.forEach(Assert::assertNotNull);
        sortedModels = service.getAndSortByTitle(false);
        sortedModels.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndSortByUrlThenReturnNotEmptyCollection() {
        final ContentService<T> service = getService();
        Collection<T> sortedModels = service.getAndSortByUrl(true);
        assertFalse(sortedModels.isEmpty());
        sortedModels = service.getAndSortByUrl(false);
        assertFalse(sortedModels.isEmpty());
    }

    @Test
    public void whenGetAndSortByUrlThenReturnCollectionWithNotNullObjects() {
        final ContentService<T> service = getService();
        Collection<T> sortedModels = service.getAndSortByUrl(true);
        sortedModels.forEach(Assert::assertNotNull);
        sortedModels = service.getAndSortByUrl(false);
        sortedModels.forEach(Assert::assertNotNull);
    }

    /*
    @Test
    public void whenSortWithComparatorByTitleThenReturnNotEmptyCollection() {
        final Comparator<T> comparatorByTitle = new ContentComparator.ByTitle<>();
        whenSortWithComparatorThenReturnNotEmptyCollection(comparatorByTitle);
    }

    @Test
    public void whenSortWithComparatorByUrlThenReturnNotEmptyCollection() {
        final Comparator<T> comparatorByUrl = new ContentComparator.ByUrl<>();
        whenSortWithComparatorThenReturnNotEmptyCollection(comparatorByUrl);
    }
*/

    @Test
    public void whenSortEmptyCollectionWithComparatorByTitleThenReturnEmptyCollection() {
        final Comparator<T> comparatorByTitle = new ContentComparator.ByTitle<>();
        whenSortEmptyCollectionWithComparatorThenReturnEmptyCollection(comparatorByTitle);
    }

    @Test
    public void whenSortNullCollectionWithComparatorByTitleThenReturnEmptyCollection() {
        final Comparator<T> comparatorByTitle = new ContentComparator.ByTitle<>();
        whenSortNullCollectionWithComparatorThenReturnEmptyCollection(comparatorByTitle);
    }

    @Test
    public void whenSortEmptyCollectionWithComparatorByUrlThenReturnEmptyCollection() {
        final Comparator<T> comparatorByUrl = new ContentComparator.ByUrl<>();
        whenSortEmptyCollectionWithComparatorThenReturnEmptyCollection(comparatorByUrl);
    }

    @Test
    public void whenSortNullCollectionWithComparatorByUrlThenReturnEmptyCollection() {
        final Comparator<T> comparatorByUrl = new ContentComparator.ByUrl<>();
        whenSortNullCollectionWithComparatorThenReturnEmptyCollection(comparatorByUrl);
    }

    @Test
    public void whenSortWithNullComparatorThenReturnNotEmptyCollection() {
        final Comparator<T> comparator = null;
        whenSortWithComparatorThenReturnNotEmptyCollection(comparator);
    }

    @Test
    public void whenSortEmptyCollectionWithNullComparatorThenReturnEmptyCollection() {
        final ContentService<T> service = getService();
        final Comparator<T> comparator = null;
        final Collection<T> models = new ArrayList<>();
        Collection<T> sortedModels = service.sort(models, comparator, true);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator, false);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator);
        assertTrue(sortedModels.isEmpty());
    }

    @Test
    public void whenSortNullCollectionWithNullComparatorThenReturnEmptyCollection() {
        final ContentService<T> service = getService();
        final Comparator<T> comparator = null;
        final Collection<T> models = null;
        Collection<T> sortedModels = service.sort(models, comparator, true);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator, false);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator);
        assertTrue(sortedModels.isEmpty());
    }

    @Ignore
    @Override
    protected abstract ContentService<T> getService();

    private void whenSortWithComparatorThenReturnNotEmptyCollection(final Comparator<T> comparator) {
        final ContentService<T> service = getService();
        final Collection<T> models = getObjects();
        Collection<T> sortedModels = service.sort(models, comparator, true);
        assertFalse(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator, false);
        assertFalse(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator);
        assertFalse(sortedModels.isEmpty());
    }

    private void whenSortEmptyCollectionWithComparatorThenReturnEmptyCollection(final Comparator<T> comparator) {
        final ContentService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        Collection<T> sortedModels = service.sort(models, comparator, true);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator, false);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator);
        assertTrue(sortedModels.isEmpty());
    }

    private void whenSortNullCollectionWithComparatorThenReturnEmptyCollection(final Comparator<T> comparator) {
        final ContentService<T> service = getService();
        final Collection<T> models = null;
        Collection<T> sortedModels = service.sort(models, comparator, true);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator, false);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator);
        assertTrue(sortedModels.isEmpty());
    }
}
