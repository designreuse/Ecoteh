package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.content.ContentEntity;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class ContentEntityComparatorTest {

    @Test
    public void contentComparator() {
        ContentComparator comparator = new ContentComparator();
        assertNotNull(comparator);
    }

    @Test
    public void getContentComparatorByTitle() {
        Comparator<ContentEntity> comparator = new ContentComparator.ByTitle<>();
        assertNotNull(comparator);

        final ArticleEntity articleEntity1 = MockEntity.getArticleEntity();
        final ArticleEntity articleEntity2 = MockEntity.getArticleEntity();
        int value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, 0);

        final String title1 = "A";
        final String title2 = "B";
        articleEntity1.setTitle(title1);
        articleEntity2.setTitle(title2);
        value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, -1);

        articleEntity1.setTitle(title2);
        articleEntity2.setTitle(title1);
        value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getContentComparatorByTitleWithInvalidContents() {
        Comparator<ContentEntity> comparator = new ContentComparator.ByTitle<>();
        getContentComparatorWithInvalidContents(comparator);
    }

    @Test
    public void getContentComparatorByUrl() {
        Comparator<ContentEntity> comparator = new ContentComparator.ByUrl<>();
        assertNotNull(comparator);

        final ArticleEntity articleEntity1 = MockEntity.getArticleEntity();
        final ArticleEntity articleEntity2 = MockEntity.getArticleEntity();
        int value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, 0);

        final String url1 = "A";
        final String url2 = "B";
        articleEntity1.setUrl(url1);
        articleEntity2.setUrl(url2);
        value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, -1);

        articleEntity1.setUrl(url2);
        articleEntity2.setUrl(url1);
        value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getContentComparatorByUrlWithInvalidContents() {
        Comparator<ContentEntity> comparator = new ContentComparator.ByUrl<>();
        getContentComparatorWithInvalidContents(comparator);
    }

    @Ignore
    private static void getContentComparatorWithInvalidContents(Comparator<ContentEntity> comparator) {
        int value = comparator.compare(null, null);
        assertNotNull(value);
        assertEquals(value, 0);

        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        value = comparator.compare(articleEntity, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, articleEntity);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
