package com.example.queue.queue.api;

import com.example.queue.queue.api.bo.QueueRule;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface QueueRuleService {

    /**
     * 
     * @param queueId
     * @return
     */
    List<QueueRule> listQueueRules(String queueId);

    /**
     * 
     * @param queueId
     * @param ruleId
     * @param value
     * @param creator
     * @return
     */
    QueueRule insertQueueRule(BigInteger queueId, BigInteger ruleId, Integer value, String creator);

    /**
     * 
     * @param id
     * @param value
     * @param modifier
     * @return
     */
    QueueRule updateQueueRule(BigInteger id, Integer value, String modifier);

    /**
     * 
     * @param id
     * @param modifier
     * @return
     */
    QueueRule deleteQueueRule(BigInteger id, String modifier);

}
