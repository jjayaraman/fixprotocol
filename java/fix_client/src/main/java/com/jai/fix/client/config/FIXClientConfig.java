package com.jai.fix.client.config;

import com.jai.fix.client.quickfix.ClientApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.*;

@Configuration
@Slf4j
public class FIXClientConfig {

    @Autowired
    private ClientApplication application;

    @Value(value = "")
    private final String configFile = "/Users/jay/git_jay/fixprotocol/java/fix_server/src/main/resources/settings.cfg";

    @Bean
    public void fixClientConfig() {

        SocketInitiator initiator = null;

        try {

            MessageStoreFactory messageStoreFactory = new MemoryStoreFactory();
            SessionSettings sessionSettings = new SessionSettings(configFile);

            MessageFactory logFactory = null;

            initiator = new SocketInitiator(application, messageStoreFactory, sessionSettings, logFactory);
            initiator.start();

            log.debug("Initiator started...");
            while (true) {
                // TODO: logics


            }


        } catch (ConfigError ce) {
            ce.printStackTrace();
        } finally {
            initiator.stop();
            log.debug("Initiator stopped...");
        }
    }
}
