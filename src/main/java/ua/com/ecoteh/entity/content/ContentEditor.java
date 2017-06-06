package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileEditor;
import ua.com.ecoteh.entity.model.ModelEditor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentEditor<T extends Content, R extends ContentEditor<T, R>>
        extends ModelEditor<T, R> {

    private final T content;

    /**
     * The title of a contentEntity.
     */
    private String title;

    /**
     * The URL of a contentEntity.
     */
    private String url;

    /**
     * The description of a contentEntity.
     */
    private String description;

    /**
     * The keywords of a contentEntity.
     */
    private String keywords;

    /**
     * The category logo.
     */
    private File logo;

    /**
     * Constructor.
     * @param content
     */
    protected ContentEditor(final T content) {
        super(content);
        this.content = content;
    }

    @Override
    public R copy(final T content) {
        return super.copy(content)
                .addTitle(content.getTitle())
                .addUrl(content.getUrl())
                .addDescription(content.getDescription())
                .addKeywords(content.getKeywords())
                .addLogo(content.getLogo());
    }

    public R addTitle(final String title) {
        this.title = title;
        return (R) this;
    }

    public R addUrl(final String url) {
        this.url = url;
        return (R) this;
    }

    public R addDescription(final String description) {
        this.description = description;
        return (R) this;
    }

    public R addKeywords(final String keywords) {
        this.keywords = keywords;
        return (R) this;
    }

    public R addLogo(final File logo) {
        this.logo = logo;
        return (R) this;
    }

    protected String getTitle() {
        return isNotNull(this.title) ? this.title : this.content.getTitle();
    }

    protected String getUrl() {
        return isNotNull(this.url) ? this.url : this.content.getUrl();
    }

    protected String getDescription() {
        return isNotNull(this.description) ? this.description : this.content.getDescription();
    }

    protected String getKeywords() {
        return isNotNull(this.keywords) ? this.keywords : this.content.getKeywords();
    }

    protected File getLogo() {
        final FileEditor fileRedactor = this.content.getLogo().getEditor();
        fileRedactor.copy(this.logo);
        return fileRedactor.update();
    }
}
