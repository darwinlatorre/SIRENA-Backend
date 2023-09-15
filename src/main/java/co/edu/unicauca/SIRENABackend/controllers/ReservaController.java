package co.edu.unicauca.SIRENABackend.controllers;

import co.edu.unicauca.SIRENABackend.models.ReservaModel;
import co.edu.unicauca.SIRENABackend.repositories.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final IReservaRepository reservaRepository;

    @Autowired
    public ReservaController(IReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    // Endpoint para crear una reserva
    @PostMapping
    public ResponseEntity<ReservaModel> crearReserva(@RequestBody ReservaModel reservaModel) {
        ReservaModel nuevaReserva = reservaRepository.save(reservaModel);
        return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<ReservaModel>> obtenerTodasLasReservas() {
        List<ReservaModel> reservas = reservaRepository.findAll();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    // Endpoint para obtener una reserva por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaModel> obtenerReservaPorId(@PathVariable Long id) {
        Optional<ReservaModel> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            return new ResponseEntity<>(reserva.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar una reserva por su ID
    @PutMapping("/{id}")
    public ResponseEntity<ReservaModel> actualizarReserva(@PathVariable Long id, @RequestBody ReservaModel reservaActualizada) {
        Optional<ReservaModel> reservaExistente = reservaRepository.findById(id);
        if (reservaExistente.isPresent()) {
            reservaActualizada.setId(id);
            reservaRepository.save(reservaActualizada);
            return new ResponseEntity<>(reservaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar una reserva por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        Optional<ReservaModel> reservaExistente = reservaRepository.findById(id);
        if (reservaExistente.isPresent()) {
            reservaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
