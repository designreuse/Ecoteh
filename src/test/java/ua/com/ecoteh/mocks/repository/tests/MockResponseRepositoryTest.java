package ua.com.ecoteh.mocks.repository.tests;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.repository.ResponseRepository;

import java.util.Collection;

import static ua.com.ecoteh.mocks.enity.MockModelEntities.getResponseEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getResponseEntity;
import static ua.com.ecoteh.mocks.repository.MockRepository.getResponseRepository;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockResponseRepositoryTest extends MockDataRepositoryTest<ResponseEntity> {

    private static ResponseEntity response;
    private static Collection<ResponseEntity> responses;
    private static ResponseRepository repository;

    @BeforeClass
    public static void beforeClass() {
        response = getResponseEntity();
        responses = getResponseEntities();
        repository = getResponseRepository();
    }

    @Override
    protected ResponseRepository getRepository() {
        return repository;
    }

    @Override
    protected ResponseEntity getObject() {
        return response;
    }

    @Override
    protected Collection<ResponseEntity> getObjects() {
        return responses;
    }
}
