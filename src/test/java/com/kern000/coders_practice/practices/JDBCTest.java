// package com.kern000.coders_practice.practices;

// import java.time.LocalDateTime;
// import java.util.ArrayList;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
// import org.springframework.context.annotation.Import;

// @JdbcTest
// @Import(JDBCPracticeRepository.class)

// class JDBCTest {

//     @Autowired
//     JDBCPracticeRepository repository;

//     @BeforeEach
//     void setup(){
//         ArrayList<String> topicsCovered = new ArrayList<>();
//         topicsCovered.add("REST Client");
//         topicsCovered.add("Testing");
       
//         repository.createOne(new Practice(1, "test practice 1", LocalDateTime.now(), LocalDateTime.now().plusHours(2), topicsCovered, LanguageType.JAVA));
//         repository.createOne(new Practice(2, "test practice 2", LocalDateTime.now(), LocalDateTime.now().plusHours(2), topicsCovered, LanguageType.JAVA));
//     }

//     @Test
//     void JdbcTestFindAll() {
//         assertEquals(2, repository.findAll().size());
//     }

//     @Test
//     void JdbcTestFindingSpecificPractice(){
//         var practice = repository.findById(2).get();
//         String testComparison = "[REST Client, Testing]";
//         assertEquals(testComparison, practice.getTopicsCovered().toString());
//         assertEquals("JAVA", practice.getLanguageType().toString());
//     }

//     @Test
//     void JdbcTestShouldCreateNewPractice(){

//         ArrayList<String> newTopicsCovered = new ArrayList<>();
//         newTopicsCovered.add("Revising h2 and application properties");
//         repository.createOne(new Practice(3, "test practice 3", LocalDateTime.now(), LocalDateTime.now().plusHours(2), newTopicsCovered, LanguageType.JAVA));

//         assertEquals(3, repository.findAll().size());
//     }

//     // @Test
//     // void JdbcTestShouldUpdatePractice(){
//     //     ArrayList<String> newTopicsCovered = new ArrayList<>();
//     //     newTopicsCovered.add("Revising h2 and application properties");
//     //     repository.updateOneById(1, new Practice(1, "test practice 1", LocalDateTime.now(), LocalDateTime.now().plusHours(2), newTopicsCovered, LanguageType.JAVA));
//     //     var foundPractice = repository.findById(1).get();
//     //     assertEquals("JAVA", foundPractice.getLanguageType().toString());
//     //     assertEquals("[Revising h2 and application properties]", foundPractice.getTopicsCovered().toString());
//     // }

//     @Test
//     void JdbcTestShouldDeletePractice(){
//         repository.delete(3);
//         assertEquals(2, repository.findAll().size());
//     }


    
// }

