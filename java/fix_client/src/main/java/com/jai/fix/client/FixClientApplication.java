package com.jai.fix.client;

import com.jai.fix.client.config.FIXClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import quickfix.*;

@SpringBootApplication
@Slf4j
public class FixClientApplication {

    @Autowired
    private SocketInitiator socketInitiator;

    public static void main(String[] args) {
        SpringApplication.run(FixClientApplication.class, args);
    }

    @EventListener
    public void contextRefreshedEvent(ContextRefreshedEvent event) {
        log.info("ContextRefreshedEvent called");
        try {
            socketInitiator.start();

//            log.debug(" socketInitiator.isLoggedOn() : 1 " + socketInitiator.isLoggedOn());
//            log.debug(" socketInitiator.getSessions() : " + socketInitiator.getSessions());
//            for (SessionID sessionID : socketInitiator.getSessions()) {
//                log.debug("sessionID :: " + sessionID);
//
//                Session session = Session.lookupSession(sessionID);
//                session.logon();
//                log.debug(" socketInitiator.isLoggedOn() 2 : " + socketInitiator.isLoggedOn());
//                Message message = new Message();
//                session.send(message);
//            }

        } catch (ConfigError ce) {
            System.out.println("Error..... " + ce);
            log.error(ce.getMessage());
        }
    }

    @EventListener
    public void contextClosedEvent(ContextClosedEvent event) {
        log.info("ContextRefreshedEvent called");
        socketInitiator.stop();
    }
}
