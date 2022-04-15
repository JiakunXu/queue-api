package com.example.queue.queue.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.queue.api.QueueRuleService;
import com.example.queue.queue.api.bo.QueueRule;
import com.example.queue.queue.dao.dataobject.QueueRuleDO;
import com.example.queue.queue.dao.mapper.QueueRuleMapper;
import com.example.queue.rule.api.RuleService;
import com.example.queue.rule.api.bo.Rule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
@Service
public class QueueRuleServiceImpl implements QueueRuleService {

    private static final Logger logger = LoggerFactory.getLogger(QueueRuleServiceImpl.class);

    @Autowired
    private RuleService         ruleService;

    @Autowired
    private QueueRuleMapper     queueRuleMapper;

    @Override
    public List<QueueRule> listQueueRules(String queueId) {
        if (StringUtils.isBlank(queueId)) {
            return null;
        }

        QueueRuleDO queueRuleDO = new QueueRuleDO();
        queueRuleDO.setQueueId(new BigInteger(queueId));

        List<QueueRule> list = BeanUtil.copy(list(queueRuleDO), QueueRule.class);

        if (list == null || list.size() == 0) {
            return null;
        }

        for (QueueRule queueRule : list) {
            queueRule.setRule(ruleService.getRule(queueRule.getRuleId()));
        }

        return list;
    }

    @Override
    public QueueRule insertQueueRule(BigInteger queueId, BigInteger ruleId, Integer value,
                                     String creator) {
        if (queueId == null || ruleId == null || value == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        Rule rule = ruleService.getRule(ruleId);

        if (rule == null) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        }

        QueueRuleDO queueRuleDO = new QueueRuleDO();
        queueRuleDO.setQueueId(queueId);
        queueRuleDO.setRuleId(ruleId);
        queueRuleDO.setRuleCode(rule.getCode());
        queueRuleDO.setValue(value);
        queueRuleDO.setCreator(creator);

        try {
            queueRuleMapper.insert(queueRuleDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(queueRuleDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        return BeanUtil.copy(queueRuleDO, QueueRule.class);
    }

    @Override
    public QueueRule updateQueueRule(BigInteger id, Integer value, String modifier) {
        if (id == null || value == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        QueueRuleDO queueRuleDO = new QueueRuleDO();
        queueRuleDO.setId(id);
        queueRuleDO.setValue(value);
        queueRuleDO.setModifier(modifier);

        try {
            if (queueRuleMapper.update(queueRuleDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(queueRuleDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(queueRuleDO, QueueRule.class);
    }

    @Override
    public QueueRule deleteQueueRule(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        QueueRuleDO queueRuleDO = new QueueRuleDO();
        queueRuleDO.setId(id);
        queueRuleDO.setModifier(modifier);

        try {
            queueRuleMapper.delete(queueRuleDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(queueRuleDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(queueRuleDO, QueueRule.class);
    }

    private List<QueueRuleDO> list(QueueRuleDO queueRuleDO) {
        try {
            return queueRuleMapper.list(queueRuleDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(queueRuleDO), e);
        }

        return null;
    }

}
