package com.example.queue.queue.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.queue.api.QueueService;
import com.example.queue.queue.api.bo.Queue;
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
@RequestMapping(value = "/api/queue")
public class QueueController extends BaseController {

    @Autowired
    private QueueService queueService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Queue> list(HttpServletRequest request, HttpServletResponse response) {
        String deptId = this.getParameter(request, "deptId");
        Queue queue = this.getParameter(request, new Queue());

        int count = queueService.countQueue(deptId, queue);

        if (count == 0) {
            return new PageResponse<>(queue.getPageNo(), queue.getPageSize(), 0, null);
        }

        return new PageResponse<>(queue.getPageNo(), queue.getPageSize(), count,
            queueService.listQueues(deptId, queue));
    }

}
