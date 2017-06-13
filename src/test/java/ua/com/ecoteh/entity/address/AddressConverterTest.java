package ua.com.ecoteh.entity.address;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelConverterTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.enity.MockModels.getAddress;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class AddressConverterTest extends ModelConverterTest<Address, AddressEntity> {

    private static AddressConverter converter;
    private static Address address;

    @BeforeClass
    public void beforeClass() {
        address = getAddress();
        converter = new AddressConverter(address);
    }

    @Override
    protected void checkEntity(final AddressEntity entity) {
        super.checkEntity(entity);
        assertEquals(entity.getPostAddress(), address.getPostAddress());
        assertEquals(entity.getGoogleMaps(), address.getGoogleMaps());
    }

    @Override
    protected AddressConverter getConverter() {
        return converter;
    }

    @Override
    protected Address getModel() {
        return address;
    }
}