package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.dtos.request.IncidenceReq;
import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceRes;

public interface IncidenceService {
    ArrayList<IncidenceRes> getIncidences();

    IncidenceRes saveIncidence(IncidenceReq prmIncidence);

    Optional<IncidenceRes> getIncidenceById(Integer prmId);

    boolean deleteIncidenceById(Integer prmId);
}
