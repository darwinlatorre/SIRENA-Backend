package co.edu.unicauca.SIRENABackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.SIRENABackend.models.ReservaModel;
import co.edu.unicauca.SIRENABackend.repositories.IReservaRepository;


@Service
public class ReservaService {

    @Autowired
    private IReservaRepository reservaRepository;

    @Autowired
    public ReservaService(IReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public ReservaModel crearReserva(ReservaModel reservaModel) {
        return reservaRepository.save(reservaModel);
    }

    public List<ReservaModel> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    public Optional<ReservaModel> obtenerReservaPorId(Integer id) {
        return reservaRepository.findById(id);
    }

    public ReservaModel actualizarReserva(Integer id, ReservaModel reservaActualizada) {
        Optional<ReservaModel> reservaExistente = reservaRepository.findById(id);
        if (reservaExistente.isPresent()) {
            reservaActualizada.setId(id);
            return reservaRepository.save(reservaActualizada);
        } else {
            throw new RuntimeException("La reserva no se encontró con el ID proporcionado.");
        }
    }

    public void eliminarReserva(Integer id) {
        Optional<ReservaModel> reservaExistente = reservaRepository.findById(id);
        if (reservaExistente.isPresent()) {
            reservaRepository.deleteById(id);
        } else {
            throw new RuntimeException("La reserva no se encontró con el ID proporcionado.");
        }
    }
}

