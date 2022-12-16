package com.clinicals.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lastName;
    private String firstName;
    private int age;
    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "patient")
    private List<ClinicalData> clinicalData;

}
