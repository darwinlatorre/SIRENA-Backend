package co.edu.unicauca.SIRENABackend.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.controllers.UserController;
import co.edu.unicauca.SIRENABackend.jwt.JwtService;
import co.edu.unicauca.SIRENABackend.models.UserModel;
import co.edu.unicauca.SIRENABackend.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse login(LoginRequest request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request)
    {
        UserModel user=UserModel.builder()
        .id(request.getUsr_id())
        .role(request.getUsr_role())
        .firstName(request.usr_firstname)
        .lastName(request.usr_lastname)
        .name(request.getUsr_name())
        .password(request.getUsr_password())
        .email(request.usr_email)
        .build();

        userRepository.save(user);
        return AuthResponse.builder().token(null).build();
        
    }
}
