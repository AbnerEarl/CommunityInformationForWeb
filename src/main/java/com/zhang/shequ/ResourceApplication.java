package com.zhang.shequ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot方式启动类
 */
@SpringBootApplication
public class ResourceApplication {

    private final static Logger logger = LoggerFactory.getLogger(ResourceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
        logger.info("ResourceApplication is success!");
    }
    
}
