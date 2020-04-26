package com.jai.fix.client.service;

import com.jai.fix.client.FixClientApplication;
import com.jai.fix.client.config.FIXClientConfig;
import com.jai.fix.client.quickfix.ClientApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quickfix.*;
import quickfix.field.*;
import quickfix.fix44.OrderCancelRequest;


@Service
@Slf4j
public class ClientService {


    public boolean send() {

        boolean success = false;

        OrderCancelRequest message = new OrderCancelRequest(
                new OrigClOrdID("123"),
                new ClOrdID("321"),
                new Side(Side.BUY),
                new TransactTime());

        message.set(new Text("Cancel My Order!"));

        try {
            success = Session.sendToTarget(message, "BANZAI", "EXEC");
        } catch (SessionNotFound e) {
            log.error("SessionNotFound : " +e.getMessage());
        }
        return success;

    }
}
