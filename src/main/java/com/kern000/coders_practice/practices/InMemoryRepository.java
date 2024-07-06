package com.kern000.coders_practice.practices;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.PostConstruct;

public class InMemoryRepository {


    private List<Practice> practices = new ArrayList<>();

    List<Practice> findAll(){
        return practices;
    }

    Optional<Practice> findById(Integer id){
        return practices.stream()   //super combo :) love this method
                        .filter(practice -> practice.getId() == id) //using the getter method
                        .findFirst();                        // .get(); //returns the object returned by findFirst, otherwise throws exception
    }
    // using optional to prevent null exception being thrown
    // returns 'optional' object that may or may not contain any value;

    void createOne(Practice practice){
        practices.add(practice);
    }

    void updateOneById(Integer id, Practice replacementPractice){
        Optional<Practice> existingPractice = findById(id);
        if(existingPractice.isPresent()){
            practices.set(practices.indexOf(existingPractice.get()),replacementPractice);
        }
    }

    void delete(Integer id){
        practices.removeIf(practice -> practice.getId().equals(id));
    }

    @PostConstruct
    private void init(){

        ArrayList <String> topicsCovered1 = new ArrayList<String>();
        topicsCovered1.add("Java Spring Boot setup");
        topicsCovered1.add("Application configuration for Spring Boot");
        topicsCovered1.add("Logging and levels");
        LanguageType languageCovered1 = LanguageType.JAVA;	

        ArrayList <String> topicsCovered2 = new ArrayList<String>();
        topicsCovered2.add("Bean, what a bean is"); //beans are objects managed in 
        topicsCovered2.add("RestController annotation");
        topicsCovered2.add("post construct - execute after dependency Injection by Spring IoC");
        LanguageType languageCovered2 = LanguageType.JAVA;

        practices.add(new Practice(1, "First Practice", LocalDateTime.now(), LocalDateTime.now().plusHours(2),topicsCovered1, languageCovered1));
        practices.add(new Practice(2, "Second Practice", LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4),topicsCovered2, languageCovered2));
    }
}

