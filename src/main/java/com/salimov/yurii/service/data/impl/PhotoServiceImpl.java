package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.PhotoDao;
import com.salimov.yurii.entity.Photo;
import com.salimov.yurii.service.data.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Photo} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Photo
 * @see PhotoService
 * @see MediaServiceImpl
 * @see DataServiceImpl
 * @see PhotoDao
 */
@Service
@ComponentScan(basePackages = "com.salimov.yurii.dao")
public final class PhotoServiceImpl
        extends MediaServiceImpl<Photo, Long>
        implements PhotoService {

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao a implementation of the {@link PhotoDao} interface.
     * @see PhotoDao
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public PhotoServiceImpl(final PhotoDao dao) {
        super(dao);
    }

    /**
     * Initializes, saves and returns a new photo.
     *
     * @param title     a title of the new photo.
     * @param url       a title of the new photo.
     * @param photoFile a photo file (image) of the new photo.
     * @return The new saving photo.
     * @see Photo
     */
    @Override
    @Transactional
    public Photo initAndAdd(
            final String title,
            final String url,
            final MultipartFile photoFile
    ) {
        final Photo photo = new Photo(title, url);
        saveFile(photoFile);
        return add(photo);
    }

    /**
     * Initializes, updates and returns photo with parameter id.
     * Returns {@code null} if id is {@code null}.
     *
     * @param id        a id of the photo to update.
     * @param title     a new title to the photo.
     * @param url       a new url to the photo.
     * @param photoFile a new photo file (image) to the photo.
     * @return The updating photo with parameter id or {@code null}.
     * @see Photo
     */
    @Override
    @Transactional
    public Photo initAndUpdate(
            final Long id,
            final String title,
            final String url,
            final MultipartFile photoFile
    ) {
        final Photo photo = get(id);
        deleteFile(photo.getUrl());
        photo.initialize(title, url);
        saveFile(photoFile);
        return add(photo);
    }

    /**
     * Returns photo with parameter url.
     * if url is blank then return {@code null}.
     *
     * @param url a url of the photo to return.
     * @return The photo with parameter url or {@code null}.
     * @see Photo
     */
    @Override
    @Transactional(readOnly = true)
    public Photo getByUrl(final String url) {
        return super.getByUrl(Photo.PATH + url);
    }

    /**
     * Removes photo with parameter url.
     * Removes photo if url is not blank.
     *
     * @param url a url of the photo to remove.
     * @see Photo
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        super.removeByUrl(Photo.PATH + url);
        deleteFile(url);
    }

    /**
     * Removes photo.
     * Removes photo if it is not {@code null}.
     *
     * @param photo the photo to remove.
     * @see Photo
     */
    @Override
    @Transactional
    public void remove(final Photo photo) {
        if (photo != null) {
            deleteFile(photo.getUrl());
            super.remove(photo);
        }
    }

    /**
     * Removes photos.
     * Removes photos if are not empty.
     *
     * @param photos The photos to remove.
     * @see Photo
     */
    @Override
    @Transactional
    public void remove(final Collection<Photo> photos) {
        if (photos != null && !photos.isEmpty()) {
            photos.forEach(this::remove);
        }
    }

    /**
     * Removes all photos.
     *
     * @see Photo
     */
    @Override
    @Transactional
    public void removeAll() {
        remove(
                getAll()
        );
    }

    /**
     * Saves the multipart file in the file system in the directory rootPath.
     * Saves file if it is not {@code null} and path is not blank.
     *
     * @param photo    the file to save.
     * @param rootPath a directory path.
     * @see Photo
     */
    @Override
    @Transactional
    public void saveFile(
            final MultipartFile photo,
            final String rootPath
    ) {
        if (photo != null && !photo.isEmpty()) {
            new Thread(() -> {
                synchronized (Photo.PATH) {
                    final String path = Photo.PATH +
                            (
                                    isNotBlank(rootPath) ? rootPath :
                                            photo.getOriginalFilename()
                            );
                    try (OutputStream stream = new FileOutputStream(path)) {
                        stream.write(photo.getBytes());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * Saves the multipart file in the file system.
     * Saves file if it is not {@code null}.
     *
     * @param photo the file to save.
     * @see Photo
     */
    @Override
    @Transactional
    public void saveFile(final MultipartFile photo) {
        saveFile(photo, null);
    }

    /**
     * Deletes file in the file system.
     * Deletes file if path is not blank.
     *
     * @param rootPath the file path.
     * @return Returns {@code true} if able to delete the file,
     * otherwise return {@code false}.
     * @see Photo
     */
    @Override
    @Transactional
    public boolean deleteFile(final String rootPath) {
        boolean result = false;
        if (isNotBlank(rootPath)) {
            File file = new File(Photo.PATH + rootPath);
            if (file.exists() && file.isFile()) {
                result = file.delete();
            }
        }
        return result;
    }

    /**
     * Updates and returns object of class {@link Photo}.
     * Updates photo if file is not {@code null}.
     *
     * @param photo    the photo to update.
     * @param file     a file to save.
     * @param title    a new title to the photo
     * @param rootPath a directory path.
     * @return The updating photo.
     * @see Photo
     */
    @Override
    @Transactional
    public Photo updatePhoto(
            final Photo photo,
            final MultipartFile file,
            final String title,
            final String rootPath
    ) {
        if ((photo != null) && (file != null) && (!file.isEmpty())) {
            deleteFile(photo.getUrl());
            photo.setTitle(title + " " + file.getOriginalFilename());
            photo.setUrl(
                    rootPath + "/" +
                            title.replace(" ", "_") +
                            "_" + file.getOriginalFilename()
            );
            update(photo);
            saveFile(
                    file,
                    photo.getUrl()
            );
        }
        return photo;
    }

    /**
     * Return Class object of {@link Photo} class.
     *
     * @return The Class object of {@link Photo} class.
     */
    @Override
    protected Class<Photo> getModelClass() {
        return Photo.class;
    }
}

