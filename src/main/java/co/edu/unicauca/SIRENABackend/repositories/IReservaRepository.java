package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.SIRENABackend.models.ReservaModel;

@Repository
public interface IReservaRepository extends JpaRepository<ReservaModel, Integer> {
    
}
