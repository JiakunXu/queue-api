package com.example.queue.rule.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ListResponse;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.rule.api.RuleService;
import com.example.queue.rule.api.bo.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JiakunXu
 */
@RestController
@RequestMapping(value = "/api/rule")
public class RuleController extends BaseController {

    @Autowired
    private RuleService ruleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ListResponse<Rule> list(HttpServletRequest request, HttpServletResponse response) {
        String code = this.getParameter(request, "code");
        return new ListResponse<>(ruleService.listRules(code));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Rule> save(HttpServletRequest request, HttpServletResponse response) {
        Rule rule = this.getParameter(request, Rule.class);
        return new ObjectResponse<>(ruleService.insertRule(rule, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Rule> update(HttpServletRequest request, HttpServletResponse response) {
        Rule rule = this.getParameter(request, Rule.class);
        return new ObjectResponse<>(
            ruleService.updateRule(rule.getId(), rule, this.getUser().getName()));
    }

}
