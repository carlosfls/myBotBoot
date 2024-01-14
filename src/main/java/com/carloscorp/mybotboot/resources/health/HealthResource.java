package com.carloscorp.mybotboot.resources.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/health")
@RestController
public class HealthResource {

    @GetMapping
    public ResponseEntity<String>getHealth(){
        return ResponseEntity.ok("Application Running");
    }
}
