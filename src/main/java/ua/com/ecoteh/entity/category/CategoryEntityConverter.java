package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.content.ContentEntityConverter;

/**
 * The class implements a set of methods
 * for converting category entities to categories.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see CategoryEntity
 * @see Category
 */
final class CategoryEntityConverter extends ContentEntityConverter<CategoryEntity, Category> {

    /**
     * The category entity for converting to category.
     */
    private final CategoryEntity entity;

    /**
     * Constructor.
     *
     * @param entity the category entity for converting to category.
     */
    CategoryEntityConverter(final CategoryEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Prepares and returns a category builder for creating
     * a new converted category.
     *
     * @return the prepared category builder.
     */
    @Override
    protected CategoryBuilder prepareBuilder() {
        final CategoryBuilder builder = Category.getBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addTitle(this.entity.getTitle())
                .addUrl(this.entity.getUrl())
                .addDescription(this.entity.getDescription())
                .addKeywords(this.entity.getKeywords())
                .addLogo(this.entity.getLogoEntity().convert());
        return builder;
    }
}
