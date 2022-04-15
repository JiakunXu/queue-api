package com.example.queue.room.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.room.api.RoomNurseService;
import com.example.queue.room.api.bo.RoomNurse;
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
@RequestMapping(value = "/api/room/nurse")
public class RoomNurseController extends BaseController {

    @Autowired
    private RoomNurseService roomNurseService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<RoomNurse> list(HttpServletRequest request, HttpServletResponse response) {
        String roomId = this.getParameter(request, "roomId");
        RoomNurse room = this.getParameter(request, new RoomNurse());

        int count = roomNurseService.countRoomNurse(roomId, room);

        if (count == 0) {
            return new PageResponse<>(room.getPageNo(), room.getPageSize(), 0, null);
        }

        return new PageResponse<>(room.getPageNo(), room.getPageSize(), count,
            roomNurseService.listRoomNurses(roomId, room));
    }

}
