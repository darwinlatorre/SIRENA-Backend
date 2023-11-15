package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.dtos.request.BookingReq;
import co.edu.unicauca.SIRENABackend.dtos.response.BookingRes;

public interface BookingService {
    BookingRes crearBooking(BookingReq bookingModel);

    List<BookingRes> obtenerTodasLasBookings();

    Optional<BookingRes> obtenerBookingPorId(Integer id);

    ArrayList<Object[]> obtenerEstadisticasReservas();

    BookingRes actualizarBooking(Integer id, BookingReq bookingActualizada);

    void eliminarBooking(Integer id);
}
