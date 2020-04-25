package com.jai.fix.client.quickfix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import quickfix.*;

@Component
@Slf4j
public class ClientApplication implements Application {

    @Override
    public void onCreate(SessionID sessionID) {
        log.info("onCreate sessionID : " +sessionID);
    }

    @Override
    public void onLogon(SessionID sessionID) {
        log.info("onLogon sessionID : " +sessionID);
    }

    @Override
    public void onLogout(SessionID sessionID) {
        log.info("onLogout sessionID : " +sessionID);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionID) {
        log.info("toAdmin message " + message + ", sessionID : " +sessionID);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        log.info("fromAdmin message " + message + ", sessionID : " +sessionID);
    }

    @Override
    public void toApp(Message message, SessionID sessionID) throws DoNotSend {
        log.info("toApp message " + message + ", sessionID : " +sessionID);
    }

    @Override
    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        log.info("fromApp message " + message + ", sessionID : " +sessionID);
    }
}
