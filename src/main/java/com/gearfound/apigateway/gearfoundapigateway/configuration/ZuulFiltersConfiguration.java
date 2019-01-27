package com.gearfound.apigateway.gearfoundapigateway.configuration;

import com.gearfound.apigateway.gearfoundapigateway.auth.AddUserIdFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFiltersConfiguration {
    @Bean
    public AddUserIdFilter sampleFilter() {
        return new AddUserIdFilter();
    }
}

