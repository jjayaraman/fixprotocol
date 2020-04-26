package com.jai.fix.client.controller;

import com.jai.fix.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/send")
    public ResponseEntity sendMessage() {
        return ResponseEntity.ok(clientService.send());
    }
}
