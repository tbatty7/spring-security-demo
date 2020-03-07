package com.battybuilds.springsecuritydemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @PostMapping("/request")
    public ResponseEntity receiveRequest(@RequestBody String body) {
        System.out.println("%%%%%%%%%%%%%%%% body: " + body);
        return ResponseEntity.ok("\nhi");
    }

    @GetMapping("/check")
    public String getInfo() {
        return "<h1>Got it!</h1>";
    }

    @GetMapping("/manage")
    public String getManagementStuff() {
        return "<h1>You are Managing!</h1>";
    }
}
