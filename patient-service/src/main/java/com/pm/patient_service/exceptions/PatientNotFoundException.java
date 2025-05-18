package com.pm.patient_service.exceptions;

import java.util.UUID;

public class PatientNotFoundException extends RuntimeException {
  public PatientNotFoundException(String message, UUID id) {
    super(message);
  }
}
