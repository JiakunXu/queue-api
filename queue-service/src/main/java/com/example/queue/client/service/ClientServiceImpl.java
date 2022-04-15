package com.example.queue.client.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.client.api.ClientService;
import com.example.queue.client.api.bo.Client;
import com.example.queue.client.dao.dataobject.ClientDO;
import com.example.queue.client.dao.mapper.ClientMapper;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientMapper        clientMapper;

    @Override
    public int countClient(String code, String name, String scene, String sceneId, Client client) {
        if (client == null) {
            return 0;
        }

        client.setCode(code);
        client.setName(name);
        client.setScene(scene);

        if (StringUtils.isNotBlank(sceneId)) {
            client.setSceneId(new BigInteger(sceneId));
        }

        return count(BeanUtil.copy(client, ClientDO.class));
    }

    @Override
    public List<Client> listClients(String code, String name, String scene, String sceneId,
                                    Client client) {
        if (client == null) {
            return null;
        }

        client.setCode(code);
        client.setName(name);
        client.setScene(scene);

        if (StringUtils.isNotBlank(sceneId)) {
            client.setSceneId(new BigInteger(sceneId));
        }

        return BeanUtil.copy(list(BeanUtil.copy(client, ClientDO.class)), Client.class);
    }

    @Override
    public Client getClient(String ip) {
        if (StringUtils.isBlank(ip)) {
            return null;
        }

        ClientDO clientDO = new ClientDO();
        clientDO.setIp(ip);

        return BeanUtil.copy(get(clientDO), Client.class);
    }

    @Override
    public Client insertClient(Client client, String creator) {
        if (client == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        ClientDO clientDO = BeanUtil.copy(client, ClientDO.class);
        clientDO.setCreator(creator);

        try {
            clientMapper.insert(clientDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(clientDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        client.setId(clientDO.getId());

        return client;
    }

    @Override
    public Client updateClient(BigInteger id, Client client, String modifier) {
        if (id == null || client == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        client.setId(id);

        ClientDO clientDO = BeanUtil.copy(client, ClientDO.class);
        clientDO.setModifier(modifier);

        try {
            if (clientMapper.update(clientDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(clientDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return client;
    }

    @Override
    public Client deleteClient(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        ClientDO clientDO = new ClientDO();
        clientDO.setId(id);
        clientDO.setModifier(modifier);

        try {
            clientMapper.delete(clientDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(clientDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(clientDO, Client.class);
    }

    private int count(ClientDO clientDO) {
        try {
            return clientMapper.count(clientDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(clientDO), e);
        }

        return 0;
    }

    private List<ClientDO> list(ClientDO clientDO) {
        try {
            return clientMapper.list(clientDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(clientDO), e);
        }

        return null;
    }

    private ClientDO get(ClientDO clientDO) {
        try {
            return clientMapper.get(clientDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(clientDO), e);
        }

        return null;
    }

}
