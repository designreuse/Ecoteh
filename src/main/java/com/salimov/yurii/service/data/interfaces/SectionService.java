package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.Photo;
import com.salimov.yurii.entity.Section;
import org.springframework.web.multipart.MultipartFile;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the class {@link Section}.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Section
 * @see ContentService
 * @see DataService
 */
public interface SectionService extends ContentService<Section, Long> {

    /**
     * Initializes, saves and returns a new section.
     *
     * @param title        a title of the new section.
     * @param description  a description of the new section.
     * @param keywords     a keywords of the new section.
     * @param photoFile    a file of main photo to the new section.
     * @param isValid      a validated of the new section.
     * @return The new saving section.
     * @see Section
     * @see Photo
     */
    Section initAndAdd(
            final String title,
            final String description,
            final String keywords,
            final MultipartFile photoFile,
            final boolean isValid
    );

    /**
     * Initializes, updates and returns section with parameter id.
     * Returns {@code null} if id is {@code null}.
     *
     * @param url          a url of the section to update.
     * @param title        a new title to the section.
     * @param description  a new title to the section.
     * @param keywords     a new title to the section.
     * @param photoFile    a file of photo to the section.
     * @param photoAction  a action on the photo.
     * @param isValid      a validated of the section.
     * @return The updating section with parameter id or {@code null}.
     * @see Section
     * @see Photo
     */
    Section initAndUpdate(
            final String url,
            final String title,
            final String description,
            final String keywords,
            final MultipartFile photoFile,
            final String photoAction,
            final boolean isValid
    );
}
