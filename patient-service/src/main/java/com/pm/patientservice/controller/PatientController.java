package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.PatientUpdateRequestDTO;
import com.pm.patientservice.service.AppInfoConfig;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @Autowired
    private AppInfoConfig infoConfig;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = service.getAllPatient();
        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getByid(@PathVariable UUID id){
        PatientResponseDTO pat = service.getbyID(id);

        return ResponseEntity.ok().body(pat);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = service.createPatient(patientRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(@PathVariable UUID id , @Valid @RequestBody PatientUpdateRequestDTO patientRequestDTO){
        PatientResponseDTO patient = service.updatePatient(id , patientRequestDTO);

        return ResponseEntity.ok().body(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.deletePatient(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/info")
    public AppInfoConfig getinfo(){
        return infoConfig;
    }

    @GetMapping("/billing-status/{id}")
    public String getStatus(@PathVariable UUID id ){
        return service.billstatus(id);
    }
}
