package com.jai.fix.server.config;

import com.jai.fix.server.quickfix.ServerApplicationImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Configuration
@Slf4j
public class FIXServerConfig {

    @Autowired
    private ServerApplicationImpl application;

    private String qfSettings = "/Users/jay/git_jay/fixprotocol/java/fix_server/src/main/resources/settings.cfg";

    @Bean
    public void server() {
        SessionSettings settings = null;
        Acceptor acceptor = null;
        Initiator initiator = null;
        try {

            settings = new SessionSettings(new FileInputStream(qfSettings));
            MessageStoreFactory storeFactory = new FileStoreFactory(settings);
            LogFactory logFactory = new FileLogFactory(settings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            //acceptor = new SocketAcceptor(application, storeFactory, settings, logFactory, messageFactory);
            // acceptor.start();
            initiator = new SocketInitiator(application, storeFactory, settings, logFactory, messageFactory);
            initiator.start();

            log.debug("Server listening... ");
            while (true) {
                // TODO: logics

            }
        } catch (ConfigError ce) {
            ce.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            //acceptor.stop();
            initiator.stop();
            log.debug("Server stopped... ");
        }
    }

}
