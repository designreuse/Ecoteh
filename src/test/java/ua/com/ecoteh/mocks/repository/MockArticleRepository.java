package ua.com.ecoteh.mocks.repository;

import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getArticleEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getArticleEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockArticleRepository extends MockContentRepository<ArticleEntity> {

    private final ArticleRepository repository;
    private final ArticleEntity entity;
    private final Collection<ArticleEntity> entities;

    MockArticleRepository() {
        this.repository = mock(ArticleRepository.class);
        this.entity = getArticleEntity();
        this.entities = getArticleEntities();
    }

    @Override
    ArticleRepository create() {
        super.create();
        initFindByNumber();
        initFindByCategoryId();
        initFindByCategoryTitle();
        return this.repository;
    }

    @Override
    ArticleRepository getRepository() {
        return this.repository;
    }

    @Override
    ArticleEntity getEntity() {
        return this.entity;
    }

    @Override
    Collection<ArticleEntity> getEntities() {
        return this.entities;
    }

    private void initFindByNumber() {
        when(this.repository.findByNumber(NUMBER)).thenReturn(this.entity);
        when(this.repository.findByNumber(ANY_STRING)).thenReturn(null);
        when(this.repository.findByNumber(null)).thenReturn(null);
    }

    private void initFindByCategoryId() {
        when(this.repository.findByCategoryId(ID)).thenReturn(this.entities);
        when(this.repository.findByCategoryId(UNKNOWN_ID)).thenReturn(new ArrayList<>());
    }

    private void initFindByCategoryTitle() {
        when(this.repository.findByCategoryTitle(TITLE)).thenReturn(this.entities);
        when(this.repository.findByCategoryTitle(null)).thenReturn(new ArrayList<>());
        when(this.repository.findByCategoryTitle(ANY_STRING)).thenReturn(new ArrayList<>());
    }
}
