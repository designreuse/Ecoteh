package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileType;
import ua.com.ecoteh.service.data.FileService;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;
import static ua.com.ecoteh.mocks.enity.MockModels.getFiles;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockFileService extends MockDataService<File> {

    private final FileService service;
    private final File file;
    private final Collection<File> files;

    MockFileService() {
        this.service = mock(FileService.class);
        this.file = getFile();
        this.files = getFiles();
    }

    @Override
    FileService create() {
        super.create();
        initGetByTitle();
        initGetByUrl();
        initGetByType();
        initSortByTitle();
        return this.service;
    }
    
    @Override
    FileService getService() {
        return this.service;
    }

    @Override
    File getModel() {
        return this.file;
    }

    @Override
    Collection<File> getModels() {
        return this.files;
    }

    private void initGetByTitle() {
        when(this.service.getByTitle(TITLE)).thenReturn(file);
        when(this.service.getByTitle(null)).thenThrow(new IllegalArgumentException());
        when(this.service.getByTitle("")).thenThrow(new IllegalArgumentException());
        when(this.service.getByTitle(ANY_STRING)).thenThrow(new NullPointerException());
    }

    private void initGetByUrl() {
        when(this.service.getByUrl(URL)).thenReturn(file);
        when(this.service.getByUrl(null)).thenThrow(new IllegalArgumentException());
        when(this.service.getByUrl("")).thenThrow(new IllegalArgumentException());
        when(this.service.getByUrl(ANY_STRING)).thenThrow(new NullPointerException());
    }

    private void initGetByType() {
        when(this.service.getByType(null)).thenThrow(new IllegalArgumentException());
        for (FileType type : FileType.values()) {
            when(this.service.getByType(type)).thenReturn(new ArrayList<>(this.files));
        }
    }

    private void initSortByTitle() {
        when(this.service.sortByTitle(this.files, true)).thenReturn(new ArrayList<>(this.files));
        when(this.service.sortByTitle(this.files, false)).thenReturn(new ArrayList<>(this.files));
        when(this.service.sortByTitle(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(this.service.sortByTitle(new ArrayList<>(), false)).thenReturn(new ArrayList<>());
        when(this.service.sortByTitle(null, true)).thenReturn(new ArrayList<>());
        when(this.service.sortByTitle(null, false)).thenReturn(new ArrayList<>());
    }
}
