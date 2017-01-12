package com.salimov.yurii.util.properties;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class ContentPropertiesImpl implements ContentProperties {

    private final static String CATALINA_HOME;

    /**
     * Content Type encoding.
     */
    private final String contentType;

    /**
     * View name prefix.
     */
    private final String prefix;

    /**
     * View name suffix.
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
     * Request for authorization.
     */
    private final String requestLogin;

    /**
     * Login view name (path).
     */
    private final String loginViewName;

    /**
     *
     */
    private final String projectDirectory;

    static {
        CATALINA_HOME = System.getenv("CATALINA_HOME") + "/";
        /*CATALINA_HOME = new File(".")
                .getAbsoluteFile()
                .getParentFile()
                .getAbsolutePath()
                .replace("bin", "") + "/"*/;
    }

    /**
     * @param contentType
     * @param prefix
     * @param suffix
     * @param exposeContextBeansAsAttributes
     * @param resourcesUrl
     * @param resourcesLocation
     * @param requestLogin
     * @param loginViewName
     * @param projectDirectory
     */
    public ContentPropertiesImpl(
            final String contentType,
            final String prefix,
            final String suffix,
            final boolean exposeContextBeansAsAttributes,
            final String resourcesUrl,
            final String resourcesLocation,
            final String requestLogin,
            final String loginViewName,
            final String projectDirectory
    ) {
        this.contentType = contentType;
        this.prefix = prefix;
        this.suffix = suffix;
        this.exposeContextBeansAsAttributes = exposeContextBeansAsAttributes;
        this.resourcesUrl = resourcesUrl;
        this.resourcesLocation = resourcesLocation;
        this.requestLogin = requestLogin;
        this.loginViewName = loginViewName;
        this.projectDirectory = projectDirectory;
    }

    /**
     * @return
     */
    @Override
    public String getCatalinaHome() {
        return CATALINA_HOME;
    }

    /**
     * @return
     */
    @Override
    public String getContentType() {
        return this.contentType;
    }

    /**
     * @return
     */
    @Override
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * @return
     */
    @Override
    public String getSuffix() {
        return this.suffix;
    }

    /**
     * @return
     */
    @Override
    public boolean isExposeContextBeansAsAttributes() {
        return exposeContextBeansAsAttributes;
    }

    /**
     * @return
     */
    @Override
    public String getResourcesUrl() {
        return this.resourcesUrl;
    }

    /**
     * @return
     */
    @Override
    public String getResourcesLocation() {
        return this.resourcesLocation;
    }

    /**
     * @return
     */
    @Override
    public String getRequestLogin() {
        return this.requestLogin;
    }

    /**
     * @return
     */
    @Override
    public String getLoginViewName() {
        return this.loginViewName;
    }

    /**
     * @return
     */
    @Override
    public String getProjectDirectory() {
        return this.projectDirectory;
    }

    /**
     * @return
     */
    @Override
    public String getProjectAbsolutePath() {
        return CATALINA_HOME
                + "webapps/"
                + this.projectDirectory;
    }

    /**
     * @return
     */
    @Override
    public String getResourcesPath() {
        return getProjectAbsolutePath() + this.resourcesLocation;
    }
}
