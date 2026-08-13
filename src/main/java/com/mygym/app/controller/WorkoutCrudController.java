package com.mygym.app.controller;

import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_ABS_ADD_EXCERCISE_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_ABS_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_BACK_ADD_EXCERCISE_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_BACK_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_BICEPS_ADD_EXCERCISE_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_BICEPS_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_CARDIO_ADD_EXCERCISE_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_CARDIO_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_CHEST_ADD_EXCERCISE_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_CHEST_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_CRUD_REST_BASE;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_LEGS_ADD_EXCERCISE_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_LEGS_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_SHOULDER_ADD_EXCERCISE_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_SHOULDER_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_TRICEPS_ADD_EXCERCISE_ENPOINT;
import static com.mygym.app.util.RecordsCrudConstants.WORKOUT_TRICEPS_ENPOINT;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygym.app.models.TargetMuscleWrapper;
import com.mygym.app.service.WorkoutCrudService;

@RestController
@RequestMapping(WORKOUT_CRUD_REST_BASE)
public class WorkoutCrudController {
	
    private final WorkoutCrudService service;

    public WorkoutCrudController(WorkoutCrudService service) {
        this.service = service;
    }
    
	@GetMapping(WORKOUT_CHEST_ENPOINT)
    public TargetMuscleWrapper getChestExercises() {
        return service.getChestExercises();
    }    

	@PutMapping(WORKOUT_CHEST_ADD_EXCERCISE_ENPOINT)
    public TargetMuscleWrapper addChestExercise(@PathVariable("name") String excersiceName) {
    	return service.addChestExercise(excersiceName);
    }

	@GetMapping(WORKOUT_SHOULDER_ENPOINT)
    public TargetMuscleWrapper getShoulderExercises() {
        return service.getShoulderExercises();
    }    

	@PutMapping(WORKOUT_SHOULDER_ADD_EXCERCISE_ENPOINT)
    public TargetMuscleWrapper addShoulderExercise(@PathVariable("name") String excersiceName) {
    	return service.addShoulderExercise(excersiceName);
    }
	
	@GetMapping(WORKOUT_BACK_ENPOINT)
    public TargetMuscleWrapper getBackExercises() {
        return service.getBackExercises();
    }    

	@PutMapping(WORKOUT_BACK_ADD_EXCERCISE_ENPOINT)
    public TargetMuscleWrapper addBackExercise(@PathVariable("name") String excersiceName) {
    	return service.addBackExercise(excersiceName);
    }

	@GetMapping(WORKOUT_BICEPS_ENPOINT)
    public TargetMuscleWrapper getBicepsExercises() {
        return service.getBicepsExercises();
    }    

	@PutMapping(WORKOUT_BICEPS_ADD_EXCERCISE_ENPOINT)
    public TargetMuscleWrapper addBicepsExercise(@PathVariable("name") String excersiceName) {
    	return service.addBicepsExercise(excersiceName);
    }
	
	@GetMapping(WORKOUT_TRICEPS_ENPOINT)
    public TargetMuscleWrapper getTricepsExercises() {
        return service.getTricepsExercises();
    }    

	@PutMapping(WORKOUT_TRICEPS_ADD_EXCERCISE_ENPOINT)
    public TargetMuscleWrapper addTricepsExercise(@PathVariable("name") String excersiceName) {
    	return service.addTricepsExercise(excersiceName);
    }
	
	@GetMapping(WORKOUT_LEGS_ENPOINT)
    public TargetMuscleWrapper getLegsExercises() {
        return service.getLegsExercises();
    }    

	@PutMapping(WORKOUT_LEGS_ADD_EXCERCISE_ENPOINT)
    public TargetMuscleWrapper addLegsExercise(@PathVariable("name") String excersiceName) {
    	return service.addLegsExercise(excersiceName);
	}

	@GetMapping(WORKOUT_ABS_ENPOINT)
    public TargetMuscleWrapper getAbsExercises() {
        return service.getAbsExercises();
    }    

	@PutMapping(WORKOUT_ABS_ADD_EXCERCISE_ENPOINT)
    public TargetMuscleWrapper addAbsExercise(@PathVariable("name") String excersiceName) {
    	return service.addAbsExercise(excersiceName);
    }
    
	@GetMapping(WORKOUT_CARDIO_ENPOINT)
    public TargetMuscleWrapper getCardioExercises() {
        return service.getCardioExercises();
    }    

	@PutMapping(WORKOUT_CARDIO_ADD_EXCERCISE_ENPOINT)
    public TargetMuscleWrapper addCardioExercise(@PathVariable("name") String excersiceName) {
    	return service.addCardioExercise(excersiceName);
    }
}
