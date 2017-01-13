package com.salimov.yurii.entity;

import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class ArticleTest extends ContentTest<Article> {

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveNull() {
        Article article = new Article();
        assertNotNull(article.getDate());
        assertNotNull(article.getNumber());

        article = new Article(null, null, null, null, null);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());

        article = new Article("", "", "", "", "");
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());

        article = new Article(" ", " ", " ", " ", " ");
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());

        article = new Article("   ", "   ", "   ", "   ", "   ");
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final Article article = new Article(
                MockConstants.TITLE,
                MockConstants.DESCRIPTION,
                MockConstants.TEXT,
                MockConstants.KEYWORDS,
                MockConstants.NUMBER
        );
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
        assertEquals(
                article.getTitle(),
                MockConstants.TITLE
        );
        assertEquals(
                article.getDescription(),
                MockConstants.DESCRIPTION
        );
        assertEquals(
                article.getText(),
                MockConstants.TEXT
        );
        assertEquals(
                article.getKeywords(),
                MockConstants.KEYWORDS
        );
        assertEquals(
                article.getNumber(),
                MockConstants.NUMBER
        );
    }

    @Test
    @Override
    public void toStringTest() {
        final Article article = MockEntity.getArticle();
        final String value = article.getClass().getSimpleName() +
                " " + article.getTitle()
                + " \nKeywords: " + article.getKeywords()
                + " \nURL: " + article.getUrl()
                + " \nDescription: " + article.getDescription()
                + " \nText: " + article.getText()
                + " \nDate: " + article.getDate()
                + " \nNumber: " + article.getNumber();
        assertEquals(
                article.toString(),
                value
        );
    }

    @Test
    public void equalsInvalidObjects() {
        final Article article1 = new Article();
        final Article article2 = new Article();
        assertEquals(
                article1.equals(article2),
                article1.getNumber()
                        .equals(
                                article2.getNumber()
                        )
        );
        article1.setTitle(MockConstants.TITLE);
        article2.setTitle(MockConstants.TITLE);
        assertEquals(
                article1.equals(article2),
                article1.getNumber()
                        .equals(
                                article2.getNumber()
                        )
        );
        article1.setText(MockConstants.TEXT);
        article2.setText(MockConstants.TEXT);
        assertEquals(
                article1.equals(article2),
                article1.getNumber()
                        .equals(
                                article2.getNumber()
                        )
        );
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final Article article1 = MockEntity.getArticle();
        final Article article2 = (Article) article1.clone();
        assertEquals(article1, article2);
        final boolean value = (
                isNotBlank(article1.getTitle())
                        ? article1.getTitle().equalsIgnoreCase(article2.getTitle()) :
                        isBlank(article2.getTitle())
        ) && (
                isNotBlank(article1.getUrl()) ?
                        article1.getUrl().equalsIgnoreCase(article2.getUrl()) :
                        isBlank(article2.getUrl())
        );
        assertEquals(
                article1.equals(article2),
                value
        );
    }

    @Test
    public void hashCodeInvalidObject() {
        final Article article = new Article();
        int value = isNotBlank(article.getNumber()) ? article.getNumber().hashCode() : 0;
        assertEquals(
                article.hashCode(),
                value
        );
        article.setTitle(MockConstants.TITLE);
        value += (isNotBlank(article.getTitle()) ? article.getTitle().hashCode() : 0)
                + (isNotBlank(article.getUrl()) ? article.getUrl().hashCode() : 0);
        assertEquals(
                article.hashCode(),
                value
        );
        article.setText(MockConstants.TEXT);
        value += isNotBlank(article.getText()) ? article.getText().hashCode() : 0;
        assertEquals(
                article.hashCode(),
                value
        );
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        final Article article = MockEntity.getArticle();
        final int value = (
                isNotBlank(article.getTitle()) ? article.getTitle().hashCode() : 0
        ) + (
                isNotBlank(article.getUrl()) ? article.getUrl().hashCode() : 0
        ) + (
                isNotBlank(article.getNumber()) ? article.getNumber().hashCode() : 0
        ) + (
                isNotBlank(article.getText()) ? article.getText().hashCode() : 0
        );
        for (int i = 0; i < 10; i++) {
            assertEquals(article.hashCode(), value);
        }
    }

    @Test
    @Override
    public void whenInitializeObjectWithInvalidParametersThenGetNull() {
        super.whenInitializeObjectWithInvalidParametersThenGetNull();
        final Article article = new Article();
        article.initialize(null, null, null, null, null, null, null, null, null);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNull(article.getMainPhoto());
        assertNotNull(article.getSlides());
        assertNotNull(article.getVideos());
        assertNotNull(article.getDate());
        assertTrue(article.getSlides().isEmpty());
        assertTrue(article.getVideos().isEmpty());

        final File file = new File();
        final List<File> slides = new ArrayList<>();
        slides.add(file);
        final Video video = new Video();
        List<Video> videos = new ArrayList<>();
        videos.add(video);

        article.initialize("", "", "", "", "", null, file, slides, videos);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNull(article.getMainPhoto());
        assertNotNull(article.getSlides());
        assertNotNull(article.getVideos());
        assertNotNull(article.getDate());
        assertTrue(article.getSlides().isEmpty());
        assertTrue(article.getVideos().isEmpty());

        article.initialize(" ", " ", " ", " ", " ", null, file, slides, videos);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNull(article.getMainPhoto());
        assertNotNull(article.getSlides());
        assertNotNull(article.getVideos());
        assertNotNull(article.getDate());
        assertTrue(article.getSlides().isEmpty());
        assertTrue(article.getVideos().isEmpty());

        article.initialize("   ", "   ", "   ", "   ", "   ", null, file, slides, videos);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNull(article.getMainPhoto());
        assertNotNull(article.getSlides());
        assertNotNull(article.getVideos());
        assertNotNull(article.getDate());
        assertTrue(article.getSlides().isEmpty());
        assertTrue(article.getVideos().isEmpty());
    }

    @Test
    @Override
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        super.whenInitializeObjectWithValidParametersThenGetThisValue();
        final Article article = new Article();
        final Category category = MockEntity.getCategory();
        final File mainFile = MockEntity.getPhoto();
        final Collection<File> slides = MockEntity.getPhotos();
        final Collection<Video> videos = MockEntity.getVideos();
        article.initialize(
                MockConstants.TITLE,
                MockConstants.DESCRIPTION,
                MockConstants.TEXT,
                MockConstants.KEYWORDS,
                MockConstants.NUMBER,
                category,
                mainFile,
                slides,
                videos
        );
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNotNull(article.getCategory());
        assertNotNull(article.getMainPhoto());
        assertNotNull(article.getSlides());
        assertNotNull(article.getVideos());
        assertNotNull(article.getDate());
        assertFalse(
                article.getSlides().isEmpty()
        );
        assertFalse(
                article.getVideos().isEmpty()
        );
        assertEquals(
                article.getTitle(),
                MockConstants.TITLE
        );
        assertEquals(
                article.getDescription(),
                MockConstants.DESCRIPTION
        );
        assertEquals(
                article.getText(),
                MockConstants.TEXT
        );
        assertEquals(
                article.getKeywords(),
                MockConstants.KEYWORDS
        );
        assertEquals(
                article.getKeywords(),
                MockConstants.KEYWORDS
        );
        assertEquals(
                article.getNumber(),
                MockConstants.NUMBER
        );
        assertEquals(
                article.getCategory(),
                category
        );
        assertEquals(
                article.getMainPhoto(),
                mainFile
        );
        assertTrue(
                article.containsSlides(slides)
        );
        assertTrue(
                article.containsVideos(videos)
        );
    }

    @Test
    public void whenSetInvalidNumberThenGetNull() {
        final Article article = MockEntity.getArticle();
        article.setNumber(null);
        assertNotNull(article.getNumber());
        article.setNumber("");
        assertNotNull(article.getNumber());
        article.setNumber(" ");
        assertNotNull(article.getNumber());
        article.setNumber("   ");
        assertNotNull(article.getNumber());
    }

    @Test
    public void whenSetValidNumberThenGetThisNumber() {
        final Article article = MockEntity.getArticle();
        article.setNumber(MockConstants.NUMBER);
        assertNotNull(article.getNumber());
        assertEquals(
                article.getNumber(),
                MockConstants.NUMBER
        );
        article.newNumber();
        assertNotNull(article.getNumber());
        assertNotEquals(
                article.getNumber(),
                MockConstants.NUMBER
        );
    }

    @Test
    public void whenSetInvalidTextThenGetNull() {
        final Article article = MockEntity.getArticle();
        article.setText(null);
        assertNull(article.getText());
        article.setText("");
        assertNull(article.getText());
        article.setText(" ");
        assertNull(article.getText());
        article.setText("        ");
        assertNull(article.getText());
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final Article article = MockEntity.getArticle();
        article.setText(MockConstants.TEXT);
        assertNotNull(article.getText());
        assertEquals(
                article.getText(),
                MockConstants.TEXT
        );
    }

    @Test
    public void whenSetInvalidDateThenGetNewDate() {
        final Article article = MockEntity.getArticle();
        article.setDate(null);
        assertNotNull(article.getDate());
        assertNotNull(article.getDateToString());
    }

    @Test
    public void whenSetValidDateThenGetThisDate() {
        final Article article = MockEntity.getArticle();
        final Date date = new Date();
        article.setDate(date);
        assertNotNull(article.getDate());
        assertEquals(
                article.getDate(),
                date
        );
        assertNotNull(article.getDateToString());
    }

    @Test
    public void whenSetNullCategoryThenGetNull() {
        final Article article = MockEntity.getArticle();
        article.setCategory(null);
        assertNull(article.getCategory());
    }

    @Test
    public void whenSetNotNullCategoryThenGetThisCategory() {
        final Article article = MockEntity.getArticle();
        final Category category = MockEntity.getCategory();
        category.setArticles(null);
        article.setCategory(null);
        article.setCategory(category);
        assertNotNull(article.getCategory());
        assertEquals(
                article.getCategory(),
                category
        );
        assertTrue(category.containsArticle(article));
        article.setCategory(null);
        assertNull(article.getCategory());
        assertFalse(category.containsArticle(article));
    }

    @Test
    public void whenSetInvalidMainPhotoThenGetNull() {
        final Article article = MockEntity.getArticle();
        article.setMainPhoto(null);
        assertNull(article.getMainPhoto());

        final File invalidFile = new File();
        article.setMainPhoto(invalidFile);
        assertNull(article.getMainPhoto());

        final File file = MockEntity.getPhoto();
        file.setUrl(null);
        article.setMainPhoto(file);
        assertNull(article.getMainPhoto());
    }

    @Test
    public void whenSetValidMainPhotoThenGetThisPhoto() {
        final Article article = MockEntity.getArticle();
        final File file = MockEntity.getPhoto();
        article.setMainPhoto(file);
        assertNotNull(article.getMainPhoto());
        assertEquals(
                article.getMainPhoto(),
                file
        );
    }

    @Test
    public void whenSlidesAreInvalidThenNotAddThey() {
        final Article article = MockEntity.getArticle();
        article.setSlides(null);
        assertTrue(article.getSlides().isEmpty());
        List<File> files = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                final File invalidFile = new File();
                files.add(invalidFile);
                article.addSlide(invalidFile);
            } else {
                files.add(null);
                article.addSlide(null);
            }
        }
        assertTrue(
                article.getSlides().isEmpty()
        );
        article.setSlides(files);
        assertTrue(
                article.getSlides().isEmpty()
        );
        article.addSlides(files);
        assertTrue(
                article.getSlides().isEmpty()
        );
    }

    @Test
    public void whenSlidesAreValidThenAddThey() {
        final Article article = MockEntity.getArticle();

        final List<File> slides = MockEntity.getPhotos(
                MockConstants.DEFAULT_SIZE
        );
        article.setSlides(slides);
        assertFalse(article.getSlides().isEmpty());
        assertEquals(
                article.getSlides().size(),
                slides.size()
        );
        article.clearSlides();
        assertTrue(
                article.getSlides().isEmpty()
        );
        for (int i = 0; i < slides.size(); i++) {
            article.addSlides(slides);
        }
        assertFalse(
                article.getSlides().isEmpty()
        );
        assertEquals(
                article.getSlides().size(),
                slides.size()
        );
        article.removeSlides(slides);
        assertTrue(
                article.getSlides().isEmpty()
        );
        article.setSlides(slides);
        assertTrue(
                article.containsSlides(slides)
        );
        for (File file : slides) {
            assertTrue(
                    article.containsSlide(file)
            );
            article.removeSlide(file);
            assertFalse(
                    article.containsSlide(file)
            );
        }
    }

    @Test
    public void whenVideosAreInvalidThenNotAddThey() {
        final Article article = MockEntity.getArticle();
        article.setVideos(null);
        assertTrue(
                article.getVideos().isEmpty()
        );
        List<Video> videos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                Video invalidVideos = new Video();
                videos.add(invalidVideos);
                article.addVideo(invalidVideos);
            } else {
                videos.add(null);
                article.addSlide(null);
            }
        }
        assertTrue(
                article.getVideos().isEmpty()
        );
        article.setVideos(videos);
        assertTrue(
                article.getVideos().isEmpty()
        );
        article.setVideos(videos);
        assertTrue(
                article.getVideos().isEmpty()
        );
    }

    @Test
    public void whenVideosAreValidThenAddThey() {
        final Article article = MockEntity.getArticle();
        final int size = 5;
        final List<Video> videos = MockEntity.getVideos(size);
        article.setVideos(videos);
        assertFalse(
                article.getVideos().isEmpty()
        );
        assertEquals(
                article.getVideos().size(),
                size
        );
        article.clearVideos();
        assertTrue(
                article.getVideos().isEmpty()
        );
        for (int i = 0; i < size; i++) {
            article.addVideos(videos);
        }
        assertFalse(
                article.getVideos().isEmpty()
        );
        assertEquals(
                article.getVideos().size(),
                size
        );
        article.removeVideos(videos);
        assertTrue(
                article.getVideos().isEmpty()
        );
        article.setVideos(videos);
        assertTrue(article.containsVideos(videos));
        for (Video video : videos) {
            assertTrue(
                    article.containsVideo(video)
            );
            article.removeVideo(video);
            assertFalse(
                    article.containsVideo(video)
            );
        }
    }

    @Test
    @Override
    public void validObject() {
        super.validObject();
        Article article = MockEntity.getArticle();
        assertFalse(Article.isValidated(null));
        assertTrue(
                Article.isValidated(article)
        );
        article.setTitle(null);
        assertFalse(
                Article.isValidated(article)
        );
        article = MockEntity.getArticle();
        article.setUrl(null);
        assertFalse(
                Article.isValidated(article)
        );
        article = MockEntity.getArticle();
        article.setText(null);
        assertFalse(
                Article.isValidated(article)
        );
    }

    @Ignore
    @Override
    protected Article getObject() {
        return MockEntity.getArticle();
    }
}
