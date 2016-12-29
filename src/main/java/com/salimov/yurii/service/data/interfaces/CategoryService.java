package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.Photo;
import com.salimov.yurii.entity.Section;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link Category} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Category
 * @see ContentService
 * @see DataService
 */
public interface CategoryService extends ContentService<Category, Long> {

    /**
     * Initializes, saves and returns a new category.
     *
     * @param title       a title of the new category.
     * @param description a description of the new category.
     * @param keywords    a keywords of the new category.
     * @param photoFile   a photo of the new category.
     * @param section     a section of the new category.
     * @param isValid     validated of the new category.
     * @return The new saving category.
     * @see Category
     * @see Section
     * @see Photo
     */
    Category initAndAdd(
            final String title,
            final String description,
            final String keywords,
            final MultipartFile photoFile,
            final Section section,
            final boolean isValid
    );

    /**
     * Initializes, updates and returns category with parameter url.
     *
     * @param url         a url of the category to update.
     * @param title       a new title to the category.
     * @param description a new description to the category.
     * @param keywords    a new keywords to the category.
     * @param photoFile   a new photo to the category.
     * @param photoAction a action on the main photo.
     * @param section     a new section to the category.
     * @param isValid     a validated of the category.
     * @return The updating category with parameter id.
     * @see Category
     * @see Section
     * @see Photo
     */
    Category initAndUpdate(
            final String url,
            final String title,
            final String description,
            final String keywords,
            final MultipartFile photoFile,
            final String photoAction,
            final Section section,
            final boolean isValid
    );

    /**
     * Filters and returns categories by the section.
     *
     * @param categories the categories to filter.
     * @param section    a section filtering.
     * @return The filtered list of categories.
     * @see Category
     * @see Section
     */
    List<Category> filterBySection(
            final Collection<Category> categories,
            final Section section
    );

    /**
     * Filters and returns categories by the sections.
     *
     * @param categories the categories to filter.
     * @param sections   a sections filtering.
     * @return The filtered list of categories.
     * @see Category
     * @see Section
     */
    List<Category> filterBySections(
            final Collection<Category> categories,
            final Collection<Section> sections
    );

    /**
     * Filters and returns categories by the section.
     *
     * @param section a section filtering.
     * @return The filtered list of categories.
     * @see Category
     * @see Section
     */
    List<Category> getAndFilterBySection(final Section section);

    /**
     * Filters and returns categories by the sections.
     *
     * @param sections a sections filtering.
     * @return The filtered list of categories.
     * @see Category
     * @see Section
     */
    List<Category> getAndFilterBySections(final List<Section> sections);
}
