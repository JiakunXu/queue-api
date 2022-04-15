package com.example.queue.queue.api;

import com.example.queue.queue.api.bo.QueueDetail;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface QueueDetailService {

    /**
     *
     * @param queueId
     * @param status
     * @param queueDetail
     * @return
     */
    int countQueueDetail(String queueId, String status, QueueDetail queueDetail);

    /**
     * 
     * @param queueId
     * @param status
     * @param queueDetail
     * @return
     */
    List<QueueDetail> listQueueDetails(String queueId, String status, QueueDetail queueDetail);

    /**
     * 
     * @param queueId
     * @param queueDetail
     * @param creator
     * @return
     */
    QueueDetail insertQueueDetail(BigInteger queueId, QueueDetail queueDetail, String creator);

    /**
     * 
     * @param id
     * @param status
     * @param modifier
     * @return
     */
    QueueDetail updateQueueDetail(BigInteger id, String status, String modifier);

}
