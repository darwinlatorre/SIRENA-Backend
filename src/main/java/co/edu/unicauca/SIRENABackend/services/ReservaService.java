package co.edu.unicauca.SIRENABackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.models.ReservaModel;
import co.edu.unicauca.SIRENABackend.repositories.IReservaRepository;

/**
 * Servicio para gestionar reservas.
 */
@Service
public class ReservaService {

    @Autowired
    private IReservaRepository reservaRepository;

    /**
     * Constructor para inyectar una instancia de IReservaRepository.
     *
     * @param reservaRepository Repositorio de reservas.
     */
    @Autowired
    public ReservaService(IReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /**
     * Crea una nueva reserva.
     *
     * @param reservaModel El objeto ReservaModel que se desea crear.
     * @return La instancia de ReservaModel creada y almacenada en la base de datos.
     */
    public ReservaModel crearReserva(ReservaModel reservaModel) {
        return reservaRepository.save(reservaModel);
    }

    /**
     * Obtiene todas las reservas almacenadas en la base de datos.
     *
     * @return Una lista de objetos ReservaModel que representan todas las reservas.
     */
    public List<ReservaModel> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    /**
     * Obtiene una reserva por su identificador único.
     *
     * @param id El identificador único de la reserva que se desea obtener.
     * @return Un objeto Optional que contiene la reserva si se encuentra, o vacío si no se encuentra.
     */
    public Optional<ReservaModel> obtenerReservaPorId(Integer id) {
        return reservaRepository.findById(id);
    }

    /**
     * Actualiza una reserva existente por su identificador único.
     *
     * @param id                  El identificador único de la reserva que se desea actualizar.
     * @param reservaActualizada  El objeto ReservaModel con los datos actualizados.
     * @return La instancia de ReservaModel actualizada y almacenada en la base de datos.
     * @throws RuntimeException Si la reserva no se encuentra con el ID proporcionado.
     */
    public ReservaModel actualizarReserva(Integer id, ReservaModel reservaActualizada) {
        Optional<ReservaModel> reservaExistente = reservaRepository.findById(id);
        if (reservaExistente.isPresent()) {
            reservaActualizada.setId(id);
            return reservaRepository.save(reservaActualizada);
        } else {
            throw new RuntimeException("La reserva no se encontró con el ID proporcionado.");
        }
    }

    /**
     * Elimina una reserva por su identificador único.
     *
     * @param id El identificador único de la reserva que se desea eliminar.
     * @throws RuntimeException Si la reserva no se encuentra con el ID proporcionado.
     */
    public void eliminarReserva(Integer id) {
        Optional<ReservaModel> reservaExistente = reservaRepository.findById(id);
        if (reservaExistente.isPresent()) {
            reservaRepository.deleteById(id);
        } else {
            throw new RuntimeException("La reserva no se encontró con el ID proporcionado.");
        }
    }
}