package com.example.queue.patient.api;

import com.example.queue.patient.api.bo.Patient;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface PatientService {

    /**
     *
     * @param code
     * @param name
     * @param patient
     * @return
     */
    int countPatient(String code, String name, Patient patient);

    /**
     * 
     * @param code
     * @param name
     * @param patient
     * @return
     */
    List<Patient> listPatients(String code, String name, Patient patient);

    /**
     * 
     * @param id
     * @return
     */
    Patient getPatient(BigInteger id);

    /**
     *
     * @param patient
     * @param creator
     * @return
     */
    Patient insertPatient(Patient patient, String creator);

    /**
     * 
     * @param id
     * @param patient
     * @param modifier
     * @return
     */
    Patient updatePatient(BigInteger id, Patient patient, String modifier);

}
