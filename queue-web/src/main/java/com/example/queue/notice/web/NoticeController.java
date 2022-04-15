package com.example.queue.notice.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.notice.api.NoticeService;
import com.example.queue.notice.api.bo.Notice;
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
@RequestMapping(value = "/api/notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Notice> list(HttpServletRequest request, HttpServletResponse response) {
        String deptId = this.getParameter(request, "deptId");
        Notice notice = this.getParameter(request, new Notice());

        int count = noticeService.countNotice(deptId, notice);

        if (count == 0) {
            return new PageResponse<>(notice.getPageNo(), notice.getPageSize(), 0, null);
        }

        return new PageResponse<>(notice.getPageNo(), notice.getPageSize(), count,
            noticeService.listNotices(deptId, notice));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Notice> save(HttpServletRequest request, HttpServletResponse response) {
        Notice notice = this.getParameter(request, Notice.class);
        return new ObjectResponse<>(
            noticeService.insertNotice(notice.getDeptId(), notice, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Notice> update(HttpServletRequest request, HttpServletResponse response) {
        Notice notice = this.getParameter(request, Notice.class);
        return new ObjectResponse<>(
            noticeService.updateNotice(notice.getId(), notice, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Notice> remove(HttpServletRequest request, HttpServletResponse response) {
        Notice notice = this.getParameter(request, Notice.class);
        return new ObjectResponse<>(
            noticeService.deleteNotice(notice.getId(), this.getUser().getName()));
    }

}
