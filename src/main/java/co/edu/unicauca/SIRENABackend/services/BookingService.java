package co.edu.unicauca.SIRENABackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.models.BookingModel;
import co.edu.unicauca.SIRENABackend.repositories.IBookingRepository;

@Service
public class BookingService {

    @Autowired
    private IBookingRepository bookingRepository;

    @Autowired
    public BookingService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public BookingModel crearBooking(BookingModel bookingModel) {
        return bookingRepository.save(bookingModel);
    }

    public List<BookingModel> obtenerTodasLasBookings() {
        return bookingRepository.findAll();
    }

    public Optional<BookingModel> obtenerBookingPorId(Integer id) {
        return bookingRepository.findById(id);
    }

    public BookingModel actualizarBooking(Integer id, BookingModel bookingActualizada) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            bookingActualizada.setId(id);
            return bookingRepository.save(bookingActualizada);
        } else {
            throw new RuntimeException("La booking no se encontró con el ID proporcionado.");
        }
    }

    public void eliminarBooking(Integer id) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            bookingRepository.deleteById(id);
        } else {
            throw new RuntimeException("La booking no se encontró con el ID proporcionado.");
        }
    }
}