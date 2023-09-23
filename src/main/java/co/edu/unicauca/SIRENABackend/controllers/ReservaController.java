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
    public ResponseEntity<ReservaModel> obtenerReservaPorId(@PathVariable Integer id) {
        Optional<ReservaModel> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            return new ResponseEntity<>(reserva.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar una reserva por su ID
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

    // Endpoint para eliminar una reserva por su ID
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

