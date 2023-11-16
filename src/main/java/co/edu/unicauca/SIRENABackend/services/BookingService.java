package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.dtos.request.BookingReq;
import co.edu.unicauca.SIRENABackend.dtos.response.BookingRes;

/**
 * Interfaz que define los servicios relacionados con las reservas (bookings) en el sistema.
 */
public interface BookingService {
    /**
     * Crea una nueva reserva en el sistema.
     *
     * @param bookingModel La información de la reserva que se va a crear.
     * @return La reserva creada.
     */
    BookingRes crearBooking(BookingReq bookingModel);

    /**
     * Obtiene todas las reservas en el sistema.
     *
     * @return Lista de todas las reservas.
     */
    List<BookingRes> obtenerTodasLasBookings();

    /**
     * Obtiene una reserva por su ID.
     *
     * @param id El ID de la reserva que se va a obtener.
     * @return Un {@link Optional} que contiene la reserva si se encuentra, o vacío si no.
     */
    Optional<BookingRes> obtenerBookingPorId(Integer id);

    /**
     * Obtiene estadísticas relacionadas con las reservas en el sistema.
     *
     * @return Lista de objetos que representan estadísticas de reservas.
     */
    ArrayList<Object[]> obtenerEstadisticasReservas();


    /**
     * Actualiza una reserva existente en el sistema.
     *
     * @param id                  El ID de la reserva que se va a actualizar.
     * @param bookingActualizada La información actualizada de la reserva.
     * @return La reserva actualizada.
     */
    BookingRes actualizarBooking(Integer id, BookingReq bookingActualizada);

    /**
     * Elimina una reserva por su ID.
     *
     * @param id El ID de la reserva que se va a eliminar.
     */
    void eliminarBooking(Integer id);
}
