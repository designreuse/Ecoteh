package ua.com.ecoteh.entity.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.ID;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelEditorTest<T extends Model> {

    @Test
    public void whenBuildEmptyThenReturnValidModel() {
        final ModelEditor<T, ?> editor = getEditor();
        final T updatedModel = editor.update();
        final T model = getModel();
        assertTrue(updatedModel.getId() == model.getId());
        assertEquals(updatedModel, model);
    }

    @Test
    public void whenAddIdThenBuildWithIt() {
        final ModelEditor<T, ?> editor = getEditor();
        T model;
        for (int i = 1; i < 5; i++) {
            editor.addId(i);
            model = editor.update();
            assertTrue(model.getId() == i);
        }
    }

    @Test
    public void whenAddNegativeIdThenBuildWithId() {
        final ModelEditor<T, ?> editor = getEditor();
        editor.addId(-ID);
        final T updatedModel = editor.update();
        final T model = getModel();
        assertEquals(updatedModel.getId(), model.getId());
    }

    @Test
    public void whenAddValidatedThenBuildWithIt() {
        final ModelEditor<T, ?> editor = getEditor();
        editor.addValidated(true);
        T model = editor.update();
        assertTrue(model.isValidated());
        editor.addValidated(false);
        model = editor.update();
        assertFalse(model.isValidated());
    }

    @Test
    public void whenSetValidThenReturnBuildValid() {
        final ModelEditor<T, ?> editor = getEditor();
        editor.isValid();
        final T model = editor.update();
        assertTrue(model.isValidated());
    }

    @Test
    public void whenSetNotValidThenReturnBuildInvalid() {
        final ModelEditor<T, ?> editor = getEditor();
        editor.isNotValid();
        final T model = editor.update();
        assertFalse(model.isValidated());
    }

    protected abstract ModelEditor<T, ?> getEditor();

    protected abstract T getModel();
}