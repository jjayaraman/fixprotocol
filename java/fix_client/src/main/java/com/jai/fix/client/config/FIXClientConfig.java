package com.jai.fix.client.config;

import com.jai.fix.client.quickfix.ClientApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SocketInitiator getSocketInitiator() {

        SocketInitiator socketInitiator = null;

        try {
            SessionSettings sessionSettings = new SessionSettings(ResourceUtils.getFile(configFile).getAbsolutePath());
            MessageStoreFactory messageStoreFactory = new FileStoreFactory(sessionSettings);
            LogFactory logFactory = new FileLogFactory(sessionSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();

            socketInitiator = new SocketInitiator(application, messageStoreFactory, sessionSettings, logFactory, messageFactory);

        } catch (ConfigError ce) {
            log.error(ce.getMessage());
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
        return socketInitiator;
    }
}
