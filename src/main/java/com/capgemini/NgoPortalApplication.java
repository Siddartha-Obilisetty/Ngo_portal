package com.capgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.capgemini.model.DonationBox;

@SpringBootApplication
public class NgoPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NgoPortalApplication.class, args);
	}
}