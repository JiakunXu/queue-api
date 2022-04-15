package com.example.queue.doctor.api;

import com.example.queue.doctor.api.bo.Doctor;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface DoctorService {

    /**
     *
     * @param code
     * @param name
     * @param doctor
     * @return
     */
    int countDoctor(String code, String name, Doctor doctor);

    /**
     * 
     * @param code
     * @param name
     * @param doctor
     * @return
     */
    List<Doctor> listDoctors(String code, String name, Doctor doctor);

    /**
     * 
     * @param id
     * @return
     */
    Doctor getDoctor(BigInteger id);

    /**
     *
     * @param userId
     * @param doctor
     * @param creator
     * @return
     */
    Doctor insertDoctor(BigInteger userId, Doctor doctor, String creator);

    /**
     *
     * @param id
     * @param doctor
     * @param modifier
     * @return
     */
    Doctor updateDoctor(BigInteger id, Doctor doctor, String modifier);

    /**
     * 
     * @param id
     * @param modifier
     * @return
     */
    Doctor deleteDoctor(BigInteger id, String modifier);

}
