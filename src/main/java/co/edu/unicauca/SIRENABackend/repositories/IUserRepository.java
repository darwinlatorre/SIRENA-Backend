package co.edu.unicauca.SIRENABackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, Integer>{
    Optional<UserModel> findByEmail(String prmEmail);
}
