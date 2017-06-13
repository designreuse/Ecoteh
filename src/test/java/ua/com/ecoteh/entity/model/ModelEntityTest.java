package ua.com.ecoteh.entity.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelEntityTest {

    @Test
    public void toStringTest() {
        final ModelEntity entity = getInstance();
        final String toStringTest = "ModelEntity{" +
                "id=" + entity.getId() +
                ", validated=" + entity.isValidated() +
                '}';
        assertEquals(entity.toString(), toStringTest);
    }

    @Test
    public void equalsWithNull() throws Exception {
        final ModelEntity entity = getInstance();
        assertFalse(entity.equals(null));
    }

    @Test
    public void equalsWithObjectOfTheAnotherClass() throws Exception {
        final ModelEntity entity = getInstance();
        final String string = "Hello World!";
        assertFalse(entity.equals(string));
    }

    @Test
    public void equalsTwoObjects() throws Exception {
        final ModelEntity first = getInstance();
        final ModelEntity second = getInstance().clone();
        for (int i = 0; i < 5; i++) {
            assertTrue(first.equals(second));
            assertTrue(second.equals(first));
        }
    }

    @Test
    public void equalsThreeObjects() throws Exception {
        final ModelEntity first = getInstance();
        final ModelEntity second = getInstance().clone();
        final ModelEntity third = getInstance().clone();
        for (int i = 0; i < 5; i++) {
            assertTrue(first.equals(second));
            assertTrue(second.equals(third));
            assertTrue(third.equals(first));
        }
    }

    @Test
    public void hashCodeTest() throws Exception {
        final ModelEntity model = getInstance();
        int hashCode = model.hashCode();
        for (int i = 0; i < 5; i++) {
            int temp = model.hashCode();
            assertEquals(temp, hashCode);
            hashCode = temp;
        }
    }

    @Test
    public void cloneTest() throws Exception {
        final ModelEntity model = getInstance();
        final ModelEntity clone = model.clone();
        assertTrue(model != clone);
        assertEquals(model, clone);
    }

    @Test
    public void whenSetIdThenReturnIt() {
        final ModelEntity entity = getInstance();
        for (int i = 0; i < 10; i++) {
            entity.setId(i);
            assertEquals(entity.getId(), i);
        }
    }

    @Test
    public void whenSetValidatedThenReturnIt() {
        final ModelEntity entity = getInstance();
        entity.setValidated(true);
        assertTrue(entity.isValidated());
        entity.setValidated(false);
        assertFalse(entity.isValidated());
    }

    @Test
    public void whenConvertThenReturnNotNull() {
        final ModelEntity entity = getInstance();
        final Model model = entity.convert();
        assertNotNull(model);
    }

    protected abstract ModelEntity getInstance();
}