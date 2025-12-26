package kz.bmstu.kritinina.controller;

import kz.bmstu.kritinina.dto.LoginRequest;
import kz.bmstu.kritinina.dto.TokenResponse;
import kz.bmstu.kritinina.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        try {
            TokenResponse token = authService.login(request);
            return ResponseEntity.ok(token);
        } catch (HttpClientErrorException.Unauthorized e) {
            // 401 от Keycloak (неверный логин/пароль)
            return ResponseEntity.status(401).body("Invalid credentials");
        } catch (HttpClientErrorException.BadRequest e) {
            // 400 от Keycloak (неверный grant_type, client_id и т.д.)
            return ResponseEntity.status(400).body("Bad request: " + e.getResponseBodyAsString());
        } catch (RestClientException e) {
            return ResponseEntity.status(503).body("Keycloak unavailable: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Authentication failed: " + e.getMessage());
        }
    }
}