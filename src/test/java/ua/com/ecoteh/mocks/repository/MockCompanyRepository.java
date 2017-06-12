package ua.com.ecoteh.mocks.repository;

import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.company.CompanyType;
import ua.com.ecoteh.repository.CompanyRepository;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.DOMAIN;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCompanyEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCompanyEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
class MockCompanyRepository extends MockContentRepository<CompanyEntity> {

    private final CompanyRepository repository;
    private final CompanyEntity entity;
    private final Collection<CompanyEntity> entities;

    MockCompanyRepository() {
        this.repository = mock(CompanyRepository.class);
        this.entity = getCompanyEntity();
        this.entities = getCompanyEntities();
    }

    @Override
    CompanyRepository create() {
        super.create();
        initFindByType();
        initFindByDomain();
        initFindLastByType();
        return this.repository;
    }

    @Override
    CompanyRepository getRepository() {
        return this.repository;
    }

    @Override
    CompanyEntity getEntity() {
        return this.entity;
    }

    @Override
    Collection<CompanyEntity> getEntities() {
        return this.entities;
    }

    private void initFindByType() {
        for (CompanyType type : CompanyType.values()) {
            when(this.repository.findAllByType(type)).thenReturn(this.entities);
        }
    }

    private void initFindLastByType() {
        for (CompanyType type : CompanyType.values()) {
            when(this.repository.findLastByType(type)).thenReturn(this.entity);
        }
    }

    private void initFindByDomain() {
        when(this.repository.findByDomain(DOMAIN)).thenReturn(this.entity);
        when(this.repository.findByDomain(ANY_STRING)).thenReturn(null);
        when(this.repository.findByDomain(null)).thenReturn(null);
    }
}
