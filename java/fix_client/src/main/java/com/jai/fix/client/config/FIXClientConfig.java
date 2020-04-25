package com.jai.fix.client.config;

import com.jai.fix.client.quickfix.ClientApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import quickfix.*;

import java.io.FileNotFoundException;

@Configuration
@Slf4j
public class FIXClientConfig {

    @Autowired
    private ClientApplication application;

    private String configFile = ResourceUtils.CLASSPATH_URL_PREFIX + "settings.cfg";

    @Bean
    public void client() {

        SocketInitiator initiator = null;

        try {
            SessionSettings sessionSettings = new SessionSettings(ResourceUtils.getFile(configFile).getAbsolutePath());
            MessageStoreFactory messageStoreFactory = new FileStoreFactory(sessionSettings);
            LogFactory logFactory = new FileLogFactory(sessionSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();

            initiator = new SocketInitiator(application, messageStoreFactory, sessionSettings, logFactory, messageFactory);
            initiator.start();


            log.debug("Initiator started...");
            while (true) {
                // TODO: logics


            }

        } catch (ConfigError ce) {
            ce.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            initiator.stop();
            log.debug("Initiator stopped...");
        }
    }
}
