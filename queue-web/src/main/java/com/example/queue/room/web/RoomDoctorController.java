package com.example.queue.room.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.room.api.RoomDoctorService;
import com.example.queue.room.api.bo.RoomDoctor;
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
@RequestMapping(value = "/api/room/doctor")
public class RoomDoctorController extends BaseController {

    @Autowired
    private RoomDoctorService roomDoctorService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<RoomDoctor> list(HttpServletRequest request, HttpServletResponse response) {
        String roomId = this.getParameter(request, "roomId");
        RoomDoctor room = this.getParameter(request, new RoomDoctor());

        int count = roomDoctorService.countRoomDoctor(roomId, room);

        if (count == 0) {
            return new PageResponse<>(room.getPageNo(), room.getPageSize(), 0, null);
        }

        return new PageResponse<>(room.getPageNo(), room.getPageSize(), count,
            roomDoctorService.listRoomDoctors(roomId, room));
    }

}
