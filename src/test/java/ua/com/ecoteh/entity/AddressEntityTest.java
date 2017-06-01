package ua.com.ecoteh.entity;

import org.junit.Assert;
import org.junit.Test;
import ua.com.ecoteh.entity.address.AddressEntity;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class AddressEntityTest extends ModelTest<AddressEntity> {

    @Test
    public void whenInitializeByConstructorThenSetNotNullRole() {
        final AddressEntity addressEntity = new AddressEntity();
        assertNotNull(addressEntity.getAddress());
        assertNotNull(addressEntity.getGoogleMaps());
    }

    @Test
    public void whenPassNullParametersInConstructorThenSaveEmptyString() {
        final AddressEntity addressEntity = new AddressEntity(null, null);
        assertNotNull(addressEntity.getAddress());
        assertNotNull(addressEntity.getGoogleMaps());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_1() {
        final AddressEntity addressEntity = new AddressEntity("", "");
        assertNotNull(addressEntity.getAddress());
        assertNotNull(addressEntity.getGoogleMaps());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final AddressEntity addressEntity = new AddressEntity(" ", " ");
        assertNotNull(addressEntity.getAddress());
        assertNotNull(addressEntity.getGoogleMaps());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_3() {
        final AddressEntity addressEntity = new AddressEntity("   ", "   ");
        assertNotNull(addressEntity.getAddress());
        assertNotNull(addressEntity.getGoogleMaps());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final AddressEntity addressEntity = new AddressEntity(MockConstants.ADDRESS, MockConstants.GOOGLE_MAPS);
        assertNotNull(addressEntity.getAddress());
        assertNotNull(addressEntity.getGoogleMaps());
        Assert.assertEquals(addressEntity.getAddress(), MockConstants.ADDRESS);
        Assert.assertEquals(addressEntity.getGoogleMaps(), MockConstants.GOOGLE_MAPS);
    }

    @Test
    public void toStringTest() {
        final AddressEntity addressEntity = MockEntity.getAddressEntity();
        assertNotNull(addressEntity.toString());
        final String userToString = "AddressEntity{" +
                "ModelEntity{" +
                "id=" + addressEntity.getId() +
                ", validated=" + addressEntity.isValidated() +
                '}' +
                ", addressEntity='" + addressEntity.getAddress() + '\'' +
                ", googleMaps='" + addressEntity.getGoogleMaps() + '\'' +
                '}';
        assertEquals(addressEntity.toString(), userToString);
    }

    @Test
    public void equalsInvalidObjects() {
        final AddressEntity addressEntity1 = new AddressEntity();
        final AddressEntity addressEntity2 = new AddressEntity();
        assertTrue(addressEntity1.equals(addressEntity2));
        addressEntity1.setAddress(MockConstants.ADDRESS);
        addressEntity2.setAddress(MockConstants.ADDRESS);
        assertTrue(addressEntity1.equals(addressEntity2));
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final AddressEntity addressEntity1 = MockEntity.getAddressEntity();
        final AddressEntity addressEntity2 = addressEntity1.clone();
        assertEquals(addressEntity1, addressEntity2);
        final boolean value = addressEntity1.getAddress().equalsIgnoreCase(addressEntity2.getAddress());
        assertEquals(addressEntity1.equals(addressEntity2), value);
    }

    @Test
    public void hashCodeInvalidObject() {
        final AddressEntity addressEntity = new AddressEntity();
        int value = addressEntity.getAddress().hashCode() + addressEntity.getGoogleMaps().hashCode();
        assertEquals(addressEntity.hashCode(), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        super.hashCodeValidObject();
        final AddressEntity addressEntity = MockEntity.getAddressEntity();
        int value = addressEntity.getAddress().hashCode() + addressEntity.getGoogleMaps().hashCode();
        assertEquals(addressEntity.hashCode(), value);
    }

    @Test
    public void whenSetNullAddressThenGetEmptyString() {
        final AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddress(null);
        assertNotNull(addressEntity.getAddress());
    }

    @Test
    public void whenSetBlankAddressThenGetEmptyString() {
        final AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddress("");
        assertNotNull(addressEntity.getAddress());
    }

    @Test
    public void whenSetValidAddressThenGetThisName() {
        final AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddress(MockConstants.ADDRESS);
        assertNotNull(addressEntity.getAddress());
        Assert.assertEquals(addressEntity.getAddress(), MockConstants.ADDRESS);
    }

    @Test
    public void whenSetNullGoogleMapsThenGetEmptyString() {
        final AddressEntity addressEntity = new AddressEntity();
        addressEntity.setGoogleMaps(null);
        assertNotNull(addressEntity.getGoogleMaps());
    }

    @Test
    public void whenSetBlankGoogleMapsThenGetEmptyString() {
        final AddressEntity addressEntity = new AddressEntity();
        addressEntity.setGoogleMaps("");
        assertNotNull(addressEntity.getGoogleMaps());
    }

    @Test
    public void whenSetValidGoogleMapsThenGetThisName() {
        final AddressEntity addressEntity = new AddressEntity();
        addressEntity.setGoogleMaps(MockConstants.GOOGLE_MAPS);
        assertNotNull(addressEntity.getGoogleMaps());
        Assert.assertEquals(addressEntity.getGoogleMaps(), MockConstants.GOOGLE_MAPS);
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final AddressEntity model1 = getInstance();
        final AddressEntity model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final AddressEntity model1 = getInstance();
        final AddressEntity model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Override
    protected AddressEntity getObject() {
        return MockEntity.getAddressEntity();
    }

    @Override
    protected AddressEntity getInstance() {
        return new AddressEntity();
    }
}
