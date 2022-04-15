package com.example.queue.queue.api;

import com.example.queue.queue.api.bo.Queue;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface QueueService {

    /**
     *
     * @param deptId
     * @param queue
     * @return
     */
    int countQueue(String deptId, Queue queue);

    /**
     * 
     * @param deptId
     * @param queue
     * @return
     */
    List<Queue> listQueues(String deptId, Queue queue);

    /**
     *
     * @param deptId
     * @return
     */
    Queue getQueue(BigInteger deptId);

    /**
     *
     * @param deptId
     * @param queue
     * @param creator
     * @return
     */
    Queue insertQueue(BigInteger deptId, Queue queue, String creator);

    /**
     * 
     * @param id
     * @param queue
     * @param modifier
     * @return
     */
    Queue updateQueue(BigInteger id, Queue queue, String modifier);

}
