package com.example.queue.tunnel.service;

import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.tunnel.api.TunnelService;
import com.example.queue.tunnel.api.bo.Tunnel;
import com.example.queue.tunnel.dao.dataobject.TunnelDO;
import com.example.queue.tunnel.dao.mapper.TunnelMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

/**
 * @author JiakunXu
 */
@Service
public class TunnelServiceImpl implements TunnelService {

    private static final Logger logger = LoggerFactory.getLogger(TunnelServiceImpl.class);

    @Autowired
    private TunnelMapper        tunnelMapper;

    @Override
    public int countTunnel(BigInteger clientId) {
        if (clientId == null) {
            return 0;
        }

        TunnelDO tunnelDO = new TunnelDO();
        tunnelDO.setClientId(clientId);

        return count(tunnelDO);
    }

    @Override
    public List<Tunnel> listTunnels(BigInteger clientId) {
        if (clientId == null) {
            return null;
        }

        TunnelDO tunnelDO = new TunnelDO();
        tunnelDO.setClientId(clientId);

        return BeanUtil.copy(list(tunnelDO), Tunnel.class);
    }

    @Override
    public Tunnel insertTunnel(BigInteger clientId, String creator) {
        if (clientId == null) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        TunnelDO tunnelDO = new TunnelDO();
        tunnelDO.setClientId(clientId);
        tunnelDO.setTunnelId(UUID.randomUUID().toString());
        tunnelDO.setCreator(creator);

        try {
            tunnelMapper.insert(tunnelDO);
        } catch (Exception e) {
            logger.error(tunnelDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        return BeanUtil.copy(tunnelDO, Tunnel.class);
    }

    @Override
    public Tunnel deleteTunnel(String tunnelId, String modifier) {
        if (StringUtils.isBlank(tunnelId)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        TunnelDO tunnelDO = new TunnelDO();
        tunnelDO.setTunnelId(tunnelId);
        tunnelDO.setModifier(modifier);

        try {
            if (tunnelMapper.delete(tunnelDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(tunnelDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(tunnelDO, Tunnel.class);
    }

    private int count(TunnelDO tunnelDO) {
        try {
            return tunnelMapper.count(tunnelDO);
        } catch (Exception e) {
            logger.error(tunnelDO.toString(), e);
        }

        return 0;
    }

    private List<TunnelDO> list(TunnelDO tunnelDO) {
        try {
            return tunnelMapper.list(tunnelDO);
        } catch (Exception e) {
            logger.error(tunnelDO.toString(), e);
        }

        return null;
    }

}
