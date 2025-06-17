package com.unipath.ms_unipath.rest;

import com.unipath.ms_unipath.rest.resources.DTOs.AnswerChasideDetailDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
