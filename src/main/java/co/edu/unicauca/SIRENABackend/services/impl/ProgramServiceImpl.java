package co.edu.unicauca.SIRENABackend.services.impl;

import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.models.ProgramModel;
import co.edu.unicauca.SIRENABackend.repositories.IProgramRepository;
import co.edu.unicauca.SIRENABackend.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private IProgramRepository programRepository;

    @Override
    public List<ProgramModel> getPrograms() {
        return programRepository.findAll();
    }

    @Override
    public ProgramModel saveProgram(ProgramModel prmProgram) {
        return programRepository.save(prmProgram);
    }

    @Override
    public Optional<ProgramModel> getProgramById(Integer prmId) {
        return programRepository.findById(prmId);
    }

    @Override
    public boolean deleteProgramById(Integer prmId) {
        try {
            programRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
