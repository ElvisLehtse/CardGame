package com.Elvis.HiLoCardGame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfig {

    @Bean
    public Random getRandom() {
        return new Random();
    }
}
