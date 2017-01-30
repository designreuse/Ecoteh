package com.salimov.yurii.util.comparator;

import com.salimov.yurii.entity.Content;
import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.entity.Article;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.enity.MockEntity.getArticle;

public final class ContentComparatorTest {

    @Test
    public void contentComparator() {
        ContentComparator comparator = new ContentComparator();
        assertNotNull(comparator);
    }

    @Test
    public void getContentComparatorByTitle() {
        Comparator<Content> comparator = new ContentComparator.ByTitle<>();
        assertNotNull(comparator);

        final Article article1 = getArticle();
        final Article article2 = getArticle();
        int value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, 0);

        final String title1 = "A";
        final String title2 = "B";
        article1.setTitle(title1);
        article2.setTitle(title2);
        value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, -1);

        article1.setTitle(title2);
        article2.setTitle(title1);
        value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getContentComparatorByTitleWithInvalidContents() {
        Comparator<Content> comparator = new ContentComparator.ByTitle<>();
        getContentComparatorWithInvalidContents(comparator);
    }

    @Test
    public void getContentComparatorByUrl() {
        Comparator<Content> comparator = new ContentComparator.ByUrl<>();
        assertNotNull(comparator);

        final Article article1 = getArticle();
        final Article article2 = getArticle();
        int value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, 0);

        final String url1 = "A";
        final String url2 = "B";
        article1.setUrl(url1);
        article2.setUrl(url2);
        value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, -1);

        article1.setUrl(url2);
        article2.setUrl(url1);
        value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getContentComparatorByUrlWithInvalidContents() {
        Comparator<Content> comparator = new ContentComparator.ByUrl<>();
        getContentComparatorWithInvalidContents(comparator);
    }

    @Ignore
    private static void getContentComparatorWithInvalidContents(Comparator<Content> comparator) {
        int value = comparator.compare(null, null);
        assertNotNull(value);
        assertEquals(value, 0);

        final Article article = getArticle();
        value = comparator.compare(article, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, article);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
