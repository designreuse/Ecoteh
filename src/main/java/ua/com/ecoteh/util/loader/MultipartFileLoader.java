package ua.com.ecoteh.util.loader;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements the methods for working
 * with multipart files in file system.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class MultipartFileLoader extends AbstractLoader implements Loader {

    /**
     * The object for logging information.
     */
    private final static Logger LOGGER = Logger.getLogger(MultipartFileLoader.class);

    /**
     * The file to write.
     * A representation of an uploaded file
     * received in a multipart request.
     */
    private final MultipartFile file;

    /**
     * Constructor.
     *
     * @param path a root path of a file.
     */
    public MultipartFileLoader(final String path) {
        this(null, path);
    }

    /**
     * Constructor.
     *
     * @param file a file to write.
     * @param path a root path of a file.
     */
    public MultipartFileLoader(
            final MultipartFile file,
            final String path
    ) {
        super(path);
        this.file = file;
    }

    /**
     * Saves a file in the file system.
     */
    @Override
    public void write() {
        if (this.file != null) {
            final String path = getPathToFile();
            checkPath(path);
            try (final OutputStream stream = new FileOutputStream(path)) {
                stream.write(this.file.getBytes());
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * MultipartFileLoader does NOT support this method.
     *
     * @return Throws UnsupportedOperationException.
     * @throws UnsupportedOperationException MultipartFileLoader does not support
     *                                       the read feature.
     */
    @Override
    public String read() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported by MultipartFileLoader");
    }

    /**
     * Returns a multipart file.
     *
     * @return The multipart file or (@code null) if file not initialized.
     */
    public MultipartFile getFile() {
        return this.file;
    }

    /**
     * Returns a path to file.
     *
     * @return The path to file.
     */
    private String getPathToFile() {
        return isNotBlank(getPath()) ? getPath() : this.file.getOriginalFilename();
    }
}
