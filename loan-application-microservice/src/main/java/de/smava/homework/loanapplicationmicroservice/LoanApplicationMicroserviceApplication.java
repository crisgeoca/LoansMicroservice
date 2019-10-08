package de.smava.homework.loanapplicationmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LoanApplicationMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanApplicationMicroserviceApplication.class, args);
    }

}
