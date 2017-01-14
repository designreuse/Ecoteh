package com.salimov.yurii.util.properties;

/**
 * The class implements the methods for getting application properties.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see ContentProperties
 */
public final class ContentPropertiesImpl implements ContentProperties {

    /**
     * The catalina home constant.
     */
    private final static String CATALINA_HOME
            = System.getenv("CATALINA_HOME") + "/";
    /*CATALINA_HOME = new File(".")
                .getAbsoluteFile()
                .getParentFile()
                .getAbsolutePath()
                .replace("bin", "") + "/";*/

    /**
     * The content type encoding.
     */
    private final String contentType;

    /**
     * The view name prefix.
     */
    private final String prefix;

    /**
     * The view name suffix.
     */
    private final String suffix;

    /**
     * It is exposed context beans as attributes.
     */
    private final boolean exposeContextBeansAsAttributes;

    /**
     * The url of resources.
     */
    private final String resourcesUrl;

    /**
     * The location of resources.
     */
    private final String resourcesLocation;

    /**
     * The login request for authorization.
     */
    private final String loginRequest;

    /**
     * The login view name (path).
     */
    private final String loginViewName;

    /**
     * The project directory name.
     */
    private final String projectDirectory;

    /**
     * Constructor.
     *
     * @param contentType                    a content type encoding.
     * @param prefix                         a view name prefix.
     * @param suffix                         a view name prefix.
     * @param exposeContextBeansAsAttributes is exposed context beans as attributes.
     * @param resourcesUrl                   a url of resources.
     * @param resourcesLocation              a location of resources.
     * @param loginRequest                   a login request for authorization.
     * @param loginViewName                  a login view name (path).
     * @param projectDirectory               a project directory name.
     */
    public ContentPropertiesImpl(
            final String contentType,
            final String prefix,
            final String suffix,
            final boolean exposeContextBeansAsAttributes,
            final String resourcesUrl,
            final String resourcesLocation,
            final String loginRequest,
            final String loginViewName,
            final String projectDirectory
    ) {
        this.contentType = contentType;
        this.prefix = prefix;
        this.suffix = suffix;
        this.exposeContextBeansAsAttributes = exposeContextBeansAsAttributes;
        this.resourcesUrl = resourcesUrl;
        this.resourcesLocation = resourcesLocation;
        this.loginRequest = loginRequest;
        this.loginViewName = loginViewName;
        this.projectDirectory = projectDirectory;
    }

    /**
     * Returns a catalina home constant.
     *
     * @return The catalina home constant.
     */
    @Override
    public String getCatalinaHome() {
        return CATALINA_HOME;
    }

    /**
     * Returns a content type encoding.
     *
     * @return The content type encoding.
     */
    @Override
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Returns a view name prefix.
     *
     * @return The view name prefix.
     */
    @Override
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Returns a view name suffix.
     *
     * @return The view name suffix.
     */
    @Override
    public String getSuffix() {
        return this.suffix;
    }

    /**
     * Returns is exposed context beans as attributes.
     *
     * @return It is exposed context beans as attributes.
     */
    @Override
    public boolean isExposeContextBeansAsAttributes() {
        return exposeContextBeansAsAttributes;
    }

    /**
     * Returns a resources URL.
     *
     * @return The resources URL.
     */
    @Override
    public String getResourcesUrl() {
        return this.resourcesUrl;
    }

    /**
     * Returns a resources location.
     *
     * @return The resources location.
     */
    @Override
    public String getResourcesLocation() {
        return this.resourcesLocation;
    }

    /**
     * Returns a login request for authorization.
     *
     * @return The login request for authorization.
     */
    @Override
    public String getLoginRequest() {
        return this.loginRequest;
    }

    /**
     * Returns a login view name (path).
     *
     * @return The login view name (path).
     */
    @Override
    public String getLoginViewName() {
        return this.loginViewName;
    }

    /**
     * Returns a project directory name.
     *
     * @return The project directory name.
     */
    @Override
    public String getProjectDirectory() {
        return this.projectDirectory;
    }


    /**
     * Returns a project absolute path.
     *
     * @return The project absolute path.
     */
    @Override
    public String getProjectAbsolutePath() {
        return CATALINA_HOME
                + "webapps/"
                + this.projectDirectory;
    }

    /**
     * Returns a resources path.
     * It is an absolute path of project
     * and a resources location.
     *
     * @return The resources path.
     */
    @Override
    public String getResourcesAbsolutePath() {
        return getProjectAbsolutePath()
                + getResourcesLocation();
    }
}
