package ua.com.ecoteh.mocks.repository.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.company.CompanyType;
import ua.com.ecoteh.repository.CompanyRepository;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.DOMAIN;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCompanyEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCompanyEntity;
import static ua.com.ecoteh.mocks.repository.MockRepository.getCompanyRepository;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockCompanyRepositoryTest extends MockContentRepositoryTest<CompanyEntity> {

    private static CompanyEntity company;
    private static Collection<CompanyEntity> companies;
    private static CompanyRepository repository;

    @BeforeClass
    public static void beforeClass() {
        company = getCompanyEntity();
        companies = getCompanyEntities();
        repository = getCompanyRepository();
    }

    @Test
    public void whenFindByTypeThenReturnNotEmptyCollection() {
        for (CompanyType type : CompanyType.values()) {
            final Collection<CompanyEntity> entities = repository.findAllByType(type);
            assertFalse(entities.isEmpty());
        }
    }

    @Test
    public void whenFindLastByTypeThenReturnNotNull() {
        for (CompanyType type : CompanyType.values()) {
            final CompanyEntity entity = repository.findLastByType(type);
            assertNotNull(entity);
        }
    }

    @Test
    public void whenFindByTypeThenReturnCollectionWithNotNullObjects() {
        for (CompanyType type : CompanyType.values()) {
            final Collection<CompanyEntity> entities = repository.findAllByType(type);
            entities.forEach(Assert::assertNotNull);
        }
    }

    @Test
    public void whenFindByDomainThenReturnNotNull() {
        final CompanyEntity entity = repository.findByDomain(DOMAIN);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownDomainThenReturnNull() {
        final CompanyEntity entity = repository.findByDomain(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullDomainThenReturnNull() {
        final CompanyEntity entity = repository.findByDomain(null);
        assertNull(entity);
    }

    @Override
    protected CompanyRepository getRepository() {
        return repository;
    }

    @Override
    protected CompanyEntity getObject() {
        return company;
    }

    @Override
    protected Collection<CompanyEntity> getObjects() {
        return companies;
    }
}
