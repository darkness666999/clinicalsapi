package com.clinicals.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String last_name;
    private String first_name;
    private int age;
    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "patient")
    private List<ClinicalData> clinicalData;

}
