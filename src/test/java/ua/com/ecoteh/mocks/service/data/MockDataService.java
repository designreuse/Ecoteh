package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.entity.model.Model;
import ua.com.ecoteh.service.data.DataService;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.MockConstants.INDEX;
import static ua.com.ecoteh.mocks.MockConstants.UNKNOWN_ID;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
abstract class MockDataService<T extends Model> {

    DataService<T> create() {
        initAdd();
        initUpdate();
        initGet();
        initGetAll();
        initExists();
        initSubList();
        initFilteredByValid();
        initShuffle();
        return getService();
    }

    abstract DataService<T> getService();

    abstract T getModel();

    abstract Collection<T> getModels();

    private void initAdd() {
        final DataService<T> service = getService();
        final T model = getModel();
        final Collection<T> models = getModels();
        when(service.add(model)).thenReturn(model);
        when(service.add((T) null)).thenThrow(new IllegalArgumentException());
        when(service.add(models)).thenReturn(models);
        for (T m : models) {
            when(service.add(m)).thenReturn(model);
        }
    }

    private void initUpdate() {
        final DataService<T> service = getService();
        final T model = getModel();
        final Collection<T> models = getModels();
        when(service.update(model)).thenReturn(model);
        when(service.update((T) null)).thenThrow(new IllegalArgumentException());
        when(service.update(models)).thenReturn(models);
        for (T m : models) {
            when(service.update(m)).thenReturn(model);
        }
    }

    private void initGet() {
        final DataService<T> service = getService();
        final T model = getModel();
        when(service.get(ID)).thenReturn(model);
        when(service.get(UNKNOWN_ID)).thenThrow(new NullPointerException());
    }

    private void initGetAll() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        when(service.getAll()).thenReturn(models);
        when(service.getAll(true)).thenReturn(models);
        when(service.getAll(false)).thenReturn(models);
    }

    private void initExists() {
        final DataService<T> service = getService();
        final T model = getModel();
        final Collection<T> models = getModels();
        when(service.exists(model)).thenReturn(true);
        when(service.exists(null)).thenReturn(false);
        when(service.exists(ID)).thenReturn(true);
        when(service.exists(UNKNOWN_ID)).thenReturn(false);
        for (T m : models) {
            when(service.exists(m)).thenReturn(true);
        }
    }

    private void initSubList() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        when(service.subList(models, INDEX, INDEX)).thenReturn(new ArrayList<>(models));
        when(service.subList(new ArrayList<>(), INDEX, INDEX)).thenReturn(new ArrayList<>());
        when(service.subList(null, INDEX, INDEX)).thenReturn(new ArrayList<>());
        when(service.getAndSubList(INDEX, INDEX)).thenReturn(new ArrayList<>(models));
    }

    private void initFilteredByValid() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        when(service.filteredByValid(models)).thenReturn(new ArrayList<>(models));
        when(service.filteredByValid(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(service.filteredByValid(null)).thenReturn(new ArrayList<>());
    }

    private void initShuffle() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        when(service.shuffle(models)).thenReturn(new ArrayList<>(models));
        when(service.shuffle(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(service.shuffle(null)).thenReturn(new ArrayList<>());
    }
}
