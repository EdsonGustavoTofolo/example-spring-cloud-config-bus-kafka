package com.example.configclient;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValueRestController {

    private final Environment environment;

    public ValueRestController(Environment environment) {
        this.environment = environment;
    }

    @EventListener({ApplicationReadyEvent.class, RefreshScopeRefreshedEvent.class})
    public void onEvent() {
        System.out.println(" New value = " + this.readValue());
    }

    @GetMapping("/value")
    public String read() {
        return this.readValue();
    }

    private String readValue() {
        return this.environment.getProperty("token.secret");
    }
}
