package com.example.queue.client.api;

import com.example.queue.client.api.bo.Client;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface ClientService {

    /**
     * 未使用.
     */
    String STATUS_NOT_USED = "not_used";

    /**
     * 使用中.
     */
    String STATUS_USED     = "used";

    /**
     * 
     * @param code
     * @param name
     * @param scene
     * @param sceneId
     * @param client
     * @return
     */
    int countClient(String code, String name, String scene, String sceneId, Client client);

    /**
     * 
     * @param code
     * @param name
     * @param scene
     * @param sceneId
     * @param client
     * @return
     */
    List<Client> listClients(String code, String name, String scene, String sceneId, Client client);

    /**
     * 
     * @param ip
     * @return
     */
    Client getClient(String ip);

    /**
     *
     * @param client
     * @param creator
     * @return
     */
    Client insertClient(Client client, String creator);

    /**
     *
     * @param id
     * @param client
     * @param modifier
     * @return
     */
    Client updateClient(BigInteger id, Client client, String modifier);

    /**
     *
     * @param id
     * @param modifier
     * @return
     */
    Client deleteClient(BigInteger id, String modifier);

}
