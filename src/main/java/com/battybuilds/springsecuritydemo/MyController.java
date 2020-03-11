package com.battybuilds.springsecuritydemo;

import com.battybuilds.springsecuritydemo.config.MyUserDetailsService;
import com.battybuilds.springsecuritydemo.models.AuthenticationRequest;
import com.battybuilds.springsecuritydemo.models.AuthenticationResponse;
import com.battybuilds.springsecuritydemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtTokenUtil;

    @PostMapping("/request")
    public ResponseEntity receiveRequest(@RequestBody String body) {
        System.out.println("%%%%%%%%%%%%%%%% body: " + body);
        return ResponseEntity.ok("\nhi");
    }

    @GetMapping("/")
    public String basicStuff() {
        return "<h1>Anybody can see this</h1>";
    }

    @GetMapping("/check")
    public String getInfo() {
        return "<h1>Got it!</h1>";
    }

    @GetMapping("/manage")
    public String getManagementStuff() {
        return "<h1>You are Managing!</h1>";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password" + e);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
