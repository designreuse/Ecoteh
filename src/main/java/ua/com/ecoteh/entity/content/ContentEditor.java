package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileEditor;
import ua.com.ecoteh.entity.model.ModelEditor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The abstract superclass implements a set of standard methods
 * for editing an objects of the {@link Content} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Content
 */
public abstract class ContentEditor<T extends Content, E extends ContentEditor<T, E>> extends ModelEditor<T, E> {

    /**
     * The content to edit.
     */
    private final T content;

    /**
     * The new title of the content.
     */
    private String title;

    /**
     * The new URL of the content.
     */
    private String url;

    /**
     * The new text of the article.
     */
    private String text;

    /**
     * The new description of the contentE.
     */
    private String description;

    /**
     * The new keywords of the content.
     */
    private String keywords;

    /**
     * The new logo of the content.
     */
    private File logo;

    /**
     * Constructor.
     *
     * @param content the content to edit.
     */
    protected ContentEditor(final T content) {
        super(content);
        this.content = content;
    }

    /**
     * Copies the incoming content.
     *
     * @param content the content to copy.
     * @return the content editor.
     */
    @Override
    public E copy(final T content) {
        return super.copy(content)
                .addTitle(content.getTitle())
                .addUrl(content.getUrl())
                .addText(content.getText())
                .addDescription(content.getDescription())
                .addKeywords(content.getKeywords())
                .addLogo(content.getLogo());
    }

    /**
     * Adds new title to the content.
     *
     * @param title a new title to the content.
     * @return the content editor.
     */
    public E addTitle(final String title) {
        this.title = title;
        return (E) this;
    }

    /**
     * Adds new URL to the content.
     *
     * @param url a new URL to the content.
     * @return the content editor.
     */
    public E addUrl(final String url) {
        this.url = url;
        return (E) this;
    }

    /**
     * Adds new text to the content.
     *
     * @param text a new text to the content.
     * @return the content editor.
     */
    public E addText(final String text) {
        this.text = text;
        return (E) this;
    }

    /**
     * Adds new description to the content.
     *
     * @param description a new description to the content.
     * @return the content editor.
     */
    public E addDescription(final String description) {
        this.description = description;
        return (E) this;
    }

    /**
     * Adds new keywords to the content.
     *
     * @param keywords a new keywords to the content.
     * @return the content editor.
     */
    public E addKeywords(final String keywords) {
        this.keywords = keywords;
        return (E) this;
    }

    /**
     * Adds new logo to the content.
     *
     * @param logo a new logo to the content.
     * @return the content editor.
     */
    public E addLogo(final File logo) {
        this.logo = logo;
        return (E) this;
    }

    /**
     * Returns a new title of the content.
     * Returns the content title if the title is null.
     *
     * @return The new content title.
     */
    protected String getTitle() {
        return isNotNull(this.title) ? this.title : this.content.getTitle();
    }

    /**
     * Returns a new title of the content.
     * Returns the content title if the title is null.
     *
     * @return The new content title.
     */
    protected String getUrl() {
        return isNotNull(this.url) ? this.url : this.content.getUrl();
    }

    /**
     * Returns a new text of the content.
     * Returns the content text if the text is null.
     *
     * @return The new content text.
     */
    protected String getText() {
        return isNotNull(this.text) ? this.text : this.content.getText();
    }

    /**
     * Returns a new description of the content.
     * Returns the content description if the description is null.
     *
     * @return The new content description.
     */
    protected String getDescription() {
        return isNotNull(this.description) ? this.description : this.content.getDescription();
    }

    /**
     * Returns a new keywords of the content.
     * Returns the content keywords if the keywords is null.
     *
     * @return The new content keywords.
     */
    protected String getKeywords() {
        return isNotNull(this.keywords) ? this.keywords : this.content.getKeywords();
    }

    /**
     * Returns a new logo of the content.
     * Returns the content logo if the logo is null.
     *
     * @return The new content keywords.
     * @see FileEditor
     */
    protected File getLogo() {
        final FileEditor fileEditor = this.content.getLogo().getEditor();
        fileEditor.copy(this.logo);
        return fileEditor.update();
    }
}
