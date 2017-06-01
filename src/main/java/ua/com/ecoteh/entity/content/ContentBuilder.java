package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.ModelBuilder;
import ua.com.ecoteh.util.translator.Translator;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentBuilder<T extends Content, B extends ContentBuilder<T, B>>
        extends ModelBuilder<T, B> {

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

    protected ContentBuilder() {
    }

    public B addTitle(final String title) {
        this.title = title;
        return (B) this;
    }

    public B addUrl(final String url) {
        this.url = url;
        return (B) this;
    }

    public B addDescription(final String description) {
        this.description = description;
        return (B) this;
    }

    public B addKeywords(final String keywords) {
        this.keywords = keywords;
        return (B) this;
    }

    public B addLogo(final File logo) {
        this.logo = logo;
        return (B) this;
    }

    protected String getTitle() {
        return isNotEmpty(this.title) ? this.title : "";
    }

    protected String getUrl() {
        String result;
        if (isNotEmpty(this.url)) {
            result = this.url;
        } else if (isNotEmpty(this.title)) {
            result = Translator.fromCyrillicToLatin(this.title);
        } else {
            result = "";
        }
        return result;
    }

    protected String getDescription() {
        return isNotEmpty(this.description) ? this.description : "";
    }

    protected String getKeywords() {
        return isNotEmpty(this.keywords) ? this.keywords : "";
    }

    protected File getLogo() {
        return isNotNull(this.logo) ? this.logo : File.getBuilder().build();
    }
}
