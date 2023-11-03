package co.edu.unicauca.SIRENABackend.configs;

import static co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum.ADMIN;
import static co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum.COORDINADOR;
import static co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum.DOCENTE;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.unicauca.SIRENABackend.common.enums.ClassroomTypeEnum;
import co.edu.unicauca.SIRENABackend.dtos.request.ClassroomTypeReq;
import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import co.edu.unicauca.SIRENABackend.security.dtos.request.RoleReq;
import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.services.AuthService;
import co.edu.unicauca.SIRENABackend.security.services.RoleService;
import co.edu.unicauca.SIRENABackend.services.ClassroomTypeService;

@Configuration
public class CommandLineRunnerConfig {

    @Bean
    public CommandLineRunner commandLineRunner(AuthService authService, RoleService roleService,
            ClassroomTypeService classroomTypeService) {
        return args -> {
            registroRoles(roleService);
            registroUsuarios(authService);
            registroSalones(classroomTypeService);
        };
    }

    private void registroSalones(ClassroomTypeService classroomTypeService) {
        for (ClassroomTypeEnum classroom : ClassroomTypeEnum.values()) {
            try {
                classroomTypeService.saveClassroomType(ClassroomTypeReq.builder().name(classroom).build());
            } catch (Exception e) {
                System.out.println("Error al ingresar el salon");
            }
        }
    }

    private void registroRoles(RoleService roleService) {
        for (RoleEnum Role : RoleEnum.values()) {
            try {
                roleService.saveRole(RoleReq.builder().name(Role).build());
            } catch (Exception e) {
                System.out.println("Error al ingresar el rol");
            }
        }
    }

    private void registroUsuarios(AuthService authService) {

        try {
            var adminUser = UserRegisterReq.builder()
                    .usr_name("admin")
                    .usr_firstname("admin")
                    .usr_lastname("admin")
                    .usr_email("admin@unicauca.edu.co")
                    .usr_password("admin")
                    .usr_role(ADMIN)
                    .build();
            System.out.println("Admin Token: " + authService.register(adminUser).getAccesToken());
        } catch (Exception e) {
            System.out.println("El usuario admin ya exsite");
        }

        try {
            var coordinadorTestUser = UserRegisterReq.builder()
                    .usr_name("coordinadorTest")
                    .usr_firstname("coordinadorTest")
                    .usr_lastname("coordinadorTest")
                    .usr_email("coordinadorTest@unicauca.edu.co")
                    .usr_password("coordinadorTest")
                    .usr_role(COORDINADOR)
                    .build();
            System.out.println("Coordinador Token: " + authService.register(coordinadorTestUser).getAccesToken());
        } catch (Exception e) {
            System.out.println("El usuario coordinadorTest ya existe");
        }

        try {
            var docenteTestUser = UserRegisterReq.builder()
                    .usr_name("docenteTest")
                    .usr_firstname("docenteTest")
                    .usr_lastname("docenteTest")
                    .usr_email("docenteTest@unicauca.edu.co")
                    .usr_password("docenteTest")
                    .usr_role(DOCENTE)
                    .build();
            System.out.println("Docente Token: " + authService.register(docenteTestUser).getAccesToken());
        } catch (Exception e) {
            System.out.println("El usuario docenteTest ya existe");
        }
    }

}
