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
	
	/*
	 * @Bean public DonationBox donationBox() { return new
	 * DonationBox("Sprint NGO","1234","xxxxxxxxxxxx2021",100000.0,0,0,0,0); }
	 */
}