package com.jai.fix.server.config;

import com.jai.fix.server.quickfix.ServerApplicationImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import quickfix.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Configuration
@Slf4j
public class FIXServerConfig {

    @Autowired
    private ServerApplicationImpl application;

    private String configFile = ResourceUtils.CLASSPATH_URL_PREFIX + "settings.cfg";

    @Bean
    public Acceptor getSocketAcceptor() {
        Acceptor acceptor = null;
        try {
            SessionSettings settings = new SessionSettings(ResourceUtils.getFile(configFile).getAbsolutePath());
            MessageStoreFactory storeFactory = new FileStoreFactory(settings);
            LogFactory logFactory = new FileLogFactory(settings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            acceptor = new SocketAcceptor(application, storeFactory, settings, logFactory, messageFactory);
        } catch (ConfigError ce) {
            log.error(ce.getMessage());
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
        return acceptor;
    }

}
