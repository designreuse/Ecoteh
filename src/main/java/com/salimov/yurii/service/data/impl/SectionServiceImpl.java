package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.SectionDao;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.Photo;
import com.salimov.yurii.entity.Section;
import com.salimov.yurii.service.data.interfaces.CategoryService;
import com.salimov.yurii.service.data.interfaces.PhotoService;
import com.salimov.yurii.service.data.interfaces.SectionService;
import com.salimov.yurii.util.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Section} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Section
 * @see SectionService
 * @see ContentServiceImpl
 * @see DataServiceImpl
 * @see SectionDao
 */
@Service
@ComponentScan(
        basePackages = {
                "com.salimov.yurii.dao",
                "com.salimov.yurii.service.data"
        }
)
public final class SectionServiceImpl
        extends ContentServiceImpl<Section, Long>
        implements SectionService {

    /**
     * The interface of the service layer, describes
     * a set of methods for working with objects
     * of the class {@link Category}.
     *
     * @see CategoryService
     */
    private final CategoryService categoryService;

    /**
     * The interface of the service layer, describes
     * a set of methods for working with objects
     * of the class {@link Photo}.
     *
     * @see PhotoService
     */
    private final PhotoService photoService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao             a implementation
     *                        of the {@link SectionDao} interface.
     * @param categoryService a implementation
     *                        of the {@link CategoryService} interface.
     * @param photoService    a implementation
     *                        of the {@link PhotoService} interface.
     * @see SectionDao
     * @see CategoryService
     * @see PhotoService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SectionServiceImpl(
            final SectionDao dao,
            final CategoryService categoryService,
            final PhotoService photoService
    ) {
        super(dao);
        this.categoryService = categoryService;
        this.photoService = photoService;
    }

    /**
     * Initializes, saves and returns a new section.
     *
     * @param title       a title of the new section.
     * @param description a description of the new section.
     * @param keywords    a keywords of the new section.
     * @param photoFile   a file of main photo to the new section.
     * @param isValid     a validated of the new section.
     * @return The new saving section.
     * @see Section
     * @see Photo
     */
    @Override
    @Transactional
    public Section initAndAdd(
            final String title,
            final String description,
            final String keywords,
            final MultipartFile photoFile,
            final boolean isValid
    ) {
        final Photo photo = updatePhoto(
                new Photo(), photoFile, title
        );
        final Section section = new Section();
        section.initialize(title, description, keywords, photo);
        section.setValidated(isValid);
        return add(section);
    }

    /**
     * Initializes, updates and returns section with parameter id.
     * Returns {@code null} if id is {@code null}.
     *
     * @param url         a url of the section to update.
     * @param title       a new title to the section.
     * @param description a new title to the section.
     * @param keywords    a new title to the section.
     * @param photoFile   a file of photo to the section.
     * @param photoAction a action on the photo.
     * @param isValid     a validated of the section.
     * @return The updating section with parameter id or {@code null}.
     * @see Section
     * @see Photo
     */
    @Override
    @Transactional
    public Section initAndUpdate(
            final String url,
            final String title,
            final String description,
            final String keywords,
            final MultipartFile photoFile,
            final String photoAction,
            final boolean isValid
    ) {
        final Section section = getByUrl(url, false);
        section.initialize(title, description, keywords);
        section.setValidated(isValid);
        Photo photo = section.getPhoto();
        updatePhoto(section, photoFile, title, photoAction);
        final Section _section = update(section);
        removePhoto(photo, photoAction);
        return update(_section);
    }

    /**
     * Updates section photo.
     *
     * @param section the section to update.
     * @param file    a photo file.
     * @param title   a photo title.
     * @param action  a action on the photo.
     */
    private void updatePhoto(
            final Section section,
            final MultipartFile file,
            final String title,
            final String action
    ) {
        switch (action) {
            case "replace":
                section.setPhoto(
                        updatePhoto(
                                section.getPhoto(),
                                file,
                                title
                        )
                );
                break;
            case "delete":
                section.setPhoto(null);
                break;
        }
    }

    /**
     * Returns section with the parameter url.
     *
     * @param url     a url of the section to return.
     * @param isValid is get valid section or not.
     * @return The section with the parameter url.
     * @see Section
     */
    @Override
    @Transactional(readOnly = true)
    public Section getByUrl(final String url, final boolean isValid) {
        final Section section = super.getByUrl(url, isValid);
        section.getCategories().size();
        return section;
    }

    /**
     * Removes section. Removes section if it is not {@code null}.
     * Also deletes photo file if category photo is not {@code null}
     *
     * @param section the section to remove.
     * @see Section
     */
    @Override
    @Transactional
    public void remove(final Section section) {
        if (section != null) {
            removePhoto(section);
            clearCategories(section);
            super.remove(section);
        }
    }

    /**
     * Returns a list valid sections.
     * Returns empty sections list if section is empty.
     *
     * @param sections the sections to filter.
     * @return The list of valid sections.
     * @see Section
     */
    @Override
    @Transactional
    public List<Section> filteredByValid(final Collection<Section> sections) {
        List<Section> result = new ArrayList<>();
        if (sections != null && !sections.isEmpty()) {
            result.addAll(
                    sections.stream()
                            .filter(
                                    section -> (section != null)
                                            && (section.isValidated())
                            ).collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Return Class object of {@link Section} class.
     *
     * @return The Class object of {@link Section} class.
     */
    @Override
    protected Class getModelClass() {
        return Section.class;
    }

    /**
     * Removes section photo.
     *
     * @param section the section to remove photo.
     */
    private void removePhoto(final Section section) {
        this.photoService.remove(section.getPhoto());
    }

    /**
     * Clears section categories.
     *
     * @param section the section to clear categories.
     */
    private void clearCategories(final Section section) {
        final Collection<Category> categories = section.getCategories();
        section.clearCategories();
        this.categoryService.update(categories);
    }

    /**
     * Updates photo.
     *
     * @param photo the photo to updates.
     * @param file  a photo file.
     * @param title a photo title.
     * @return The updating photo.
     */
    private Photo updatePhoto(
            final Photo photo,
            final MultipartFile file,
            final String title
    ) {
        return this.photoService.updatePhoto(
                photo != null ? photo : new Photo(),
                file,
                Translator.fromCyrillicToLatin(title) + " photo "
                , "sections"
        );
    }

    /**
     * Removes photo if action equals "delete".
     *
     * @param photo  the photo to remove.
     * @param action a action on the photo.
     */
    private void removePhoto(final Photo photo, final String action) {
        if (action.equals("delete")) {
            this.photoService.remove(photo);
        }
    }
}
