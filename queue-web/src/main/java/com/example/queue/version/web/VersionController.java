package com.example.queue.version.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.version.api.VersionService;
import com.example.queue.version.api.bo.Version;
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
@RequestMapping(value = "/api/version")
public class VersionController extends BaseController {

    @Autowired
    private VersionService versionService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Version> list(HttpServletRequest request, HttpServletResponse response) {
        String appId = this.getParameter(request, "appId");
        Version version = this.getParameter(request, new Version());

        int count = versionService.countVersion(appId, version);

        if (count == 0) {
            return new PageResponse<>(version.getPageNo(), version.getPageSize(), 0, null);
        }

        return new PageResponse<>(version.getPageNo(), version.getPageSize(), count,
            versionService.listVersions(appId, version));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Version> save(HttpServletRequest request, HttpServletResponse response) {
        Version versionDetail = this.getParameter(request, Version.class);
        return new ObjectResponse<>(versionService.insertVersion(versionDetail.getAppId(),
            versionDetail.getClient(), versionDetail.getUrl(), this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Version> update(HttpServletRequest request,
                                          HttpServletResponse response) {
        Version versionDetail = this.getParameter(request, Version.class);
        return new ObjectResponse<>(versionService.updateVersion(versionDetail.getId(),
            versionDetail.getUrl(), this.getUser().getName()));
    }

}
