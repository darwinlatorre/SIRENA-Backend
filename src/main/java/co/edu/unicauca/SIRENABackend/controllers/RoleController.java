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

import co.edu.unicauca.SIRENABackend.models.RoleModel;
import co.edu.unicauca.SIRENABackend.services.RoleService;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping()
    public ArrayList<RoleModel> getRoles(){
        return roleService.getRoles();
    }

    @PostMapping()
    public RoleModel saveRole(@RequestBody RoleModel prmRole){
        return this.roleService.saveRole(prmRole);
    }

    @GetMapping( path = "/{id}" )
    public Optional<RoleModel> getRoleById(@PathVariable("id") Integer roleID){
        return this.roleService.getRoleById(roleID);
    }

    @DeleteMapping( path = "/{id}" )
    public String deleteById(@PathVariable("id") Integer roleID){
        boolean confirmation = this.roleService.deleteRoleById(roleID);
        if (confirmation) {
            return "Se ha eliminado el rol con id = " + roleID;
        } else {
            return "No se elimino el rol con id = " + roleID;
        }
    }

}
