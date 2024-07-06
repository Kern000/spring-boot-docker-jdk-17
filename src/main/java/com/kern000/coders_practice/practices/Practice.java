package com.kern000.coders_practice.practices;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Objects;


import org.springframework.data.annotation.Id;
// Note the annotation Id from using Spring Data, in the interface
import org.springframework.data.annotation.Version;

import com.kern000.coders_practice.practices.LanguageType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

// Or can use a "record" to define these, including the getters, setters, equals, hashcode, and toString Methods
// Record class are immutable (cannot change);

public class Practice {

    //fields tt this class needs to know about
    @Positive
    @Id
    private Integer id;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9._, ]{1,50}$")
    private String title;
    private LocalDateTime timeStarted;
    private LocalDateTime timeEnded;
    private ArrayList<@Pattern(regexp = "^[a-zA-Z0-9._, ]{1,50}$") String> topicsCovered = new ArrayList<String>();
    
    private LanguageType codingLanguage; //enum constants
    // private Location location;
    @Version //Specific to Spring Data JDBC
    Integer version; // track whether new row or existing row

    public Practice(
                    Integer id, 
                    String title, 
                    LocalDateTime timeStarted, 
                    LocalDateTime timeEnded, 
                    ArrayList<String> topicsCovered, 
                    LanguageType codingLanguage){
        this.id = id;
        this.title = title;
        this.timeStarted = timeStarted;
        this.timeEnded = timeEnded;
        this.topicsCovered = topicsCovered;
        this.codingLanguage = codingLanguage;

        
        if(timeStarted.isAfter(timeEnded)){
            throw new IllegalArgumentException("Time started cannot be after time ended.");
        }

    }

    //getters
    public Duration geDuration(){
        return Duration.between(timeStarted, timeEnded);
    }

    public Integer getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public LocalDateTime getStartTime(){
        return timeStarted;
    }

    public LocalDateTime getEndTime(){
        return timeEnded;
    }

    public ArrayList<String> getTopicsCovered(){
        return topicsCovered;
    }

    public Duration getDuration(){
        return Duration.between(timeStarted, timeEnded);
    }

    public LanguageType getLanguageType(){
        return codingLanguage;
    }

    // setters
    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setStartTime(LocalDateTime timeStarted){
        this.timeStarted = timeStarted;
    }

    public void setEndTime(LocalDateTime timeEnded){
        this.timeEnded = timeEnded;
    }

    public void setTopicsCovered(ArrayList<String> topicsCovered){
        this.topicsCovered = topicsCovered;
    }

    //objects that are equal (or the same object) must consistently return the same hash code;
    //diff objects do not need return diff hash codes, though distinct hash codes improves performance of hash tables;
    //hashcode method return hash code of non-null argument and 0 for null argument;
    // hash code (of the object) can be used as keys in the hashmap to ensure objects properly distributed within map;

    @Override
    public int hashCode(){
        return Objects.hash(id, title, timeStarted, timeEnded, topicsCovered);
    }

    @Override
    public String toString(){
        return  "Practice {" + "id:" + id + 
                ", title:" + title + 
                ", timeStarted:" + timeStarted +
                ", timeEnded:" + timeEnded +
                ", topicsCovered:" + topicsCovered.toString() +
                ", codingLanguage:" + codingLanguage.toString() +
                "}";
    }


}