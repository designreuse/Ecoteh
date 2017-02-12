package com.salimov.yurii.util.loader;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements the methods for working
 * with files on the file system.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class FileLoader implements Loader {

    /**
     * The object for logging information.
     */
    private final static Logger LOGGER
            = Logger.getLogger(FileLoader.class);

    /**
     * The file to write.
     * A representation of an uploaded file
     * received in a multipart request.
     */
    private final MultipartFile file;

    /**
     * The root path of a file.
     */
    private final String path;

    /**
     * Constructor.
     *
     * @param path a root path of a file.
     */
    public FileLoader(final String path) {
        this(null, path);
    }

    /**
     * Constructor.
     *
     * @param file a file to write.
     * @param path a root path of a file.
     */
    public FileLoader(
            final MultipartFile file,
            final String path
    ) {
        this.file = file;
        this.path = path;
    }

    /**
     * Saves a file in the file system.
     */
    @Override
    public void write()  {
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
     * Deletes a file with the rootPath.
     * Deletes a file if it is exists
     * and it is a file (not a directory)
     *
     * @return {@code true} if a file is deleted,
     * {@code false} otherwise.
     */
    @Override
    public boolean delete() {
        boolean result = false;
        if (isNotBlank(this.path)) {
            final File file = new File(this.path);
            if (file.exists() && file.isFile()) {
                result = file.delete();
            }
        }
        return result;
    }

    /**
     * Returns a path to file.
     *
     * @return The path to file.
     */
    private String getPathToFile() {
        return isNotBlank(this.path)
                ? this.path
                : this.file.getOriginalFilename();
    }

    /**
     * Checks a path to file.
     * Creates directories if it is not exist.
     *
     * @param path a path to file.
     * @return {@code true} if directories to file is exist,
     * {@code false} otherwise.
     */
    private static boolean checkPath(final String path) {
        final File directory = new File(path).getParentFile();
        boolean isExists = directory.exists();
        if (!isExists) {
            isExists = directory.mkdirs();
        }
        return isExists;
    }
}
