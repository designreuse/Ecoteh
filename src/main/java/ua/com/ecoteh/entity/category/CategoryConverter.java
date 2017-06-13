package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.content.ContentConverter;

import java.util.ArrayList;

/**
 * The class implements a set of methods
 * for converting categories to category entities.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Category
 * @see CategoryEntity
 */
final class CategoryConverter extends ContentConverter<Category, CategoryEntity> {

    /**
     * The category for converting to category entity.
     */
    private final Category category;

    /**
     * Constructor.
     *
     * @param category the category for converting to category entity.
     */
    CategoryConverter(final Category category) {
        super(category);
        this.category = category;
    }

    /**
     * Converts the category and returns a new category entity.
     *
     * @return The converted category entity (newer null).
     */
    @Override
    public CategoryEntity convert() {
        final CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(this.category.getId());
        categoryEntity.setValidated(this.category.isValidated());
        categoryEntity.setTitle(this.category.getTitle());
        categoryEntity.setUrl(this.category.getUrl());
        categoryEntity.setDescription(this.category.getDescription());
        categoryEntity.setKeywords(this.category.getKeywords());
        categoryEntity.setLogoEntity(this.category.getLogo().convert());
        categoryEntity.setArticleEntities(new ArrayList<>());
        return categoryEntity;
    }
}
