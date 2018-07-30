package org.ifly.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("org.ifly.springboot")
@EnableAsync
public class App 
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
