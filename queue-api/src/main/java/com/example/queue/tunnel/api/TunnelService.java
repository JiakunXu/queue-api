package com.example.queue.tunnel.api;

import com.example.queue.tunnel.api.bo.Tunnel;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface TunnelService {

    /**
     * 
     * @param clientId
     * @return
     */
    int countTunnel(BigInteger clientId);

    /**
     * 
     * @param clientId
     * @return
     */
    List<Tunnel> listTunnels(BigInteger clientId);

    /**
     *
     * @param clientId
     * @param creator
     * @return
     */
    Tunnel insertTunnel(BigInteger clientId, String creator);

    /**
     *
     * @param tunnelId
     * @param modifier
     * @return
     */
    Tunnel deleteTunnel(String tunnelId, String modifier);

}
