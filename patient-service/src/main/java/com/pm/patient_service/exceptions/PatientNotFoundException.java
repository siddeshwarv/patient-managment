package com.pm.patient_service.exceptions;

public class PatientNotFoundExceptoin extends RuntimeException {
  public PatientNotFoundExceptoin(String message) {
    super(message);
  }
}
