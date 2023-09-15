package co.edu.unicauca.SIRENABackend.repositories;

import co.edu.unicauca.SIRENABackend.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservaRepository extends JpaRepository<ReservaModel, Long> {
    
}
