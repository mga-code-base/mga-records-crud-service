package com.mygym.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mygym.app.models.MyRecord;

@Repository
public interface MyRecordRepository extends MongoRepository<MyRecord, String> {

	Optional<MyRecord> findByMemberId(String memberId);
	
	void deleteByMemberId(String memberId);
	
	@Query("{ 'memberId': ?0, 'targetMuscle': ?1, 'exerciseName': ?2 }")
    List<MyRecord> findPreviousRecords(String memberId, String targetMuscle, String exerciseName);

	List<MyRecord> findAllByMemberId(String memberId);
	
	Optional<MyRecord> findByMemberIdAndTargetMuscleAndExerciseNameAndDate(
		    String memberId, 
		    String targetMuscle, 
		    String exerciseName, 
		    String date
		);
}
