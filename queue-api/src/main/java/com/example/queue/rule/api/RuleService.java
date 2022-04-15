package com.example.queue.rule.api;

import com.example.queue.rule.api.bo.Rule;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface RuleService {

    /**
     * 
     * @param code
     * @return
     */
    List<Rule> listRules(String code);

    /**
     * 
     * @param id
     * @return
     */
    Rule getRule(BigInteger id);

    /**
     *
     * @param rule
     * @param creator
     * @return
     */
    Rule insertRule(Rule rule, String creator);

    /**
     *
     * @param id
     * @param rule
     * @param modifier
     * @return
     */
    Rule updateRule(BigInteger id, Rule rule, String modifier);

}
