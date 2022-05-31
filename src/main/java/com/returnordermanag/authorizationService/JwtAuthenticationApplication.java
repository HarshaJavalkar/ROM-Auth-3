package com.returnordermanag.authorizationService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.returnordermanag.authorizationService.Repository.UserRepository;
import com.returnordermanag.authorizationService.model.MyUser;
 

@SpringBootApplication
public class JwtAuthenticationApplication {
	
	@Autowired
    private UserRepository repository;

    @PostConstruct
    public void initUsers() {
        List<MyUser> users = Stream.of(
               new MyUser("101","user1","pwd1"),
               new MyUser("102","user2","pwd2"),
               new MyUser("103","user3","pwd3"),
               new MyUser("104","Joe","joe" ),
               new MyUser("105","Harsha", "Harsha"),
               new MyUser("106","Javalkar","Javalkar"),
               new MyUser("107","Ben","Ben")
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationApplication.class, args);
	}

}
