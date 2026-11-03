package com.mygym.app.controller;

import com.mygym.app.models.TargetMuscleWrapper;
import com.mygym.app.service.WorkoutCrudService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutCrudControllerTest {

    @Mock
    private WorkoutCrudService service;

    @InjectMocks
    private WorkoutCrudController controller;

    @Test
    @DisplayName("Should fetch chest exercises from service layer")
    void testGetChestExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Bench Press", "Incline Fly"));
        when(service.getChestExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getChestExercises();

        assertNotNull(result);
        verify(service, times(1)).chestExercises();
    }

    @Test
    @DisplayName("Should add chest exercise via service layer")
    void testAddChestExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Cable Fly"));
        when(service.addChestExercise("Cable Fly")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addChestExercise("Cable Fly");

        assertNotNull(result);
        verify(service, times(1)).addChestExercise("Cable Fly");
    }

    @Test
    @DisplayName("Should fetch shoulder exercises from service layer")
    void testGetShoulderExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Overhead Press"));
        when(service.getShoulderExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getShoulderExercises();

        assertNotNull(result);
        verify(service, times(1)).shoulderExercises();
    }

    @Test
    @DisplayName("Should add shoulder exercise via service layer")
    void testAddShoulderExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Lateral Raise"));
        when(service.addShoulderExercise("Lateral Raise")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addShoulderExercise("Lateral Raise");

        assertNotNull(result);
        verify(service, times(1)).addShoulderExercise("Lateral Raise");
    }

    @Test
    @DisplayName("Should fetch back exercises from service layer")
    void testGetBackExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Lat Pulldown"));
        when(service.getBackExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getBackExercises();

        assertNotNull(result);
        verify(service, times(1)).backExercises();
    }

    @Test
    @DisplayName("Should add back exercise via service layer")
    void testAddBackExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Barbell Row"));
        when(service.addBackExercise("Barbell Row")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addBackExercise("Barbell Row");

        assertNotNull(result);
        verify(service, times(1)).addBackExercise("Barbell Row");
    }

    @Test
    @DisplayName("Should fetch biceps exercises from service layer")
    void testGetBicepsExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Dumbbell Curl"));
        when(service.getBicepsExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getBicepsExercises();

        assertNotNull(result);
        verify(service, times(1)).bicepsExercises();
    }

    @Test
    @DisplayName("Should add biceps exercise via service layer")
    void testAddBicepsExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Hammer Curl"));
        when(service.addBicepsExercise("Hammer Curl")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addBicepsExercise("Hammer Curl");

        assertNotNull(result);
        verify(service, times(1)).addBicepsExercise("Hammer Curl");
    }

    @Test
    @DisplayName("Should fetch triceps exercises from service layer")
    void testGetTricepsExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Triceps Pushdown"));
        when(service.getTricepsExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getTricepsExercises();

        assertNotNull(result);
        verify(service, times(1)).tricepsExercises();
    }

    @Test
    @DisplayName("Should add triceps exercise via service layer")
    void testAddTricepsExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Skullcrusher"));
        when(service.addTricepsExercise("Skullcrusher")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addTricepsExercise("Skullcrusher");

        assertNotNull(result);
        verify(service, times(1)).addTricepsExercise("Skullcrusher");
    }

    @Test
    @DisplayName("Should fetch legs exercises from service layer")
    void testGetLegsExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Barbell Squat"));
        when(service.getLegsExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getLegsExercises();

        assertNotNull(result);
        verify(service, times(1)).legsExercises();
    }

    @Test
    @DisplayName("Should add legs exercise via service layer")
    void testAddLegsExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Leg Extension"));
        when(service.addLegsExercise("Leg Extension")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addLegsExercise("Leg Extension");

        assertNotNull(result);
        verify(service, times(1)).addLegsExercise("Leg Extension");
    }

    @Test
    @DisplayName("Should fetch abs exercises from service layer")
    void testGetAbsExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Crunches"));
        when(service.getAbsExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getAbsExercises();

        assertNotNull(result);
        verify(service, times(1)).absExercises();
    }

    @Test
    @DisplayName("Should add abs exercise via service layer")
    void testAddAbsExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Leg Raise"));
        when(service.addAbsExercise("Leg Raise")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addAbsExercise("Leg Raise");

        assertNotNull(result);
        verify(service, times(1)).addAbsExercise("Leg Raise");
    }

    @Test
    @DisplayName("Should fetch cardio exercises from service layer")
    void testGetCardioExercises_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Treadmill"));
        when(service.getCardioExercises()).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.getCardioExercises();

        assertNotNull(result);
        verify(service, times(1)).cardioExercises();
    }

    @Test
    @DisplayName("Should add cardio exercise via service layer")
    void testAddCardioExercise_Success() {
        TargetMuscleWrapper wrapper = new TargetMuscleWrapper(List.of("Elliptical"));
        when(service.addCardioExercise("Elliptical")).thenReturn(wrapper);

        TargetMuscleWrapper result = controller.addCardioExercise("Elliptical");

        assertNotNull(result);
        verify(service, times(1)).addCardioExercise("Elliptical");
    }
}