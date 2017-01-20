package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.Model;
import com.salimov.yurii.service.data.interfaces.DataService;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public abstract class DataServiceImplTest<T extends Model<E>, E extends Number> {

    @Test
    public void whenAddInvalidModelThenReturnsNull() {
        assertNull(
                getService().add(null)
        );
        assertNull(
                getService().add(
                        getInvalidObject()
                )
        );
    }

    @Test
    public void whenAddValidModelThenReturnsIt() {
        assertNotNull(
                getService().add(
                        getObject()
                )
        );
    }

    @Test
    public void whenAddInvalidModelsThenReturnsEmptyCollection() {
        assertTrue(
                getService()
                        .addAll(null)
                        .isEmpty()
        );
        assertTrue(
                getService()
                        .addAll(
                                new ArrayList<>()
                        ).isEmpty()
        );
    }

    @Test
    public void whenAddValidModelsThenReturnsIt() {
        assertFalse(
                getService()
                        .addAll(
                                getObjects()
                        ).isEmpty()
        );
    }

    @Ignore
    protected abstract DataService<T, E> getService();

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();

    @Ignore
    protected abstract T getInvalidObject();
}
