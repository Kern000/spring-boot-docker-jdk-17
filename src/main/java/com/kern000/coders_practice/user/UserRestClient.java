package com.kern000.coders_practice.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

// manual method;
@Component
public class UserRestClient {

    private final RestClient restClient;

    // constructor for an instance of UserRestClient
    public UserRestClient(RestClient.Builder builder){
        this.restClient = builder.baseUrl("https://jsonplaceholder.typicode.com")
            .build();        
    }

    public List<User> findAll(){
        return restClient.get()
                         .uri("/users")
                         .retrieve()   // initiate request
                         .body(new ParameterizedTypeReference<>(){}); //what to do w the response // parameterized type reference, get a list of things
                            //.body here, the new parameterized type reference is used to specify that the response body should be converted to what Java Type u will want to work with - in this case it is List of User;
    }

    public User findById(Integer id){
        return restClient.get()
                         .uri("/users/{id}", id)
                         .retrieve()
                         .body(User.class);
    }

}
