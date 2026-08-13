package com.mygym.app.service;

import com.mygym.app.models.ExcerciseList;
import com.mygym.app.models.TargetMuscleWrapper;
import org.bson.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutCrudServiceTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private WorkoutCrudService workoutCrudService;

    @Test
    @DisplayName("Should pass init when exercise configuration document exists")
    void testInit_Success() {
        ExcerciseList doc = new ExcerciseList();
        doc.setAbsExercises(List.of("Crunches"));

        when(mongoTemplate.findOne(any(Query.class), eq(ExcerciseList.class), eq("_exercise"))).thenReturn(doc);

        assertDoesNotThrow(() -> workoutCrudService.init());
    }

    @Test
    @DisplayName("Should throw RuntimeException during init when document is missing")
    void testInit_NotFound_ThrowsException() {
        when(mongoTemplate.findOne(any(Query.class), eq(ExcerciseList.class), eq("_exercise"))).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> workoutCrudService.init());
        assertTrue(exception.getMessage().contains("Startup Error"));
    }

    @Test
    @DisplayName("Should fetch chest exercises successfully")
    void testGetChestExercises_Success() {
        ExcerciseList doc = new ExcerciseList();
        doc.setChestExercises(List.of("Bench Press", "Incline Press"));

        when(mongoTemplate.findOne(any(Query.class), eq(ExcerciseList.class), eq("_exercise"))).thenReturn(doc);

        TargetMuscleWrapper wrapper = workoutCrudService.getChestExercises();

        assertNotNull(wrapper);
    }

    @Test
    @DisplayName("Should add chest exercise and return updated muscle wrapper")
    void testAddChestExercise_Success() {
        ExcerciseList doc = new ExcerciseList();
        doc.setChestExercises(List.of("Bench Press", "Chest Fly"));

        when(mongoTemplate.updateFirst(any(Query.class), any(Update.class), eq(Document.class), eq("_exercise")))
                .thenReturn(null);
        when(mongoTemplate.findOne(any(Query.class), eq(ExcerciseList.class), eq("_exercise"))).thenReturn(doc);

        TargetMuscleWrapper wrapper = workoutCrudService.addChestExercise("Chest Fly");

        assertNotNull(wrapper);
        verify(mongoTemplate, times(1)).updateFirst(any(), any(), eq(Document.class), eq("_exercise"));
    }
}