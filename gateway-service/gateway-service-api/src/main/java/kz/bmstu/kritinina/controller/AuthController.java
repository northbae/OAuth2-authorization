package kz.bmstu.kritinina.controller;

import kz.bmstu.kritinina.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/v1")
public interface AuthController {
    @PostMapping("/authorize")
    ResponseEntity<?> login(@RequestBody LoginRequest request);
}