package com.invest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringRunner.class, args);
    }

}
