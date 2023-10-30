package co.edu.unicauca.SIRENABackend.configs;

import static co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum.ADMIN;
import static co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum.COORDINADOR;
import static co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum.DOCENTE;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.models.RoleModel;
import co.edu.unicauca.SIRENABackend.security.services.AuthService;
import co.edu.unicauca.SIRENABackend.security.services.RoleService;

@Configuration
public class CommandLineRunnerConfig {

    @Bean
    public CommandLineRunner commandLineRunner(AuthService authService, RoleService roleService) {
        return args -> {
            registroRoles(roleService);
            registroUsuarios(authService);
        };
    }

    private void registroRoles(RoleService roleService) {
        for (RoleEnum Role : RoleEnum.values()) {
            try {
                roleService.saveRole(RoleModel.builder().name(Role).build());
            } catch (Exception e) {
                System.out.println("Error al ingresar el rol");
            }
        }
    }

    private void registroUsuarios(AuthService authService) {
        var adminUser = UserRegisterReq.builder()
                .usr_name("admin")
                .usr_firstname("admin")
                .usr_lastname("admin")
                .usr_email("admin@unicauca.edu.co")
                .usr_password("admin")
                .usr_role(ADMIN)
                .build();

        try {
            System.out.println("Admin token: " + authService.register(adminUser).getAccesToken());
        } catch (Exception e) {
            // System.out.println("Error al registrar el usuario admin: " + e.getMessage());
            System.out.println("El usuario admin ya exsite");
        }

        var coordinadorTestUser = UserRegisterReq.builder()
                .usr_name("coordinadorTest")
                .usr_firstname("coordinadorTest")
                .usr_lastname("coordinadorTest")
                .usr_email("coordinadorTest@unicauca.edu.co")
                .usr_password("coordinadorTest")
                .usr_role(COORDINADOR)
                .build();

        try {
            System.out.println("Coordinador token: " + authService.register(coordinadorTestUser).getAccesToken());
        } catch (Exception e) {
            // System.out.println("Error al registrar el usuario coordinadorTest: " +
            // e.getMessage());
            System.out.println("El usuario coordinadorTest ya existe");
        }

        var docenteTestUser = UserRegisterReq.builder()
                .usr_name("docenteTest")
                .usr_firstname("docenteTest")
                .usr_lastname("docenteTest")
                .usr_email("docenteTest@unicauca.edu.co")
                .usr_password("docenteTest")
                .usr_role(DOCENTE)
                .build();

        try {
            System.out.println("Docente token: " + authService.register(docenteTestUser).getAccesToken());
        } catch (Exception e) {
            // System.out.println("Error al registrar el usuario docenteTest: " +
            // e.getMessage());
            System.out.println("El usuario docenteTest ya existe");
        }
    }

}
