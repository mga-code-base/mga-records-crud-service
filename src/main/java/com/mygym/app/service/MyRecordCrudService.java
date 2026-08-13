package com.mygym.app.service;

import static com.mygym.app.util.RecordsCrudConstants.DATE_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mygym.app.models.MyRecord;
import com.mygym.app.models.MyRecordsWrapper;
import com.mygym.app.models.Set;
import com.mygym.app.repository.MyRecordRepository;

/**
 * Service managing user workout records, set entries, historical queries, and progress tracking.
 */
@Service
public class MyRecordCrudService {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private final MyRecordRepository repository;

    public MyRecordCrudService(MyRecordRepository repository) {
        this.repository = repository;
    }

    /**
     * Saves or appends set entries to an existing workout record for today's date.
     */
    public MyRecord createRecord(MyRecord records) {
        return repository.findByMemberIdAndTargetMuscleAndExerciseNameAndDate(
                records.getMemberId(), 
                records.getTargetMuscle(), 
                records.getExerciseName(), 
                LocalDate.now().format(formatter)
        ).map(olderRecords -> {
            List<Set> todaysSet = olderRecords.getSets();
            todaysSet.addAll(records.getSets());
            olderRecords.setSets(todaysSet);
            return repository.save(olderRecords);
        }).orElseGet(() -> repository.save(records));
    }

    public MyRecord getRecordById(String id) {
        return repository.findByMemberId(id)
                .orElseThrow(() -> new RuntimeException("Gym record not found with id: " + id));
    }

    public void deleteRecord(String memberId) {
        repository.deleteByMemberId(memberId);
    }

    /**
     * Retrieves the history for Gym Workout for upto 1 Month.
     */
    public MyRecordsWrapper getPreviousRecords(String memberId, String targetMuscle, String exerciseName) {
        List<MyRecord> allRecords = repository.findPreviousRecords(memberId, targetMuscle, exerciseName);
        String todayStr = LocalDate.now().format(formatter);
        
        LinkedHashSet<String> top3Dates = allRecords.stream()
                .map(MyRecord::getDate)
                .filter(dateStr -> !todayStr.equals(dateStr))
                .distinct()
                .sorted(Comparator.comparing(dateStr -> LocalDate.parse(dateStr, formatter), Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toCollection(LinkedHashSet::new));
                
        allRecords = allRecords.stream()
                .filter(record -> top3Dates.contains(record.getDate()))
                .sorted(Comparator.comparing(record -> LocalDate.parse(record.getDate(), formatter), Comparator.reverseOrder()))
                .collect(Collectors.toList());
                
        return new MyRecordsWrapper(allRecords);
    }

    public MyRecordsWrapper getProgressRecords(String memberId) {
        List<MyRecord> allRecords = repository.findAllByMemberId(memberId);
        return (allRecords == null || allRecords.isEmpty()) 
                ? new MyRecordsWrapper() 
                : new MyRecordsWrapper(allRecords);
    }
}