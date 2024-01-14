package com.carloscorp.mybotboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Slf4j
@Configuration
public class BotConfig {

    @Bean
    public Robot getBot() throws Exception {
        try {
            log.info("Creating the bot");
            Robot r =  new Robot();
            r.setAutoDelay(50);
            log.info("Bot created successfully");
            return r;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Cannot create the bot");
        }
    }
}
