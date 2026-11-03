package com.mygym.app.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import org.bson.Document;

import com.mygym.app.models.ExcerciseList;
import com.mygym.app.models.TargetMuscleWrapper;

@Service
public class WorkoutCrudService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final String COLLECTION_NAME = "_exercise";
    private final String TARGET_ID = "1";

    @PostConstruct
    public void init() {
        try {
            ExcerciseList doc = fetchDocument();
            System.out.println("Workout database connected successfully. Target config ID verified: " + doc.absExercises());
        } catch (Exception e) {
            throw new RuntimeException("Startup Error: Could not find workout configuration where id = " + TARGET_ID, e);
        }
    }

    private ExcerciseList fetchDocument() {
        Query query = new Query(Criteria.where("docId").is(TARGET_ID));
        ExcerciseList doc = mongoTemplate.findOne(query, ExcerciseList.class, COLLECTION_NAME);
        if (doc == null) {
            throw new RuntimeException("No configuration document found in '" + COLLECTION_NAME + "' matching id: " + TARGET_ID);
        }
        return doc;
    }

    private TargetMuscleWrapper updateExerciseArray(String dbFieldName, String exerciseName) {
        Query query = new Query(Criteria.where("id").is(TARGET_ID));
        Update update = new Update().addToSet(dbFieldName, exerciseName);
        mongoTemplate.updateFirst(query, update, Document.class, COLLECTION_NAME);
        return getExercisesForField(dbFieldName);
    }

    private TargetMuscleWrapper getExercisesForField(String dbFieldName) {
        ExcerciseList doc = fetchDocument();
        switch (dbFieldName) {
            case "Chest": return new TargetMuscleWrapper(doc.chestExercises());
            case "Shoulder": return new TargetMuscleWrapper(doc.shoulderExercises());
            case "Back": return new TargetMuscleWrapper(doc.backExercises());
            case "Biceps": return new TargetMuscleWrapper(doc.bicepsExercises());
            case "Triceps": return new TargetMuscleWrapper(doc.tricepsExercises());
            case "Legs": return new TargetMuscleWrapper(doc.legsExercises());
            case "Abs": return new TargetMuscleWrapper(doc.absExercises());
            case "Cardio": return new TargetMuscleWrapper(doc.cardioExercises());
            default: throw new IllegalArgumentException("Unknown muscle field: " + dbFieldName);
        }
    }

    public TargetMuscleWrapper getChestExercises() {
        return getExercisesForField("Chest");
    }

    public TargetMuscleWrapper addChestExercise(String exerciseName) {
        return updateExerciseArray("Chest", exerciseName);
    }

    public TargetMuscleWrapper getShoulderExercises() {
        return getExercisesForField("Shoulder");
    }

    public TargetMuscleWrapper addShoulderExercise(String exerciseName) {
        return updateExerciseArray("Shoulder", exerciseName);
    }

    public TargetMuscleWrapper getBackExercises() {
        return getExercisesForField("Back");
    }

    public TargetMuscleWrapper addBackExercise(String exerciseName) {
        return updateExerciseArray("Back", exerciseName);
    }

    public TargetMuscleWrapper getBicepsExercises() {
        return getExercisesForField("Biceps");
    }

    public TargetMuscleWrapper addBicepsExercise(String exerciseName) {
        return updateExerciseArray("Biceps", exerciseName);
    }

    public TargetMuscleWrapper getTricepsExercises() {
        return getExercisesForField("Triceps");
    }

    public TargetMuscleWrapper addTricepsExercise(String exerciseName) {
        return updateExerciseArray("Triceps", exerciseName);
    }

    public TargetMuscleWrapper getLegsExercises() {
        return getExercisesForField("Legs");
    }

    public TargetMuscleWrapper addLegsExercise(String exerciseName) {
        return updateExerciseArray("Legs", exerciseName);
    }

    public TargetMuscleWrapper getAbsExercises() {
        return getExercisesForField("Abs");
    }

    public TargetMuscleWrapper addAbsExercise(String exerciseName) {
        return updateExerciseArray("Abs", exerciseName);
    }

    public TargetMuscleWrapper getCardioExercises() {
        return getExercisesForField("Cardio");
    }

    public TargetMuscleWrapper addCardioExercise(String exerciseName) {
        return updateExerciseArray("Cardio", exerciseName);
    }
}