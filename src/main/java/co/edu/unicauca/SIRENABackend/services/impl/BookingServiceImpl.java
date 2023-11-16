package co.edu.unicauca.SIRENABackend.services.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.common.enums.BookingStateTypeEnum;
import co.edu.unicauca.SIRENABackend.dtos.request.BookingReq;
import co.edu.unicauca.SIRENABackend.dtos.response.BookingRes;
import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceRes;
import co.edu.unicauca.SIRENABackend.models.BookingModel;
import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import co.edu.unicauca.SIRENABackend.models.IncidenceModel;
import co.edu.unicauca.SIRENABackend.repositories.IBookingRepository;
import co.edu.unicauca.SIRENABackend.repositories.IClassroomRepository;
import co.edu.unicauca.SIRENABackend.repositories.IIncidenceRepository;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import co.edu.unicauca.SIRENABackend.security.repositories.IUserRepository;
import co.edu.unicauca.SIRENABackend.services.BookingService;
import lombok.RequiredArgsConstructor;

/**
 * Implementación del servicio de reservas (bookings) en la aplicación.
 */
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final IBookingRepository bookingRepository;
    private final IClassroomRepository classroomRepository;
    private final IIncidenceRepository incidenceRepository;
    private final IUserRepository userRepository;

    /**
     * Crea una nueva reserva en la aplicación.
     *
     * @param bookingModel Objeto de solicitud que contiene la información de la reserva.
     * @return Objeto {@code BookingRes} que representa la reserva creada.
     */
    public BookingRes crearBooking(BookingReq bookingModel) {

        if (bookingModel.getUserID() == null || bookingModel.getClassroomID() == null) {
            System.out.println("El salon y el usuario no pueden ser nulos");
            return null;
        }
        // Verificar que la fecha de solicitud es anterior a la de inicio
        if (!bookingModel.getFechaSolicitud().isBefore(bookingModel.getFechaReservaInicio())) {
            System.out.println("La fecha de solicitud debe ser anterior a la de inicio");
            return null;
        }

        // Verificar que la fecha fin es posterior a la de inicio
        if (!bookingModel.getFechaReservaInicio().isBefore(bookingModel.getHoraFin())) {
            System.out.println("La fecha de inicio debe ser anterior a la de fin");
            return null;
        }

        // Verificar que la reserva esta en el rango
        if (!isTimeInRange(bookingModel.getFechaReservaInicio()) || !isTimeInRange(bookingModel.getHoraFin())) {
            System.out.println("La reserva debe estar entre las 6am y las 11pm");
            return null;
        }

        // Verificar que el estado es valido
        boolean bandera = false;
        for (BookingStateTypeEnum BookingState : BookingStateTypeEnum.values()) {
            if (BookingState.equals(bookingModel.getEstado())) {
                bandera = true;
                break;
            }
        }
        if (!bandera) {
            System.out.println("El estado no" + bookingModel.getEstado().name() + " es valido");
            return null;
        }

        if (bookingRepository.findById(bookingModel.getId()).orElse(null) != null) {
            System.out.println("Ya existe una reserva con ese ID");
            return null;
        }

        ClassroomModel classroomFound = classroomRepository.findById(bookingModel.getClassroomID()).orElse(null);
        if(classroomFound==null)
        {
            System.out.println("Id del salon no encontrada");
            return null;
        }
        System.out.println(classroomFound.toString());
        // Verificar que el numero de estudiante no supera la capcidad
        if (bookingModel.getNumEstudiantes() > classroomFound.getCapacity()) {
            System.out.println("El numero de estudiante debe ser menor o igual a la capacidad del salon");
            return null;
        }

        // Verificar que ese salon no tiene una reserva activa en ese horario
        List<BookingModel> bookings = bookingRepository.findAll();
        for (BookingModel booking : bookings) {
            if (booking.getClassroom().getId() == classroomFound.getId()) {
                boolean bandera1 = booking.getFechaReservaInicio().isBefore(bookingModel.getHoraFin());
                boolean bandera2 = booking.getHoraFin().isAfter(bookingModel.getFechaReservaInicio());
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

        IncidenceModel incidenceFound = incidenceRepository.findById(bookingModel.getIncidenciasID()).orElse(null);
        if (incidenceFound == null) {
            System.out.println("No existe una incidencia con ese ID");
        }

        UserModel userFound = userRepository.findById(bookingModel.getUserID()).orElse(null);
        if (userFound == null) {
            System.out.println("No existe un usuario con ese ID");
            return null;
        }

        BookingModel bookingBuild = BookingModel.builder().id(bookingModel.getId())
                .fechaSolicitud(bookingModel.getFechaSolicitud())
                .fechaReservaInicio(bookingModel.getFechaReservaInicio())
                .horaFin(bookingModel.getHoraFin())
                .numEstudiantes(bookingModel.getNumEstudiantes())
                .estado(bookingModel.getEstado())
                .detalles(bookingModel.getDetalles())
                .incidencias(incidenceFound)
                .classroom(classroomFound)
                .user(userFound)
                .build();
        BookingModel BookingSaved = bookingRepository.save(bookingBuild);


        String usenameRes = BookingSaved.getUser().getUsername();
        IncidenceRes incidenceResponse = IncidenceRes.builder()
                .id(BookingSaved.getIncidencias().getId())
                .name(BookingSaved.getIncidencias().getName())
                .teacherName(BookingSaved.getIncidencias().getTeacherName().getUsername())
                .incidenceType(BookingSaved.getIncidencias().getInsidenciaType())
                .build();

        BookingRes bookingRes = BookingRes.builder()
                .id(BookingSaved.getId())
                .fechaSolicitud(BookingSaved.getFechaSolicitud())
                .fechaReservaInicio(BookingSaved.getFechaReservaInicio())
                .horaFin(BookingSaved.getHoraFin())
                .numEstudiantes(BookingSaved.getNumEstudiantes())
                .estado(BookingSaved.getEstado())
                .detalles(BookingSaved.getDetalles())
                .incidencias(incidenceResponse)
                .classroom(BookingSaved.getClassroom().getId())
                .user(usenameRes)
                .build();

        return bookingRes;
    }

    /**
     * Obtiene todas las reservas existentes en la aplicación.
     *
     * @return Lista de objetos {@code BookingRes} que representan las reservas.
     */
    public List<BookingRes> obtenerTodasLasBookings() {
        List<BookingModel> bookings = bookingRepository.findAll();
        List<BookingRes> bookingsRes = new ArrayList<>();
        for (BookingModel booking : bookings) {
            BookingRes bookingRes = BookingRes.builder().id(booking.getId())
                    .fechaSolicitud(booking.getFechaSolicitud())
                    .fechaReservaInicio(booking.getFechaReservaInicio())
                    .horaFin(booking.getHoraFin())
                    .numEstudiantes(booking.getNumEstudiantes())
                    .estado(booking.getEstado())
                    .detalles(booking.getDetalles())
                    .incidencias(IncidenceRes.builder()
                            .id(booking.getIncidencias().getId())
                            .name(booking.getIncidencias().getName())
                            .teacherName(booking.getIncidencias().getTeacherName().getUsername())
                            .incidenceType(booking.getIncidencias().getInsidenciaType())
                            .build())
                    .classroom(booking.getClassroom().getId())
                    .user(booking.getUser().getUsername())
                    .build();
            bookingsRes.add(bookingRes);
        }
        return bookingsRes;
    }


    /**
     * Obtiene una reserva por su identificador.
     *
     * @param id Identificador de la reserva.
     * @return Un objeto {@code Optional<BookingRes>} que representa la reserva si existe.
     */
    public Optional<BookingRes> obtenerBookingPorId(Integer id) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            BookingModel booking = bookingExistente.get();
            BookingRes bookingRes = BookingRes.builder().id(booking.getId())
                    .fechaSolicitud(booking.getFechaSolicitud())
                    .fechaReservaInicio(booking.getFechaReservaInicio())
                    .horaFin(booking.getHoraFin())
                    .numEstudiantes(booking.getNumEstudiantes())
                    .estado(booking.getEstado())
                    .detalles(booking.getDetalles())
                    .incidencias(IncidenceRes.builder()
                            .id(booking.getIncidencias().getId())
                            .name(booking.getIncidencias().getName())
                            .teacherName(booking.getIncidencias().getTeacherName().getUsername())
                            .incidenceType(booking.getIncidencias().getInsidenciaType())
                            .build())
                    .classroom(booking.getClassroom().getId())
                    .user(booking.getUser().getUsername())
                    .build();
            return Optional.of(bookingRes);
        } else {
            return Optional.empty();
        }
    }


    /**
     * Actualiza una reserva por su identificador.
     *
     * @param id                 Identificador de la reserva a actualizar.
     * @param bookingActualizada Objeto que contiene los datos actualizados de la reserva.
     * @return Un objeto {@code BookingRes} que representa la reserva actualizada.
     */
    public BookingRes actualizarBooking(Integer id, BookingReq bookingActualizada) {

        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {

            if (bookingActualizada.getUserID() == null || bookingActualizada.getClassroomID() == null) {
                System.out.println("El salon y el usuario no pueden ser nulos");
                return null;
            }
            // Verificar que la fecha de solicitud es anterior a la de inicio
            if (!bookingActualizada.getFechaSolicitud().isBefore(bookingActualizada.getFechaReservaInicio())) {
                System.out.println("La fecha de solicitud debe ser anterior a la de inicio");
                return null;
            }

            // Verificar que la fecha fin es posterior a la de inicio
            if (!bookingActualizada.getFechaReservaInicio().isBefore(bookingActualizada.getHoraFin())) {
                System.out.println("La fecha de inicio debe ser anterior a la de fin");
                return null;
            }

            // Verificar que la reserva esta en el rango
            if (!isTimeInRange(bookingActualizada.getFechaReservaInicio()) || !isTimeInRange(bookingActualizada.getHoraFin())) {
                System.out.println("La reserva debe estar entre las 6am y las 11pm");
                return null;
            }

            // Verificar que el estado es valido
            boolean bandera = false;
            for (BookingStateTypeEnum BookingState : BookingStateTypeEnum.values()) {
                if (BookingState.equals(bookingActualizada.getEstado())) {
                    bandera = true;
                    break;
                }
            }
            if (!bandera) {
                System.out.println("El estado no" + bookingActualizada.getEstado().name() + " es valido");
                return null;
            }

            ClassroomModel classroomFound = classroomRepository.findById(bookingActualizada.getClassroomID()).orElse(null);
            if(classroomFound==null)
            {
                System.out.println("Id del salon no encontrada");
                return null;
            }
            System.out.println(classroomFound.toString());
            // Verificar que el numero de estudiante no supera la capcidad
            if (bookingActualizada.getNumEstudiantes() > classroomFound.getCapacity()) {
                System.out.println("El numero de estudiante debe ser menor o igual a la capacidad del salon");
                return null;
            }

            IncidenceModel incidenceFound = incidenceRepository.findById(bookingActualizada.getIncidenciasID()).orElse(null);
            if (incidenceFound == null) {
                System.out.println("No existe una incidencia con ese ID");
            }

            UserModel userFound = userRepository.findById(bookingActualizada.getUserID()).orElse(null);
            if (userFound == null) {
                System.out.println("No existe un usuario con ese ID");
                return null;
            }

            BookingModel bookingBuild = BookingModel.builder().id(bookingActualizada.getId())
                    .fechaSolicitud(bookingActualizada.getFechaSolicitud())
                    .fechaReservaInicio(bookingActualizada.getFechaReservaInicio())
                    .horaFin(bookingActualizada.getHoraFin())
                    .numEstudiantes(bookingActualizada.getNumEstudiantes())
                    .estado(bookingActualizada.getEstado())
                    .detalles(bookingActualizada.getDetalles())
                    .incidencias(incidenceFound)
                    .classroom(classroomFound)
                    .user(userFound)
                    .build();
            BookingModel BookingSaved = bookingRepository.save(bookingBuild);


            String usenameRes = BookingSaved.getUser().getUsername();
            IncidenceRes incidenceResponse = IncidenceRes.builder()
                    .id(BookingSaved.getIncidencias().getId())
                    .name(BookingSaved.getIncidencias().getName())
                    .teacherName(BookingSaved.getIncidencias().getTeacherName().getUsername())
                    .incidenceType(BookingSaved.getIncidencias().getInsidenciaType())
                    .build();

            BookingRes bookingRes = BookingRes.builder()
                    .id(BookingSaved.getId())
                    .fechaSolicitud(BookingSaved.getFechaSolicitud())
                    .fechaReservaInicio(BookingSaved.getFechaReservaInicio())
                    .horaFin(BookingSaved.getHoraFin())
                    .numEstudiantes(BookingSaved.getNumEstudiantes())
                    .estado(BookingSaved.getEstado())
                    .detalles(BookingSaved.getDetalles())
                    .incidencias(incidenceResponse)
                    .classroom(BookingSaved.getClassroom().getId())
                    .user(usenameRes)
                    .build();

            return bookingRes;
        } else {
            throw new RuntimeException("La booking no se encontró con el ID proporcionado.");
        }
    }

    /**
     * Elimina una reserva por su identificador.
     *
     * @param id Identificador de la reserva a eliminar.
     * @throws RuntimeException Si la reserva no se encuentra con el ID proporcionado.
     */
    public void eliminarBooking(Integer id) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            bookingRepository.deleteById(id);
        } else {
            throw new RuntimeException("La booking no se encontró con el ID proporcionado.");
        }
    }


    /**
     * Verifica si el tiempo de una fecha está en un rango específico (de 6:00 AM a 11:00 PM).
     *
     * @param localDate Fecha y hora para verificar.
     * @return {@code true} si la hora está en el rango, {@code false} en caso contrario.
     */
    public static boolean isTimeInRange(LocalDateTime localDate) {

        LocalTime localTime = localDate.toLocalTime();

        LocalTime startTime = LocalTime.of(6, 0);
        LocalTime endTime = LocalTime.of(23, 0);

        return !localTime.isBefore(startTime) && !localTime.isAfter(endTime);
    }

    /**
     * Obtiene estadísticas de reservas.
     *
     * @return Una lista de arreglos de objetos que representan estadísticas de reservas.
     */
    @Override
    public ArrayList<Object[]> obtenerEstadisticasReservas() {
        return bookingRepository.obtenerEstadisticasReservas();
    }

}