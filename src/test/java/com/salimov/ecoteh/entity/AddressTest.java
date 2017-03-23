package com.salimov.ecoteh.entity;

import org.junit.Test;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getAddress;
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
        final Address address = new Address(ADDRESS, GOOGLE_MAPS);
        assertNotNull(address.getAddress());
        assertNotNull(address.getGoogleMaps());
        assertEquals(address.getAddress(), ADDRESS);
        assertEquals(address.getGoogleMaps(), GOOGLE_MAPS);
    }

    @Test
    public void toStringTest() {
        final Address address = getAddress();
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
        address1.setAddress(ADDRESS);
        address2.setAddress(ADDRESS);
        assertTrue(address1.equals(address2));
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final Address address1 = getAddress();
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
        final Address address = getAddress();
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
        address.setAddress(ADDRESS);
        assertNotNull(address.getAddress());
        assertEquals(address.getAddress(), ADDRESS);
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
        address.setGoogleMaps(GOOGLE_MAPS);
        assertNotNull(address.getGoogleMaps());
        assertEquals(address.getGoogleMaps(), GOOGLE_MAPS);
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
        return getAddress();
    }

    @Override
    protected Address getInstance() {
        return new Address();
    }
}
