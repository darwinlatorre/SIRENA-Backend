package co.edu.unicauca.SIRENABackend.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.IncidenceModel;
import co.edu.unicauca.SIRENABackend.services.IncidenceService;

@RestController
@RequestMapping("/api/v1/incidence")
public class IncidenceController {
    @Autowired
    IncidenceService incidenceService;

    @GetMapping()
    public ArrayList<IncidenceModel> getIncidences() {
        return incidenceService.getIncidences();
    }

    @PostMapping()
    public IncidenceModel saveIncidence(@RequestBody IncidenceModel prmIncidence) {
        return this.incidenceService.saveIncidence(prmIncidence);
    }


    @GetMapping(path = "/{id}")
    public Optional<IncidenceModel> getIncidenceById(@PathVariable("id") Integer incidenceID) {
        return this.incidenceService.getIncidenceById(incidenceID);
    }


    @DeleteMapping(path = "/{id}")
    public String deleteIncidenceById(@PathVariable("id") Integer incidenceID) {
        boolean confirmation = this.incidenceService.deleteIncidenceById(incidenceID);
        if (confirmation) {
            return "Se ha eliminado el incidencia con id = " + incidenceID;
        } else {
            return "No se eliminó el incidencia con id = " + incidenceID;
        }
    }
}
