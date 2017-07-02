package ua.com.ecoteh.config.properties;

/**
 * The interface describes the methods
 * for getting application properties.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface ContentProperties {
    /**
     * Returns a catalina home constant.
     *
     * @return The catalina home constant.
     */
    String getCatalinaHome();

    /**
     * Returns a content type encoding.
     *
     * @return The content type encoding.
     */
    String getContentType();

    /**
     * Returns a view name prefix.
     *
     * @return The view name prefix.
     */
    String getPrefix();

    /**
     * Returns a view name suffix.
     *
     * @return The view name suffix.
     */
    String getSuffix();

    /**
     * Returns is exposed context beans as attributes.
     *
     * @return It is exposed context beans as attributes.
     */
    boolean isExposeContextBeansAsAttributes();

    /**
     * Returns a resources URL.
     *
     * @return The resources URL.
     */
    String getResourcesUrl();

    /**
     * Returns a resources location.
     *
     * @return The resources location.
     */
    String getResourcesLocation();

    /**
     * Returns a maximum file size.
     *
     * @return The maximum file size.
     */
    long getMaxFileSize();

    /**
     * Returns a login request for authorization.
     *
     * @return The login request for authorization.
     */
    String getLoginRequest();

    /**
     * Returns a login view name (path).
     *
     * @return The login view name (path).
     */
    String getLoginViewName();

    /**
     * Returns a project directory name.
     *
     * @return The project directory name.
     */
    String getProjectDirectory();

    /**
     * Returns a project absolute path.
     *
     * @return The project absolute path.
     */
    String getProjectAbsolutePath();

    /**
     * Returns a resources path.
     *
     * @return The resources path.
     */
    String getResourcesAbsolutePath();

    /**
     * Returns a builder for creating a new content properties implementation.
     *
     * @return the content properties builder (newer null).
     */
    static ContentPropertiesBuilder getBuilder() {
        return new ContentPropertiesBuilder();
    }
}
