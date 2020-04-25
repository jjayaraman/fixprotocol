package com.jai.fix.server;

import com.jai.fix.server.config.FIXServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.*;
import quickfix.ConfigError;

@SpringBootApplication
@Slf4j
public class FIXServerApplication {

	@Autowired
	private FIXServerConfig fixServerConfig;

	public static void main(String[] args) {
		SpringApplication.run(FIXServerApplication.class, args);
	}


	@EventListener
	public void contextRefreshedEvent(ContextRefreshedEvent event) {
		log.info("ContextRefreshedEvent ");
		try {
			fixServerConfig.getSocketAcceptor().start();
		} catch (ConfigError ce) {
			log.error(ce.getMessage());
		}
	}

	@EventListener
	public void contextStartedEvent(ContextStartedEvent event) {
		log.info("ContextStartedEvent ");
	}

	@EventListener
	public void contextStoppedEvent(ContextStoppedEvent event) {
		log.info("ContextStoppedEvent ");
	}

	@EventListener
	public void ContextClosedEvent(ContextClosedEvent event) {
		log.info("ContextClosedEvent ");
		fixServerConfig.getSocketAcceptor().stop();
	}
}
