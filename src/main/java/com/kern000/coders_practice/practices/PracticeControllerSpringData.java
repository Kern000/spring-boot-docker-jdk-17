package com.kern000.coders_practice.practices;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController //class that response to req
@RequestMapping("/api/springdata") // every api here have this base API
public class PracticeControllerSpringData {
    
    private final SpringDataPracticeRepo practiceRepository;

    public PracticeControllerSpringData(SpringDataPracticeRepo practiceRepository){
        this.practiceRepository = practiceRepository;
    }

    @GetMapping()
    List<Practice> findAllPractices(){


        return practiceRepository.findAll();
    }
    
    @GetMapping("/{id}") //{id} is placeholder
    Practice findById(@Valid @PathVariable Integer id) {
        Optional<Practice> practice = practiceRepository.findById(id);
        if(practice.isEmpty()){
            throw new PracticeNotFoundException();
        }
        return practice.get(); //can use .get() because practice is a list;
    }

    @GetMapping("/find")
    List<Practice> searchByTitle(@RequestParam("title") String titleString){
        return practiceRepository.findByTitle(titleString);
    }
    
    //post
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void createOne(@Valid @RequestBody Practice practice){
        System.out.println("Managed to come in here");

        practiceRepository.save(practice);
    }
    
    //put
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateOne(@Valid @PathVariable Integer id, @RequestBody Practice practice){
        Practice existingPractice = practiceRepository.findById(id).get();
        existingPractice.setTitle(practice.getTitle());
        existingPractice.setStartTime(practice.getStartTime());
        existingPractice.setEndTime(practice.getEndTime());
        existingPractice.setTopicsCovered(practice.getTopicsCovered());
        practiceRepository.save(existingPractice);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOne(@Valid @PathVariable Integer id){
        practiceRepository.delete(practiceRepository.findById(id).get());
    }
    
}
