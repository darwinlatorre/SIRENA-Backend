package co.edu.unicauca.SIRENABackend.security.services.Impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import co.edu.unicauca.SIRENABackend.security.repositories.IUserRepository;
import co.edu.unicauca.SIRENABackend.security.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
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
    public Optional<UserModel> getUserById(Integer prmId) {
        return userRepository.findById(prmId);
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