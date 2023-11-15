package co.edu.unicauca.SIRENABackend.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.SIRENABackend.models.BookingModel;

@Repository
public interface IBookingRepository extends JpaRepository<BookingModel, Integer> {

    @Query("SELECT b.id AS bookingID, c.name AS classroomName " +
            "FROM BookingModel b " +
            "JOIN b.classroomID c")
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
