package com.example.queue.user.api;

import com.example.queue.user.api.bo.User;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
public interface UserService {

    /**
     * 
     * @param id
     * @return
     */
    User getUser(BigInteger id);

    /**
     * 
     * @param passport
     * @param password
     * @return
     */
    User getUser(String passport, String password);

    /**
     *
     * @param user
     * @param creator
     * @return
     */
    User insertUser(User user, String creator);

    /**
     * 
     * @param id
     * @param user
     * @param modifier
     * @return
     */
    User updateUser(BigInteger id, User user, String modifier);

}
