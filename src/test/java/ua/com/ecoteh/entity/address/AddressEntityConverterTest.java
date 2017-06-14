package ua.com.ecoteh.entity.address;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelEntityConverterTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getAddressEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class AddressEntityConverterTest extends ModelEntityConverterTest<AddressEntity, Address> {

    private static AddressEntityConverter converter;
    private static AddressEntity entity;

    @BeforeClass
    public static void beforeClass() {
        entity = getAddressEntity();
        converter = new AddressEntityConverter(entity);
    }

    @Override
    protected void checkEntity(final Address address) {
        super.checkEntity(address);
        assertEquals(address.getPostAddress(), entity.getPostAddress());
        assertEquals(address.getGoogleMaps(), entity.getGoogleMaps());
    }

    @Override
    protected AddressEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected AddressEntity getEntity() {
        return entity;
    }
}