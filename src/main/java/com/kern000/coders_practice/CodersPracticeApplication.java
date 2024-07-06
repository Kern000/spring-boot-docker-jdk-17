//cont 1:08

package com.kern000.coders_practice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; //logger
import org.springframework.web.client.RestClient; //interface, not the actual logger
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.kern000.coders_practice.user.User;
import com.kern000.coders_practice.user.UserHTTPClient;


@SpringBootApplication
public class CodersPracticeApplication {

	private static final Logger log = LoggerFactory.getLogger(CodersPracticeApplication.class);

	public static void main(String[] args) {
			
			SpringApplication.run(CodersPracticeApplication.class, args);		

			// ConfigurableApplicationContext context = SpringApplication.run(CodersPracticeApplication.class, args);		
			// WelcomeMessage welcomeMessage = (WelcomeMessage) context.getBean("welcomeMessage"); //casting
			// System.out.println(welcomeMessage); // Need to use toString
			// after retrieving a bean, need to cast to its desired type;
			// log.info("Application started successfully");
			// log.trace("Main method accessed");			
	}

	// @Bean  //declares method tt returns object to be managed by Spring (IOC) inversion of control container //declare the output to be managed as beans by Spring IoC Container // dependency injection, dependencies of bean auto resolved and injected by Spring IoC Container, and dun need instantiate and config these components
	// CommandLineRunner runner(){ //command line runner indicate bean shld run when within Spring Application // params are at the runner ()
	// 	return (args) -> {
	// 		ArrayList <String> topicsCovered = new ArrayList<String>();
	// 		topicsCovered.add("Java Spring Boot setup");
	// 		topicsCovered.add("Application configuration for Spring Boot");
	// 		topicsCovered.add("Logging and levels");
	// 		LanguageType languageCovered = LanguageType.Java;	
	// 		Practice practice = new Practice(1, "First Practice", LocalDateTime.now(), LocalDateTime.now().plusHours(2),topicsCovered, languageCovered);
	// 		log.info("Practice: " + practice);
	// 	};
	// } //run when application start

	

	@Bean 	//boiler plate to tell Spring that this is the HTTP Client, we create a rest client using a static factory method
	UserHTTPClient userHTTPClient(){
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/"); //
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHTTPClient.class);
	}

	@Bean
	CommandLineRunner runner(UserHTTPClient restClient){
		return args -> {
			List<User> users = restClient.findAll();
			System.out.println(users);

			User user = restClient.findById(1);
			System.out.println(user);

			System.out.println("Completion Auto here");
		};
	}

	// manual method implemental w UserRestClient
	// @Bean
	// CommandLineRunner runner2(UserRestClient restClient){ //pass in an instance of UserRestClient as a variable name restClient - the instance is managed by Spring Container
	// 	return args -> {
	// 		List<User> users = restClient.findAll(); //calls the findAll() method of UserRestClient
	// 		System.out.println(users);

	// 		User user = restClient.findById(1);
	// 		System.out.println(user);

	// 		System.out.println("Completion Manual here");
	// 	};
	// }

}
