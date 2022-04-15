package com.example.queue.queue.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.queue.api.QueueDetailService;
import com.example.queue.queue.api.bo.QueueDetail;
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
@RequestMapping(value = "/api/queue/detail")
public class QueueDetailController extends BaseController {

    @Autowired
    private QueueDetailService queueDetailService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<QueueDetail> list(HttpServletRequest request,
                                          HttpServletResponse response) {
        String queueId = this.getParameter(request, "queueId");
        String status = this.getParameter(request, "status");
        QueueDetail queueDetail = this.getParameter(request, new QueueDetail());

        int count = queueDetailService.countQueueDetail(queueId, status, queueDetail);

        if (count == 0) {
            return new PageResponse<>(queueDetail.getPageNo(), queueDetail.getPageSize(), 0, null);
        }

        return new PageResponse<>(queueDetail.getPageNo(), queueDetail.getPageSize(), count,
            queueDetailService.listQueueDetails(queueId, status, queueDetail));
    }

}
