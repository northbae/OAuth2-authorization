package kz.bmstu.kritinina.config;

import feign.RequestInterceptor;
import kz.bmstu.kritinina.client.CarClient;
import kz.bmstu.kritinina.client.PaymentClient;
import kz.bmstu.kritinina.client.RentalClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
@EnableFeignClients(clients = {CarClient.class, RentalClient.class, PaymentClient.class}, defaultConfiguration = FeignConfig.class)
public class FeignConfig {

    @Bean
    public RequestInterceptor jwtRequestInterceptor() {
        return requestTemplate -> {
            var authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken jwtAuth) {
                Jwt jwt = jwtAuth.getToken();
                requestTemplate.header("Authorization", "Bearer " + jwt.getTokenValue());
            }
        };
    }
}