package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.models.BookingModel;

public interface BookingService {
    BookingModel crearBooking(BookingModel bookingModel);

    List<BookingModel> obtenerTodasLasBookings();

    Optional<BookingModel> obtenerBookingPorId(Integer id);

    ArrayList<Object[]> obtenerEstadisticasReservas();

    BookingModel actualizarBooking(Integer id, BookingModel bookingActualizada);

    void eliminarBooking(Integer id);
}
