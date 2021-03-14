package com.soy.timido.bot.listener;

import java.util.ArrayList;
import java.util.List;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public abstract class MessageListener {

    public Mono<Void> processCommand(Message eventMessage) {
    	
    	List<String> comandosConocidos = new ArrayList<String>();
    	comandosConocidos.add(".actividades");
    	comandosConocidos.add(".recordatorio");
    	comandosConocidos.add(".alarma");
    	
        return Mono.just(eventMessage)
          .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
          .filter(message -> message.getContent().equalsIgnoreCase("!todo"))
          .flatMap(Message::getChannel)
          .flatMap(channel -> channel.createMessage("Mis actividades de hoy:\n - programar un bot\n - Almorzrar\n - Jugar a algo"))
          .then();
    }
    
}