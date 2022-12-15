package com.clinicals.api.dto;

import lombok.Data;

@Data
public class ClinicalDataRequest {
    private int patientId;
    private String componentName;
    private String componentValue;
}
