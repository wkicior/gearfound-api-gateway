package com.gearfound.apigateway.gearfoundapigateway.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/oauth/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api/items/lost-items**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/items/lost-items**").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers(HttpMethod.PUT, "/api/items/lost-items**").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers(HttpMethod.GET, "/api/items/lost-items?registrantId=**").access("hasRole('ADMIN') or hasRole('USER')")

                .antMatchers(HttpMethod.GET, "/api/items/found-items**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/items/found-items**").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers(HttpMethod.GET, "/api/items/found-items?registrantId**").access("hasRole('ADMIN') or hasRole('USER')")
                .and()
            .exceptionHandling()
            .accessDeniedHandler(new OAuth2AccessDeniedHandler());

        CorsConfigurationSource source = corsConfigurationSource();
        http.addFilterBefore(new CorsFilter(source), ChannelProcessingFilter.class);
    }

    private CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        //more config
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
