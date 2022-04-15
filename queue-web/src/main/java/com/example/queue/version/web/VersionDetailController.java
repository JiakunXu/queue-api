package com.example.queue.version.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.version.api.VersionDetailService;
import com.example.queue.version.api.bo.VersionDetail;
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
@RequestMapping(value = "/api/version/detail")
public class VersionDetailController extends BaseController {

    @Autowired
    private VersionDetailService versionDetailService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<VersionDetail> list(HttpServletRequest request,
                                            HttpServletResponse response) {
        String versionId = this.getParameter(request, "versionId");
        VersionDetail versionDetail = this.getParameter(request, new VersionDetail());

        int count = versionDetailService.countVersionDetail(versionId, versionDetail);

        if (count == 0) {
            return new PageResponse<>(versionDetail.getPageNo(), versionDetail.getPageSize(), 0,
                null);
        }

        return new PageResponse<>(versionDetail.getPageNo(), versionDetail.getPageSize(), count,
            versionDetailService.listVersionDetails(versionId, versionDetail));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<VersionDetail> save(HttpServletRequest request,
                                              HttpServletResponse response) {
        VersionDetail versionDetail = this.getParameter(request, VersionDetail.class);
        return new ObjectResponse<>(
            versionDetailService.insertVersionDetail(versionDetail.getVersionId(),
                versionDetail.getVersion(), versionDetail.getStatus(), this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<VersionDetail> update(HttpServletRequest request,
                                                HttpServletResponse response) {
        VersionDetail versionDetail = this.getParameter(request, VersionDetail.class);
        return new ObjectResponse<>(versionDetailService.updateVersionDetail(versionDetail.getId(),
            versionDetail.getStatus(), this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<VersionDetail> remove(HttpServletRequest request,
                                                HttpServletResponse response) {
        VersionDetail versionDetail = this.getParameter(request, VersionDetail.class);
        return new ObjectResponse<>(versionDetailService.deleteVersionDetail(versionDetail.getId(),
            this.getUser().getName()));
    }

}
