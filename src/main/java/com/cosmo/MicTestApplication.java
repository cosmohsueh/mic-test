package com.cosmo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class MicTestApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MicTestApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MicTestApplication.class, args);

        for (String name : applicationContext.getBeanDefinitionNames()) {
            LOGGER.debug(name);
        }
    }
}
