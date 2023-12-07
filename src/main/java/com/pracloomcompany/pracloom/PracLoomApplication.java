package com.pracloomcompany.pracloom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories
@Slf4j
public class PracLoomApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PracLoomApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        log.info("\n========================================\n");
        log.info("  PracLoom Started Successfully!   ");
        log.info("\n========================================\n");
    }
}

