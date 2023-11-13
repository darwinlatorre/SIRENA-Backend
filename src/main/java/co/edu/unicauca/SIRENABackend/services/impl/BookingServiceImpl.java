package co.edu.unicauca.SIRENABackend.services.impl;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.models.BookingModel;
import co.edu.unicauca.SIRENABackend.repositories.IBookingRepository;
import co.edu.unicauca.SIRENABackend.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private IBookingRepository bookingRepository;

    public BookingModel crearBooking(BookingModel bookingModel) {
        if (bookingModel.getUserID() == null || bookingModel.getClassroomID() == null) {
            System.out.println("El salon y el usuario no pueden ser nulos");
            return null;
        }
        // Verificar que la fecha de solicitud es anterior a la de inicio
        if (!bookingModel.getFechaSolicitud().before(bookingModel.getFechaReservaInicio())) {
            System.out.println("La fecha de solicitud debe ser anterior a la de inicio");
            return null;
        }
        // Verificar que la fecha fin es posterior a la de inicio
        if (!bookingModel.getFechaReservaInicio().before(bookingModel.getHoraFin())) {
            System.out.println("La fecha de fin debe ser posterior a la de inicio");
            return null;
        }
        // Verificar que la reserva esta en el rango
        if (!isTimeInRange(bookingModel.getFechaReservaInicio()) || !isTimeInRange(bookingModel.getHoraFin())) {
            System.out.println("La reserva debe estar entre las 6am y las 11pm");
            return null;
        }
        //Verificar que el numero de estudiante no supera la capcidad
        if (bookingModel.getNumEstudiantes()>bookingModel.getClassroomID().getCapacity()) {
            System.out.println("El numero de estudiante debe ser menor o igual a la capacidad del salon");
            return null;
        }
        //Verificar que el estado es valido
        if(!bookingModel.getEstado().equals("PENDIENTE") && !bookingModel.getEstado().equals("ACEPTADA") && !bookingModel.getEstado().equals("RECHAZADA"))
        {
            System.out.println("El estado debe ser (PENDIENTE,ACEPTADA,RECHAZADA)");
            return null;
        }
        // Verificar que ese salon no tiene una reserva activa en ese horario
        List<BookingModel> bookings = bookingRepository.findAll();
        for (BookingModel booking : bookings) {
            if (booking.getClassroomID().getId() == bookingModel.getClassroomID().getId()) {
                boolean bandera1 = booking.getFechaReservaInicio().before(bookingModel.getHoraFin());
                boolean bandera2 = booking.getHoraFin().after(bookingModel.getFechaReservaInicio());
                boolean bandera3 = booking.getHoraFin().equals(bookingModel.getFechaReservaInicio());
                boolean bandera4 = booking.getFechaReservaInicio().equals(bookingModel.getHoraFin());

                if (bandera1 && bandera2) {
                    if (!bandera3 && !bandera4) {
                        System.out.println("Ya hay una reserva para el salón en ese horario");
                        return null;
                    }
                }
            }
        }
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

    public boolean isTimeInRange(Date date) {
        // Convierte la Date a un objeto LocalTime
        LocalTime localTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

        // Define los límites de tiempo (6 AM y 11 PM)
        LocalTime startTime = LocalTime.of(6, 0);
        LocalTime endTime = LocalTime.of(23, 0);

        // Comprueba si la hora está en el rango
        return !localTime.isBefore(startTime) && !localTime.isAfter(endTime);
    }

}