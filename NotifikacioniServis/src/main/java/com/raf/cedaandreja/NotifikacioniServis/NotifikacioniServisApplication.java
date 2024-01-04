package com.raf.cedaandreja.NotifikacioniServis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.raf.cedaandreja.NotifikacioniServis")
public class NotifikacioniServisApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifikacioniServisApplication.class, args);
	}

}
