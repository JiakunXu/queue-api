package com.example.queue.media.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.media.api.MediaService;
import com.example.queue.media.api.bo.Media;
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
@RequestMapping(value = "/api/media")
public class MediaController extends BaseController {

    @Autowired
    private MediaService mediaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Media> list(HttpServletRequest request, HttpServletResponse response) {
        String code = this.getParameter(request, "code");
        String name = this.getParameter(request, "name");
        Media media = this.getParameter(request, new Media());

        int count = mediaService.countMedia(code, name, media);

        if (count == 0) {
            return new PageResponse<>(media.getPageNo(), media.getPageSize(), 0, null);
        }

        return new PageResponse<>(media.getPageNo(), media.getPageSize(), count,
            mediaService.listMedias(code, name, media));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Media> save(HttpServletRequest request, HttpServletResponse response) {
        Media media = this.getParameter(request, Media.class);
        return new ObjectResponse<>(mediaService.insertMedia(media, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Media> update(HttpServletRequest request, HttpServletResponse response) {
        Media media = this.getParameter(request, Media.class);
        return new ObjectResponse<>(
            mediaService.updateMedia(media.getId(), media, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Media> remove(HttpServletRequest request, HttpServletResponse response) {
        Media media = this.getParameter(request, Media.class);
        return new ObjectResponse<>(
            mediaService.deleteMedia(media.getId(), this.getUser().getName()));
    }

}
