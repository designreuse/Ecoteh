package com.salimov.ecoteh.util.properties;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements the methods for getting application properties.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class ContentPropertiesImpl implements ContentProperties {

    /**
     * The file path. It is path to tomcat.
     */
    private static String filePath;

    /**
     * Apache Catalina home path.
     */
    private final String catalinaHome;

    /**
     * Content type encoding.
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
     * URL of resources.
     */
    private final String resourcesUrl;

    /**
     * Location of resources.
     */
    private final String resourcesLocation;

    /**
     * Maximum file size (Mb).
     */
    private final long maxFileSize;

    /**
     * Login request for authorization.
     */
    private final String loginRequest;

    /**
     * Login view name (path).
     */
    private final String loginViewName;

    /**
     * Project directory name.
     */
    private final String projectDirectory;

    /**
     * Constructor.
     *
     * @param catalinaHome                   a Apache Catalina home path.
     * @param contentType                    a content type encoding.
     * @param prefix                         a view name prefix.
     * @param suffix                         a view name prefix.
     * @param exposeContextBeansAsAttributes is exposed context beans as attributes.
     * @param resourcesUrl                   a url of resources.
     * @param resourcesLocation              a location of resources.
     * @param maxFileSize                    a maximum file size.
     * @param loginRequest                   a login request for authorization.
     * @param loginViewName                  a login view name (path).
     * @param projectDirectory               a project directory name.
     */
    public ContentPropertiesImpl(
            final String catalinaHome,
            final String contentType,
            final String prefix,
            final String suffix,
            final boolean exposeContextBeansAsAttributes,
            final String resourcesUrl,
            final String resourcesLocation,
            final long maxFileSize,
            final String loginRequest,
            final String loginViewName,
            final String projectDirectory
    ) {
        this.catalinaHome = isNotBlank(catalinaHome) ? catalinaHome : "";
        this.contentType = isNotBlank(contentType) ? contentType : "";
        this.prefix = isNotBlank(prefix) ? prefix : "";
        this.suffix = isNotBlank(suffix) ? suffix : "";
        this.exposeContextBeansAsAttributes = exposeContextBeansAsAttributes;
        this.resourcesUrl = isNotBlank(resourcesUrl) ? resourcesUrl : "";
        this.resourcesLocation = isNotBlank(resourcesLocation) ? resourcesLocation : "";
        this.maxFileSize = maxFileSize;
        this.loginRequest = isNotBlank(loginRequest) ? loginRequest : "";
        this.loginViewName = isNotBlank(loginViewName) ? loginViewName : "";
        this.projectDirectory = isNotBlank(projectDirectory) ? projectDirectory : "";
    }

    /**
     * Returns a catalina home constant.
     *
     * @return The catalina home constant.
     */
    @Override
    public String getCatalinaHome() {
        return this.catalinaHome;
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
     * Returns a maximum file size.
     *
     * @return The maximum file size.
     */
    @Override
    public long getMaxFileSize() {
        return this.maxFileSize;
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
        if (isBlank(filePath)) {
            createFilePath();
        }
        return filePath + "/webapps/" + this.projectDirectory;
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
        return getProjectAbsolutePath() + getResourcesLocation();
    }

    /**
     * Creates file path.
     * It is path to tomcat.
     */
    private static void createFilePath() {
        final java.io.File file = new java.io.File("");
        try {
            filePath = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            filePath = file.getAbsolutePath();
        }
        filePath = filePath.replace("/bin", "").replace("\\bin", "");
    }
}
