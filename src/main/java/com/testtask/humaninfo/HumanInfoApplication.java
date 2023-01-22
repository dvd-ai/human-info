package com.testtask.humaninfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HumanInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanInfoApplication.class, args);
    }

    //todo

    // tests:
    //DONE 1. controller level [success (dto fields corresponding), no id found [dto fields to err msg]]
    //DONE 2. service level -found user [with age], -user not found: [throws exception with message]]
    //DONE 3. repository level -found user / not found (optional empty or not)
    //4. integration test full app launch: testing the endpoint without any mocks

}
