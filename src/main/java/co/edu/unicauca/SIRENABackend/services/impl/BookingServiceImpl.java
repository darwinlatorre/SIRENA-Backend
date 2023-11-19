package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.dtos.request.BookingReq;
import co.edu.unicauca.SIRENABackend.dtos.response.BookingRes;
import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceRes;
import co.edu.unicauca.SIRENABackend.dtos.response.UserRes;
import co.edu.unicauca.SIRENABackend.models.BookingModel;
import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import co.edu.unicauca.SIRENABackend.models.FacultyModel;
import co.edu.unicauca.SIRENABackend.models.IncidenceModel;
import co.edu.unicauca.SIRENABackend.repositories.IBookingRepository;
import co.edu.unicauca.SIRENABackend.repositories.IClassroomRepository;
import co.edu.unicauca.SIRENABackend.repositories.IFacultyRepository;
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
    private final IFacultyRepository facultyRepository;

    /**
     * Crea una nueva reserva en la aplicación.
     *
     * @param bookingModel Objeto de solicitud que contiene la información de la
     *                     reserva.
     * @return Objeto {@code BookingRes} que representa la reserva creada.
     */
    public BookingRes crearBooking(BookingReq bookingModel) {

        ClassroomModel classroomFound = classroomRepository.findById(bookingModel.getClassroomID()).orElse(null);
        if (classroomFound == null) {
            System.out.println("Id del salon no encontrada");
            return null;
        }

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

        IncidenceModel incidenceFound = null;
        if (bookingModel.getIncidenciasID() != null) {
            incidenceFound = incidenceRepository.findById(bookingModel.getIncidenciasID()).orElse(null);
            if (incidenceFound == null) {
                System.out.println("No existe una incidencia con ese ID");
                return null;
            }
        }

        UserModel userFound = userRepository.findById(bookingModel.getUserID()).orElse(null);
        if (userFound == null) {
            System.out.println("No existe un usuario con ese ID");
            return null;
        }

        Optional<FacultyModel> facultyFound = facultyRepository.findById(bookingModel.getFacultyId());
        if (!facultyFound.isPresent()) {
            System.out.println("No existe una facultad con ese ID");
            return null;
        }

        BookingModel bookingBuild = BookingModel.builder()
                .fechaSolicitud(bookingModel.getFechaSolicitud())
                .fechaReservaInicio(bookingModel.getFechaReservaInicio())
                .horaFin(bookingModel.getHoraFin())
                .numEstudiantes(bookingModel.getNumEstudiantes())
                .estado(bookingModel.getEstado())
                .detalles(bookingModel.getDetalles())
                .incidencias(incidenceFound)
                .classroom(classroomFound)
                .user(userFound)
                .faculty(facultyFound.get())
                .build();

        BookingModel BookingSaved = bookingRepository.save(bookingBuild);

        UserRes usenameRes = UserRes.builder()
                .id(BookingSaved.getUser().getId())
                .username(BookingSaved.getUser().getUsername())
                .role(BookingSaved.getUser().getRole().getName())
                .build();

        IncidenceRes incidenceResponse = null;
        if (BookingSaved.getIncidencias() != null) {
            incidenceResponse = IncidenceRes.builder()
                    .id(BookingSaved.getIncidencias().getId())
                    .name(BookingSaved.getIncidencias().getName())
                    .teacherName(BookingSaved.getIncidencias().getTeacherName().getUsername())
                    .incidenceType(BookingSaved.getIncidencias().getInsidenciaType())
                    .build();
        }

        BookingRes bookingRes = BookingRes.builder()
                .id(BookingSaved.getId())
                .fechaSolicitud(BookingSaved.getFechaSolicitud())
                .fechaReservaInicio(BookingSaved.getFechaReservaInicio())
                .horaFin(BookingSaved.getHoraFin())
                .numEstudiantes(BookingSaved.getNumEstudiantes())
                .estado(BookingSaved.getEstado())
                .detalles(BookingSaved.getDetalles())
                .incidencias(incidenceResponse)
                .classroomID(BookingSaved.getClassroom().getId())
                .user(usenameRes)
                .facultyId(facultyFound.get().getId())
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
            IncidenceRes incidenceResponse = null;
            if (booking.getIncidencias() != null) {
                incidenceResponse = IncidenceRes.builder()
                        .id(booking.getIncidencias().getId())
                        .name(booking.getIncidencias().getName())
                        .teacherName(booking.getIncidencias().getTeacherName().getUsername())
                        .incidenceType(booking.getIncidencias().getInsidenciaType())
                        .build();
            }

            UserRes usenameRes = UserRes.builder()
                    .id(booking.getUser().getId())
                    .username(booking.getUser().getUsername())
                    .role(booking.getUser().getRole().getName())
                    .build();

            BookingRes bookingRes = BookingRes.builder().id(booking.getId())
                    .fechaSolicitud(booking.getFechaSolicitud())
                    .fechaReservaInicio(booking.getFechaReservaInicio())
                    .horaFin(booking.getHoraFin())
                    .numEstudiantes(booking.getNumEstudiantes())
                    .estado(booking.getEstado())
                    .detalles(booking.getDetalles())
                    .incidencias(incidenceResponse)
                    .classroomID(booking.getClassroom().getId())
                    .user(usenameRes)
                    .facultyId(booking.getFaculty().getId())
                    .build();
            bookingsRes.add(bookingRes);
        }
        return bookingsRes;
    }

    /**
     * Obtiene una reserva por su identificador.
     *
     * @param id Identificador de la reserva.
     * @return Un objeto {@code Optional<BookingRes>} que representa la reserva si
     *         existe.
     */
    public Optional<BookingRes> obtenerBookingPorId(Integer id) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            BookingModel booking = bookingExistente.get();

            IncidenceRes incidenceResponse = null;
            if (booking.getIncidencias() != null) {
                incidenceResponse = IncidenceRes.builder()
                        .id(booking.getIncidencias().getId())
                        .name(booking.getIncidencias().getName())
                        .teacherName(booking.getIncidencias().getTeacherName().getUsername())
                        .incidenceType(booking.getIncidencias().getInsidenciaType())
                        .build();
            }

            UserRes usenameRes = UserRes.builder()
                    .id(booking.getUser().getId())
                    .username(booking.getUser().getUsername())
                    .role(booking.getUser().getRole().getName())
                    .build();

            BookingRes bookingRes = BookingRes.builder().id(booking.getId())
                    .fechaSolicitud(booking.getFechaSolicitud())
                    .fechaReservaInicio(booking.getFechaReservaInicio())
                    .horaFin(booking.getHoraFin())
                    .numEstudiantes(booking.getNumEstudiantes())
                    .estado(booking.getEstado())
                    .detalles(booking.getDetalles())
                    .incidencias(incidenceResponse)
                    .classroomID(booking.getClassroom().getId())
                    .user(usenameRes)
                    .facultyId(booking.getFaculty().getId())
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
     * @param bookingActualizada Objeto que contiene los datos actualizados de la
     *                           reserva.
     * @return Un objeto {@code BookingRes} que representa la reserva actualizada.
     */
    public BookingRes actualizarBooking(Integer id, BookingReq bookingModel) {

        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {

            ClassroomModel classroomFound = classroomRepository.findById(bookingModel.getClassroomID()).orElse(null);
            if (classroomFound == null) {
                System.out.println("Id del salon no encontrada");
                return null;
            }

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

            IncidenceModel incidenceFound = null;
            if (bookingModel.getIncidenciasID() != null) {
                incidenceFound = incidenceRepository.findById(bookingModel.getIncidenciasID()).orElse(null);
                if (incidenceFound == null) {
                    System.out.println("No existe una incidencia con ese ID");
                    return null;
                }
            }

            UserModel userFound = userRepository.findById(bookingModel.getUserID()).orElse(null);
            if (userFound == null) {
                System.out.println("No existe un usuario con ese ID");
                return null;
            }

            Optional<FacultyModel> facultyFound = facultyRepository.findById(bookingModel.getFacultyId());
            if (!facultyFound.isPresent()) {
                System.out.println("No existe una facultad con ese ID");
                return null;
            }

            BookingModel bookingBuild = BookingModel.builder()
                    .fechaSolicitud(bookingModel.getFechaSolicitud())
                    .fechaReservaInicio(bookingModel.getFechaReservaInicio())
                    .horaFin(bookingModel.getHoraFin())
                    .numEstudiantes(bookingModel.getNumEstudiantes())
                    .estado(bookingModel.getEstado())
                    .detalles(bookingModel.getDetalles())
                    .incidencias(incidenceFound)
                    .classroom(classroomFound)
                    .user(userFound)
                    .faculty(facultyFound.get())
                    .build();

            BookingModel BookingSaved = bookingRepository.save(bookingBuild);

            UserRes usenameRes = UserRes.builder()
                    .id(BookingSaved.getUser().getId())
                    .username(BookingSaved.getUser().getUsername())
                    .role(BookingSaved.getUser().getRole().getName())
                    .build();

            IncidenceRes incidenceResponse = null;
            if (BookingSaved.getIncidencias() != null) {
                incidenceResponse = IncidenceRes.builder()
                        .id(BookingSaved.getIncidencias().getId())
                        .name(BookingSaved.getIncidencias().getName())
                        .teacherName(BookingSaved.getIncidencias().getTeacherName().getUsername())
                        .incidenceType(BookingSaved.getIncidencias().getInsidenciaType())
                        .build();
            }

            BookingRes bookingRes = BookingRes.builder()
                    .id(BookingSaved.getId())
                    .fechaSolicitud(BookingSaved.getFechaSolicitud())
                    .fechaReservaInicio(BookingSaved.getFechaReservaInicio())
                    .horaFin(BookingSaved.getHoraFin())
                    .numEstudiantes(BookingSaved.getNumEstudiantes())
                    .estado(BookingSaved.getEstado())
                    .detalles(BookingSaved.getDetalles())
                    .incidencias(incidenceResponse)
                    .classroomID(BookingSaved.getClassroom().getId())
                    .user(usenameRes)
                    .facultyId(facultyFound.get().getId())
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
     * @throws RuntimeException Si la reserva no se encuentra con el ID
     *                          proporcionado.
     */
    public void eliminarBooking(Integer id) {
        Optional<BookingModel> bookingExistente = bookingRepository.findById(id);
        if (bookingExistente.isPresent()) {
            bookingRepository.deleteById(id);
        } else {
            throw new RuntimeException("La booking no se encontró con el ID proporcionado.");
        }
    }

}