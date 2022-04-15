package com.example.queue.queue.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ListResponse;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.queue.api.QueueRuleService;
import com.example.queue.queue.api.bo.QueueRule;
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
@RequestMapping(value = "/api/queue/rule")
public class QueueRuleController extends BaseController {

    @Autowired
    private QueueRuleService queueRuleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ListResponse<QueueRule> list(HttpServletRequest request, HttpServletResponse response) {
        String queueId = this.getParameter(request, "queueId");
        return new ListResponse<>(queueRuleService.listQueueRules(queueId));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<QueueRule> save(HttpServletRequest request,
                                          HttpServletResponse response) {
        QueueRule queueRule = this.getParameter(request, QueueRule.class);
        return new ObjectResponse<>(queueRuleService.insertQueueRule(queueRule.getQueueId(),
            queueRule.getRuleId(), queueRule.getValue(), this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<QueueRule> update(HttpServletRequest request,
                                            HttpServletResponse response) {
        QueueRule queueRule = this.getParameter(request, QueueRule.class);
        return new ObjectResponse<>(queueRuleService.updateQueueRule(queueRule.getId(),
            queueRule.getValue(), this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<QueueRule> remove(HttpServletRequest request,
                                            HttpServletResponse response) {
        QueueRule queueRule = this.getParameter(request, QueueRule.class);
        return new ObjectResponse<>(
            queueRuleService.deleteQueueRule(queueRule.getId(), this.getUser().getName()));
    }

}
