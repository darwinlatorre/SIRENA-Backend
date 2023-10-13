package co.edu.unicauca.SIRENABackend.security.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.security.models.RoleModel;
import co.edu.unicauca.SIRENABackend.security.services.RoleService;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    /**
     * Obtiene una lista de todos los roles disponibles.
     *
     * @return Una lista de objetos RoleModel que representan todos los roles.
     */
    @GetMapping()
    public ResponseEntity<ArrayList<RoleModel>> getRoles() {
        ArrayList<RoleModel> roles = this.roleService.getRoles();

        if (!roles.isEmpty()) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Guarda un nuevo rol.
     *
     * @param prmRole El objeto RoleModel que se desea guardar.
     * @return El objeto RoleModel guardado en la base de datos.
     */
    @PostMapping()
    public ResponseEntity<RoleModel> saveRole(@RequestBody RoleModel prmRole) {
        RoleModel savedRole = this.roleService.saveRole(prmRole);

        if (savedRole != null) {
            return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene un rol por su ID.
     *
     * @param roleID El identificador único del rol que se desea obtener.
     * @return Un objeto Optional que contiene el rol si se encuentra, o vacío si no
     *         se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<RoleModel>> getRoleById(@PathVariable("id") Integer roleID) {
        Optional<RoleModel> role = this.roleService.getRoleById(roleID);

        if (role.isPresent()) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un rol por su ID.
     *
     * @param roleID El identificador único del rol que se desea eliminar.
     * @return Un mensaje que indica si se eliminó o no el rol con el ID
     *         proporcionado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer roleID) {
        boolean confirmation = this.roleService.deleteRoleById(roleID);

        if (confirmation) {
            return new ResponseEntity<>("Se ha eliminado el rol con id = " + roleID, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se eliminó el rol con id = " + roleID, HttpStatus.NOT_FOUND);
        }
    }

}
