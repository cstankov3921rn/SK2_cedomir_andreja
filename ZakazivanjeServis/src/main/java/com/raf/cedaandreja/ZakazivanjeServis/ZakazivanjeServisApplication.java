package com.raf.cedaandreja.ZakazivanjeServis;

import com.raf.cedaandreja.ZakazivanjeServis.repository.RezervacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ZakazivanjeServisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZakazivanjeServisApplication.class, args);
	}

}
