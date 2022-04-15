package com.example.queue.client.web;

import com.example.queue.client.api.ClientService;
import com.example.queue.client.api.bo.Client;
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
@RequestMapping(value = "/api/client")
public class ClientController extends BaseController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Client> list(HttpServletRequest request, HttpServletResponse response) {
        String code = this.getParameter(request, "code");
        String name = this.getParameter(request, "name");
        String scene = this.getParameter(request, "scene");
        String sceneId = this.getParameter(request, "sceneId");
        Client client = this.getParameter(request, new Client());

        int count = clientService.countClient(code, name, scene, sceneId, client);

        if (count == 0) {
            return new PageResponse<>(client.getPageNo(), client.getPageSize(), 0, null);
        }

        return new PageResponse<>(client.getPageNo(), client.getPageSize(), count,
            clientService.listClients(code, name, scene, sceneId, client));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Client> save(HttpServletRequest request, HttpServletResponse response) {
        Client client = this.getParameter(request, Client.class);
        return new ObjectResponse<>(clientService.insertClient(client, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Client> update(HttpServletRequest request, HttpServletResponse response) {
        Client client = this.getParameter(request, Client.class);
        return new ObjectResponse<>(
            clientService.updateClient(client.getId(), client, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Client> remove(HttpServletRequest request, HttpServletResponse response) {
        Client client = this.getParameter(request, Client.class);
        return new ObjectResponse<>(
            clientService.deleteClient(client.getId(), this.getUser().getName()));
    }

}
