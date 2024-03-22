package com.example.sbboard2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sbboard2Application {

    public static void main(String[] args) {
        SpringApplication.run(Sbboard2Application.class, args);
    }

}
