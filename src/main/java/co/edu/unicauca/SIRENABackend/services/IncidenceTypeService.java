package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.dtos.request.IncidenceTypeReq;
import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceTypeRes;

public interface IncidenceTypeService {
    ArrayList<IncidenceTypeRes> getIncidenceTypes();

    IncidenceTypeRes saveIncidenceTypes(IncidenceTypeReq prmIncidence);

    Optional<IncidenceTypeRes> getIncidenceTypeById(Integer prmId);

    boolean deleteIncidenceTypeById(Integer prmId);
}
