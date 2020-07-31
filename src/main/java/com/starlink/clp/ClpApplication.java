package com.starlink.clp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ClpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClpApplication.class, args);
    }

}
