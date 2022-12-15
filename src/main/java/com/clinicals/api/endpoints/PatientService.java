package com.clinicals.api.endpoints;

import com.clinicals.api.model.ClinicalData;
import com.clinicals.api.model.Patient;
import com.clinicals.api.repositories.PatientRepository;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Consumes("application/json")
@Produces("application/json")
@Path("/api")
@CrossOriginResourceSharing(
        allowAllOrigins = true,
        allowCredentials = true,
        maxAge = 1209600 )
public class PatientService {
    @Autowired
    PatientRepository repo;

    @Path("/patients")
    @POST
    public Patient createPatient(Patient patient){
        return repo.save(patient);
    }

    @Path("/patients/{id}")
    @GET
    public Patient getPatient(@PathParam("id") int id){
        return repo.findById(id).get();
    }

    @Path("/patients/")
    @GET
    public List<Patient> getPatients(){
        return repo.findAll();
    }

    @Path("/patients/analyze/{id}")
    @GET
    public Patient analyze(@PathParam("id") int id){
        Patient patient = repo.findById(id).get();
        List<ClinicalData> clinicalData = new ArrayList<>(patient.getClinicalData());
        for(ClinicalData eachEntry : clinicalData){
            if(eachEntry.getComponent_name().equals("hw")){
                String[] heightAndWeight = eachEntry.getComponent_value().split("/");
                String height = heightAndWeight[0];
                String weigth = heightAndWeight[1];

                float heightInMeters = Float.parseFloat(height) * 0.4536F;
                Float bmi = Float.parseFloat(weigth) / (heightInMeters * heightInMeters);
                ClinicalData bmiData = new ClinicalData();
                bmiData.setComponent_name("bmi");
                bmiData.setComponent_value(bmi.toString());
                patient.getClinicalData().add(bmiData);
            }
        }
        return patient;
    }

}
