package com.example.queue.config.web;

import com.example.queue.config.api.StyleService;
import com.example.queue.config.api.bo.Style;
import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
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
@RequestMapping(value = "/api/config/style")
public class StyleController extends BaseController {

    @Autowired
    private StyleService styleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Style> list(HttpServletRequest request, HttpServletResponse response) {
        String code = this.getParameter(request, "code");
        String name = this.getParameter(request, "name");
        Style style = this.getParameter(request, new Style());

        int count = styleService.countStyle(code, name, style);

        if (count == 0) {
            return new PageResponse<>(style.getPageNo(), style.getPageSize(), 0, null);
        }

        return new PageResponse<>(style.getPageNo(), style.getPageSize(), count,
            styleService.listStyles(code, name, style));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Style> save(HttpServletRequest request, HttpServletResponse response) {
        Style style = this.getParameter(request, Style.class);
        return new ObjectResponse<>(styleService.insertStyle(style, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Style> update(HttpServletRequest request, HttpServletResponse response) {
        Style style = this.getParameter(request, Style.class);
        return new ObjectResponse<>(
            styleService.updateStyle(style.getId(), style, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Style> remove(HttpServletRequest request, HttpServletResponse response) {
        Style style = this.getParameter(request, Style.class);
        return new ObjectResponse<>(
            styleService.deleteStyle(style.getId(), this.getUser().getName()));
    }

}
