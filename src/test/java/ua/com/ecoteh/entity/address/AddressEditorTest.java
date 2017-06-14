package ua.com.ecoteh.entity.address;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelEditorTest;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.ADDRESS;
import static ua.com.ecoteh.mocks.MockConstants.GOOGLE_MAPS;
import static ua.com.ecoteh.mocks.enity.MockModels.getAddress;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class AddressEditorTest extends ModelEditorTest<Address> {

    private AddressEditor editor;
    private Address address;

    @Before
    public void beforeTests() {
        this.address = getAddress();
        this.editor = new AddressEditor(this.address);
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Address updatedAddress = this.editor.update();
        assertEquals(updatedAddress.getPostAddress(), this.address.getPostAddress());
        assertNotNull(updatedAddress.getGoogleMaps(), this.address.getGoogleMaps());
    }

    @Test
    public void whenAddPostAddressThenBuildWithIt() {
        Address address;
        String postAddress;
        for (int i = 0; i < 5; i++) {
            postAddress = ADDRESS + i;
            this.editor.addPostAddress(postAddress);
            address = this.editor.update();
            assertEquals(address.getPostAddress(), postAddress);
        }
    }

    @Test
    public void whenAddNullPostAddressThenBuildWithEmptyIt() {
        this.editor.addPostAddress(null);
        final Address address = this.editor.update();
        assertEquals(address.getPostAddress(), this.address.getPostAddress());
    }

    @Test
    public void whenAddGoogleMapsThenBuildWithIt() {
        Address address;
        String googleMaps;
        for (int i = 0; i < 5; i++) {
            googleMaps = GOOGLE_MAPS + i;
            this.editor.addGoogleMaps(googleMaps);
            address = this.editor.update();
            assertEquals(address.getGoogleMaps(), googleMaps);
        }
    }

    @Test
    public void whenAddNullGoogleMapsThenBuildWithEmptyIt() {
        this.editor.addGoogleMaps(null);
        final Address address = this.editor.update();
        assertEquals(address.getGoogleMaps(), this.address.getGoogleMaps());
    }

    @Override
    protected AddressEditor getEditor() {
        return this.editor;
    }

    @Override
    protected Address getModel() {
        return this.address;
    }
}