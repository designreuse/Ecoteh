package ua.com.ecoteh.mocks.repository;

import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.repository.ResponseRepository;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getResponseEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getResponseEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockResponseRepository extends MockDataRepository<ResponseEntity> {

    private final ResponseRepository repository;
    private final ResponseEntity entity;
    private final Collection<ResponseEntity> entities;

    MockResponseRepository() {
        this.repository = mock(ResponseRepository.class);
        this.entity = getResponseEntity();
        this.entities = getResponseEntities();
    }

    @Override
    ResponseRepository create() {
        super.create();
        return this.repository;
    }

    @Override
    ResponseRepository getRepository() {
        return this.repository;
    }

    @Override
    ResponseEntity getEntity() {
        return this.entity;
    }

    @Override
    Collection<ResponseEntity> getEntities() {
        return this.entities;
    }
}