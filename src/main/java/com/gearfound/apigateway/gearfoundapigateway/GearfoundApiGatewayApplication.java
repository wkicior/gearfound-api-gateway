package com.gearfound.apigateway.gearfoundapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class GearfoundApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GearfoundApiGatewayApplication.class, args);
	}
}
