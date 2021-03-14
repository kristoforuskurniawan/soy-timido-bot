package com.soy.timido.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Bot {
	
	private static Logger log = LoggerFactory.getLogger(Bot.class);

	public static void main(String[] args) {
		log.info("Despertando al bot...");
		SpringApplication.run(Bot.class, args);
	}
	
}
