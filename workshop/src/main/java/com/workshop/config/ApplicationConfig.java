package com.workshop.config;

import com.workshop.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

/*
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
*/

}
