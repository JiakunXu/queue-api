package com.example.queue.queue.service;

import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.queue.api.QueueTemplateService;
import com.example.queue.queue.api.bo.QueueTemplate;
import com.example.queue.queue.dao.dataobject.QueueTemplateDO;
import com.example.queue.queue.dao.mapper.QueueTemplateMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@Service
public class QueueTemplateServiceImpl implements QueueTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(QueueTemplateServiceImpl.class);

    @Autowired
    private QueueTemplateMapper queueTemplateMapper;

    @Override
    public QueueTemplate getQueueTemplate(BigInteger queueId) {
        if (queueId == null) {
            return null;
        }

        QueueTemplateDO queueTemplateDO = new QueueTemplateDO();
        queueTemplateDO.setQueueId(queueId);

        return BeanUtil.copy(get(queueTemplateDO), QueueTemplate.class);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public QueueTemplate insertQueueTemplate(BigInteger queueId, BigInteger templateId,
                                             String creator) {
        if (queueId == null || templateId == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        deleteQueueTemplate(queueId, null, creator);

        QueueTemplateDO queueTemplateDO = new QueueTemplateDO();
        queueTemplateDO.setQueueId(queueId);
        queueTemplateDO.setTemplateId(templateId);
        queueTemplateDO.setCreator(creator);

        try {
            queueTemplateMapper.insert(queueTemplateDO);
        } catch (Exception e) {
            logger.error(queueTemplateDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        return BeanUtil.copy(queueTemplateDO, QueueTemplate.class);
    }

    @Override
    public QueueTemplate deleteQueueTemplate(BigInteger queueId, BigInteger templateId,
                                             String modifier) {
        if ((queueId == null && templateId == null) || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        QueueTemplateDO queueTemplateDO = new QueueTemplateDO();
        queueTemplateDO.setQueueId(queueId);
        queueTemplateDO.setTemplateId(templateId);
        queueTemplateDO.setModifier(modifier);

        try {
            queueTemplateMapper.delete(queueTemplateDO);
        } catch (Exception e) {
            logger.error(queueTemplateDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(queueTemplateDO, QueueTemplate.class);
    }

    private QueueTemplateDO get(QueueTemplateDO queueTemplateDO) {
        try {
            return queueTemplateMapper.get(queueTemplateDO);
        } catch (Exception e) {
            logger.error(queueTemplateDO.toString(), e);
        }

        return null;
    }

}
