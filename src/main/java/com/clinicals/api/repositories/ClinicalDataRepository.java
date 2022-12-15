package com.clinicals.api.repositories;

import com.clinicals.api.model.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {
}