package com.usgov.ev.dms.controllers;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {
    // Just a place holder for now to indiacte other controller comes here
    @GetMapping("/")
    public ResponseEntity<Map<String, String>> check() {
        return new ResponseEntity<>(Map.of("status", "OK", "message","ev-data-mgmt-system successfully up and running !"), HttpStatus.OK);
    }
}
