package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.Content;
import com.salimov.yurii.service.data.interfaces.ContentService;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static com.salimov.yurii.mocks.MockConstants.ANY_STRING;
import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static com.salimov.yurii.mocks.MockConstants.URL;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public abstract class ContentServiceImplTest<T extends Content<E>, E extends Number>
        extends DataServiceImplTest<T, E> {

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullTitleWithValidThenThrowsIllegalArgumentException() {
        getService().getByTitle(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullTitleWithInvalidThenThrowsIllegalArgumentException() {
        getService().getByTitle(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankTitleWithValidThenThrowsIllegalArgumentException() {
        getService().getByTitle("", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankTitleWithInvalidThenThrowsIllegalArgumentException() {
        getService().getByTitle(" ", false);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownTitleWithValidThenThrowsNullPointerException() {
        getService().getByTitle(ANY_STRING, true);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownTitleWithInvalidThenThrowsNullPointerException() {
        getService().getByTitle(ANY_STRING, false);
    }

    @Test
    public void whenGetByTitleWithTrueValidThenReturnsSomeContent() {
        assertNotNull(
                getService().getByTitle(TITLE, true)
        );
    }

    @Test
    public void whenGetByTitleWithFalseValidThenReturnsSomeContent() {
        assertNotNull(
                getService().getByTitle(TITLE, false)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullUrlWithValidThenThrowsIllegalArgumentException() {
        getService().getByUrl(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullUrlWithInvalidThenThrowsIllegalArgumentException() {
        getService().getByUrl(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankUrlWithValidThenThrowsIllegalArgumentException() {
        getService().getByUrl("", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankUrlWithInvalidThenThrowsIllegalArgumentException() {
        getService().getByUrl(" ", false);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownUrlWithValidThenThrowsNullPointerException() {
        getService().getByUrl(ANY_STRING, true);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownUrlWithInvalidThenThrowsNullPointerException() {
        getService().getByUrl(ANY_STRING, false);
    }

    @Test
    public void whenGetByUrlWithTrueValidThenReturnsSomeContent() {
        assertNotNull(
                getService().getByUrl(URL, false)
        );
    }

    @Test
    public void whenGetByUrlWithFalseValidThenReturnsSomeContent() {
        assertNotNull(
                getService().getByUrl(URL, false)
        );
    }

    @Test
    public void whenSortByTitleWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                getService()
                        .sortByTitle(null, true)
                        .isEmpty()
        );
    }

    @Test
    public void whenSortByTitleWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                getService()
                        .sortByTitle(null, false)
                        .isEmpty()
        );
    }

    @Test
    public void whenSortByTitleWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                getService()
                        .sortByTitle(
                                new ArrayList<>(), true
                        ).isEmpty()
        );
    }

    @Test
    public void whenSortByTitleWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                getService()
                        .sortByTitle(
                                new ArrayList<>(), false
                        ).isEmpty()
        );
    }

    @Test
    public void whenSortByTitleWithTrueReversThenReturnSomeList() {
        assertFalse(
                getService()
                        .sortByTitle(
                                getObjects(), true
                        ).isEmpty()
        );
    }

    @Test
    public void whenSortByTitleWithFalseReversThenReturnSomeList() {
        assertFalse(
                getService()
                        .sortByTitle(
                                getObjects(), false
                        ).isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                getService()
                        .sortByUrl(null, true)
                        .isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                getService()
                        .sortByUrl(null, false)
                        .isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                getService()
                        .sortByUrl(
                                new ArrayList<>(), true
                        ).isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                getService()
                        .sortByUrl(
                                new ArrayList<>(), false
                        ).isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithTrueReversThenReturnSomeList() {
        assertFalse(
                getService()
                        .sortByUrl(
                                getObjects(), true
                        ).isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithFalseReversThenReturnSomeList() {
        assertFalse(
                getService()
                        .sortByUrl(
                                getObjects(), false
                        ).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByTitleWithTrueReversThenReturnSomeCollection() {
        assertFalse(
                getService()
                        .getAndSortByTitle(true)
                        .isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByTitleWithFalseReversThenReturnSomeCollection() {
        assertFalse(
                getService()
                        .getAndSortByTitle(false)
                        .isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByUrlWithTrueReversThenReturnSomeCollection() {
        assertFalse(
                getService()
                        .getAndSortByUrl(true)
                        .isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByUrlWithFalseReversThenReturnSomeCollection() {
        assertFalse(
                getService()
                        .getAndSortByUrl(false)
                        .isEmpty()
        );
    }

    @Test
    public void whenRemoveByNullTitleThenDoNothing() {
        getService().removeByTitle(null);
    }

    @Test
    public void whenRemoveByBlankTitleThenDoNothing() {
        getService().removeByTitle("");
        getService().removeByTitle(" ");
        getService().removeByTitle("  ");
    }

    @Test
    public void whenRemoveByTitleThenDoIt() {
        getService().removeByTitle(TITLE);
    }

    @Test
    public void whenRemoveByNullUrlThenDoNothing() {
        getService().removeByUrl(null);
    }

    @Test
    public void whenRemoveByBlankUrlThenDoNothing() {
        getService().removeByUrl("");
        getService().removeByUrl(" ");
        getService().removeByUrl("  ");
    }

    @Test
    public void whenRemoveByUrlThenDoIt() {
        getService().removeByUrl(URL);
    }

    @Ignore
    @Override
    protected abstract ContentService<T, E> getService();
}
