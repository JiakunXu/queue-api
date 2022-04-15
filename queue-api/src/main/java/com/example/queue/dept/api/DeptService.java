package com.example.queue.dept.api;

import com.example.queue.dept.api.bo.Dept;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface DeptService {

    /**
     * 
     * @param code
     * @param name
     * @param dept
     * @return
     */
    int countDept(String code, String name, Dept dept);

    /**
     * 
     * @param code
     * @param name
     * @param dept
     * @return
     */
    List<Dept> listDepts(String code, String name, Dept dept);

    /**
     * 
     * @param id
     * @return
     */
    Dept getDept(BigInteger id);

    /**
     * 
     * @param dept
     * @param creator
     * @return
     */
    Dept insertDept(Dept dept, String creator);

    /**
     *
     * @param id
     * @param dept
     * @param modifier
     * @return
     */
    Dept updateDept(BigInteger id, Dept dept, String modifier);

    /**
     * 
     * @param id
     * @param modifier
     * @return
     */
    Dept deleteDept(BigInteger id, String modifier);

}
