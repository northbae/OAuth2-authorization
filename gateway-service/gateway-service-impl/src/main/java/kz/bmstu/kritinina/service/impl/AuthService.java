package kz.bmstu.kritinina.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@Service
@Slf4j
public class AuthService {

    private final WebClient webClient;
    private final String tokenUrl;
    private final String clientId;
    private final String clientSecret;

    public AuthService(
            @Value("${keycloak.auth-server-url}") String authServerUrl,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.client-id}") String clientId,
            @Value("${keycloak.client-secret}") String clientSecret) {

        this.tokenUrl = authServerUrl + "/realms/" + realm +
                "/protocol/openid-connect/token";
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.webClient = WebClient.builder().build();
    }

    /**
     * Resource Owner Password Credentials Flow
     */
    public TokenResponse authenticate(String username, String password) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("username", username);
        formData.add("password", password);
        formData.add("scope", "openid profile email");

        try {
            TokenResponse response = webClient.post()
                    .uri(tokenUrl)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(TokenResponse.class)
                    .block();

            log.info("User {} authenticated successfully", username);
            return response;

        } catch (WebClientResponseException e) {
            log.error("Authentication failed for user {}: {}", username, e.getMessage());
            throw new AuthenticationException("Invalid credentials");
        }
    }

    /**
     * Refresh токена
     */
    public TokenResponse refreshToken(String refreshToken) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "refresh_token");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("refresh_token", refreshToken);

        try {
            return webClient.post()
                    .uri(tokenUrl)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(TokenResponse.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new AuthenticationException("Invalid refresh token");
        }
    }
}