package co.edu.unicauca.SIRENABackend.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.dtos.request.IncidenceReq;
import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceRes;
import co.edu.unicauca.SIRENABackend.services.IncidenceService;

@RestController
@RequestMapping("/incidence")
public class IncidenceController {
    @Autowired
    IncidenceService incidenceService;

    @GetMapping()
    public ResponseEntity<ArrayList<IncidenceRes>> getIncidences() {
        ArrayList<IncidenceRes> incidences = incidenceService.getIncidences();

        if (!incidences.isEmpty()) {
            return new ResponseEntity<>(incidences, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<IncidenceRes> saveIncidence(@RequestBody IncidenceReq prmIncidence) {
        IncidenceRes savedIncidence = this.incidenceService.saveIncidence(prmIncidence);

        if (savedIncidence != null) {
            return new ResponseEntity<>(savedIncidence, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<IncidenceRes>> getIncidenceById(@PathVariable("id") Integer incidenceID) {
        Optional<IncidenceRes> incidence = this.incidenceService.getIncidenceById(incidenceID);

        if (incidence.isPresent()) {
            return new ResponseEntity<>(incidence, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIncidenceById(@PathVariable("id") Integer incidenceID) {
        boolean confirmation = this.incidenceService.deleteIncidenceById(incidenceID);

        if (confirmation) {
            return new ResponseEntity<>("Se ha eliminado la incidencia con id = " + incidenceID, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se eliminó la incidencia con id = " + incidenceID, HttpStatus.NOT_FOUND);
        }
    }

}
