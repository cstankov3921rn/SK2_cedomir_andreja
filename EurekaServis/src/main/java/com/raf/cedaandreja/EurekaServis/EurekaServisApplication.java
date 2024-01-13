package com.raf.cedaandreja.EurekaServis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class EurekaServisApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServisApplication.class, args);
	}

}
