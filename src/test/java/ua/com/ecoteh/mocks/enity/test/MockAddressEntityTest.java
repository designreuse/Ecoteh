package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.address.AddressEntity;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getAddressEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getAddressEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockAddressEntityTest extends MockModelEntityTest<AddressEntity> {

    private static AddressEntity address;
    private static Collection<AddressEntity> addresses;

    @BeforeClass
    public static void beforeClass() {
        address = getAddressEntity();
        addresses = getAddressEntities();
    }

    @Test
    public void whenGetPostAddressThenReturnNotEmpty() {
        assertFalse(address.getPostAddress().isEmpty());
    }

    @Test
    public void whenGetGoogleMapsThenReturnNotEmpty() {
        assertFalse(address.getGoogleMaps().isEmpty());
    }

    @Override
    protected AddressEntity getObject() {
        return address;
    }

    @Override
    protected Collection<AddressEntity> getObjects() {
        return addresses;
    }
}
