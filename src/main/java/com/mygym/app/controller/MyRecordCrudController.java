package com.mygym.app.controller;

import static com.mygym.app.util.RecordsCrudConstants.ID_PLACEHOLDER_ENDPOINT;
import static com.mygym.app.util.RecordsCrudConstants.PREVIOUS_RECORDS_ENDPOINT;
import static com.mygym.app.util.RecordsCrudConstants.PROGRESS_RECORDS_ENDPOINT;
import static com.mygym.app.util.RecordsCrudConstants.RECORDS_CRUD_REST_BASE;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mygym.app.models.MyRecord;
import com.mygym.app.models.MyRecordsWrapper;
import com.mygym.app.service.MyRecordCrudService;

@RestController
@RequestMapping(RECORDS_CRUD_REST_BASE)
public class MyRecordCrudController {

    private final MyRecordCrudService service;

    public MyRecordCrudController(MyRecordCrudService service) {
        this.service = service;
    }

    @PostMapping
    public MyRecord create(@RequestBody MyRecord record) {
        return service.createRecord(record);
    }

    @GetMapping(ID_PLACEHOLDER_ENDPOINT)
    public MyRecord readById(@PathVariable String id) {
        return service.getRecordById(id);
    }

    @DeleteMapping(ID_PLACEHOLDER_ENDPOINT)
    public String delete(@PathVariable String id) {
        service.deleteRecord(id);
        return "Gym record deleted successfully with id: " + id;
    }
    
    @GetMapping(PREVIOUS_RECORDS_ENDPOINT)
    public MyRecordsWrapper getPreviousRecords(@RequestParam("memberId") String memberId, 
    		@RequestParam("targetMuscle") String targetMuscle, 
    		@RequestParam("exerciseName") String exerciseName) {
    	return service.getPreviousRecords(memberId, targetMuscle, exerciseName);
    }
    
    @GetMapping(PROGRESS_RECORDS_ENDPOINT)
    public MyRecordsWrapper getPreviousRecords(@RequestParam("memberId") String memberId) {
    	return service.getProgressRecords(memberId);
    }
}
