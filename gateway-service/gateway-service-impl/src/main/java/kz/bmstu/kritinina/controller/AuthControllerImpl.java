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
    public ResponseEntity<TokenResponse> login(LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Override
    public ResponseEntity<TokenResponse> getToken(String grantType, String username, String password, String clientId, String clientSecret, String scope
    ) {
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        return ResponseEntity.ok(authService.login(request));
    }

    @Override
    public ResponseEntity<String> callback(String code) {
        return ResponseEntity.ok("Callback received");
    }
}