package co.edu.unicauca.SIRENABackend.security.controllers;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserLoginReq;
import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.AuthTokenRes;
import co.edu.unicauca.SIRENABackend.security.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Maneja la solicitud de inicio de sesión.
     *
     * @param request La solicitud de inicio de sesión.
     * @return ResponseEntity que contiene la respuesta de autenticación.
     */
    @PostMapping(value = "/login")
    public ResponseEntity<AuthTokenRes> login(@RequestBody UserLoginReq request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws StreamWriteException, DatabindException, IOException {
        authService.refreshToken(request, response);
    }

    /**
     * Maneja la solicitud de registro.
     *
     * @param request La solicitud de registro.
     * @return ResponseEntity que contiene la respuesta de autenticación.
     */
    @PostMapping(value = "/register")
    public ResponseEntity<AuthTokenRes> register(@RequestBody UserRegisterReq request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
