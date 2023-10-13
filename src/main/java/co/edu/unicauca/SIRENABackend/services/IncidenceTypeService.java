package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;
import co.edu.unicauca.SIRENABackend.repositories.IIncidenceTypeRepository;

@Service
public class IncidenceTypeService {
    @Autowired
    private IIncidenceTypeRepository incidenceTypeRepository;

    @Transactional(readOnly = true)
    public ArrayList<IncidenceTypeModel> getIncidenceTypes() {
        return (ArrayList<IncidenceTypeModel>) incidenceTypeRepository.findAll();
    }

    @Transactional
    public IncidenceTypeModel saveIncidenceTypes(IncidenceTypeModel prmIncidence) {
        return incidenceTypeRepository.save(prmIncidence);
    }

    @Transactional(readOnly = true)
    public Optional<IncidenceTypeModel> getIncidenceTypeById(Integer prmId) {
        return incidenceTypeRepository.findById(prmId);
    }

    @Transactional
    public boolean deleteIncidenceTypeById(Integer prmId) {
        try {
            incidenceTypeRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}