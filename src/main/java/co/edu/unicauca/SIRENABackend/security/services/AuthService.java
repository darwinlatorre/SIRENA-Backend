package co.edu.unicauca.SIRENABackend.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.security.dto.AuthTokenRes;
import co.edu.unicauca.SIRENABackend.security.dto.UserLoginReq;
import co.edu.unicauca.SIRENABackend.security.dto.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.enums.TokenTypeEnum;
import co.edu.unicauca.SIRENABackend.security.jwt.JwtService;
import co.edu.unicauca.SIRENABackend.security.models.RoleModel;
import co.edu.unicauca.SIRENABackend.security.models.TokenModel;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import co.edu.unicauca.SIRENABackend.security.repositories.IRoleRepository;
import co.edu.unicauca.SIRENABackend.security.repositories.ITokenRepository;
import co.edu.unicauca.SIRENABackend.security.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final IUserRepository userRepository;
        private final IRoleRepository roleRepository;
        private final ITokenRepository tokenRepository;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;

        /**
         * Realiza la autenticación del usuario y genera un token de autenticación.
         *
         * @param request La solicitud de inicio de sesión.
         * @return La respuesta de autenticación que contiene el token.
         * @throws AuthenticationException Si la autenticación falla.
         */
        public AuthTokenRes login(UserLoginReq request) {
                authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                                                request.getPassword()));
                var user = userRepository.findByUsername(request.getUsername()).orElseThrow();

                var token = jwtService.getToken(user);
                saveUserToken(user, token);
                return AuthTokenRes.builder()
                                .token(token)
                                .build();
        }

        /**
         * Registra un nuevo usuario y retorna una respuesta de autenticación sin token.
         *
         * @param request La solicitud de registro.
         * @return La respuesta de autenticación sin token.
         */
        public AuthTokenRes register(UserRegisterReq request) {
                RoleModel role_insert = roleRepository.findByName(request.getUsr_role()).orElseThrow();
                UserModel user = UserModel.builder()
                                .id(request.getUsr_id())
                                .role(role_insert)
                                .firstName(request.getUsr_firstname())
                                .lastName(request.getUsr_lastname())
                                .username(request.getUsr_name())
                                .password(passwordEncoder.encode(request.getUsr_password()))
                                .email(request.getUsr_email())
                                .build();

                var savedUser = userRepository.save(user);
                var jwtToken = jwtService.getToken(user);
                saveUserToken(savedUser, jwtToken);

                return AuthTokenRes.builder()
                                .token(jwtToken)
                                .build();
        }

        private void saveUserToken(UserModel prmUser, String prmJwtToken) {
                var token = TokenModel.builder()
                                .user(prmUser)
                                .token(prmJwtToken)
                                .tokenType(TokenTypeEnum.BEARER)
                                .expired(false)
                                .revoked(false)
                                .build();
                tokenRepository.save(token);
        }
}
