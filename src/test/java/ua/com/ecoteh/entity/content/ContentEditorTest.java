package ua.com.ecoteh.entity.content;

import org.junit.Test;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.ModelEditorTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentEditorTest<T extends Content> extends ModelEditorTest<T> {

    @Test
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final ContentEditor<T, ?> editor = getEditor();
        final T updatedContent = editor.update();
        final T content = getModel();
        assertEquals(updatedContent.getTitle(), content.getTitle());
        assertEquals(updatedContent.getUrl(), content.getUrl());
        assertEquals(updatedContent.getText(), content.getText());
        assertEquals(updatedContent.getDescription(), content.getDescription());
        assertEquals(updatedContent.getKeywords(), content.getKeywords());
        assertEquals(updatedContent.getLogo(), content.getLogo());
    }

    @Test
    public void whenAddTitleThenBuildWithIt() {
        final ContentEditor<T, ?> editor = getEditor();
        T content;
        String title;
        for (int i = 0; i < 5; i++) {
            title = TITLE + i;
            editor.addTitle(title);
            content = editor.update();
            assertEquals(content.getTitle(), title);
        }
    }

    @Test
    public void whenAddNullTitleThenBuildWithEmptyIt() {
        final ContentEditor<T, ?> editor = getEditor();
        editor.addTitle(null);
        final T updatedContent = editor.update();
        final T content = getModel();
        assertEquals(updatedContent.getTitle(), content.getTitle());
    }

    @Test
    public void whenAddUrlThenBuildWithIt() {
        final ContentEditor<T, ?> editor = getEditor();
        T content;
        String url;
        for (int i = 0; i < 5; i++) {
            url = URL + i;
            editor.addUrl(url);
            content = editor.update();
            assertEquals(content.getUrl(), url);
        }
    }

    @Test
    public void whenAddNullUrlThenBuildWithEmptyIt() {
        final ContentEditor<T, ?> editor = getEditor();
        editor.addUrl(null);
        final T updatedContent = editor.update();
        final T content = getModel();
        assertEquals(updatedContent.getUrl(), content.getUrl());
    }

    @Test
    public void whenAddTextThenBuildWithIt() {
        final ContentEditor<T, ?> editor = getEditor();
        T content;
        String text;
        for (int i = 0; i < 5; i++) {
            text = TEXT + i;
            editor.addText(text);
            content = editor.update();
            assertEquals(content.getText(), text);
        }
    }

    @Test
    public void whenAddNullTextThenBuildWithEmptyIt() {
        final T thisContent = getModel();
        final ContentEditor<T, ?> editor = getEditor();
        editor.addText(null);
        final T content = editor.update();
        assertEquals(content.getText(), thisContent.getText());
    }

    @Test
    public void whenAddDescriptionThenBuildWithIt() {
        final ContentEditor<T, ?> editor = getEditor();
        T content;
        String description;
        for (int i = 0; i < 5; i++) {
            description = DESCRIPTION + i;
            editor.addDescription(description);
            content = editor.update();
            assertEquals(content.getDescription(), description);
        }
    }

    @Test
    public void whenAddNullDescriptionThenBuildWithEmptyIt() {
        final ContentEditor<T, ?> editor = getEditor();
        editor.addDescription(null);
        final T updatedContent = editor.update();
        final T content = getModel();
        assertEquals(updatedContent.getDescription(), content.getDescription());
    }

    @Test
    public void whenAddKeywordsThenBuildWithIt() {
        final ContentEditor<T, ?> editor = getEditor();
        T content;
        String keywords;
        for (int i = 0; i < 5; i++) {
            keywords = KEYWORDS + i;
            editor.addKeywords(keywords);
            content = editor.update();
            assertEquals(content.getKeywords(), keywords);
        }
    }

    @Test
    public void whenAddNullKeywordsThenBuildWithEmptyIt() {
        final ContentEditor<T, ?> editor = getEditor();
        editor.addKeywords(null);
        final T updatedContent = editor.update();
        final T content = getModel();
        assertEquals(updatedContent.getKeywords(), content.getKeywords());
    }

    @Test
    public void whenAddLogoThenBuildWithIt() {
        final ContentEditor<T, ?> editor = getEditor();
        final File logo = getFile();
        editor.addLogo(logo);
        final T content = editor.update();
        assertEquals(content.getLogo(), logo);
    }

    @Test
    public void whenAddNullLogoThenBuildWithEmptyIt() {
        final ContentEditor<T, ?> editor = getEditor();
        editor.addLogo(null);
        final T updatedContent = editor.update();
        final T content = getModel();
        assertEquals(updatedContent.getLogo(), content.getLogo());
    }

    @Override
    protected abstract ContentEditor<T, ?> getEditor();
}