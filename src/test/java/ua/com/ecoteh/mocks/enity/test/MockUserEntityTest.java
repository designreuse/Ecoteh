package ua.com.ecoteh.mocks.enity.test;

import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;

public final class MockUserEntityTest extends MockModelTest<UserEntity> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final UserEntity userEntity = MockEntity.getUserEntity();
        assertNotNull(userEntity.getId());
        assertNotNull(userEntity.getName());
        assertNotNull(userEntity.getLogin());
        assertNotNull(userEntity.getPassword());
        assertNotNull(userEntity.getDescription());
    }

    @Ignore
    @Override
    protected UserEntity getObject() {
        return MockEntity.getUserEntity();
    }

    @Ignore
    @Override
    protected Collection<UserEntity> getObjects() {
        return MockEntity.getUserEntities();
    }
}
