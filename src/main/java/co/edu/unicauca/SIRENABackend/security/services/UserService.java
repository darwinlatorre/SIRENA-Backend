package co.edu.unicauca.SIRENABackend.security.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.security.models.UserModel;

public interface UserService {
    ArrayList<UserModel> getUsers();

    Optional<UserModel> getByUsername(String prmUsername);

    boolean existsByUsername(String prmUsername);

    boolean existsByEmail(String prmEmail);

    UserModel saveUser(UserModel prmUser);

    Optional<UserModel> getUserById(Integer prmId);

    boolean deleteUserById(Integer prmId);
}
