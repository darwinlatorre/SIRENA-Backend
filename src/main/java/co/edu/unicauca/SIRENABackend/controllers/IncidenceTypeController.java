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

import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;
import co.edu.unicauca.SIRENABackend.services.IncidenceTypeService;

@RestController
@RequestMapping("/api/v1/incidenceType")
public class IncidenceTypeController {
    @Autowired
    IncidenceTypeService incidenceTypeService;

    @GetMapping()
    public ArrayList<IncidenceTypeModel> getIncidenceTypes() {
        return incidenceTypeService.getIncidenceTypes();
    }

    @PostMapping()
    public IncidenceTypeModel saveIncidenceTypes(@RequestBody IncidenceTypeModel prmIncidence) {
        return this.incidenceTypeService.saveIncidenceTypes(prmIncidence);
    }


    @GetMapping(path = "/{id}")
    public Optional<IncidenceTypeModel> getIncidenceTypeById(@PathVariable("id") Integer incidenceTypeID) {
        return this.incidenceTypeService.getIncidenceTypeById(incidenceTypeID);
    }


    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer incidenceTypeID) {
        boolean confirmation = this.incidenceTypeService.deleteIncidenceTypeById(incidenceTypeID);
        if (confirmation) {
            return "Se ha eliminado el tipo de incidencia con id = " + incidenceTypeID;
        } else {
            return "No se eliminó el tipo de incidencia con id = " + incidenceTypeID;
        }
    }
}
