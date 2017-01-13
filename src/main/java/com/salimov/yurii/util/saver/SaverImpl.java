package com.salimov.yurii.util.saver;

import com.salimov.yurii.exception.DuplicateException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class SaverImpl implements Sever {
    /**
     *
     */
    private final MultipartFile file;
    /**
     *
     */
    private final String rootPath;

    public SaverImpl(final String rootPath) {
        this(null, rootPath);
    }

    /**
     *
     * @param file
     * @param rootPath
     */
    public SaverImpl(final MultipartFile file, final String rootPath) {
        this.file = file;
        this.rootPath = rootPath;
    }

    /**
     *
     */
    @Override
    public void write() throws DuplicateException {
        if (this.file != null) {
            final String path =
                    isNotBlank(this.rootPath)
                            ? this.rootPath
                            : this.file.getOriginalFilename();
            boolean isExist = checkPath(path);
            try (final OutputStream stream = new FileOutputStream(path)) {
                stream.write(this.file.getBytes());
            } catch (IOException ex) {
                final DuplicateException exp = new DuplicateException(
                        ex.getMessage()
                                + "; Path: " + path
                                + "; isExist = " + isExist,
                        ex
                );
                exp.setStackTrace(ex.getStackTrace());
                throw exp;
            }
        }
    }

    @Override
    public boolean delete() {
        boolean result = false;
        if (isNotBlank(this.rootPath)) {
            java.io.File file = new java.io.File(this.rootPath);
            if (file.exists() && file.isFile()) {
                result = file.delete();
            }
        }
        return result;
    }

    /**
     *
     * @param path
     * @return
     */
    private static boolean checkPath(final String path) {
        final java.io.File dir = new java.io.File(path).getParentFile();
        boolean isExists = dir.exists();
        if (!isExists) {
            isExists = dir.mkdirs();
        }
        return isExists;
    }
}
