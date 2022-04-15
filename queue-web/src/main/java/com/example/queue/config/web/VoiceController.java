package com.example.queue.config.web;

import com.example.queue.config.api.VoiceService;
import com.example.queue.config.api.bo.Voice;
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
@RequestMapping(value = "/api/config/voice")
public class VoiceController extends BaseController {

    @Autowired
    private VoiceService voiceService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Voice> list(HttpServletRequest request, HttpServletResponse response) {
        String code = this.getParameter(request, "code");
        String name = this.getParameter(request, "name");
        Voice voice = this.getParameter(request, new Voice());

        int count = voiceService.countVoice(code, name, voice);

        if (count == 0) {
            return new PageResponse<>(voice.getPageNo(), voice.getPageSize(), 0, null);
        }

        return new PageResponse<>(voice.getPageNo(), voice.getPageSize(), count,
            voiceService.listVoices(code, name, voice));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Voice> save(HttpServletRequest request, HttpServletResponse response) {
        Voice voice = this.getParameter(request, Voice.class);
        return new ObjectResponse<>(voiceService.insertVoice(voice, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Voice> update(HttpServletRequest request, HttpServletResponse response) {
        Voice voice = this.getParameter(request, Voice.class);
        return new ObjectResponse<>(
            voiceService.updateVoice(voice.getId(), voice, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Voice> remove(HttpServletRequest request, HttpServletResponse response) {
        Voice voice = this.getParameter(request, Voice.class);
        return new ObjectResponse<>(
            voiceService.deleteVoice(voice.getId(), this.getUser().getName()));
    }

}
