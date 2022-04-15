package com.example.queue.queue.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.queue.api.QueueDetailService;
import com.example.queue.queue.api.bo.QueueDetail;
import com.example.queue.queue.dao.dataobject.QueueDetailDO;
import com.example.queue.queue.dao.mapper.QueueDetailMapper;
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
public class QueueDetailServiceImpl implements QueueDetailService {

    private static final Logger logger = LoggerFactory.getLogger(QueueDetailServiceImpl.class);

    @Autowired
    private QueueDetailMapper   queueDetailMapper;

    @Override
    public int countQueueDetail(String queueId, String status, QueueDetail queueDetail) {
        if (StringUtils.isBlank(queueId) || queueDetail == null) {
            return 0;
        }

        queueDetail.setQueueId(new BigInteger(queueId));
        queueDetail.setStatus(status);

        return count(BeanUtil.copy(queueDetail, QueueDetailDO.class));
    }

    @Override
    public List<QueueDetail> listQueueDetails(String queueId, String status,
                                              QueueDetail queueDetail) {
        if (StringUtils.isBlank(queueId) || queueDetail == null) {
            return null;
        }

        queueDetail.setQueueId(new BigInteger(queueId));
        queueDetail.setStatus(status);

        return BeanUtil.copy(list(BeanUtil.copy(queueDetail, QueueDetailDO.class)),
            QueueDetail.class);
    }

    @Override
    public QueueDetail insertQueueDetail(BigInteger queueId, QueueDetail queueDetail,
                                         String creator) {
        if (queueId == null || queueDetail == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        queueDetail.setQueueId(queueId);

        QueueDetailDO queueDetailDO = BeanUtil.copy(queueDetail, QueueDetailDO.class);
        queueDetailDO.setCreator(creator);

        try {
            queueDetailMapper.insert(queueDetailDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(queueDetailDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        queueDetail.setId(queueDetailDO.getId());

        return queueDetail;
    }

    @Override
    public QueueDetail updateQueueDetail(BigInteger id, String status, String modifier) {
        if (id == null || StringUtils.isBlank(status) || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        QueueDetailDO queueDetailDO = new QueueDetailDO();
        queueDetailDO.setId(id);
        queueDetailDO.setStatus(status);
        queueDetailDO.setModifier(modifier);

        try {
            if (queueDetailMapper.update(queueDetailDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(queueDetailDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(queueDetailDO, QueueDetail.class);
    }

    private int count(QueueDetailDO queueDetailDO) {
        try {
            return queueDetailMapper.count(queueDetailDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(queueDetailDO), e);
        }

        return 0;
    }

    private List<QueueDetailDO> list(QueueDetailDO queueDetailDO) {
        try {
            return queueDetailMapper.list(queueDetailDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(queueDetailDO), e);
        }

        return null;
    }

}
