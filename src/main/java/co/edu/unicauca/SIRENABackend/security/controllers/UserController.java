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

import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.UserRes;
import co.edu.unicauca.SIRENABackend.security.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    @Autowired
    UserService userService;

    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista de todos los usuarios registrados en el sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "Usuarios obtenidos exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserRes.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios")
    })
    @GetMapping()
    public ResponseEntity<ArrayList<UserRes>> getUsers() {
        ArrayList<UserRes> users = this.userService.getUsers();

        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Registrar un nuevo usuario", description = "Registra un nuevo usuario en el sistema.", responses = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente", content = @Content(schema = @Schema(implementation = UserRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping()
    public ResponseEntity<UserRes> saveUser(@RequestBody UserRegisterReq prmUser) {
        UserRes savedUser = this.userService.saveUser(prmUser);

        if (savedUser != null) {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Registrar un nuevo usuario", description = "Registra un nuevo usuario en el sistema.", responses = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente", content = @Content(schema = @Schema(implementation = UserRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserRes>> getUserById(@PathVariable("id") Integer userID) {
        Optional<UserRes> user = this.userService.getUserById(userID);

        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar un usuario por ID", description = "Elimina un usuario específico por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer userID) {
        boolean confirmation = this.userService.deleteUserById(userID);

        if (confirmation) {
            return new ResponseEntity<>("Se ha eliminado el usuario con id = " + userID, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se eliminó el usuario con id = " + userID, HttpStatus.NOT_FOUND);
        }
    }

}