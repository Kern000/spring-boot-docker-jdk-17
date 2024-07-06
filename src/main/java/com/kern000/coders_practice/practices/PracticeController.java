package com.kern000.coders_practice.practices;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;



@RestController //class that response to req
@RequestMapping("/api/practices") // every api here have this base API
public class PracticeController {

    // Don't initialize new instance of repository every time a req comes into the controller;
    // so don't do public PracticeController(){
        // this.PracticeRepository = new PracticeRepository();
    // }

    // Dependency injection, we are going to ask Spring for it

    // @Autowired //not recommended - mock testing problematic - Autowire means Spring auto inject a dependency from the beans in its container // also create tight coupling betw classes and spring;
    private final JDBCPracticeRepository practiceRepository;

    public PracticeController(JDBCPracticeRepository practiceRepository){
        this.practiceRepository = practiceRepository;
    }

    @GetMapping()
    List<Practice> findAllPractices(){
        return practiceRepository.findAll();
    }

    // @GetMapping("/{id}") //{id} is placeholder
    // Practice findById(@PathVariable Integer id) {
    //     return practiceRepository.findById(id);
    // }
    
    @GetMapping("/{id}") //{id} is placeholder
    Practice findById(@Valid @PathVariable Integer id) {
        Optional<Practice> practice = practiceRepository.findById(id);
        if(practice.isEmpty()){
            throw new PracticeNotFoundException();
        }
        return practice.get(); //can use .get() because practice is a list;
    }

    //post
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    void createOne(@Valid @RequestBody Practice practice){
        practiceRepository.createOne(practice);
    }
    
    //put
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateOne(@Valid @PathVariable Integer id, @RequestBody Practice practice){
        practiceRepository.updateOneById(id, practice);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOne(@Valid @PathVariable Integer id){
        practiceRepository.delete(id);
    }
    
}
