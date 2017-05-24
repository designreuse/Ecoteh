package ua.com.ecoteh.util.loader;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static ua.com.ecoteh.exception.ExceptionMessage.UNSUPPORTED_OPERATION_MESSAGE;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements the methods for working
 * with multipart files in file system.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class MultipartFileLoader extends AbstractLoader implements Loader {

    /**
     * The file to write.
     * A representation of an uploaded file
     * received in a multipart request.
     */
    private final MultipartFile file;

    /**
     * Constructor.
     *
     * @param path the root path of a file.
     */
    public MultipartFileLoader(final String path) {
        this(null, path);
    }

    /**
     * Constructor.
     *
     * @param file the multipart file to write.
     * @param path the root path of a file.
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
        if (isNotNull(this.file)) {
            final String path = getPathToFile();
            checkPath(path);
            try (final OutputStream stream = new FileOutputStream(path)) {
                stream.write(this.file.getBytes());
            } catch (IOException ex) {
                logException(ex);
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
        throw new UnsupportedOperationException(
                String.format(
                        UNSUPPORTED_OPERATION_MESSAGE,
                        MultipartFileLoader.class.getSimpleName()
                )
        );
    }



    /**
     * Returns a multipart file.
     *
     * @return The multipart file or null if file not initialized.
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
        return isNotEmpty(getPath()) ? getPath() : this.file.getOriginalFilename();
    }
}
