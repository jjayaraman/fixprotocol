package com.jai.fix.server;

import com.jai.fix.server.config.FIXServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.*;
import quickfix.*;

@SpringBootApplication
@Slf4j
public class FIXServerApplication {

    @Autowired
    private Acceptor socketAcceptor;

    public static void main(String[] args) {
        SpringApplication.run(FIXServerApplication.class, args);
    }

    @EventListener
    public void contextRefreshedEvent(ContextRefreshedEvent event) {
        log.info("ContextRefreshedEvent server ...");
        try {
            socketAcceptor.start();
//            log.debug("isLoggedOn : " +socketAcceptor.isLoggedOn());
//            log.debug("getSessions : " +socketAcceptor.getSessions());
//            for(SessionID sessionID :  socketAcceptor.getSessions()) {
//                System.out.println("sessionID :: " +sessionID);
//                Session session = Session.lookupSession(sessionID);
//                session.logon();
//            }

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
        socketAcceptor.stop();
    }
}
