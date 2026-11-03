package com.mygym.app.service;

import com.mygym.app.models.MyRecord;
import com.mygym.app.models.MyRecordsWrapper;
import com.mygym.app.models.WorkoutSet;
import com.mygym.app.repository.MyRecordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyRecordCrudServiceTest {

    @Mock
    private MyRecordRepository repository;

    @InjectMocks
    private MyRecordCrudService myRecordCrudService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    @DisplayName("Should create new record when no record exists for today")
    void testCreateRecord_NewRecord_Success() {
        MyRecord record = new MyRecord(null, "M-101", "Bench Press", "Chest", null, null);

        when(repository.findByMemberIdAndTargetMuscleAndExerciseNameAndDate(
                eq("M-101"), eq("Chest"), eq("Bench Press"), eq(LocalDate.now().format(formatter))
        )).thenReturn(Optional.empty());

        when(repository.save(any(MyRecord.class))).thenReturn(record);

        MyRecord created = myRecordCrudService.createRecord(record);

        assertNotNull(created);
        assertEquals("M-101", created.memberId());
        verify(repository, times(1)).save(record);
    }

    @Test
    @DisplayName("Should append sets to existing record when record exists for today")
    void testCreateRecord_ExistingRecord_AppendsSets() {
        MyRecord existingRecord = new MyRecord(null, "M-101", null, null, new ArrayList<>(), null);

        MyRecord newRecord = new MyRecord(null, "M-101", "Bench Press", "Chest", List.of(new WorkoutSet(10f, "KG", 8, null)), null);

        when(repository.findByMemberIdAndTargetMuscleAndExerciseNameAndDate(
                eq("M-101"), eq("Chest"), eq("Bench Press"), eq(LocalDate.now().format(formatter))
        )).thenReturn(Optional.of(existingRecord));

        when(repository.save(any(MyRecord.class))).thenReturn(existingRecord);

        MyRecord updated = myRecordCrudService.createRecord(newRecord);

        assertNotNull(updated);
        ArgumentCaptor<MyRecord> saved = ArgumentCaptor.forClass(MyRecord.class);
        verify(repository, times(1)).save(saved.capture());
        assertEquals(1, saved.getValue().sets().size());
    }

    @Test
    @DisplayName("Should fetch record by member ID")
    void testGetRecordById_Success() {
        MyRecord record = new MyRecord(null, "M-101", null, null, null, null);

        when(repository.findByMemberId("M-101")).thenReturn(Optional.of(record));

        MyRecord result = myRecordCrudService.getRecordById("M-101");

        assertNotNull(result);
        assertEquals("M-101", result.memberId());
    }

    @Test
    @DisplayName("Should throw RuntimeException when record not found by member ID")
    void testGetRecordById_NotFound_ThrowsException() {
        when(repository.findByMemberId("M-999")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> myRecordCrudService.getRecordById("M-999"));
    }

    @Test
    @DisplayName("Should delete record by member ID")
    void testDeleteRecord_Success() {
        doNothing().when(repository).deleteByMemberId("M-101");

        myRecordCrudService.deleteRecord("M-101");

        verify(repository, times(1)).deleteByMemberId("M-101");
    }

    @Test
    @DisplayName("Should filter and return top 3 previous records excluding today")
    void testGetPreviousRecords_Success() {
        MyRecord rec1 = new MyRecord(null, null, null, null, null, "2026-08-10");

        MyRecord rec2 = new MyRecord(null, null, null, null, null, "2026-08-09");

        when(repository.findPreviousRecords("M-101", "Chest", "Bench Press"))
                .thenReturn(List.of(rec1, rec2));

        MyRecordsWrapper result = myRecordCrudService.getPreviousRecords("M-101", "Chest", "Bench Press");

        assertNotNull(result);
        verify(repository, times(1)).findPreviousRecords("M-101", "Chest", "Bench Press");
    }
}