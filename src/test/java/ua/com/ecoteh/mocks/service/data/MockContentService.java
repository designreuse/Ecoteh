package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.service.data.ContentService;
import ua.com.ecoteh.service.data.comparator.ContentComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.TITLE;
import static ua.com.ecoteh.mocks.MockConstants.URL;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
abstract class MockContentService<T extends Content> extends MockDataService<T> {

    @Override
    ContentService<T> create() {
        super.create();
        initGetByTitle();
        initGetByUrl();
        initGetAndSortByTitle();
        initGetAndSortByUrl();
        initSortByTitle();
        initSortByUrl();
        initGetAndSortByTitle();
        initGetAndSortByUrl();
        initSort();
        return getService();
    }

    @Override
    abstract ContentService<T> getService();

    @Override
    abstract T getModel();

    @Override
    abstract Collection<T> getModels();

    private void initGetByTitle() {
        final ContentService<T> service = getService();
        final T content = getModel();
        when(service.getByTitle(TITLE, true)).thenReturn(content);
        when(service.getByTitle(TITLE, false)).thenReturn(content);
        when(service.getByTitle(null, true)).thenThrow(new IllegalArgumentException());
        when(service.getByTitle(null, false)).thenThrow(new IllegalArgumentException());
        when(service.getByTitle("", true)).thenThrow(new IllegalArgumentException());
        when(service.getByTitle("", false)).thenThrow(new IllegalArgumentException());
        when(service.getByTitle(ANY_STRING, true)).thenThrow(new NullPointerException());
        when(service.getByTitle(ANY_STRING, false)).thenThrow(new NullPointerException());
    }

    private void initGetByUrl() {
        final ContentService<T> service = getService();
        final T content = getModel();
        when(service.getByUrl(URL, true)).thenReturn(content);
        when(service.getByUrl(URL, false)).thenReturn(content);
        when(service.getByUrl(null, true)).thenThrow(new IllegalArgumentException());
        when(service.getByUrl(null, false)).thenThrow(new IllegalArgumentException());
        when(service.getByUrl("", true)).thenThrow(new IllegalArgumentException());
        when(service.getByUrl("", false)).thenThrow(new IllegalArgumentException());
        when(service.getByUrl(ANY_STRING, true)).thenThrow(new NullPointerException());
        when(service.getByUrl(ANY_STRING, false)).thenThrow(new NullPointerException());
    }

    private void initSortByTitle() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getModels();
        when(service.sortByTitle(contents, true)).thenReturn(new ArrayList<>(contents));
        when(service.sortByTitle(contents, false)).thenReturn(new ArrayList<>(contents));
        when(service.sortByTitle(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(service.sortByTitle(new ArrayList<>(), false)).thenReturn(new ArrayList<>());
        when(service.sortByTitle(null, true)).thenReturn(new ArrayList<>());
        when(service.sortByTitle(null, false)).thenReturn(new ArrayList<>());
    }

    private void initSortByUrl() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getModels();
        when(service.sortByUrl(contents, true)).thenReturn(new ArrayList<>(contents));
        when(service.sortByUrl(contents, false)).thenReturn(new ArrayList<>(contents));
        when(service.sortByUrl(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(service.sortByUrl(new ArrayList<>(), false)).thenReturn(new ArrayList<>());
        when(service.sortByUrl(null, true)).thenReturn(new ArrayList<>());
        when(service.sortByUrl(null, false)).thenReturn(new ArrayList<>());
    }

    private void initGetAndSortByTitle() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getModels();
        when(service.getAndSortByTitle(true)).thenReturn(new ArrayList<>(contents));
        when(service.getAndSortByTitle(false)).thenReturn(new ArrayList<>(contents));
    }

    private void initGetAndSortByUrl() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getModels();
        when(service.getAndSortByUrl(true)).thenReturn(new ArrayList<>(contents));
        when(service.getAndSortByUrl(false)).thenReturn(new ArrayList<>(contents));
    }

    private void initSort() {
        final ContentService<T> service = getService();
        final Collection<T> contents = getModels();
        final Comparator<T> comparatorByTitle = new ContentComparator.ByTitle<>();
        when(service.sort(contents, comparatorByTitle, true)).thenReturn(new ArrayList<>(contents));
        when(service.sort(contents, comparatorByTitle, false)).thenReturn(new ArrayList<>(contents));
        when(service.sort(contents, comparatorByTitle)).thenReturn(new ArrayList<>(contents));

        when(service.sort(new ArrayList<>(), comparatorByTitle, true)).thenReturn(new ArrayList<>());
        when(service.sort(new ArrayList<>(), comparatorByTitle, false)).thenReturn(new ArrayList<>());
        when(service.sort(new ArrayList<>(), comparatorByTitle)).thenReturn(new ArrayList<>());

        when(service.sort(null, comparatorByTitle, true)).thenReturn(new ArrayList<>());
        when(service.sort(null, comparatorByTitle, false)).thenReturn(new ArrayList<>());
        when(service.sort(null, comparatorByTitle)).thenReturn(new ArrayList<>());

        final Comparator<T> comparatorByUrl = new ContentComparator.ByUrl<>();
        when(service.sort(contents, comparatorByUrl, true)).thenReturn(new ArrayList<>(contents));
        when(service.sort(contents, comparatorByUrl, false)).thenReturn(new ArrayList<>(contents));
        when(service.sort(contents, comparatorByUrl)).thenReturn(new ArrayList<>(contents));

        when(service.sort(new ArrayList<>(), comparatorByUrl, true)).thenReturn(new ArrayList<>());
        when(service.sort(new ArrayList<>(), comparatorByUrl, false)).thenReturn(new ArrayList<>());
        when(service.sort(new ArrayList<>(), comparatorByUrl)).thenReturn(new ArrayList<>());

        when(service.sort(null, comparatorByUrl, true)).thenReturn(new ArrayList<>());
        when(service.sort(null, comparatorByUrl, false)).thenReturn(new ArrayList<>());
        when(service.sort(null, comparatorByUrl)).thenReturn(new ArrayList<>());

        when(service.sort(contents, null, true)).thenReturn(new ArrayList<>(contents));
        when(service.sort(contents, null, false)).thenReturn(new ArrayList<>(contents));
        when(service.sort(contents, null)).thenReturn(new ArrayList<>(contents));

        when(service.sort(new ArrayList<>(), null, true)).thenReturn(new ArrayList<>());
        when(service.sort(new ArrayList<>(), null, false)).thenReturn(new ArrayList<>());
        when(service.sort(new ArrayList<>(), null)).thenReturn(new ArrayList<>());

        when(service.sort(null, null, true)).thenReturn(new ArrayList<>());
        when(service.sort(null, null, false)).thenReturn(new ArrayList<>());
        when(service.sort(null, null)).thenReturn(new ArrayList<>());
    }
}
