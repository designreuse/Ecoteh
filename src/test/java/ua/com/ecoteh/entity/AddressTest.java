package ua.com.ecoteh.entity;

import org.junit.Assert;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class AddressTest extends ModelTest<Address> {

    @Test
    public void whenInitializeByConstructorThenSetNotNullRole() {
        final Address address = new Address();
        assertNotNull(address.getAddress());
        assertNotNull(address.getGoogleMaps());
    }

    @Test
    public void whenPassNullParametersInConstructorThenSaveEmptyString() {
        final Address address = new Address(null, null);
        assertNotNull(address.getAddress());
        assertNotNull(address.getGoogleMaps());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_1() {
        final Address address = new Address("", "");
        assertNotNull(address.getAddress());
        assertNotNull(address.getGoogleMaps());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final Address address = new Address(" ", " ");
        assertNotNull(address.getAddress());
        assertNotNull(address.getGoogleMaps());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_3() {
        final Address address = new Address("   ", "   ");
        assertNotNull(address.getAddress());
        assertNotNull(address.getGoogleMaps());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final Address address = new Address(MockConstants.ADDRESS, MockConstants.GOOGLE_MAPS);
        assertNotNull(address.getAddress());
        assertNotNull(address.getGoogleMaps());
        Assert.assertEquals(address.getAddress(), MockConstants.ADDRESS);
        Assert.assertEquals(address.getGoogleMaps(), MockConstants.GOOGLE_MAPS);
    }

    @Test
    public void toStringTest() {
        final Address address = MockEntity.getAddress();
        assertNotNull(address.toString());
        final String userToString = "Address{" +
                "Model{" +
                "id=" + address.getId() +
                ", validated=" + address.isValidated() +
                '}' +
                ", address='" + address.getAddress() + '\'' +
                ", googleMaps='" + address.getGoogleMaps() + '\'' +
                '}';
        assertEquals(address.toString(), userToString);
    }

    @Test
    public void equalsInvalidObjects() {
        final Address address1 = new Address();
        final Address address2 = new Address();
        assertTrue(address1.equals(address2));
        address1.setAddress(MockConstants.ADDRESS);
        address2.setAddress(MockConstants.ADDRESS);
        assertTrue(address1.equals(address2));
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final Address address1 = MockEntity.getAddress();
        final Address address2 = address1.clone();
        assertEquals(address1, address2);
        final boolean value = address1.getAddress().equalsIgnoreCase(address2.getAddress());
        assertEquals(address1.equals(address2), value);
    }

    @Test
    public void hashCodeInvalidObject() {
        final Address address = new Address();
        int value = address.getAddress().hashCode() + address.getGoogleMaps().hashCode();
        assertEquals(address.hashCode(), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        super.hashCodeValidObject();
        final Address address = MockEntity.getAddress();
        int value = address.getAddress().hashCode() + address.getGoogleMaps().hashCode();
        assertEquals(address.hashCode(), value);
    }

    @Test
    public void whenSetNullAddressThenGetEmptyString() {
        final Address address = new Address();
        address.setAddress(null);
        assertNotNull(address.getAddress());
    }

    @Test
    public void whenSetBlankAddressThenGetEmptyString() {
        final Address address = new Address();
        address.setAddress("");
        assertNotNull(address.getAddress());
    }

    @Test
    public void whenSetValidAddressThenGetThisName() {
        final Address address = new Address();
        address.setAddress(MockConstants.ADDRESS);
        assertNotNull(address.getAddress());
        Assert.assertEquals(address.getAddress(), MockConstants.ADDRESS);
    }

    @Test
    public void whenSetNullGoogleMapsThenGetEmptyString() {
        final Address address = new Address();
        address.setGoogleMaps(null);
        assertNotNull(address.getGoogleMaps());
    }

    @Test
    public void whenSetBlankGoogleMapsThenGetEmptyString() {
        final Address address = new Address();
        address.setGoogleMaps("");
        assertNotNull(address.getGoogleMaps());
    }

    @Test
    public void whenSetValidGoogleMapsThenGetThisName() {
        final Address address = new Address();
        address.setGoogleMaps(MockConstants.GOOGLE_MAPS);
        assertNotNull(address.getGoogleMaps());
        Assert.assertEquals(address.getGoogleMaps(), MockConstants.GOOGLE_MAPS);
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final Address model1 = getInstance();
        final Address model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final Address model1 = getInstance();
        final Address model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Override
    protected Address getObject() {
        return MockEntity.getAddress();
    }

    @Override
    protected Address getInstance() {
        return new Address();
    }
}
