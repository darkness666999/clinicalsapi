package com.clinicals.api.endpoints;

import com.clinicals.api.dto.ClinicalDataRequest;
import com.clinicals.api.model.ClinicalData;
import com.clinicals.api.model.Patient;
import com.clinicals.api.repositories.ClinicalDataRepository;
import com.clinicals.api.repositories.PatientRepository;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import java.sql.Timestamp;

@Consumes("application/json")
@Produces("application/json")
@Path("/api")
@CrossOriginResourceSharing(
        allowAllOrigins = true,
        allowCredentials = true,
        maxAge = 1209600 )
public class ClinicalDataService {
    @Autowired
    PatientRepository patientRepo;
    @Autowired
    ClinicalDataRepository clinicalDateRepo;

    @Path("/clinicals")
    @POST
    public ClinicalData saveClinicalData(ClinicalDataRequest request){
        Patient patient = patientRepo.getReferenceById(request.getPatientId());
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setPatient(patient);
        clinicalData.setComponent_name(request.getComponentName());
        clinicalData.setComponent_value(request.getComponentValue());
        clinicalData.setMeasured_date_time(new Timestamp(System.currentTimeMillis()));
        return clinicalDateRepo.save(clinicalData);
    }

}
