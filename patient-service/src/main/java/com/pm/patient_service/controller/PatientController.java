package com.pm.patient_service.controller;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.dto.validators.CreatePatientValidationGroup;
import com.pm.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "APIs for managing patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Operation(summary = "Get all patients", description = "Retrieve a list of all patients")
    @ApiResponse(responseCode = "200", description = "List of patients retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PatientResponseDto.class)))
    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients (){
        List<PatientResponseDto> patientResponseDtos = patientService.getPatients();
        return ResponseEntity.ok().body(patientResponseDtos);
    }

    @Operation(summary = "Create a new patient", description = "Add a new patient to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient created successfully",
                    content = @Content(schema = @Schema(implementation = PatientResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto patientResponseDto = patientService.createPatient(patientRequestDto);
        return ResponseEntity.ok(patientResponseDto);
    }

    @Operation(summary = "Update a patient", description = "Update details of an existing patient by ID")
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id,@Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto patientResponseDto = patientService.updatePatient(id,patientRequestDto);
        return ResponseEntity.ok(patientResponseDto);
    }

    @Operation(summary = "Delete a patient", description = "Delete an existing patient by ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
