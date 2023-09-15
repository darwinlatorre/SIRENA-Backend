package co.edu.unicauca.SIRENABackend.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.UserModel;
import co.edu.unicauca.SIRENABackend.services.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<UserModel> getUsers(){
        return userService.getUsers();
    }

    @PostMapping()
    public UserModel saveRole(@RequestBody UserModel prmUser){
        return this.userService.saveUser(prmUser);
    }

    @GetMapping( path = "/{id}" )
    public Optional<UserModel> getUserById(@PathVariable("id") Integer userID){
        return this.userService.getUserById(userID);
    }

    @DeleteMapping( path = "/{id}" )
    public String deleteById(@PathVariable("id") Integer userID){
        boolean confirmation = this.userService.deleteUserById(userID);
        if (confirmation) {
            return "Se ha eliminado el rol con id = " + userID;
        } else {
            return "No se elimino el rol con id = " + userID;
        }
    }
}
