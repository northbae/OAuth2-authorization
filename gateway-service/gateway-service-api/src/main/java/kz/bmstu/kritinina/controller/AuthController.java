package kz.bmstu.kritinina.controller;

import kz.bmstu.kritinina.dto.LoginRequest;
import kz.bmstu.kritinina.dto.TokenResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1")
public interface AuthController {
    @PostMapping("/authorize")
    ResponseEntity<?> login(@RequestBody LoginRequest request);

    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<TokenResponse> getToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam(value = "scope", defaultValue = "openid") String scope
    );

    public ResponseEntity<String> callback(String code);
}