package ua.com.ecoteh.util.properties;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods
 * for building an objects of the {@link ContentPropertiesImpl} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ContentProperties
 * @see ContentPropertiesImpl
 */
public final class ContentPropertiesBuilder {

    /**
     * Apache Catalina home path.
     */
    private String catalinaHome;

    /**
     * Content type encoding.
     */
    private String contentType;

    /**
     * View name prefix.
     */
    private String prefix;

    /**
     * View name suffix.
     */
    private String suffix;

    /**
     * It is exposed context beans as attributes.
     */
    private boolean exposeContextBeansAsAttributes;

    /**
     * URL of resources.
     */
    private String resourcesUrl;

    /**
     * Location of resources.
     */
    private String resourcesLocation;

    /**
     * Maximum file size (Mb).
     */
    private long maxFileSize;

    /**
     * Login request for authorization.
     */
    private String loginRequest;

    /**
     * Login view name (path).
     */
    private String loginViewName;

    /**
     * Project directory name.
     */
    private String projectDirectory;

    /**
     * Constructor.
     */
    ContentPropertiesBuilder() {
    }

    /**
     * Builds and returns a new address.
     *
     * @return The new address.
     */
    public ContentProperties build() {
        return new ContentPropertiesImpl(
                getCatalinaHome(), getContentType(),
                getPrefix(), getSuffix(),
                isExposeContextBeansAsAttributes(),
                getResourcesUrl(), getResourcesLocation(),
                getMaxFileSize(),
                getLoginRequest(), getLoginViewName(),
                getProjectDirectory()
        );
    }

    /**
     * Adds a new catalina home value.
     *
     * @param catalinaHome the new catalina home value.
     * @return the content properties builder.
     */
    public ContentPropertiesBuilder addCatalinaHome(final String catalinaHome) {
        this.catalinaHome = catalinaHome;
        return this;
    }

    /**
     * Adds a new content type.
     *
     * @param contentType the new content type.
     * @return the content properties builder.
     */
    public ContentPropertiesBuilder addContentType(final String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * Adds a new view prefix.
     *
     * @param prefix the new view prefix.
     * @return the content properties builder.
     */
    public ContentPropertiesBuilder addPrefix(final String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * Adds a new view suffix.
     *
     * @param suffix the new view suffix.
     * @return the content properties builder.
     */
    public ContentPropertiesBuilder addSuffix(final String suffix) {
        this.suffix = suffix;
        return this;
    }

    /**
     * Adds a new exposed context beans as attributes.
     *
     * @param exposeContextBeansAsAttributes is exposed context beans as attributes.
     * @return the content properties builder.
     */
    public ContentPropertiesBuilder addExposeContextBeansAsAttributes(final boolean exposeContextBeansAsAttributes) {
        this.exposeContextBeansAsAttributes = exposeContextBeansAsAttributes;
        return this;
    }

    /**
     * Adds a new resources URL.
     *
     * @param resourcesUrl the new resources URL.
     * @return the content properties builder.
     */
    public ContentPropertiesBuilder addResourcesUrl(final String resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
        return this;
    }

    /**
     * Adds a new resources location.
     *
     * @param resourcesLocation the new resources location.
     * @return the content properties builder.
     */
    public ContentPropertiesBuilder addResourcesLocation(final String resourcesLocation) {
        this.resourcesLocation = resourcesLocation;
        return this;
    }

    /**
     * Adds a new max file size.
     *
     * @param maxFileSize the new max file size.
     * @return the content properties builder.
     */
    public ContentPropertiesBuilder addMaxFileSize(final long maxFileSize) {
        this.maxFileSize = maxFileSize;
        return this;
    }

    /**
     * Adds a new login request.
     *
     * @param loginRequest the new login request.
     * @return the content properties builder.
     */
    public ContentPropertiesBuilder addLoginRequest(final String loginRequest) {
        this.loginRequest = loginRequest;
        return this;
    }

    /**
     * Adds a new login view name.
     *
     * @param loginViewName the new login view name.
     * @return the content properties builder.
     */
    public ContentPropertiesBuilder addLoginViewName(final String loginViewName) {
        this.loginViewName = loginViewName;
        return this;
    }

    /**
     * Adds a new project directory name.
     *
     * @param projectDirectory the new project directory name.
     * @return the content properties builder.
     */
    public ContentPropertiesBuilder addProjectDirectory(final String projectDirectory) {
        this.projectDirectory = projectDirectory;
        return this;
    }

    /**
     * Returns a catalina home value.
     * Returns an empty string if the catalina home value is null or empty.
     *
     * @return The catalina home value or empty string (newer null).
     */
    private String getCatalinaHome() {
        return isNotEmpty(this.catalinaHome) ? this.catalinaHome : "";
    }

    /**
     * Returns a content type.
     * Returns an empty string if the content type is null or empty.
     *
     * @return The content type or empty string (newer null).
     */
    private String getContentType() {
        return isNotEmpty(this.contentType) ? this.contentType : "";
    }

    /**
     * Returns a view prefix.
     * Returns an empty string if the view prefix is null or empty.
     *
     * @return The view prefix or empty string (newer null).
     */
    private String getPrefix() {
        return isNotEmpty(this.prefix) ? this.prefix : "";
    }

    /**
     * Returns a view suffix.
     * Returns an empty string if the view suffix is null or empty.
     *
     * @return Theview suffix or empty string (newer null).
     */
    private String getSuffix() {
        return isNotEmpty(this.suffix) ? this.suffix : "";
    }

    /**
     * Returns is exposed context beans as attributes.
     *
     * @return It is exposed context beans as attributes.
     */
    private boolean isExposeContextBeansAsAttributes() {
        return this.exposeContextBeansAsAttributes;
    }

    /**
     * Returns a resources URL.
     * Returns an empty string if the resources URL is null or empty.
     *
     * @return The resources URL or empty string (newer null).
     */
    private String getResourcesUrl() {
        return isNotEmpty(this.resourcesUrl) ? this.resourcesUrl : "";
    }

    /**
     * Returns a resources location.
     * Returns an empty string if the resources location is null or empty.
     *
     * @return The resources location or empty string (newer null).
     */
    private String getResourcesLocation() {
        return isNotEmpty(this.resourcesLocation) ? this.resourcesLocation : "";
    }

    /**
     * Returns a maximum file size.
     * Returns 0 if the file size is less 0.
     *
     * @return The maximum file size.
     */
    private long getMaxFileSize() {
        return (this.maxFileSize > 0) ? this.maxFileSize : 0;
    }

    /**
     * Returns a login request.
     * Returns an empty string if the login request is null or empty.
     *
     * @return The login request or empty string (newer null).
     */
    private String getLoginRequest() {
        return isNotEmpty(this.loginRequest) ? this.loginRequest : "";
    }

    /**
     * Returns a login view name.
     * Returns an empty string if the login view name is null or empty.
     *
     * @return The login view name or empty string (newer null).
     */
    private String getLoginViewName() {
        return isNotEmpty(this.loginViewName) ? this.loginViewName : "";
    }

    /**
     * Returns a project directory name.
     * Returns an empty string if the project directory name is null or empty.
     *
     * @return The project directory name or empty string (newer null).
     */
    private String getProjectDirectory() {
        return isNotEmpty(this.projectDirectory) ? this.projectDirectory : "";
    }
}
