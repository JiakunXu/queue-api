package com.example.queue.user.api;

import com.example.queue.user.api.bo.UserDept;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface UserDeptService {

    /**
     * 
     * @param deptId
     * @param userDept
     * @return
     */
    int countUserDept(String deptId, UserDept userDept);

    /**
     * 
     * @param deptId
     * @param userDept
     * @return
     */
    List<UserDept> listUserDepts(String deptId, UserDept userDept);

    /**
     *
     * @param userId
     * @return
     */
    List<UserDept> listUserDepts(BigInteger userId);

    /**
     * 
     * @param deptId
     * @param modifier
     * @return
     */
    UserDept deleteUserDept(BigInteger deptId, String modifier);

}
