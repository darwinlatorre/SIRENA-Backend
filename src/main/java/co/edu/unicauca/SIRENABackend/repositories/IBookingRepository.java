package co.edu.unicauca.SIRENABackend.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.SIRENABackend.models.BookingModel;

/**
 * Repositorio para acceder a la persistencia de las entidades BookingModel.
 */
@Repository
public interface IBookingRepository extends JpaRepository<BookingModel, Integer> {

    /**
     * Obtiene estadísticas personalizadas de reservas, incluyendo el ID de la reserva y el nombre del aula.
     *
     * @return Lista de arreglos de objetos que contienen el ID de la reserva y el nombre del aula.
     */
    @Query("SELECT b.id AS bookingID, c.name AS classroomName " +
            "FROM BookingModel b " +
            "JOIN b.classroom c")
    ArrayList<Object[]> obtenerEstadisticasReservas();

    // @Query("SELECT b.building.name AS buildingName, f.name AS facultyName, c.name
    // AS classroomName, COUNT(b.id) AS reservationCount "
    // +
    // "FROM BookingModel b " +
    // "JOIN b.classroomID c " +
    // "JOIN c.building building " +
    // "JOIN building.facultades f " +
    // "GROUP BY buildingName, facultyName, classroomName")
    // ArrayList<Object[]> obtenerEstadisticasReservas();
}
