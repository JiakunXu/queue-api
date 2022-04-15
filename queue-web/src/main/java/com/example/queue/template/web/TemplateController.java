package com.example.queue.template.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.template.api.TemplateService;
import com.example.queue.template.api.bo.Template;
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
@RequestMapping(value = "/api/template")
public class TemplateController extends BaseController {

    @Autowired
    private TemplateService templateService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Template> list(HttpServletRequest request, HttpServletResponse response) {
        String templateId = this.getParameter(request, "templateId");
        String name = this.getParameter(request, "name");
        Template template = this.getParameter(request, new Template());

        int count = templateService.countTemplate(templateId, name, template);

        if (count == 0) {
            return new PageResponse<>(template.getPageNo(), template.getPageSize(), 0, null);
        }

        return new PageResponse<>(template.getPageNo(), template.getPageSize(), count,
            templateService.listTemplates(templateId, name, template));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Template> save(HttpServletRequest request, HttpServletResponse response) {
        Template template = this.getParameter(request, Template.class);
        return new ObjectResponse<>(templateService.insertTemplate(template.getTemplateId(),
            template, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Template> update(HttpServletRequest request,
                                           HttpServletResponse response) {
        Template template = this.getParameter(request, Template.class);
        return new ObjectResponse<>(
            templateService.updateTemplate(template.getId(), template, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Template> remove(HttpServletRequest request,
                                           HttpServletResponse response) {
        Template template = this.getParameter(request, Template.class);
        return new ObjectResponse<>(
            templateService.deleteTemplate(template.getId(), this.getUser().getName()));
    }

}
