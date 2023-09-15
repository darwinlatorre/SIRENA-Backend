package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.models.UserModel;
import co.edu.unicauca.SIRENABackend.repositories.IUserRepository;

@Service
public class UserService {
    
    @Autowired
    IUserRepository userRepository;

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>)userRepository.findAll();
    }

    public UserModel saveUser(UserModel prmUser){
        return userRepository.save(prmUser);
    }

    public Optional<UserModel> getUserById(Integer prmId){
        return userRepository.findById(prmId);
    }

    public boolean deleteUserById(Integer prmId){
        try {
            userRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
