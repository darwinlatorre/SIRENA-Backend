package co.edu.unicauca.SIRENABackend.security.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.UserRegisterRes;

public interface UserService {
    ArrayList<UserRegisterRes> getUsers();

    Optional<UserRegisterRes> getByUsername(String prmUsername);

    boolean existsByUsername(String prmUsername);

    boolean existsByEmail(String prmEmail);

    UserRegisterRes saveUser(UserRegisterReq prmUser);

    Optional<UserRegisterRes> getUserById(Integer prmId);

    boolean deleteUserById(Integer prmId);
}
