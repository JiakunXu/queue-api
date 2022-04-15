package com.example.queue.queue.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.queue.api.QueueTemplateService;
import com.example.queue.queue.api.bo.QueueTemplate;
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
@RequestMapping(value = "/api/queue/template")
public class QueueTemplateController extends BaseController {

    @Autowired
    private QueueTemplateService queueTemplateService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<QueueTemplate> save(HttpServletRequest request,
                                              HttpServletResponse response) {
        QueueTemplate queueTemplate = this.getParameter(request, QueueTemplate.class);
        return new ObjectResponse<>(queueTemplateService.insertQueueTemplate(
            queueTemplate.getQueueId(), queueTemplate.getTemplateId(), this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<QueueTemplate> remove(HttpServletRequest request,
                                                HttpServletResponse response) {
        QueueTemplate queueTemplate = this.getParameter(request, QueueTemplate.class);
        return new ObjectResponse<>(queueTemplateService
            .deleteQueueTemplate(queueTemplate.getQueueId(), null, this.getUser().getName()));
    }

}
