package co.edu.unicauca.SIRENABackend.security.services.Impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.security.dtos.response.UserRes;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import co.edu.unicauca.SIRENABackend.security.repositories.IUserRepository;
import co.edu.unicauca.SIRENABackend.security.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public ArrayList<UserRes> getUsers() {

        ArrayList<UserModel> users = (ArrayList<UserModel>) userRepository.findAll();
        ArrayList<UserRes> usersRes = new ArrayList<>();

        for (UserModel user : users) {
            var userRes = UserRes.builder()
                    .usr_id(user.getId())
                    .usr_name(user.getUsername())
                    .usr_firstname(user.getFirstName())
                    .usr_lastname(user.getLastName())
                    .usr_email(user.getEmail())
                    .usr_role(user.getRole().getName())
                    .build();

            usersRes.add(userRes);
        }

        return usersRes;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserModel> getByUsername(String prmUsername) {
        return userRepository.findByUsername(prmUsername);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String prmUsuername) {
        return userRepository.existsByUsername(prmUsuername);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String prmEmail) {
        return userRepository.existsByEmail(prmEmail);
    }

    @Override
    @Transactional
    public UserModel saveUser(UserModel prmUser) {
        return userRepository.save(prmUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserRes> getUserById(Integer prmId) {

        var userRes = userRepository.findById(prmId).map(user -> UserRes.builder()
                .usr_id(user.getId())
                .usr_name(user.getUsername())
                .usr_firstname(user.getFirstName())
                .usr_lastname(user.getLastName())
                .usr_email(user.getEmail())
                .usr_role(user.getRole().getName())
                .build());

        return userRes;
    }

    @Override
    @Transactional
    public boolean deleteUserById(Integer prmId) {
        try {
            userRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}