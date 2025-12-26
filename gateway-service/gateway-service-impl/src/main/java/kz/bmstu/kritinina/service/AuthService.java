package kz.bmstu.kritinina.service;

import kz.bmstu.kritinina.dto.LoginRequest;
import kz.bmstu.kritinina.dto.TokenResponse;

public interface AuthService {
    TokenResponse login(LoginRequest request);
}