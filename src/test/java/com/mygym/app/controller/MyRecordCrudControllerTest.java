package com.mygym.app.controller;

import com.mygym.app.models.MyRecord;
import com.mygym.app.models.MyRecordsWrapper;
import com.mygym.app.service.MyRecordCrudService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyRecordCrudControllerTest {

    @Mock
    private MyRecordCrudService service;

    @InjectMocks
    private MyRecordCrudController controller;

    @Test
    @DisplayName("Should invoke service create record and return saved entity")
    void testCreate_Success() {
        MyRecord record = new MyRecord();
        record.setMemberId("M-101");
        record.setTargetMuscle("Chest");

        when(service.createRecord(any(MyRecord.class))).thenReturn(record);

        MyRecord result = controller.create(record);

        assertNotNull(result);
        assertEquals("M-101", result.getMemberId());
        verify(service, times(1)).createRecord(record);
    }

    @Test
    @DisplayName("Should read record by ID via service layer")
    void testReadById_Success() {
        MyRecord record = new MyRecord();
        record.setMemberId("M-101");

        when(service.getRecordById("M-101")).thenReturn(record);

        MyRecord result = controller.readById("M-101");

        assertNotNull(result);
        assertEquals("M-101", result.getMemberId());
        verify(service, times(1)).getRecordById("M-101");
    }

    @Test
    @DisplayName("Should invoke service delete and return success message")
    void testDelete_Success() {
        doNothing().when(service).deleteRecord("M-101");

        String result = controller.delete("M-101");

        assertEquals("Gym record deleted successfully with id: M-101", result);
        verify(service, times(1)).deleteRecord("M-101");
    }

    @Test
    @DisplayName("Should delegate previous records search to service layer")
    void testGetPreviousRecords_Success() {
        MyRecordsWrapper wrapper = new MyRecordsWrapper(List.of(new MyRecord()));
        when(service.getPreviousRecords("M-101", "Chest", "Bench Press")).thenReturn(wrapper);

        MyRecordsWrapper result = controller.getPreviousRecords("M-101", "Chest", "Bench Press");

        assertNotNull(result);
        verify(service, times(1)).getPreviousRecords("M-101", "Chest", "Bench Press");
    }

    @Test
    @DisplayName("Should delegate progress records search to service layer")
    void testGetProgressRecords_Success() {
        MyRecordsWrapper wrapper = new MyRecordsWrapper(List.of(new MyRecord()));
        when(service.getProgressRecords("M-101")).thenReturn(wrapper);

        MyRecordsWrapper result = controller.getPreviousRecords("M-101");

        assertNotNull(result);
        verify(service, times(1)).getProgressRecords("M-101");
    }
}