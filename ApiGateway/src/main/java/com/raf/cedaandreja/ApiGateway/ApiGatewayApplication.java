package com.raf.cedaandreja.ApiGateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin(origins = "http://localhost:8000")
public class ApiGatewayApplication {



	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
