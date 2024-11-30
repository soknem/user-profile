package com.kh.edu.cstad.khotixs.user_profile_service.test_controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/token")
    public String getToken(Authentication authentication) {
        // Check if the authentication is a valid OAuth2 token
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
            // You can access the token directly from the authentication object
            return jwtToken.getToken().getTokenValue(); // Return the access token value
        }
        return "No valid token found";
    }

    @GetMapping
    public ResponseEntity<String> getAllItems() {

        return ResponseEntity.ok("items");
    }

    @GetMapping("/{endpoint}")
    @PreAuthorize("hasAnyAuthority('file:read')")
    public ResponseEntity<String> getEndpoint(@PathVariable String endpoint) {

        return ResponseEntity.ok("GET request received at endpoint: " + endpoint);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('file:write')")
    public ResponseEntity<String> postEndpoint(
            @RequestBody String body) {

        return ResponseEntity.ok("POST request received at endpoint: "+ " with body: " + body);
    }

    @PutMapping("/{endpoint}")
    @PreAuthorize("hasAnyAuthority('file:update')")
    public ResponseEntity<String> putEndpoint(
            @PathVariable String endpoint,
            @RequestBody String body) {
        // Mock response for PUT, echoing back the PUT body
        return ResponseEntity.ok("PUT request received at endpoint: " + endpoint + " with body: " + body);
    }

    @PatchMapping("/{endpoint}")
    @PreAuthorize("hasAnyAuthority('file:update')")
    public ResponseEntity<String> patchEndpoint(
            @PathVariable String endpoint,
            @RequestBody String body) {
        // Mock response for PATCH, echoing back the PATCH body
        return ResponseEntity.ok("PATCH request received at endpoint: " + endpoint + " with body: " + body);
    }


    @DeleteMapping("/{endpoint}")
    @PreAuthorize("hasAnyAuthority('file:delete')")
    public ResponseEntity<String> deleteEndpoint(@PathVariable String endpoint) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("DELETE request received at endpoint: " + endpoint);
    }
}
