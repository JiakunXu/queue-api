package com.example.queue.nurse.api;

import com.example.queue.nurse.api.bo.Nurse;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface NurseService {

    /**
     * 
     * @param code
     * @param name
     * @param nurse
     * @return
     */
    int countNurse(String code, String name, Nurse nurse);

    /**
     *
     * @param code
     * @param name
     * @param nurse
     * @return
     */
    List<Nurse> listNurses(String code, String name, Nurse nurse);

    /**
     * 
     * @param id
     * @return
     */
    Nurse getNurse(BigInteger id);

    /**
     *
     * @param userId
     * @param nurse
     * @param creator
     * @return
     */
    Nurse insertNurse(BigInteger userId, Nurse nurse, String creator);

    /**
     *
     * @param id
     * @param nurse
     * @param modifier
     * @return
     */
    Nurse updateNurse(BigInteger id, Nurse nurse, String modifier);

    /**
     *
     * @param id
     * @param modifier
     * @return
     */
    Nurse deleteNurse(BigInteger id, String modifier);

}
