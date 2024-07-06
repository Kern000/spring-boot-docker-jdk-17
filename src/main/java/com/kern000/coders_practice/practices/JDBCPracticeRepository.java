package com.kern000.coders_practice.practices;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
// import jakarta.annotation.PostConstruct;

@Repository //central hub for managing all data access logic //This makes this a bean and joins the Spring IoC container
public class JDBCPracticeRepository {

    private static final Logger log = LoggerFactory.getLogger(JDBCPracticeRepository.class);
    public final JdbcClient jdbcClient;

    public JDBCPracticeRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Practice> findAll(){
        return jdbcClient.sql("SELECT * FROM Practice")
                            .query(Practice.class) //may the result to a Practice object
                            .list(); //return list of things
    }

    public Optional<Practice> findById(Integer id){
        return jdbcClient.sql("SELECT * FROM Practice WHERE id = :id")
                            .param("id", id)
                            .query(Practice.class)
                            .optional();
    }

    public void createOne(Practice practice){
        // is creating variable without specifying type
        var updated = jdbcClient.sql("INSERT INTO Practice(id,title,time_started,time_ended,topics_covered,coding_language) values(?,?,?,?,?,?)")
                        .params(List.of(    practice.getId(),
                                            practice.getTitle(),
                                            practice.getStartTime(),
                                            practice.getEndTime(),
                                            practice.getTopicsCovered().toString(),
                                            practice.getLanguageType().toString()
                                        )
                                )
                        .update(); //returns how many rows affected so check if it is 1
        Assert.state(updated == 1, "Failed to create new practice " + practice.getTitle());
    }

    public void updateOneById(Integer id, Practice practice){
        var updated = jdbcClient.sql("update Practice set title = ?, time_started = ?, time_ended = ?, topics_covered = ?, coding_language = ? WHERE id =?")
                                    .params(List.of(    practice.getTitle(), 
                                                        practice.getStartTime(), 
                                                        practice.getEndTime(), 
                                                        practice.getTopicsCovered().toString(), 
                                                        practice.getLanguageType().toString(),
                                                        id))
                                    .update(); // jbdc methods
        Assert.state(updated == 1, "Failed to update practice " + practice.getTitle());
    }

    public void delete(Integer id){
        var updated = jdbcClient.sql("delete from Practice where id = :id")
                                    .param("id", id)
                                    .update();
        Assert.state(updated == 1, "Failed to delete practice id " + id);
    }


}

