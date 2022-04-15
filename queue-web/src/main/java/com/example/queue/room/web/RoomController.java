package com.example.queue.room.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.room.api.RoomService;
import com.example.queue.room.api.bo.Room;
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
@RequestMapping(value = "/api/room")
public class RoomController extends BaseController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Room> list(HttpServletRequest request, HttpServletResponse response) {
        String deptId = this.getParameter(request, "deptId");
        String code = this.getParameter(request, "code");
        String name = this.getParameter(request, "name");
        String status = this.getParameter(request, "status");
        Room room = this.getParameter(request, new Room());

        int count = roomService.countRoom(deptId, code, name, status, room);

        if (count == 0) {
            return new PageResponse<>(room.getPageNo(), room.getPageSize(), 0, null);
        }

        return new PageResponse<>(room.getPageNo(), room.getPageSize(), count,
            roomService.listRooms(deptId, code, name, status, room));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Room> save(HttpServletRequest request, HttpServletResponse response) {
        Room room = this.getParameter(request, Room.class);
        return new ObjectResponse<>(
            roomService.insertRoom(room.getDeptId(), room, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Room> update(HttpServletRequest request, HttpServletResponse response) {
        Room room = this.getParameter(request, Room.class);
        return new ObjectResponse<>(
            roomService.updateRoom(room.getId(), room, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Room> remove(HttpServletRequest request, HttpServletResponse response) {
        Room room = this.getParameter(request, Room.class);
        return new ObjectResponse<>(roomService.deleteRoom(room.getId(), this.getUser().getName()));
    }

}
