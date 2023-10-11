package co.edu.unicauca.SIRENABackend.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.security.dto.AuthTokenRes;
import co.edu.unicauca.SIRENABackend.security.dto.UserLoginReq;
import co.edu.unicauca.SIRENABackend.security.dto.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.services.AuthService;
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
