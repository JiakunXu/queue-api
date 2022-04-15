package com.example.queue.rule.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.rule.api.RuleService;
import com.example.queue.rule.api.bo.Rule;
import com.example.queue.rule.dao.dataobject.RuleDO;
import com.example.queue.rule.dao.mapper.RuleMapper;
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
public class RuleServiceImpl implements RuleService {

    private static final Logger logger = LoggerFactory.getLogger(RuleServiceImpl.class);

    @Autowired
    private RuleMapper          ruleMapper;

    @Override
    public List<Rule> listRules(String code) {
        RuleDO ruleDO = new RuleDO();
        ruleDO.setCode(code);

        return BeanUtil.copy(list(ruleDO), Rule.class);
    }

    @Override
    public Rule getRule(BigInteger id) {
        if (id == null) {
            return null;
        }

        RuleDO ruleDO = new RuleDO();
        ruleDO.setId(id);

        return BeanUtil.copy(get(ruleDO), Rule.class);
    }

    @Override
    public Rule insertRule(Rule rule, String creator) {
        if (rule == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        RuleDO ruleDO = BeanUtil.copy(rule, RuleDO.class);
        ruleDO.setCreator(creator);

        try {
            ruleMapper.insert(ruleDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(ruleDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        rule.setId(ruleDO.getId());

        return rule;
    }

    @Override
    public Rule updateRule(BigInteger id, Rule rule, String modifier) {
        if (id == null || rule == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        rule.setId(id);

        RuleDO ruleDO = BeanUtil.copy(rule, RuleDO.class);
        ruleDO.setModifier(modifier);

        try {
            if (ruleMapper.update(ruleDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(ruleDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return rule;
    }

    private List<RuleDO> list(RuleDO ruleDO) {
        try {
            return ruleMapper.list(ruleDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(ruleDO), e);
        }

        return null;
    }

    private RuleDO get(RuleDO ruleDO) {
        try {
            return ruleMapper.get(ruleDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(ruleDO), e);
        }

        return null;
    }

}
