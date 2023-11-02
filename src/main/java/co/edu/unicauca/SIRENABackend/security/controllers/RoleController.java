package co.edu.unicauca.SIRENABackend.security.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.security.models.RoleModel;
import co.edu.unicauca.SIRENABackend.security.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/role")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Operation(summary = "Obtener un rol por ID", description = "Obtiene un rol específico por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Rol obtenido exitosamente", content = @Content(schema = @Schema(implementation = RoleModel.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ArrayList<RoleModel>> getRoles() {
        ArrayList<RoleModel> roles = this.roleService.getRoles();

        if (!roles.isEmpty()) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener un rol por ID", description = "Obtiene un rol específico por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Rol obtenido exitosamente", content = @Content(schema = @Schema(implementation = RoleModel.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<RoleModel>> getRoleById(@PathVariable("id") Integer roleID) {
        Optional<RoleModel> role = this.roleService.getRoleById(roleID);

        if (role.isPresent()) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
