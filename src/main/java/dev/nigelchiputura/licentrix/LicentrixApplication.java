package dev.nigelchiputura.licentrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LicentrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(LicentrixApplication.class, args);
    }
}
