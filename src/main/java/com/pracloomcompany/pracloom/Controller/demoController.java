package com.pracloomcompany.pracloom.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class demoController {
    @GetMapping()
    @PreAuthorize("hasRole('ROLE_GENERAL')")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }
}
