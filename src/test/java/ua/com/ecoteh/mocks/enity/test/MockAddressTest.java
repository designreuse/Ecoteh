package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.mocks.enity.MockModels;

import java.util.Collection;

import static org.junit.Assert.assertFalse;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockAddressTest extends MockModelTest<Address> {

    private static Address address;
    private static Collection<Address> addresses;

    @BeforeClass
    public static void beforeClass() {
        address = MockModels.getAddress();
        addresses = MockModels.getAddresses();
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
    protected Address getObject() {
        return address;
    }

    @Override
    protected Collection<Address> getObjects() {
        return addresses;
    }
}
