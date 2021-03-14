package com.soy.timido.bot.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.soy.timido.bot.listener.EventListener;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;

@Configuration
public class BotConfiguration {
	
	private Logger log = LoggerFactory.getLogger(BotConfiguration.class);
	
	@Value("${soy.timido.bot.token}")
	private String token;
	
	@Bean
	public <T extends Event> GatewayDiscordClient connectarBot(List<EventListener<T>> eventListeners) {
		GatewayDiscordClient client = DiscordClient.builder(token).build().login().block();
		
		for(EventListener<T> listener : eventListeners) {
	        client.on(listener.getEventType())
	          .flatMap(listener::execute)
	          .onErrorResume(listener::handleError)
	          .subscribe();
	    }
		
		log.info("¡El bot está connectado!");
		return client;
	}
}
