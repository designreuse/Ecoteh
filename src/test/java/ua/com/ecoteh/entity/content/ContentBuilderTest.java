package ua.com.ecoteh.entity.content;

import org.junit.Test;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.ModelBuilderTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentBuilderTest<T extends Content> extends ModelBuilderTest<T> {

    @Test
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final ContentBuilder<T, ?> builder = getBuilder();
        final T content = builder.build();
        assertNotNull(content.getTitle());
        assertNotNull(content.getUrl());
        assertNotNull(content.getText());
        assertNotNull(content.getDescription());
        assertNotNull(content.getKeywords());
        assertNotNull(content.getLogo());
    }

    @Test
    public void whenAddTitleThenBuildWithIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        T content;
        String title;
        for (int i = 0; i < 5; i++) {
            title = TITLE + i;
            builder.addTitle(title);
            content = builder.build();
            assertEquals(content.getTitle(), title);
        }
    }

    @Test
    public void whenAddNullTitleThenBuildWithEmptyIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        builder.addTitle(null);
        final T content = builder.build();
        assertTrue(content.getTitle().isEmpty());
    }

    @Test
    public void whenAddUrlThenBuildWithIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        T content;
        String url;
        for (int i = 0; i < 5; i++) {
            url = URL + i;
            builder.addUrl(url);
            content = builder.build();
            assertEquals(content.getUrl(), url);
        }
    }

    @Test
    public void whenAddNullUrlThenBuildWithEmptyIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        builder.addUrl(null);
        final T content = builder.build();
        assertTrue(content.getUrl().isEmpty());
    }

    @Test
    public void whenAddTextThenBuildWithIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        T content;
        String text;
        for (int i = 0; i < 5; i++) {
            text = TEXT + i;
            builder.addText(text);
            content = builder.build();
            assertEquals(content.getText(), text);
        }
    }

    @Test
    public void whenAddNullTextThenBuildWithEmptyIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        builder.addText(null);
        final T content = builder.build();
        assertTrue(content.getText().isEmpty());
    }

    @Test
    public void whenAddDescriptionThenBuildWithIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        T content;
        String description;
        for (int i = 0; i < 5; i++) {
            description = DESCRIPTION + i;
            builder.addDescription(description);
            content = builder.build();
            assertEquals(content.getDescription(), description);
        }
    }

    @Test
    public void whenAddNullDescriptionThenBuildWithEmptyIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        builder.addDescription(null);
        final T content = builder.build();
        assertTrue(content.getDescription().isEmpty());
    }

    @Test
    public void whenAddKeywordsThenBuildWithIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        T content;
        String keywords;
        for (int i = 0; i < 5; i++) {
            keywords = KEYWORDS + i;
            builder.addKeywords(keywords);
            content = builder.build();
            assertEquals(content.getKeywords(), keywords);
        }
    }

    @Test
    public void whenAddNullKeywordsThenBuildWithEmptyIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        builder.addKeywords(null);
        final T content = builder.build();
        assertTrue(content.getKeywords().isEmpty());
    }

    @Test
    public void whenAddLogoThenBuildWithIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        final File logo = getFile();
        builder.addLogo(logo);
        final T content = builder.build();
        assertEquals(content.getLogo(), logo);
    }

    @Test
    public void whenAddNullLogoThenBuildWithEmptyIt() {
        final ContentBuilder<T, ?> builder = getBuilder();
        builder.addLogo(null);
        final T content = builder.build();
        assertNotNull(content.getLogo());
    }

    @Override
    protected abstract ContentBuilder<T, ?> getBuilder();
}