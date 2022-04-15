package com.example.queue.queue.api;

import com.example.queue.queue.api.bo.QueueTemplate;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
public interface QueueTemplateService {

    /**
     *
     * @param queueId
     * @return
     */
    QueueTemplate getQueueTemplate(BigInteger queueId);

    /**
     *
     * @param queueId
     * @param templateId
     * @param creator
     * @return
     */
    QueueTemplate insertQueueTemplate(BigInteger queueId, BigInteger templateId, String creator);

    /**
     * 
     * @param queueId
     * @param templateId
     * @param modifier
     * @return
     */
    QueueTemplate deleteQueueTemplate(BigInteger queueId, BigInteger templateId, String modifier);

}
