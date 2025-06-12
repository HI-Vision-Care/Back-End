package com.hivision.hivision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class HiVisionApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiVisionApplication.class, args);
    }

}
