package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.exceptions.EmailAlreadyExistsException;
import com.pm.patient_service.exceptions.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<PatientResponseDto> getPatients(){
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream().map(PatientMapper::toDto).toList();
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        if(patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email" +
                    "already exists");
        }
        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(patientRequestDto)
        );
        return PatientMapper.toDto(newPatient);
    }

    public PatientResponseDto updatePatient(UUID id,
                                            PatientRequestDto patientRequestDto){
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        Patient patient = patientRepository.findById(id).orElseThrow(()-> {
            return new PatientNotFoundException("Patient not found with ID: ", id);
        });
        patient.setName(patientResponseDto.getName());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        Patient updatedPatient  = patientRepository.save(patient);
        return PatientMapper.toDto(updatedPatient);
    }
}
