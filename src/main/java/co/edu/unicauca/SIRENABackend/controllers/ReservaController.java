package co.edu.unicauca.SIRENABackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.ReservaModel;
import co.edu.unicauca.SIRENABackend.repositories.IReservaRepository;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    private final IReservaRepository reservaRepository;

    @Autowired
    public ReservaController(IReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /**
     * Crea una nueva reserva.
     *
     * @param reservaModel El objeto ReservaModel que se desea crear y guardar.
     * @return Una respuesta HTTP con el objeto ReservaModel creado y el código de estado 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<ReservaModel> crearReserva(@RequestBody ReservaModel reservaModel) {
        ReservaModel nuevaReserva = reservaRepository.save(reservaModel);
        return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
    }

    /**
     * Obtiene todas las reservas almacenadas en la base de datos.
     *
     * @return Una respuesta HTTP con una lista de objetos ReservaModel y el código de estado 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<ReservaModel>> obtenerTodasLasReservas() {
        List<ReservaModel> reservas = reservaRepository.findAll();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    /**
     * Obtiene una reserva por su ID.
     *
     * @param id El identificador único de la reserva que se desea obtener.
     * @return Una respuesta HTTP con el objeto ReservaModel encontrado y el código de estado 200 (OK)
     *         si la reserva existe, o código de estado 404 (NOT FOUND) si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReservaModel> obtenerReservaPorId(@PathVariable Integer id) {
        Optional<ReservaModel> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            return new ResponseEntity<>(reserva.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza una reserva por su ID.
     *
     * @param id                  El identificador único de la reserva que se desea actualizar.
     * @param reservaActualizada  El objeto ReservaModel con los datos actualizados.
     * @return Una respuesta HTTP con el objeto ReservaModel actualizado y el código de estado 200 (OK)
     *         si la reserva existe, o código de estado 404 (NOT FOUND) si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReservaModel> actualizarReserva(@PathVariable Integer id, @RequestBody ReservaModel reservaActualizada) {
        Optional<ReservaModel> reservaExistente = reservaRepository.findById(id);
        if (reservaExistente.isPresent()) {
            reservaActualizada.setId(id);
            reservaRepository.save(reservaActualizada);
            return new ResponseEntity<>(reservaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una reserva por su ID.
     *
     * @param id El identificador único de la reserva que se desea eliminar.
     * @return Una respuesta HTTP con el código de estado 204 (NO CONTENT) si la reserva se eliminó con éxito,
     *         o código de estado 404 (NOT FOUND) si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Integer id) {
        Optional<ReservaModel> reservaExistente = reservaRepository.findById(id);
        if (reservaExistente.isPresent()) {
            reservaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}