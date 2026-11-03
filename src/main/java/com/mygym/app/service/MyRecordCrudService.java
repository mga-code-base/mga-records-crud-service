package com.mygym.app.service;

import static com.mygym.app.util.RecordsCrudConstants.DATE_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mygym.app.models.MyRecord;
import com.mygym.app.models.MyRecordsWrapper;
import com.mygym.app.models.WorkoutSet;
import com.mygym.app.repository.MyRecordRepository;

@Service
public class MyRecordCrudService {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private final MyRecordRepository repository;

    public MyRecordCrudService(MyRecordRepository repository) {
        this.repository = repository;
    }

    public MyRecord createRecord(MyRecord records) {
        return repository.findByMemberIdAndTargetMuscleAndExerciseNameAndDate(
                records.memberId(), 
                records.targetMuscle(), 
                records.exerciseName(), 
                LocalDate.now().format(formatter)
        ).map(olderRecords -> {
            List<WorkoutSet> todaysSets = new ArrayList<>(olderRecords.sets() == null ? List.of() : olderRecords.sets());
            todaysSets.addAll(records.sets() == null ? List.of() : records.sets());
            return repository.save(new MyRecord(olderRecords._id(), olderRecords.memberId(), olderRecords.exerciseName(),
                    olderRecords.targetMuscle(), todaysSets, olderRecords.date()));
        }).orElseGet(() -> repository.save(records));
    }

    public MyRecord getRecordById(String id) {
        return repository.findByMemberId(id)
                .orElseThrow(() -> new RuntimeException("Gym record not found with id: " + id));
    }

    public void deleteRecord(String memberId) {
        repository.deleteByMemberId(memberId);
    }

    public MyRecordsWrapper getPreviousRecords(String memberId, String targetMuscle, String exerciseName) {
        List<MyRecord> allRecords = repository.findPreviousRecords(memberId, targetMuscle, exerciseName);
        String todayStr = LocalDate.now().format(formatter);
        
        LinkedHashSet<String> top3Dates = allRecords.stream()
                .map(MyRecord::date)
                .filter(dateStr -> !todayStr.equals(dateStr))
                .distinct()
                .sorted(Comparator.comparing(dateStr -> LocalDate.parse(dateStr, formatter), Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toCollection(LinkedHashSet::new));
                
        allRecords = allRecords.stream()
                .filter(record -> top3Dates.contains(record.date()))
                .sorted(Comparator.comparing(record -> LocalDate.parse(record.date(), formatter), Comparator.reverseOrder()))
                .collect(Collectors.toList());
                
        return new MyRecordsWrapper(allRecords);
    }

    public MyRecordsWrapper getProgressRecords(String memberId) {
        List<MyRecord> allRecords = repository.findAllByMemberId(memberId);
        return (allRecords == null || allRecords.isEmpty()) 
                ? MyRecordsWrapper.empty() 
                : new MyRecordsWrapper(allRecords);
    }
}